<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class transaksi extends REST_Controller
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
        $tanggal = $this->input->post('tanggal');
        $status = $this->input->post('status_pesanan');
        $keranjang = $this->db->query("SELECT * FROM tb_transaksi where tanggal = '$tanggal' AND status_pesanan = '$status'")->result();

        $this->response(array("result" => $keranjang, 200));
    }
}
