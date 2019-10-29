<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Makanan extends CI_Controller
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
        $this->load->view('Makanan/add_makanan', $data);
    }

    public function datamakanan()
    {
        $data['judul'] = 'Data Produk';
        $this->load->view('Makanan/list_makanan.php', $data);
    }
}
