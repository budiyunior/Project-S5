<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_historibahan extends CI_Model
{

    private $_table = "tb_historibahan";

    public $nama_bahan;
    public $jumlah;


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




    public function save()
    {
        $post = $this->input->post();

        $nama_bahan = $post["nama_bahan"];
        $jumlah = $post["jumlah"];

        $this->nama_bahan = $nama_bahan;
        $this->jumlah = $jumlah;
        date_default_timezone_set('Asia/Jakarta');
        $this->jam = date('H:i');
        // $this->tanggal = date('d-m-Y');

        $this->db->insert($this->_table, $this);
        $this->session->set_flashdata('success', 'Stok/Bahan ' . $nama_bahan . ' Berhasil Berkurang ' . $jumlah);
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


}
