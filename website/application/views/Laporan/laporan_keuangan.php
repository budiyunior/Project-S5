<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Laporan Keuangan</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="">Dahboard</a>
            </li>
            <li class="breadcrumb-item">
                <a>Laporan</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Laporan Keuangan</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Laporan Keuangan</h5>
                    <br />
                    <h5><?= $this->session->flashdata('success') ?></h5>
                    <form method="get" action="<?= site_url('Laporan/laporankeuangan') ?>">
                        <div class="form-group">
                            <label>Pilih Tanggal</label>
                            <?php
                            $tgl = date("Y-m-d");
                            ?>
                            <input type="date" name="tanggal" value="<?= $tgl ?>">
                            <input class="btn btn-primary" type="submit" value="pilih">
                        </div>
                    </form>
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
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>Total Finansial Perhari</th>
                                    <th>Total Keseluruhan Finansial</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="gradeA">
                                    <td><?php echo $fh ?></td>
                                    <td><?php echo $fk ?></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<?php $this->load->view('partials/footer.php'); ?>
<?php $this->load->view('partials/js.php'); ?>