<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Adminpegawai extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        cek_akses();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('M_resep');
        $this->load->model('M_produk');
        $this->load->model('M_stok');
        $this->load->model('M_historibahan');
        $this->load->model('M_hakakses');
    }

    public function index($id_produk = null)
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] =  'Laporan Shift';
        $data['resep'] = $this->M_resep->view();
        $data['trans'] = $this->M_produk->view();
        $data['abc'] = $this->M_produk->kb($id_produk);
        $data['np'] = $this->M_stok->getAll();

        $cek_id_akses = $this->M_hakakses->cek_akses_adm();
        if ($cek_id_akses == 1) {
            redirect('Menu');
        } else {
            $this->load->view('Hakakses_Pegawai/laporan_shift', $data);
        }
    }


    public function stok()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] =  'Laporan Shift';
        $data['resep'] = $this->M_resep->view();
        $data['trans'] = $this->M_produk->view();
        $data['np'] = $this->M_stok->getAll();

        $stok = $this->M_historibahan;
        $validation = $this->form_validation;
        $validation->set_rules($stok->rules());

        if ($validation->run()) {
            $stok->save();
        }

        $this->load->view('Hakakses_Pegawai/laporan_shift', $data);
    }


    public function histori()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Histori Stok Bahan';
        $data["bahan"] = $this->M_historibahan->getAll();
        $cek_id_akses = $this->M_hakakses->cek_akses_adm();
        if ($cek_id_akses == 1) {
            redirect('Menu');
        } else {
            $this->load->view('Hakakses_Pegawai/histori', $data);
        }
    }




    public function detailresep($id_resep = null)
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'detail resep';
        $resep = $this->M_resep;
        $data['resep'] = $resep->getById($id_resep);
        if (!$data["resep"]) show_404();
        $this->load->view("Hakakses_Pegawai/detail_resep", $data);
    }
}
