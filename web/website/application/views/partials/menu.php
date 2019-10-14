<body>
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav metismenu" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <img alt="image" class="rounded-circle" src="<?= base_url() ?>assets/img/us.png" />
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="block m-t-xs font-bold">Reza</span>
                                <span class="text-muted text-xs block">Manager/Admin <b class="caret"></b></span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="dropdown-item" href="login.html">Logout</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            Cak Wang
                        </div>
                    </li>
                    <li>
                        <a href="<?= site_url('Menu') ?>"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboards</span></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-shopping-cart"></i> <span class="nav-label">Makanan</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="<?= site_url('Produk') ?>">Tambah Makanan</a></li>
                            <li><a href="<?= site_url('Produk/dataproduk') ?>">Data Makanan</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-shopping-cart"></i> <span class="nav-label">Minuman</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="<?= site_url('Produk') ?>">Tambah Minuman</a></li>
                            <li><a href="<?= site_url('Produk/dataproduk') ?>">Data Minuman</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-database"></i> <span class="nav-label">Stok Makanan</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="<?= site_url('Produk') ?>">Tambah Stok Makanan</a></li>
                            <li><a href="<?= site_url('Produk/dataproduk') ?>">Data Stok Makanan</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-database"></i> <span class="nav-label">Stok Minuman</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="<?= site_url('Produk') ?>">Tambah Stok Minuman</a></li>
                            <li><a href="<?= site_url('Produk/dataproduk') ?>">Data Stok Minuman</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="metrics.html"><i class="fa fa-history"></i> <span class="nav-label">Histori Penjualan</span> </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-user"></i> <span class="nav-label">Kepegawaian</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="<?= site_url('Pegawai') ?>">Tambah Pegawai</a></li>
                            <li><a href="<?= site_url('Pegawai/datapegawai') ?>">Data Pegawai</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-book"></i> <span class="nav-label">Laporan</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="metrics.html">Laporan Penjualan</a></li>
                            <li><a href="metrics.html">Laporan Produk</a></li>

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
                            <a href="login.html">
                                <i class="fa fa-sign-out"></i> Log out
                            </a>
                        </li>
                    </ul>

                </nav>
            </div>