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
        $this->load->model('M_pegawai');
    }

    public function index()
    {
        $data['admin'] = $this->M_pegawai->getAll();
        $data['judul'] = 'pegawai';
        $this->load->view('Pegawai/add_pegawai', $data);
    }

    public function datapegawai()
    {
        $data['judul'] = 'Data Pegawai';
        $data['admin'] = $this->M_pegawai->getAll();
        $this->load->view('Pegawai/list_pegawai', $data);
    }

    public function save()
    {
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
