<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_pegawai extends CI_Model
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
    public $shift;

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
        return $this->db->query('SELECT * FROM tb_pengguna WHERE id_akses = 2')->result();
    }

    public function getById($id_pengguna)
    {
        return $this->db->get_where($this->_table, ["id_pengguna" => $id_pengguna])->row();
    }


    public function simpan()
    {
        $akses = 2;
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
        $this->shift = $post['shift'];
        $this->aktif = $aktif;
        $this->db->insert($this->_table, $this);
    }

    public function edit()
    {
        $akses = 2;
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
        $this->shift = $post['shift'];
        $this->aktif = $aktif;
        $this->db->update($this->_table, $this, array('id_pengguna' => $post['id_pengguna']));
    }

    public function hapus($id_pengguna)
    {
        return $this->db->delete($this->_table, array("id_pengguna" => $id_pengguna));
    }
}
