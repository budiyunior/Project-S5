<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Pegawai extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        cek_akses();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('M_pegawai');
        $this->load->model('M_hakakses');
    }

    public function index()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['admin'] = $this->M_pegawai->getAll();
        $data['judul'] = 'pegawai';
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('Pegawai/add_pegawai', $data);
        }
    }

    public function datapegawai()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Data Pegawai';
        $data['admin'] = $this->M_pegawai->getAll();
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('Pegawai/list_pegawai', $data);
        }
    }

    public function save()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Pegawai';
        $admin = $this->M_pegawai;
        $validation = $this->form_validation;
        $validation->set_rules($admin->rules());

        if ($validation->run()) {
            $admin->simpan();
            $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            redirect('Pegawai/datapegawai');
        }

        $this->load->view("Pegawai/add_pegawai", $data);
    }

    public function edit($id_pengguna = null)
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        if (!isset($id_pengguna)) redirect('Pegawai/datapegawai');
        $data['judul'] = 'Edit Pegawai';

        $admin = $this->M_pegawai;
        $validation = $this->form_validation;
        $validation->set_rules($admin->rules());

        if ($validation->run()) {
            $admin->edit();
            $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            redirect('Pegawai/datapegawai');
        }

        $data["pegawai"] = $admin->getById($id_pengguna);
        if (!$data["pegawai"]) show_404();

        $this->load->view("Pegawai/edit_pegawai", $data);
    }

    public function editan()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['admin'] = $this->M_pegawai->getAll();
        $admin = $this->M_pegawai;
        $admin->edit();
        $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Diubah :)</div>');
        redirect('Pegawai/datapegawai', $data);
    }

    public function hapus($id_pengguna =  null)
    {
        if (!isset($id_pengguna)) show_404();

        if ($this->M_pegawai->hapus($id_pengguna)) {
            redirect(site_url('Pegawai/datapegawai'));
        }
    }
}
