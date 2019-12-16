<?php $this->load->view("partials/head.php"); ?>

<body class="bg-gradient-primary">

    <div class="container">
        <div class="login-form col-md-5 offset-md-4">
            <div class="md-6 mt-4 text-center">
                <img class="mt-4" src="<?= base_url('assets/img/cw.jpg'); ?>" width="300" height="175">
            </div>
            <h3 class="mt-3 text-white text-center">Lupa Password</h3>
            <h3 class="mt-3 text-white text-center"><?= $this->session->flashdata('message') ?></h3>
            <div>
                <form class="m-t" role="form" method="post" action="<?= base_url('Login/forgotpassword') ?>">
                    <div class="form-group">
                        <input type="email" class="form-control" name="email" placeholder="Email" required="">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">Kirim</button>
                    <div class="text-center">
                        <a href="<?= site_url('Login') ?>"><small>Anda Sudah memiliki akun?</small></a>
                    </div>
                </form>
                <br />
            </div>
        </div>
    </div>
    <style>
        .bg-gradient-primary {
            width: 100%;
            height: auto;
            background-image: url(<?= base_url('assets/img/cb.webp'); ?>);
            background-size: cover;
        }

        .login-form {
            margin-top: 3%;
            box-shadow: 0px 0px 10px 1px grey;
            border-radius: 5px;
            padding-bottom: 20px;
            background: rgba(0, 0, 0, 0.39);
        }
    </style>
    <?php $this->load->view("partials/js.php"); ?>