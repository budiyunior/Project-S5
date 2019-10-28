<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Pegawai extends CI_Controller
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
        $data['judul'] = 'pegawai';
        $this->load->view('Pegawai/add_pegawai', $data);
    }

    public function datapegawai()
    {
        $data['judul'] = 'Data Pegawai';
        $this->load->view('Pegawai/list_pegawai', $data);
    }
}
