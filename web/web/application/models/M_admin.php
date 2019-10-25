<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_admin extends CI_Model
{
    private $_table = "pengguna";
    public $id_pengguna;
    public $nama;
    public $tanggal_lahir;
    public $alamat;
    public $id_akses;
    public $no_telepon;
    public $email;
    public $password;

    public function rules()
    {
        return [


            [
                'field' => 'nama',
                'label' => 'nama',
                'rules' => 'required'
            ],



        ];
    }

    public function getAll()
    {
        return $this->db->get($this->_table)->result();
    }


    public function simpan()
    {
        $akses = 1;
        $aktif = 1;
        $post = $this->input->post();
        $this->id_pengguna = $post["id_pengguna"];
        $this->nama = $post["nama"];
        $this->tanggal_lahir = $post["tanggal_lahir"];
        $this->alamat = $post["alamat"];
        $this->id_akses = $akses;
        $this->no_telepon = $post["no_telepon"];
        $this->email = $post["email"];
        $this->password = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $this->aktif = $aktif;

        $this->db->insert($this->_table, $this);
    }
}
