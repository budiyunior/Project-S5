<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_hakakses extends CI_Model
{

    private $_table = "tb_pengguna";

    public $email;
    public $id_akses;


    public function rules()
    {
        return [


            [
                'field' => 'email',
                'label' => 'email',
                'rules' => 'required'
            ],



        ];
    }


    public function cek_akses_adm()
    {
        $periksa = $this->db->get_where('tb_pengguna', array('email' =>
        $this->session->userdata('email'), 'id_akses' => ('1')));
        if ($periksa->num_rows() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public function cek_akses_kas()
    {
        $periksa = $this->db->get_where('tb_pengguna', array('email' =>
        $this->session->userdata('email'), 'id_akses' => ('2')));
        if ($periksa->num_rows() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public function cek_akses_su()
    {
        $periksa = $this->db->get_where('tb_pengguna', array('email' =>
        $this->session->userdata('email'), 'id_akses' => ('3')));
        if ($periksa->num_rows() > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
