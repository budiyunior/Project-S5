<?php $this->load->view("partials/head.php"); ?>

<body class="bg-gradient-primary">

    <div class="container">
        <div class="login-form col-md-5 offset-md-4">
            <div class="md-6 mt-4 text-center">
                <img class="mt-4" src="<?= base_url('assets/img/cw.jpg'); ?>" width="300" height="175">
            </div>
            <h3 class="mt-3 text-white text-center">Login</h3>
            <div>
                <div><?= $this->session->flashdata('message'); ?></div>
                <form action="<?php echo base_url('index.php/Login/masuk'); ?>" class="m-t" role="form" method="post">
                    <div class="form-group">
                        <input type="email" id="email" class="form-control" name="email" placeholder="Username" required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
                    </div>
                    <button type="submit" class="btn btn-success block full-width m-b">Login</button>
                    <div class="text-center">
                        <a href="<?= site_url('Login/forgot'); ?>"><small style="font-size: 20px;">Forgot password?</small></a>
                    </div>
                </form>
                <br />
            </div>
        </div>
    </div>
    <style>
        .bg-gradient-primary {
            background-color: #0F5102;
            background-image: -webkit-gradient(linear, left top, left bottom, color-stop(#5CDF43 10%), to(#0F5102));
            background-image: linear-gradient(180deg, #5CDF43 10%, #0F5102 100%);
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