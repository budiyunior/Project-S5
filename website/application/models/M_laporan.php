<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_laporan extends CI_Model
{
    private $_table = 'tb_transaksi';

    public function getAll()
    {
        return $this->db->get($this->_table)->result();
    }

    public function hapuspenjualan($id_transaksi)
    {
        return $this->db->delete($this->_table, array("id_transaksi" => $id_transaksi));
    }

    public function jumlah()
    {
        $sql = "SELECT count(id_transaksi) as count FROM tb_transaksi";
        $result = $this->db->query($sql);
        return $result->row()->count;
    }

    public function totalharga()
    {
        $sql = "SELECT sum(total_harga) as sum FROM tb_transaksi";
        $result = $this->db->query($sql);
        return $result->row()->sum;
    }

    public function lfh($tanggal)
    {
        $this->tanggal = $this->input->get("tanggal");
        $this->db->get_where($this->_table, ['tanggal' => $tanggal])->row();
    }

    public function finansialhari()
    {
        $tanggal = $this->input->get('tanggal');
        $tanggal = "SELECT sum(total_harga) as jumlah FROM tb_transaksi where tanggal = '$tanggal'";
        $result = $this->db->query($tanggal);
        return $result->row()->jumlah;
    }
}
