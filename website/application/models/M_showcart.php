<?php if (!defined('BASEPATH')) exit('No direct script access allowed');
class M_showcart extends CI_Model
{
    function __construct()
    {
        parent::__construct();
    }
    function cek_login($id_pengguna)
    {

        $this->db->where('id_pengguna', $id_pengguna);
        $data = $this->db->get('cart')->row_array();
        return $data;
    }

    function cek_item($id_pengguna)
    {
        $this->db->where('id_pengguna', $id_pengguna);
        $data = $this->db->get('item')->row_array();
        return $data;
    }
}
