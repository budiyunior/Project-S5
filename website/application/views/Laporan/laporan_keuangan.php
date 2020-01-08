<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>
<?php
$koneksi =  mysqli_connect("localhost", "root", "", "cakwang");
$kb = 1;
$tanggal = mysqli_query($koneksi, "SELECT * FROM tb_transaksi");
?>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Laporan Keuangan</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<?= site_url('Menu') ?>">Dashboard</a>
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
                            <label class="ml-2">Pilih Shift</label>
                            <select class="form-control-sm-8 m-b" name="shift">
                                <option value="1">Shift Pagi</option>
                                <option value="2">Shift Sore</option>
                            </select>
                            <input class="btn btn-primary" type="submit" value="pilih">
                        </div>
                    </form>
                    <form method="get" action="<?= base_url('Laporan/laporankeuangan') ?>">
                        <div class="form-group">
                            <label>Pilih Bulan</label>
                            <?php
                            $bln = date("m");
                            ?>
                            <select class="form-control-sm-8 m-b" name="bulan">
                                <option value="01">Januari</option>
                                <option value="02">Februari</option>
                                <option value="03">Maret</option>
                                <option value="04">April</option>
                                <option value="05">Mei</option>
                                <option value="06">Juni</option>
                                <option value="07">Juli</option>
                                <option value="08">Agustus</option>
                                <option value="09">September</option>
                                <option value="10">Oktober</option>
                                <option value="11">November</option>
                                <option value="12">Desember</option>
                            </select>
                            <select class="form-control-sm-8 m-b" name="tahun">
                                <?php
                                $qry=mysqli_query($koneksi, "SELECT tanggal FROM tb_transaksi GROUP BY year(tanggal)");
                                while($t=mysqli_Fetch_array($qry)){
                                $data = explode('-',$t['tanggal']);
                                $tahun = $data[0];
                                echo "<option value='$tahun'>$tahun</option>";
                                }
                                ?>
                            </select>

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
                                    <th>Total Finansial Perbulan</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="gradeA">
                                    <td><?php echo $fh ?></td>
                                    <td><?php echo $fb ?></td>
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