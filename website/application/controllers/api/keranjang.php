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
            'id_transaksi'   => $this->post('id_transaksi'),
            'id_produk'      => $this->post('id_produk'),
            'nama_produk' => $this->post('nama_produk'),
            'gambar'    => $this->post('gambar'),
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
    function index_delete()
    {
        // $nama_pelanggan = $this->input->post('nama_pelanggan');
        // $no_meja = $this->input->post('no_meja');
        // $id_trans = $this->input->post('id_transaksi');
        // $id_produk = $this->input->post('id_produk');
        // $keranjang = $this->db->query("DELETE FROM tb_keranjang where id_transaksi=$id_trans AND id_produk= $id_trans")->result();
        // $keranjang = $this->db->query("SELECT SUM(subtotal_harga) FROM desain_cart where id_pengguna = $id_pengguna")->result();
        $id_trans = $this->delete('id_transaksi');
        $id_produk = $this->delete('id_produk');
        $this->db->where('id_transaksi', $id_trans);
        $this->db->where('id_produk', $id_produk);
        $delete = $this->db->delete('tb_keranjang');
        if ($delete) {
            $this->response(array('status' => 'success'), 201);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
        //$perbaikan = $this->db->get_where('perbaikan',['id_user'=>$id_user])->result();
        // $this->response(array("result" => $keranjang, 200));

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
