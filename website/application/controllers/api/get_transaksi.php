<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class get_transaksi extends REST_Controller
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
        $id_transaksi = $this->input->post('id_transaksi');
        // $total_harga = $this->db->query("SELECT * from tb_transaksi where id_transaksi = $id_trans")->result();
        // if ($total_harga) {
        //     $output['total_harga'] = $total_harga['total_harga'];
        //     //       $output['nama_pengguna'] = $cek['nama_pengguna'];
        //     //       $output['total_harga'] = $total_harga;
        //     $this->response($output, 200);
        // }
        // $this->response(array("result" => $total_harga, 200));
        $cek = $this->m_logintest->cek_trans($id_transaksi);
        if ($cek) {
            $output['id_transaksi'] = $id_transaksi;
            $output['nama_pelanggan'] = $cek['nama_pelanggan'];
            $output['no_meja'] = $cek['no_meja'];
            $output['jam'] = $cek['jam'];
            $output['tanggal'] = $cek['tanggal'];
            $output['shift'] = $cek['shift'];
            $output['total_harga'] = $cek['total_harga'];
            $this->response($output, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
}
