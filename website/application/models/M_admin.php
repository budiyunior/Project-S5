<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_admin extends CI_Model
{
    private $_table = "tb_pengguna";
    private static $_table1 = "tb_produk";
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
        $this->password = $post['password'];
        $this->aktif = $aktif;
        $this->db->insert($this->_table, $this);
    }

    public function hapus($id_pengguna)
    {
        $this->db->delete($this->_table, array("id_pengguna" => $id_pengguna));
    }

    public function get_produk()
	 {
		return $this->db->get(self::$_table1)->result();
     }


}
