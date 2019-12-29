<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_admin extends CI_Model
{
    private $_table = "tb_pengguna";
    private static $_table1 = "tb_produk";
    private static $_table2 = "tb_transaksi";
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

    function get_data_produk(){
        $query = $this->db->query("SELECT nama_bahan,SUM(jumlah) AS jumlah FROM tb_bahan GROUP BY nama_bahan");
         
        if($query->num_rows() > 0){
            foreach($query->result() as $data){
                $hasil[] = $data;
            }
            return $hasil;
        }
    }

    function laporanTahunan(){
        $tahun= date("Y-m-d");
        $bc = $this->db->query("
 
       SELECT
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=1)AND (YEAR(tanggal)=2019))),0) AS `Januari`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=2)AND (YEAR(tanggal)=2019))),0) AS `Februari`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=3)AND (YEAR(tanggal)=2019))),0) AS `Maret`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=4)AND (YEAR(tanggal)=2019))),0) AS `April`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=5)AND (YEAR(tanggal)=2019))),0) AS `Mei`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=6)AND (YEAR(tanggal)=2019))),0) AS `Juni`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=7)AND (YEAR(tanggal)=2019))),0) AS `Juli`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=8)AND (YEAR(tanggal)=2019))),0) AS `Agustus`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=9)AND (YEAR(tanggal)=2019))),0) AS `September`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=10)AND (YEAR(tanggal)=2019))),0) AS `Oktober`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=11)AND (YEAR(tanggal)=2019))),0) AS `November`,
       ifnull((SELECT count(id_transaksi) FROM (tb_transaksi)WHERE((Month(tanggal)=12)AND (YEAR(tanggal)=2019))),0) AS `Desember`
       from tb_transaksi GROUP BY YEAR(tanggal) 
 
 ");
 
 return $bc;
 
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

    public function fetch_year()
    {
     $this->db->select('tanggal');
     $this->db->from('tb_transaksi');
     $this->db->where(YEAR('tanggal'));
     return $this->db->get();
     }

     public function fetch_chart_data($tanggal)
     {
     $this->db->where(YEAR('tanggal'), $tanggal);
     return $this->db->get('tb_transaksi');
     }


}
