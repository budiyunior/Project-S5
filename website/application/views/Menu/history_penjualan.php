<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>
<?php
$koneksi =  mysqli_connect("localhost", "root", "", "cakwang");
?>


<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>History Penjualan</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<?= site_url('Menu') ?>">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>History Penjualan</strong>
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
                    <h5>History Penjualan</h5>
                    <br />
                    <h5><?= $this->session->flashdata('success') ?></h5>
                    <form method="get" action="<?= site_url('Menu/historypenjualan') ?>">
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
                                    <th>Nama Produk</th>
                                    <th>Tanggal</th>
                                    <th>Jumlah</th>
                                    <th>Harga</th>
                                    <th>Sub Total</th>
                                </tr>
                            </thead>
                            <tbody>
                            <?php
                                if (isset($_GET['tanggal'])) {
                                    $tgl = $_GET['tanggal'];
                                    $sql = mysqli_query($koneksi, "SELECT * FROM tb_detail_transaksi WHERE tanggal = '$tgl'");
                                } else {
                                    $sql = mysqli_query($koneksi, "SELECT * FROM tb_detail_transaksi");
                                }
                                while ($data = mysqli_fetch_array($sql)) { ?>       
                                    <tr class="gradeA">
                                        <td><?php echo $data['nama_produk'] ?></td>
                                        <td><?php echo $data['tanggal'] ?></td>
                                        <td><?php echo $data['jumlah'] ?></td>
                                        <td><?php echo $data['harga'] ?></td>
                                        <td><?php echo $data['sub_total'] ?></td>
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