<?php defined('BASEPATH') or exit('No direct script access allowed');

class Admin extends CI_Model
{
    private $_table = "pengguna";
    public $id_pengguna;
    public $nama;
    public $tangal_lahir;
    public $alamat;
    public $id_akses;
    public $no_telepon;
    public $email;
    public $password;

    public function rules()
    {
        return [

            [
                'field' => 'id_pengguna',
                'label' => 'id_pengguna',
                'rules' => 'required'
            ],

            [
                'field' => 'nama',
                'label' => 'nama',
                'rules' => 'required'
            ],

            [
                'field' => 'tanggal_lahir',
                'label' => 'tanggal_lahir',
                'rules' => 'required'
            ],

            [
                'field' => 'alamat',
                'label' => 'alamat',
                'rules' => 'required'
            ],

            [
                'field' => 'no_telepon',
                'label' => 'no_telepon',
                'rules' => 'required'
            ],

            [
                'field' => 'email',
                'label' => 'email',
                'rules' => 'required'
            ],

            [
                'field' => 'password',
                'label' => 'password',
                'rules' => 'required'
            ]

        ];
    }

    public function getAll()
    {
        return $this->db->get($this->_table)->result();
    }


    public function simpan()
    {
        $akses = 1;
        $post = $this->input->post();
        $this->id_pengguna = $post["id_pengguna"];
        $this->nama = $post["nama"];
        $this->tanggal_lahir = $post["tanggal_lahir"];
        $this->alamat = $post["alamat"];
        $this->id_akses = $akses;
        $this->no_telepon = $post["no_telepon"];
        $this->email = $post["email"];
        $this->password = $post["password"];

        $this->db->insert('pengguna');
    }
}
