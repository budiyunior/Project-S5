<?php $this->load->view('partials/head.php'); ?>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
            <h3 class="font-bold">Resep <?= $resep->nama_produk ?></h3>

            <div class="error-desc">
                <?= $resep->resep ?> <br /><a href="<?= site_url('Adminpegawai') ?>" class="btn btn-primary m-t"><i class="fa fa-arrow-alt-circle-left"> Kembali</i></a>
            </div>
    </div>

    <?php $this->load->view('partials/js.php'); ?>