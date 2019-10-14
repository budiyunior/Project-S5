<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Menu extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('Admin');
    }

    public function index()
    {
        $data['judul'] = 'Dashboard';
        $this->load->view('Menu/dashboard', $data);
    }

    public function tambahadmin()
    {
        $data['judul'] = 'Tambah Admin';
        $this->load->view('superuser/add_admin.php', $data);
    }

    public function saveadmin()
    {
        $data['judul'] = 'Tambah Admin';
        $admin = $this->Admin;
        $validation = $this->form_validation;
        $validation->set_rules($admin->rules());

        if ($validation->run()) {
            $admin->simpan();
            $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            redirect('superuser/list_admin');
        }

        $this->load->view("superuser/add_admin", $data);
    }
}
