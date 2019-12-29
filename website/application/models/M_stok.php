<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_stok extends CI_Model
{
    private $_table = "tb_bahan";
    public $id_bahan;
    public $nama_bahan;
    public $jumlah;
    public $satuan;
    public $keterangan;

    public function rules()
    {
        return [


            [
                'field' => 'jumlah',
                'label' => 'jumlah',
                'rules' => 'required'
            ],



        ];
    }

    public function getAll()
    {
        return $this->db->get($this->_table)->result();
    }

    public function getById($id)
    {
        return $this->db->get_where($this->_table, ["id_bahan" => $id])->row();
    }


    public function tambahbahan()
    {
        $post = $this->input->post();
        $nama_bahan = $post["nama_bahan"];
        $jumlah = $post["jumlah"];
        $satuan = $post["satuan"];

        $this->nama_bahan = $nama_bahan;
        $this->jumlah = $jumlah;
        $this->satuan = $satuan;
        $this->keterangan = $post["keterangan"];

        $this->db->insert($this->_table, $this);

        $this->session->set_flashdata('success', $nama_bahan . ' Dengan Jumlah ' . $jumlah . ' ' . $satuan . ' Berhasil Ditambahkan');
        redirect('stok/');
    }


    public function tambahbahankasir()
    {
        $post = $this->input->post();
        $nama_bahan = $post["nama_bahan"];
        $jumlah = $post["jumlah"];
        $satuan = $post["satuan"];

        $this->nama_bahan = $nama_bahan;
        $this->jumlah = $jumlah;
        $this->satuan = $satuan;
        $this->keterangan = $post["keterangan"];

        $this->db->insert($this->_table, $this);

        $this->session->set_flashdata('success', $nama_bahan . ' Dengan Jumlah ' . $jumlah . ' ' . $satuan . ' Berhasil Ditambahkan');
        redirect('Stok/v_stok');
    }



    public function tambahstok()
    {
        $post = $this->input->post();
        $id_bahan = $post["id_bahan"];
        $nama_bahan = $this->input->post('nama_bahan'); //bisa juga di tulis ------>>>> $post["bahan"];
        $jumlah = $post["jumlah"];
        $satuan = $post["satuan"];

        $this->db->set('jumlah', 'jumlah+' . $jumlah, false); //field yang ingin di update dan aksinya
        $this->db->where('id_bahan', $id_bahan); //wherenya
        $this->db->update('tb_bahan'); //nama tabelnya



        $this->session->set_flashdata('success', $jumlah . $satuan . ' Stok ' . $nama_bahan . ' Berhasil Ditambahkan');
        redirect('stok');
    }

    public function tambahstokkasir()
    {
        $post = $this->input->post();
        $id_bahan = $post["id_bahan"];
        $nama_bahan = $this->input->post('nama_bahan'); //bisa juga di tulis ------>>>> $post["bahan"];
        $jumlah = $post["jumlah"];
        $satuan = $post["satuan"];

        $this->db->set('jumlah', 'jumlah+' . $jumlah, false); //field yang ingin di update dan aksinya
        $this->db->where('id_bahan', $id_bahan); //wherenya
        $this->db->update('tb_bahan'); //nama tabelnya



        $this->session->set_flashdata('success', $jumlah . $satuan . ' Stok ' . $nama_bahan . ' Berhasil Ditambahkan');
        redirect('stok/v_stok');
    }





    public function kurangstok()
    {
        $post = $this->input->post();
        $id_bahan = $post["id_bahan"];
        $nama_bahan = $this->input->post('nama_bahan'); //bisa juga di tulis ------>>>> $post["bahan"];
        $jumlah = $post["jumlah"];

        $this->db->set('jumlah', 'jumlah-' . $jumlah, false); //field yang ingin di update dan aksinya
        $this->db->where('id_bahan', $id_bahan); //wherenya
        $this->db->update('tb_bahan'); //nama tabelnya



        $this->session->set_flashdata('success', $jumlah . ' Stok ' . $nama_bahan . ' Berhasil Ditambahkan');
        redirect('adminpegawai');
    }





    //mencoba transaksi
    // public function tambahpesan()
    // {
    //     $post = $this->input->post();
    //     $id_produk = $this->bahan = $post["id_produk"];
    //     $jumlah = $this->stok = $post["jumlah"];

    //     $count_id_produk = $this->db->query("SELECT COUNT(id_produk) FROM resep WHERE id_produk = $id_produk")->result();
    //     $count_id_ = $this->db->query("SELECT COUNT(id_produk) FROM resep WHERE id_produk = $id_produk")->result();
    //     $count_id_produk = $this->db->query("SELECT COUNT(id_produk) FROM resep WHERE id_produk = $id_produk")->result();



    //     $this->db->insert($this->trans, $select);
    // }



    public function delete($id_bahan)
    {

        return $this->db->delete($this->_table, array("id_bahan" => $id_bahan));
    }
}
