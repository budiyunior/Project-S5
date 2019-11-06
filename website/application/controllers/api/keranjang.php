<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class keranjang extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
    }

    function index_get()
    {
        $data = $this->db->get('tb_keranjang')->result();
        $this->response(array("result" => $data, 200));
    }
    function index_post()
    {
        $data = array(
            'id_produk'      => $this->post('id_produk'),
            'jumlah'    => $this->post('jumlah'),
            'harga_satuan'    => $this->post('harga_satuan'),
            'sub_total'    => $this->post('sub_total'),
            'nama_pelanggan'    => $this->post('nama_pelanggan'),
            'no_meja'    => $this->post('no_meja')
        );
        $insert = $this->db->insert('tb_keranjang', $data);
        if ($insert) {
            $this->response($data, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
    
}
