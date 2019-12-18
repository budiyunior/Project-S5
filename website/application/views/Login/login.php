<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<?= base_url() ?>assets/img/cakwang.jpg">
    <title><?= $judul ?></title>
    <link href="<?php echo base_url() ?>assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="<?= base_url(); ?>assets/css/bootstrap.min.css">
</head>

<body>
    <div class="container">
        <div class="login-form col-md-5 offset-md-4">
            <div class="md-6 mt-4 text-center">
                <img class="mt-4" src="<?= base_url('assets/img/cw.jpg'); ?>" width="300" height="175">
                <br/>
                <h5 style="font-style: italic; color: green">Rest Area Jubung Jember, Jawa Timur</h5>
            </div>
            <h3 class="mt-3 text-white text-center">Login</h3>
            <div>
                <div><?= $this->session->flashdata('message'); ?></div>
                <form action="<?php echo base_url('index.php/Login/masuk'); ?>" class="m-t" role="form" method="post">
                    <div class="form-group">
                        <input type="email" id="email" class="form-control" name="email" placeholder="Email" required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
                    </div>
                    <button type="submit" class="btn btn-success block full-width m-b">Login</button>
                    <div class="text-center">
                        <a href="<?= site_url('Login/forgotpassword'); ?>"><small style="font-size: 20px;">Lupa password?</small></a>
                    </div>
                </form>
                <br />
            </div>
        </div>
    </div>
    <style type="text/css">
        body {
            width: 100%;
            height: auto;
            background-image: url(<?= base_url('assets/img/bggg.jpg'); ?>);
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

    <script src="<?= base_url() ?>assets/js/jquery-3.1.1.min.js"></script>
    <script src="<?= base_url() ?>assets/js/popper.min.js"></script>
    <script src="<?= base_url() ?>assets/js/bootstrap.js"></script>