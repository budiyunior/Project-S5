<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Menu extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        cek_akses();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
        $this->load->model('M_admin');
        $this->load->model('M_stok');
        $this->load->model('M_produk');
        $this->load->model('M_hakakses');
        $this->load->model('M_historibahan');
    }

    public function index()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Dashboard';
        $produk = $this->M_admin->get_produk();
        $data['totalproduk'] = (is_array($produk)) ? count($produk) : 0;
        // $transaksi = $this->M_admin->get_transaksi();
        // $data['totaltransaksi'] = (is_array($transaksi)) ? count($transaksi) :0;
        // $data['year_list'] = $this->M_admin->fetch_year();
        $data['data'] = $this->M_admin->get_data_produk();
        foreach ($this->M_admin->laporanTahunan()->result_array() as $row) {
            $data['grafik'][] = (int) $row['Januari'];
            $data['grafik'][] = (int) $row['Februari'];
            $data['grafik'][] = (int) $row['Maret'];
            $data['grafik'][] = (int) $row['April'];
            $data['grafik'][] = (int) $row['Mei'];
            $data['grafik'][] = (int) $row['Juni'];
            $data['grafik'][] = (int) $row['Juli'];
            $data['grafik'][] = (int) $row['Agustus'];
            $data['grafik'][] = (int) $row['September'];
            $data['grafik'][] = (int) $row['Oktober'];
            $data['grafik'][] = (int) $row['November'];
            $data['grafik'][] = (int) $row['Desember'];
        }
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('Menu/dashboard.php', $data);
        }
    }

    public function tambahadmin()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Admin';
        $this->load->view('superuser/add_admin.php', $data);
    }

    public function dataadmin()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Data Admin'; 
        $this->load->view('superuser/list_admin.php', $data);
    }

    public function superuser()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'List Admin';
        $data["admin"] = $this->M_admin->getAll();
        $this->load->view('superuser/list_admin.php', $data);
    }

    public function saveadmin()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
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

    public function historypenjualan()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'history penjualan';
        $tanggal = $this->input->get('tanggal');
        $data['view'] = $this->db->get_where('tb_detail_transaksi', ["tanggal" => $tanggal])->row_array();
        $data['story'] = $this->db->query("SELECT * FROM tb_detail_transaksi WHERE tanggal = '$tanggal'");
        $data['history'] = $this->M_produk->history();
        $data['hp'] = $this->M_produk->v_trans();
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('Menu/history_penjualan', $data);
        }
    }

    public function savestok($id_bahan = null)
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Tambah Jumlah Bahan';
        if (empty($id_bahan));

        $stok = $this->M_stok;
        $validation = $this->form_validation;
        $validation->set_rules($stok->rules());

        if ($validation->run()) {
            $stok->tambahstokkasir();
        }
        $data["bahan"] = $stok->getById($id_bahan);
        if (!$data["bahan"]) show_404();
        $this->load->view("Hakakses_Pegawai/edit_stok", $data);
    }


    public function historibahan()
    {
        $data['pengguna'] = $this->db->get_where('tb_pengguna', ['email' => $this->session->userdata('email')])->row_array();
        $data['judul'] = 'Histori Stok Bahan';
        $data["bahan"] = $this->M_historibahan->getAll();
        $cek_id_akses = $this->M_hakakses->cek_akses_kas();
        if ($cek_id_akses == 1) {
            redirect('Adminpegawai');
        } else {
            $this->load->view('Menu/histori_bahan', $data);
        }
    }




    // function fetch_data()
    // {
    // if($this->input->post('tanggal'))
    // {
    // $chart_data = $this->M_admin->fetch_chart_data($this->input->post('tanggal'));

    // foreach($chart_data->result_array() as $row)
    // {
    //     $output[] = array(
    //     'tanggal'  => $row["tanggal"],
    //     'id_transaksi' => floatval($row["id_transaksi"])
    //     );
    // }
    // echo json_encode($output);
    // }
    // }
}
