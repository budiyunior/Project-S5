<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('Hakakses_Pegawai/menu.php'); ?>
<?php
$koneksi =  mysqli_connect("localhost", "root", "", "cakwang");
$kb = 1;
$tanggal = mysqli_query($koneksi, "SELECT * FROM tb_detail_transaksi");
?>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Laporan Shift</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="index.html">Pegawai</a>
            </li>
            <li class="breadcrumb-item">
                <a>Laporan</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Laporan Shift</strong>
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
                    <h5>Data Resep</h5>
                    <h5><?= $this->session->flashdata('success') ?></h5>
                    <form method="get">
                        <div class="form-group">
                            <label>Pilih Tanggal</label>
                            <?php
                            $tgl = date("Y-m-d");
                            ?>
                            <input type="date" name="tanggal" value="<?= $tgl ?>">
                            <input class="btn btn-primary" type="submit" value="filter">
                        </div>
                    </form>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-default"><i class="fa fa-shopping-cart"></i>
                        Data Terjual Hari Ini
                    </button>
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
                                    <th width="200">Nama Produk</th>
                                    <th>Resep</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php foreach ($resep as $rsp) : ?>
                                    <tr class="gradeA">
                                        <td><?php echo $rsp->nama_produk ?></td>
                                        <td><?php echo $rsp->resep ?></td>
                                    </tr>
                                <?php endforeach; ?>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="modal fade" id="modal-default">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Produk Terjual Hari Ini</h4>
                            </div>

                            <div class="modal-body">
                                <?php
                                if (isset($_GET['tanggal'])) {
                                    $tgl = $_GET['tanggal'];
                                    $sql = mysqli_query($koneksi, "SELECT * FROM tb_detail_transaksi WHERE tanggal = '$tgl'");
                                } else {
                                    $sql = mysqli_query($koneksi, "SELECT * FROM tb_detail_transaksi");
                                }
                                while ($data = mysqli_fetch_array($sql)) { ?>

                                    <p><?php echo $data['nama_produk']; ?> = <?php echo $data['jumlah']; ?></p>
                                <?php } ?>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <div class="ibox-title mt-4">
                    <h5>Laporan Stok Per Shift</h5>
                    <h5><?= $this->session->flashdata('success') ?></h5>
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
                    <form action="">
                        <div>
                            <h5>Pilih Produk</h5>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-3">
                                <select class="form-control m-b">
                                    <?php foreach ($np as $n) : ?>
                                        <option value="<?= $n->id_bahan ?>"><?= $n->nama_bahan ?></option>
                                    <?php endforeach; ?>
                                </select>
                            </div>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="jumlah">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<?php $this->load->view('partials/footer.php'); ?>
<?php $this->load->view('partials/js.php'); ?>