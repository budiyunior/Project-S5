<body>
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav metismenu" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <img alt="image" class="rounded-circle" src="<?= base_url() ?>assets/img/us.png" />
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="block m-t-xs font-bold">User</span>
                                <span class="text-muted text-xs block">Pegawai/Kasir <b class="caret"></b></span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="dropdown-item" href="<?= base_url('Login/logout'); ?>">Logout</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            Cak Wang
                        </div>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-book"></i> <span class="nav-label">Laporan</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="<?= site_url('Adminpegawai') ?>">Laporan Shift</a>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-database"></i> <span class="nav-label">Stok</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="<?= site_url('Stok/addstok') ?>">Tambah Stok</a></li>
                            <li><a href="<?= site_url('Stok/v_stok') ?>">Data Bahan dan Stok</a></li>
                        </ul>
                    </li>
            </div>
        </nav>
        <div id="page-wrapper" class="gray-bg">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <a href="<?= base_url('Login/logout'); ?>">
                                <i class="fa fa-sign-out"></i> Log out
                            </a>
                        </li>
                    </ul>

                </nav>
            </div>