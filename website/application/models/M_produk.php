<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_produk extends CI_Model
{
    private $_table = "tb_produk";
    private $tabel = "v_produk";
    public $id_produk;
    public $nama_produk;
    public $harga_satuan;
    public $gambar;
    public $keterangan;

    public function rules()
    {
        return [

            [
                'field' => 'nama_produk',
                'label' => 'nama',
                'rules' => 'required'
            ]

        ];
    }

    public function getAll()
    {
        return $this->db->get($this->tabel)->result();
    }

    public function simpan()
    {
        $post = $this->input->post();
        if (isset($_POST['id_produk'])) { }  
        $this->nama_produk = $post["nama_produk"];
        $this->harga_satuan = $post["harga_satuan"];
        $this->gambar = $this->uploadImage();
        $this->keterangan = $post["keterangan"];
        $this->id_kategori = $post["id_kategori"];

        $this->db->insert($this->_table, $this);
    }

    private function uploadImage()
    {
        $config['upload_path']          = './assets/img/foto_produk/';
        $config['allowed_types']        = 'gif|jpg|png';
        $nama_lengkap = $_FILES['gambar']['name'];
        $config['file_name']            = $nama_lengkap;
        $config['overwrite']            = true;
        $config['max_size']             = 3024;

        $this->load->library('upload', $config);

        if ($this->upload->do_upload('gambar')) {
            return $this->upload->data("file_name");
        }

        print_r($this->upload->display_errors());
    }
}
