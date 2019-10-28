<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>
<div class="row wrapper border-bottom white-bg page-heading row mb-3">
    <h1 class="ml-3">Dashboard</h1>
</div>
<div class="container">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-lg-3">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Jumlah data order</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">40 886,200</h1>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Jumlah data produk</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">275,800</h1>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Jumlah stok makanan</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">106,120</h1>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Jumlah stok minuman</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">80,600</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>Bar Chart Example <small>With custom colors.</small></h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="#" class="dropdown-item">Config option 1</a>
                                </li>
                                <li><a href="#" class="dropdown-item">Config option 2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="flot-chart">
                            <div class="flot-chart-content" id="flot-bar-chart"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<?php $this->load->view('partials/footer.php'); ?>
<?php $this->load->view('partials/js.php'); ?>