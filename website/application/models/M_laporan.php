<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_laporan extends CI_Model
{
    private $_table = 'tb_transaksi';

    public function getAll()
    {
        return $this->db->get($this->_table)->result();
    }

}

