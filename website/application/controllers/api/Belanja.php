<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class Belanja extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
    }
    function index_post()
    {
        $data = array(
            'barang'      => $this->post('barang'),
            'harga' => $this->post('harga'),
            'tanggal'   => $this->post('tanggal'),
        );
        $insert = $this->db->insert('tb_belanja', $data);
        if ($insert) {
            $this->response($data, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
    // function index_post()
    // {
        
    //     $id_trans = $this->input->post('id_transaksi');
    //     $id_trans2 = $this->input->post('id_trans2');
    //     $total_harga = $this->db->query("SELECT SUM(tb_keranjang.sub_total) as total_harga from tb_keranjang where id_transaksi = $id_trans")->row_array();
    //     $total_harga2 = $this->db->query("SELECT SUM(tb_keranjang.sub_total) as total_harga from tb_keranjang where id_transaksi = $id_trans2")->row_array();
    //     $total_semua = $total_harga - $total_harga2;
        
    //     if ($total_semua) {
    //         $output['total_harga'] = $total_semua['total_harga'];
    //         //       $output['nama_pengguna'] = $cek['nama_pengguna'];
    //         //       $output['total_harga'] = $total_harga;
    //         $this->response($output, 200);
    //     }

    //     $this->response(array("result" => $total_harga, 200));
    // }
}
