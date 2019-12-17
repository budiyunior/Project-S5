<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class pesanan extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
    }

    function index_get()
    {
        $data = $this->db->get('tb_pesanan')->result();
        $this->response(array("result" => $data, 200));
    }
    function index_post()
    {
        $data = array(
            'id_produk'      => $this->post('id_produk'),
            'nama_produk' => $this->post('nama_produk'),
            'jumlah'    => $this->post('jumlah'),
            'harga_satuan'    => $this->post('harga_satuan'),
            'sub_total'    => $this->post('sub_total'),
            'nama_pelanggan'    => $this->post('nama_pelanggan'),
            'no_meja'    => $this->post('no_meja')
        );
        $insert = $this->db->insert('tb_pesanan', $data);
        if ($insert) {
            $this->response($data, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
    function index_delete()
    {

        $nama_pelanggan = $this->input->post('nama_pelanggan');
        $no_meja = $this->input->post('no_meja');
        $keranjang = $this->db->query("DELETE FROM tb_pesanan where nama_pelanggan = \"$nama_pelanggan\" && no_meja = $no_meja")->result();
        // $keranjang = $this->db->query("SELECT SUM(subtotal_harga) FROM desain_cart where id_pengguna = $id_pengguna")->result();

        //$perbaikan = $this->db->get_where('perbaikan',['id_user'=>$id_user])->result();
        $this->response(array("result" => $keranjang, 200));

        // $id_desain = $this->delete('id_desain');
        // $this->db->where('id_desain', $id_desain);
        // $delete = $this->db->delete('detail_cart');
        // if ($delete) {
        //     $this->response(array('status' => 'success'), 201);
        // } else {
        //     $this->response(array('status' => 'fail', 502));
        // }

    }
}
