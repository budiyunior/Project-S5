<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class post_transaksi extends REST_Controller
{
    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
        // $this->load->model('m_showcart');
    }

    function index_post()
    {
        $nama_pelanggan = $this->input->post('nama_pelanggan');
        $no_meja = $this->input->post('no_meja');
        $total_harga = $this->db->query("SELECT SUM(tb_keranjang.sub_total) as total_harga from tb_keranjang where nama_pelanggan = \"$nama_pelanggan\" && no_meja =$no_meja")->result();
        $total = $total_harga;
        $data = array(
            'nama_pelanggan'    => $this->post('nama_pelanggan'),
            'no_meja'    => $this->post('no_meja'),
            'jam'    => $this->post('jam'),
            'tanggal'    => $this->post('tanggal'),
            'total_harga'    => $this->post('total_harga'),
            'shift'    => $this->post('shift')
        );
        $insert = $this->db->insert('tb_transaksi', $data);
        if ($insert) {
            $this->response($data, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
}