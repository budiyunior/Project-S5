<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class cek_keranjang extends REST_Controller
{
    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
        $this->load->model('m_logintest');
    }
    
    function index_post()
    {
        $id_transaksi = $this->input->post('id_transaksi');
        $id_produk = $this->input->post('id_produk');
        $where = array(
            'id_transaksi' => $id_transaksi,
            'id_produk' => $id_produk
        );
        // $cek=$this->m_login->cek_login_biasa($username,$id_produk)->num_rows();
        $cek = $this->m_logintest->cek_keranjang($id_transaksi, $id_produk);
        if ($cek) {
            $output['id_keranjang'] = $cek['id_keranjang'];
            $output['id_transaksi'] = $id_transaksi;
            $output['id_produk'] = $id_produk;
            $output['jumlah'] = $cek['jumlah'];
            $this->response($output, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
}
