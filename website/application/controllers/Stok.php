<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Stok extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        cek_akses();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('M_stok');
        $this->load->model('M_hakakses');
    }

    public function index()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'List Stok Bahan';
        $data["bahan"] = $this->M_stok->getAll();
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('stok/list', $data);
        }
    }

    public function tambahstok()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Stok';
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('stok/add', $data);
        }
    }

    public function addstok()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Stok';
        $cek_id_akses = $this->M_hakakses->cek_akses_adm();
        if ($cek_id_akses == 1) {
            redirect('Menu');
        } else {
            $this->load->view('Hakakses_Pegawai/stok', $data);
        }
    }

    public function savebahan()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Bahan';
        //validasi dan pesan jika form tidak di isi
        $this->form_validation->set_rules('nama_bahan', 'Nama_bahan', 'required|is_unique[tb_bahan.nama_bahan]', [
            'required' => 'Nama Bahan Tidak Boleh Kosong!',
            'is_unique' => 'Bahan ini sudah ada di List Stok!'
        ]);
        $this->form_validation->set_rules('jumlah', 'Jumlah', 'required|alpha_numeric', [
            'required' => 'Jumlah Awal Tidak Boleh Kosong!'
        ]);



        $bahan = $this->M_stok;
        $validation = $this->form_validation;
        $validation->set_rules($bahan->rules());

        if ($validation->run()) {
            $bahan->tambahbahan();
            // $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            // redirect('Stok');
        }

        $this->load->view("stok/add", $data);
    }



    public function savestok($id_bahan = null)
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Jumlah Bahan';
        if (empty($id_bahan));

        $this->form_validation->set_rules('jumlah', 'Jumlah', 'required|integer', [
            'required' => 'Jumlah Awal Tidak Boleh Kosong!',
            'integer' => 'Jumlah Yang Dimasukkan Salah!'
        ]);



        $stok = $this->M_stok;
        $validation = $this->form_validation;
        $validation->set_rules($stok->rules());

        if ($validation->run()) {
            $stok->tambahstok();
        }
        $data["bahan"] = $stok->getById($id_bahan);
        if (!$data["bahan"]) show_404();
        $this->load->view("stok/edit", $data);
    }



    public function delete($id_bahan = null)
    {
        if (!isset($id_bahan)) show_404();

        if ($this->M_stok->delete($id_bahan)) {
            redirect(site_url('stok/'));
        }
    }


    public function pesan()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Pesan';
        $this->load->view('stok/pesan', $data);
    }

    public function beli()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Pesan';

        $pesan = $this->M_stok;
        $validation = $this->form_validation;
        $validation->set_rules($pesan->rules());

        if ($validation->run()) {
            $pesan->tambahpesan();
            redirect('coba/');
        }

        $this->load->view("stok/pesan", $data);
    }

    public function simpanbahan()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Bahan';
        //validasi dan pesan jika form tidak di isi
        $this->form_validation->set_rules('nama_bahan', 'Nama_bahan', 'required|is_unique[tb_bahan.nama_bahan]', [
            'required' => 'Nama Bahan Tidak Boleh Kosong!',
            'is_unique' => 'Bahan ini sudah ada di List Stok!'
        ]);
        $this->form_validation->set_rules('jumlah', 'Jumlah', 'required', [
            'required' => 'Jumlah Awal Tidak Boleh Kosong!'
        ]);



        // $bahan = $this->M_stok;
        // $validation = $this->form_validation;
        // $validation->set_rules($bahan->rules());

        // if ($validation->run()) {
        //     $bahan->tambahbahan();
        // }

        // $this->load->view("Hakakses_Pegawai/stok", $data);
        // $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
        // redirect('Stok/v_stok');

        $bahan = $this->M_stok;
        $validation = $this->form_validation;
        $validation->set_rules($bahan->rules());

        if ($validation->run()) {
            $bahan->tambahbahankasir();
            // $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            // redirect('Stok/v_stok');
        }

        $this->load->view("Hakakses_Pegawai/stok", $data);
    }

    public function v_stok()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'List Stok Bahan';
        $data["bahan"] = $this->M_stok->getAll();
        $cek_id_akses = $this->M_hakakses->cek_akses_adm();
        if ($cek_id_akses == 1) {
            redirect('Menu');
        } else {
            $this->load->view('Hakakses_Pegawai/datastok', $data);
        }
    }
}
