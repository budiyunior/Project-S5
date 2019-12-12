<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Laporan extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('M_laporan');
    }

    public function index()
    {
        $data['judul'] = 'Laporan Penjualan';
        $data['laporan'] = $this->M_laporan->getAll();
        $data['jml'] = $this->M_laporan->jumlah();
        $data['th'] = $this->M_laporan->totalharga();
        $this->load->view('Laporan/laporan_penjualan', $data);
    }

    public function hapuspenjualan($id_transaksi = null)
    {
        if (!isset($id_transaksi)) show_404();

        if ($this->M_laporan->hapuspenjualan($id_transaksi)) {
            redirect(site_url('Laporan'));
        }
    }
}