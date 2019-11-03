<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Produk extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('M_produk');
    }

    public function index()
    {
        $data['judul'] = 'produk';
        $this->load->view('Produk/add_produk', $data);
    }

    public function dataproduk()
    {
        $data['judul'] = 'Data Produk';
        $data['produk'] = $this->M_produk->getAll();
        $this->load->view('Produk/list_produk.php', $data);
    }

    public function simpan()
    {
        $data['judul'] = 'Tambar Produk';
        $produk = $this->M_produk;
        $validation = $this->form_validation;
        $validation->set_rules($produk->rules());

        if ($validation->run()) {
            $produk->simpan();
            $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            redirect('Produk/dataproduk');
        }

        $this->load->view("Produk/add_produk", $data);
    }
}
