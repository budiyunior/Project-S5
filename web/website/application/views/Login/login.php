<?php $this->load->view("partials/head.php"); ?>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div class="md-6 mt-4">
                <img src="<?= base_url('assets/img/cw.jpg'); ?>" width="300" height="175">
            </div>
            <h3 class="mt-3">Login</h3>
            <p>
                Silahkan Login untuk mendapatkan akses
            </p>
            <div>
                <form class="m-t" role="form" action="index.html">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Username" required="">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" required="">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

                    <a href="<?= site_url('Login/forgot'); ?>"><small>Forgot password?</small></a>
                </form>
                <br />
                <p style="font-size: 20px;" class="m-t"> <small>IfCodeStudio &copy; 2019 </small> </p>
            </div>
        </div>
        <?php $this->load->view("partials/js.php"); ?>