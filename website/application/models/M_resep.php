<?php defined('BASEPATH') or exit('No direct script access allowed');

class M_resep extends CI_Model
{
    private $tb_resep = "tb_resep";


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
        return $this->db->get($this->tb_resep)->result();
    }
}
