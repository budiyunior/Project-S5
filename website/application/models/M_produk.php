<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_produk extends CI_Model
{
    private $_table = "tb_produk";
    // private $tabel = "v_produk";
    // private $bahan = "tb_bahan";
    private $trans = "tb_keranjang";
    public $id_produk;
    public $nama_produk;
    public $harga_satuan;
    public $gambar;
    public $keterangan;
    public $id_kategori;

    public function rules()
    {
        return [

            [
                'field' => 'nama_produk',
                'label' => 'nama_produk',
                'rules' => 'required|max_lenght[22]'
            ],

            ['max_lenght' => 'Nama Produk tidak lebih dari 22 Karakter!']

        ];
    }

    public function getAll()
    {
        return $this->db->get($this->_table)->result();
    }

    // public function v_trans()
    // {
    //     return $this->db->get($this->trans)->result();
    // }

    public function view()
    {
        return $this->db->query("SELECT * FROM tb_detail_transaksi")->result();
    }

    public function getById($id_produk)
    {
        return $this->db->get_where($this->_table, ["id_produk" => $id_produk])->row();
    }

    // public function bahan()
    // {
    //     return $this->db->get($this->bahan)->result();
    // }

    public function simpan()
    {
        $post = $this->input->post();
        if (isset($_POST['id_produk']))
            $this->nama_produk = $post["nama_produk"];
        $this->harga_satuan = $post["harga_satuan"];
        $this->gambar = $this->uploadImage();
        $this->keterangan = $post["keterangan"];
        $this->id_kategori = $post["id_kategori"];

        $this->db->insert($this->_table, $this);
    }

    private function uploadImage()
    {
        $config['upload_path'] = './assets/img/foto_produk/';
        $config['allowed_types'] = 'gif|jpg|png';
        $nama_lengkap = $_FILES['gambar']['name'];
        $config['file_name'] = $nama_lengkap;
        $config['overwrite'] = true;
        $config['max_size'] = 3024;

        $this->load->library('upload', $config);

        if ($this->upload->do_upload('gambar')) {
            return $this->upload->data("file_name");
        }

        print_r($this->upload->display_errors());
    }

    public function edit()
    {
        $post = $this->input->post();
        $this->id_produk = $post["id_produk"];
        $this->nama_produk = $post["nama_produk"];
        $this->harga_satuan = $post["harga_satuan"];
        if (!empty($_FILES["gambar"]["name"])) {
            $this->gambar = $this->uploadImage();
        } else {
            $this->gambar = $post["old_image"];
        }
        $this->keterangan = $post["keterangan"];
        $this->id_kategori = $post["id_kategori"];
        $this->db->update($this->_table, $this, array('id_produk' => $post['id_produk']));
    }

    public function hapus($id_produk)
    {
        $this->_deleteImage($id_produk);
        return $this->db->delete($this->_table, array("id_produk" => $id_produk));
    }

    private function _deleteImage($id_produk)
    {
        $produk = $this->getById($id_produk);
        if ($produk->gambar != "01.jpg") {
            $filename = explode(".", $produk->gambar)[0];
        }
    }

    public function kopibanjir($id_produk)
    {
        $id_produk = $this->db->get_where($this->trans, ["id_produk" => $id_produk])->row();
        return $id_produk;
        $kb = '1';
        $kopibanjir = "SELECT SUM(jumlah) FROM tb_detail_transaksi WHERE id_produk = $kb ";
        $result = $this->db->query($kopibanjir);
        return $result->result()->SUM;
    }

    public function kb($id_produk)
    {
        $kb = '1';
        // return $this->db->query("SELECT SUM(jumlah) FROM tb_detail_transaksi WHERE id_produk = $kb");
        $query = $this->db->query("SELECT SUM(jumlah) as berat FROM tb_detail_transaksi WHERE id_produk = $kb ");

        if ($query->num_rows() > 0) {
            return $query->row()->berat;
        }
        return false;
    }

    public function v_trans()
    {
        return $this->db->get($this->trans)->result();
    }

    public function history()
    {
        $tanggal = $this->input->get('tanggal');
        $data = $this->db->query("SELECT * FROM tb_keranjang WHERE tanggal = '$tanggal' ")->result();
        return $data;
    }
}
