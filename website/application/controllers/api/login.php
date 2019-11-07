<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class login extends REST_Controller
{
    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
        $this->load->model('m_logintest');
    }
    function index_post()
    {
        $email = $this->input->post('email');
        $password = $this->input->post('password');
        $where = array(
            'email' => $email,
            'password' => $password
        );
        // $cek=$this->m_login->cek_login_biasa($username,$password)->num_rows();
        $cek = $this->m_logintest->cek_login($email, $password);
        if ($cek) {
            $output['id_pengguna'] = $cek['id_pengguna'];
            $output['nama_pengguna'] = $cek['nama_pengguna'];
            $output['id_akses'] = $cek['id_akses'];
            $output['email'] = $email;
            $output['password'] = $password;
            $output['shift'] = $cek['shift'];
            $this->response($output, 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
}
