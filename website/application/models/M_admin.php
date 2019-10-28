<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_admin extends CI_Model
{
    private $_table = "tb_pengguna";
    public $id_pengguna;
    public $nama;
    public $tgl_lahir;
    public $alamat;
    public $id_akses;
    public $no_telp;
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
        $this->tgl_lahir = $post["tgl_lahir"];
        $this->alamat = $post["alamat"];
        $this->id_akses = $akses;
        $this->no_telp = $post["no_telp"];
        $this->email = $post["email"];
        $this->password = $_POST['password'];
        $this->aktif = $aktif;

        $this->db->insert($this->_table, $this);
    }
}
