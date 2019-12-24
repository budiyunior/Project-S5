<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class delete_detail_trans extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
        $this->load->model('m_logintest');
    }

    function index_post()
    {
        $nama_pelanggan = $this->input->post('nama_pelanggan');
        $no_meja = $this->input->post('no_meja');
        $id_trans = $this->input->post('id_transaksi');
        $id_produk = $this->input->post('id_produk');
        $keranjang = $this->db->query("DELETE FROM tb_detail_transaksi WHERE id_transaksi=$id_trans AND id_produk=$id_produk")->row_array();
        // $keranjang = $this->db->query("SELECT * FROM tb_keranjang where nama_pelanggan = \"$nama_pelanggan\" && no_meja =$no_meja")->result();
        // $keranjang = $this->db->query("SELECT SUM(subtotal_harga) FROM desain_cart where id_pengguna = $id_pengguna")->result();

        //$perbaikan = $this->db->get_where('perbaikan',['id_user'=>$id_user])->result();
        $this->response(array("result" => $keranjang, 200));
    }
}
