<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Adminpegawai extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('M_resep');
        $this->load->model('M_produk');
    }

    public function index($id_produk = null)
    {
        $data['judul'] =  'Laporan Shift';
        $data['resep'] = $this->M_resep->view();
        $data['trans'] = $this->M_produk->v_trans();
        $data['kb'] = $this->M_produk->kb();
        $this->load->view('Hakakses_Pegawai/laporan_shift', $data);
    }

    public function detailresep($id_resep = null)
    {
        $data['judul'] = 'detail resep';
        $resep = $this->M_resep;
        $data['resep'] = $resep->getById($id_resep);
        if (!$data["resep"]) show_404();
        $this->load->view("Hakakses_Pegawai/detail_resep", $data);
    }

}
