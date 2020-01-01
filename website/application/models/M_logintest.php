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
    function cek_keranjang($id_transaksi, $id_produk)
    {
        // $hash = $this->db->query("SELECT password FROM pengguna WHERE email='$email'");
        // $pass = password_verify($password, $hash);
        // $password = password_hash($password);

        $this->db->where('id_transaksi', $id_transaksi);
        $this->db->where('id_produk', $id_produk);
        $data = $this->db->get('tb_keranjang')->row_array();
        return $data;
    }
    function cek_trans($id_transaksi)
    {
        // $hash = $this->db->query("SELECT password FROM pengguna WHERE email='$email'");
        // $pass = password_verify($password, $hash);
        // $password = password_hash($password);

        $this->db->where('id_transaksi', $id_transaksi);
        $data = $this->db->get('tb_transaksi')->row_array();
        return $data;
    }
    function cek_total($nama_pelanggan, $no_meja)
    {
        // $hash = $this->db->query("SELECT password FROM pengguna WHERE email='$email'");
        // $pass = password_verify($password, $hash);
        // $password = password_hash($password);

        $this->db->where('nama_pelanggan', $nama_pelanggan);
        $this->db->where('no_meja', $no_meja);
        $data = $this->db->get('tb_keranjang')->row_array();
        return $data;
    }

}
