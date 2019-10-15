<?php

defined('BASEPATH') or exit('No direct script access allowed');

class Login extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->library('form_validation');
        $this->load->helper('url');
        $this->load->helper('form');
    }

    public function index()
    {
        $data['judul'] = 'Login';
        $this->load->view('Login/login', $data);
    }

    public function forgot()
    {
        $data['judul'] = 'Lupa Password';
        $this->load->view('Login/forgot', $data);
    }

    public function forgot2()
    {
        $data['judul'] = 'Lupa Password';
        $this->load->view('Login/forgot2', $data);
    }

    function masuk()
    {
        $email = htmlspecialchars($this->input->post('email', TRUE), ENT_QUOTES);
        $password = htmlspecialchars($this->input->post('password', TRUE), ENT_QUOTES);


        $user = $this->db->get_where('pengguna', ['email' => $email])->row_array();

        if ($user) {
            if (password_verify($password, $user['password'])) {
                $data = [
                    'email' => $user['email'], 'id_akses' => $user['id_akses']
                ];
                $this->session->set_userdata($data);
                if ($user['id_akses'] == '3') {
                    redirect('menu/superuser');
                }
                if ($user['id_akses'] == '1') {
                    redirect('menu/');
                } else {
                    $this->session->unset_userdata('email');
                    $this->session->unset_userdata('id_akses');
                    $this->session->set_flashdata('message', '<div class="alert alert-danger" role="alert">Kamu tidak di izinkan masuk!</div>');
                    redirect('login');
                }
            } else {
                $this->session->set_flashdata('message', '<div class="alert alert-danger" role="alert">Password Salah!</div>');
                redirect('login');
            }
        } else {
            $this->session->set_flashdata('message', '<div class="alert alert-danger" role="alert">Emailmu Tidak Terdaftar!</div>');
            redirect('login');
        }
    }
    public function logout()
    {
        $this->session->unset_userdata('email');
        $this->session->unset_userdata('id_akses');

        $this->session->set_flashdata('message', '<div class="alert alert-success" role="alert">Berhasil logout</div>');
        redirect('login');
    }
}
