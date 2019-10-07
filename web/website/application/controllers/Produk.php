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
    }

    public function index()
    {
        $data['judul'] = 'produk';
        $this->load->view('Produk/add_produk', $data);
    }

    public function dataproduk()
    {
        $data['judul'] = 'Data Produk';
        $this->load->view('Produk/list_produk.php', $data);
    }
}
