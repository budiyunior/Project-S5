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
}
