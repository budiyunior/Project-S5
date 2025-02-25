<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class produk extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
    }

    function index_get()
    {
        $data = $this->db->get('tb_produk')->result();
        $this->response(array("result" => $data, 200));
    }
    function index_post()
    {
        // SELECT * FROM `tb_produk`  ORDER by  id_kategori , nama_produk
        $nama_pelanggan = $this->input->post('nama_pelanggan');
        $no_meja = $this->input->post('no_meja');
        $id_kategori = $this->input->post('id_kategori');
        $keranjang = $this->db->query("SELECT * FROM tb_produk where id_kategori = $id_kategori")->result();
        // $keranjang = $this->db->query("SELECT * FROM tb_keranjang where nama_pelanggan = \"$nama_pelanggan\" && no_meja =$no_meja")->result();
        // $keranjang = $this->db->query("SELECT SUM(subtotal_harga) FROM desain_cart where id_pengguna = $id_pengguna")->result();

        //$perbaikan = $this->db->get_where('perbaikan',['id_user'=>$id_user])->result();
        $this->response(array("result" => $keranjang, 200));
    }
}
