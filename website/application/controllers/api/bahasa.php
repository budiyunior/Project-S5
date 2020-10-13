<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class bahasa extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
    }
    function index_get()
    {
        $data = $this->db->get('t_setup')->result();
        $this->response(array("result" => $data, 200));
    }
    function index_post()
    {

        $id_menu = $this->input->post('id');
        $keranjang = $this->db->query("SELECT * FROM t_setup where id = $id_menu ")->result();

        $this->response(array("result" => $keranjang, 200));
    }
}
