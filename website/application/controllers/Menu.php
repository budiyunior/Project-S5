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
        $this->load->model('M_admin');
    }

    public function index()
    {
        $data['judul'] = 'Dashboard';
        $produk = $this->M_admin->get_produk();
        $data['totalproduk'] = (is_array($produk)) ? count($produk) : 0;
        $data['data']=$this->M_admin->get_data_produk();
        $this->load->view('Menu/dashboard.php', $data);
    }

    public function tambahadmin()
    {
        $data['judul'] = 'Tambah Admin';
        $this->load->view('superuser/add_admin.php', $data);
    }

    public function dataadmin()
    {
        $data['judul'] = 'Data Admin';
        $this->load->view('superuser/list_admin.php', $data);
    }

    public function superuser()
    {
        $data['judul'] = 'List Admin';
        $data["admin"] = $this->M_admin->getAll();
        $this->load->view('superuser/list_admin.php', $data);
    }

    public function saveadmin()
    {
        $data['judul'] = 'Tambah Admin';
        $admin = $this->M_admin;
        $validation = $this->form_validation;
        $validation->set_rules($admin->rules());

        if ($validation->run()) {
            $admin->simpan();
            $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            redirect('menu/superuser');
        }

        $this->load->view("superuser/add_admin", $data);
    }

    public function hapusadmin($id_pengguna)
    {
        if (!isset($id_pengguna)) show_404();

        if ($this->M_admin->hapus($id_pengguna)) {
            redirect(site_url('Menu/dataadmin'));
        }
    }
}
