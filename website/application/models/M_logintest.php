<?php if (!defined('BASEPATH')) exit('No direct script access allowed');
class M_logintest extends CI_Model
{
    function __construct()
    {
        parent::__construct();
    }
    function cek_login($email, $password)
    {
        // $hash = $this->db->query("SELECT password FROM pengguna WHERE email='$email'");
        // $pass = password_verify($password, $hash);
        // $password = password_hash($password);

        $this->db->where('email', $email);
        $this->db->where('password', $password);
        $data = $this->db->get('tb_pengguna')->row_array();
        return $data;
    }
}