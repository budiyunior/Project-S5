<?php $this->load->view("partials/head.php"); ?>

<body class="bg-dark">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div class="md-6">
                <img src="<?= base_url('assets/img/cw.jpg'); ?>" width="300" height="200">
            </div>
            <h3>Lupa Password</h3>
            <p>
                Silahkan isi password baru anda
            </p>
            <div class="row">
                <form class="m-t" role="form" action="index.html">
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password Baru" required="">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Konfirmasi Password Baru" required="">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">Kirim</button>

                    <a href="<?= site_url('Login') ?>"><small>Anda Sudah memiliki akun?</small></a>
                </form>
                <br />
                <p style="font-size: 20px;" class="m-t"> <small>IfCodeStudio &copy; 2019 </small> </p>
            </div>
        </div>
        <?php $this->load->view("partials/js.php"); ?>