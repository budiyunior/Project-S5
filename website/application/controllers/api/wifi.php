<?php
defined('BASEPATH') or exit('NO direct script access aloowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

require APPPATH . 'libraries/Format.php';

class wifi extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->database();
        // $this->load->model('m_showcart');
    }

    function index_post()
    {
    
        $keranjang = $this->db->query("SELECT * FROM tb_wifi where id = 1")->result();

        $this->response(array("result" => $keranjang, 200));
    }
}
