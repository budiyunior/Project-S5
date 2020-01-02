<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Produk extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        cek_akses();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('M_produk');
        $this->load->model('M_hakakses');
        $this->load->model('M_resep');
    }

    public function index()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'produk';
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('Produk/add_produk', $data);
        }
    }

    public function dataproduk()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Data Produk';
        $data['produk'] = $this->M_produk->getAll();
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('Produk/list_produk.php', $data);
        }
    }

    public function simpan()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Produk';
        $produk = $this->M_produk;
        $validation = $this->form_validation;
        $validation->set_rules($produk->rules());

        if ($validation->run()) {
            $produk->simpan();
            $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            redirect('Produk/dataproduk');
        }

        $this->load->view("Produk/add_produk", $data);
    }

    public function edit($id_produk =  null)
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        if (!isset($id_produk)) redirect('Produk/dataproduk');
        $data['judul'] = 'Edit Produk';

        $produk = $this->M_produk;
        $validation = $this->form_validation;
        $validation->set_rules($produk->rules());

        if ($validation->run()) {
            $produk->edit();
            $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            redirect('Produk/dataproduk');
        }

        $data["produk"] = $produk->getById($id_produk);
        if (!$data["produk"]) show_404();

        $this->load->view("Produk/edit_produk", $data);
    }

    public function editproduk()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['admin'] = $this->M_produk->getAll();
        $produk = $this->M_produk;
        $produk->edit();
        $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Diubah :)</div>');
        redirect('Produk/dataproduk', $data);
    }

    public function hapus($id_produk = null)
    {
        if (!isset($id_produk)) show_404();

        if ($this->M_produk->hapus($id_produk)) {
            redirect(site_url('Produk/dataproduk'));
        }
    }

    public function resep()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Resep';
        $data['produk'] = $this->M_produk->view();
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('Resep/add_resep', $data);
        }
    }

    public function add_resep()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Resep';
        $resep = $this->M_resep;
        $validation = $this->form_validation;
        $validation->set_rules($resep->rules());

        if ($validation->run()) {
            $resep->simpan();
            $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            redirect('Produk/dataresep');
        }

        $this->load->view("Resep/add_resep", $data);
    }

    public function dataresep()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Data Resep';
        $data['resep'] = $this->M_resep->view();
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('Resep/list_resep', $data);
        }
    }

    public function editan($id_resep = null)
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        if (!isset($id_resep)) redirect('Produk/dataresep');
        $data['judul'] = 'Edit Resep';
        $data['resep'] = $this->M_resep->view();
        $data['produk'] = $this->M_produk->view();
        $resep = $this->M_resep;
        $validation = $this->form_validation;
        $validation->set_rules($resep->rules());

        if ($validation->run()) {
            $resep->update();
            $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Disimpan :)</div>');
            redirect('Produk/dataresep');
        }

        $data["resep"] = $resep->getById($id_resep);
        if (!$data["resep"]) show_404();

        $this->load->view("Resep/edit_resep", $data);
    }

    public function editresep()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['resep'] = $this->M_resep->view();
        $data['produk'] = $this->M_produk->view();
        $produk = $this->M_resep;
        $produk->update();
        $this->session->set_flashdata('success', '<div class="alert alert-success" role="alert">Data Berhasil Diubah :)</div>');
        redirect('Produk/dataresep', $data);
        $this->load->view("Resep/edit_resep", $data);
    }

    public function hapusresep($id_resep =  null)
    {
        if (!isset($id_resep)) show_404();

        if ($this->M_resep->hapus($id_resep)) {
            redirect(site_url('Produk/dataresep'));
        }
    }
}
