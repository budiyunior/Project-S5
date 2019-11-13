<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_resep extends CI_Model
{
    private $tb_resep = "tb_resep";
    private $v_resep = "v_resep";


    public function rules()
    {
        return [

            [
                'field' => 'resep',
                'label' => 'resep',
                'rules' => 'required'
            ]

        ];
    }

    public function simpan()
    {
        $post = $this->input->post();
        if (isset($_POST['id_resep'])) { }
        $this->id_produk = $post["id_produk"];
        $this->resep = $post["resep"];
        $this->db->insert($this->tb_resep, $this);
    }

    public function view()
    {
        return $this->db->get($this->v_resep)->result();
    }

    public function getById($id)
    {
        return $this->db->get_where($this->v_resep, ["id_resep" => $id])->row();
    }

    public function update()
    {
        $post = $this->input->post();
        $this->id_resep = $post["id_resep"];
        $this->id_produk = $post["id_produk"];
        $this->resep = $post["resep"];
        $this->db->update($this->tb_resep, $this, array('id_resep' => $post['id_resep']));
    }

    public function hapus($id_resep)
    {
        return $this->db->delete($this->tb_resep, array('id_resep' => $id_resep));
    }

    public function resepdata()
    {
        $resep = $this->db->query("SELECT * FROM v_resep")->result();
        return $resep;
    }
}
