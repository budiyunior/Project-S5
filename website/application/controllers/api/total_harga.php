<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class total_harga extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
    }

    function index_post()
    {
        $nama_pelanggan = $this->input->post('nama_pelanggan');
        $no_meja = $this->input->post('no_meja');
        $total_harga = $this->db->query("SELECT SUM(tb_keranjang.sub_total) as total_harga from tb_keranjang where nama_pelanggan = \"$nama_pelanggan\" && no_meja =$no_meja")->result();
        $this->response(array("result" => $total_harga, 200));
    }
    // function index_post()
    // {
    //     $nama_pelanggan = $this->input->post('nama_pelanggan');
    //     $no_meja = $this->input->post('no_meja');
    //     $total_harga = $this->db->query("SELECT SUM(tb_keranjang.sub_total) as total_harga from tb_keranjang where nama_pelanggan = \"$nama_pelanggan\" && no_meja =$no_meja")->result();
    //     $where = array(
    //         'nama_pelanggan' => $nama_pelanggan,
    //         'no_meja' => $no_meja
    //     );
    //     // $cek=$this->m_login->cek_login_biasa($username,$password)->num_rows();
    //     $cek = $this->m_logintest->cek_login($nama_pelanggan, $no_meja);
    //     if ($cek) {
    //         $output['id_pengguna'] = $cek['id_pengguna'];
    //         $output['nama_pengguna'] = $cek['nama_pengguna'];
    //         $output['total_harga'] = $total_harga;
    //         $this->response($output, 200);
    //     } else {
    //         $this->response(array('status' => 'fail', 502));
    //     }
    // }
}
