<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Stok extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('M_stok');
    }

    public function index()
    {
        $data['judul'] = 'List Stok Bahan';
        $data["bahan"] = $this->M_stok->getAll();
        $this->load->view('stok/list', $data);
    }

    public function tambahstok()
    {
        $data['judul'] = 'Tambah Stok';
        $this->load->view('stok/add', $data);
    }

    public function savebahan()
    {
        $data['judul'] = 'Tambah Bahan';
        //validasi dan pesan jika form tidak di isi
        $this->form_validation->set_rules('nama_bahan', 'Nama_bahan', 'required|is_unique[tb_bahan.nama_bahan]', [
            'required' => 'Nama Bahan Tidak Boleh Kosong!',
            'is_unique' => 'Bahan ini sudah ada di List Stok!'
        ]);
        $this->form_validation->set_rules('jumlah', 'Jumlah', 'required', [
            'required' => 'Jumlah Awal Tidak Boleh Kosong!'
        ]);



        $bahan = $this->M_stok;
        $validation = $this->form_validation;
        $validation->set_rules($bahan->rules());

        if ($validation->run()) {
            $bahan->tambahbahan();
        }

        $this->load->view("stok/add", $data);
    }



    public function savestok($id_bahan = null)
    {
        $data['judul'] = 'Tambah Jumlah Bahan';
        if (empty($id_bahan));

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
        $data['judul'] = 'Pesan';
        $this->load->view('stok/pesan', $data);
    }

    public function beli()
    {
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
}
