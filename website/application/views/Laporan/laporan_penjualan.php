<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>
<?php
$koneksi =  mysqli_connect("localhost", "root", "", "cakwang");
$kb = 1;
$tanggal = mysqli_query($koneksi, "SELECT * FROM tb_transaksi");
?>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Laporan Penjualan</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="index.html">Dahboard</a>
            </li>
            <li class="breadcrumb-item">
                <a>Laporan</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Laporan Penjualan</strong>
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
                    <h5>Laporan Penjualan</h5>
                    <br />
                    <h5><?= $this->session->flashdata('success') ?></h5>
                    <form method="get">
                        <div class="form-group">
                            <label>Pilih Tanggal</label>
                            <?php
                            $tgl = date("Y-m-d");
                            ?>
                            <input type="date" name="tanggal" value="<?= $tgl ?>">

                            <label class="ml-2">Pilih Shift</label>
                            <select class="form-control-sm-8 m-b" name="shift">
                                <option value="1">Shift Pagi</option>
                                <option value="2">Shift Sore</option>
                            </select>
                            <input class="btn btn-primary ml-2" type="submit" value="pilih">
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
                                    <th>Nama Pelanggan</th>
                                    <th>No Meja</th>
                                    <th>Jam</th>
                                    <th>Tanggal</th>
                                    <th>Total Harga</th>
                                    <th>Shift</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php
                                    if (isset($_GET['tanggal'])) {
                                        $tgl = $_GET['tanggal'];
                                        if (isset($_GET['shift'])){
                                            $shift = $_GET['shift'];
                                        $sql = mysqli_query($koneksi, "SELECT * FROM tb_transaksi WHERE tanggal = '$tgl' AND shift = '$shift' "); 
                                    }
                                    // } else if (isset($_GET['shift'])){
                                    //     $shift = $_GET['shift'];
                                    //     $sql = mysqli_query($koneksi, "SELECT * FROM tb_transaksi WHERE shift = '$shift' ");
                                    } else {
                                        $sql = mysqli_query($koneksi, "SELECT * FROM tb_transaksi");
                                    }
                                    while ($lp = mysqli_fetch_array($sql)) {
                                ?>
                                    <tr class="gradeA">
                                        <td><?php echo $lp['nama_pelanggan'] ?></td>
                                        <td><?php echo $lp['no_meja'] ?></td>
                                        <td><?php echo $lp['jam'] ?></td>
                                        <td><?php echo $lp['tanggal'] ?></td>
                                        <td><?php echo $lp['total_harga'] ?></td>
                                        <td><?php echo $lp['shift'] ?></td>
                                    </tr>
                                <?php } ?>
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