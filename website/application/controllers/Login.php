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


    function masuk()
    {
        $email = htmlspecialchars($this->input->post('email', TRUE), ENT_QUOTES);
        $password = htmlspecialchars($this->input->post('password', TRUE), ENT_QUOTES);


        $user = $this->db->get_where('tb_pengguna', ['email' => $email])->row_array();

        if ($user) {
            if ($password == $user['password']) {
                $data = [
                    'email' => $user['email'], 'id_akses' => $user['id_akses']
                ];
                $this->session->set_userdata($data);
                if ($user['id_akses'] == '3') {
                    redirect('menu/superuser');
                }
                if ($user['id_akses'] == '1') {
                    redirect('Menu');
                } else {
                    $this->session->unset_userdata('email');
                    $this->session->unset_userdata('id_akses');
                    $this->session->set_flashdata('message', '<div class="alert alert-danger" role="alert">Anda gagal login!</div>');
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

    private function kirimemail($token, $type)
    {
        $config = [
            'protocol' => 'smtp',
            'smtp_host' => 'ssl://smtp.googlemail.com',
            'smtp_user' => 'cakwang39@gmail.com',
            'smtp_pass' => 'cakwangcafe',
            'smtp_port' => 465,
            'mailtype' => 'html',
            'charset' => 'utf-8',
            'newline' => "\r\n"
        ];

        $this->load->library('email', $config);

        $this->email->from('cakwang39@gmail.com', 'Cak Wang Cafe');
        $this->email->to($this->input->post('email'));

        if ($type == 'forgot') {
            $this->email->subject('Ubah Password Anda');
            $this->email->message('Silahkan ikuti tautan link untuk mengubah password anda : <a href="' . base_url() . 'Login/gantipassword?email=' . $this->input->post('email') .
                '&token=' . urlencode($token) . '">mengubah password anda...</a>');
        }

        if ($this->email->send()) {
            return true;
        } else {
            echo $this->email->print_debugger();
            die;
        }
    }

    public function forgotpassword()
    {
        $data['judul'] = 'Lupa Password';
        $this->form_validation->set_rules('email', 'Email', 'required|trim|valid_email');
        if ($this->form_validation->run() == false) {
            $this->load->view('Login/forgot', $data);
        } else {
            $email = $this->input->post('email');
            $user = $this->db->get_where('pengguna', ['email' => $email, 'aktif' => 1])->row_array();

            if ($user) {

                $token = base64_encode(random_bytes(32));
                $user_token = [
                    'email' => $email,
                    'token' => $token,
                    'date_create' => time()
                ];

                $this->db->insert('token', $user_token);
                $this->kirimemail($token, 'forgot');
                $this->session->set_flashdata('message', '<div class="alert alert-success" role="alert">Cek email anda untuk mengubah password!</div>');
                redirect('Login/Forgotpassword');
            } else {
                $this->session->set_flashdata('message', '<div class="alert alert-danger" role="alert">Email anda tidak terdaftar atau belum verifikasi!</div>');
                redirect('Login/Forgotpassword');
            }
        }
    }

    public function gantipassword()
    {
        $email = $this->input->get('email');
        $token = $this->input->get('token');


        $pengguna = $this->db->get_where('pengguna', ['email' => $email])->row_array();

        if ($pengguna) {

            $user_token = $this->db->get_where('token', ['token' => $token])->row_array();

            if ($user_token) {
                $this->session->set_userdata('ganti_email', $email);
                $this->ubahpassword();
            } else {
                $this->session->set_flashdata('message', '<div class="alert alert-danger" role="alert">Anda gagal ganti password! Token salah!</div>');
                redirect('Login');
            }
        } else {
            $this->session->set_flashdata('message', '<div class="alert alert-danger" role="alert">Anda gagal ganti password! Email salah!</div>');
            redirect('Login');
        }
    }

    public function ubahpassword()
    {
        $data['judul'] = 'Lupa Password';
        $this->form_validation->set_rules('password1', 'Password', 'required|trim|matches[password2]');
        $this->form_validation->set_rules('password2', 'Konfirmasi Password', 'required|trim|matches[password1]');

        if ($this->form_validation->run() == false) {

            $this->load->view('Login/forgot2', $data);
        } else {

            $password = password_hash($this->input->post('password1'), PASSWORD_DEFAULT);
            $email = $this->session->userdata('ganti_email');

            $this->db->set('password', $password);
            $this->db->where('email', $email);
            $this->db->update('pengguna');

            $this->session->unset_userdata('ganti_email');

            $this->db->delete('token', ['email' => $email]);

            $this->session->set_flashdata('message', '<div class="alert alert-success" role="alert">Password anda berhasil di ubah</div>');
            redirect('Login');
        }
    }
}
