<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('Hakakses_Pegawai/menu.php'); ?>
<?php
$koneksi =  mysqli_connect("localhost", "root", "", "cakwang");
$kb = 1;
$tanggal = mysqli_query($koneksi, "SELECT * FROM tb_detail_transaksi");
$lama = 14;
$query = "DELETE FROM tb_historibahan
          WHERE DATEDIFF(CURDATE(), tanggal) > $lama";
$hasil = mysqli_query($koneksi, $query);
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
                    <form>
                        <input type="text" placeholder="masukkan angka" id="txt1" onkeyup="sum();" /> X
                        <input type="text" placeholder="masukkan angka" id="txt2" onkeyup="sum();" />=
                        <input type="text" id="txt3" />
                    </form>
                </div>
                <div class="ibox-content">
                    <form action="<?= site_url('Adminpegawai/stok') ?>" method="post" enctype="multipart/form-data">
                        <div class="form-group" id="only-number">
                            <div class="form-group">
                                <label for="nama_bahan">Pilih Bahan</label><br>
                                <select class="form-control col-sm-3 mr-auto" name="nama_bahan" id="nama_bahan" required>
                                    <option value="">--> PILIH BAHAN <--</option> <?php
                                                                                    $servername = "localhost";
                                                                                    $database = "cakwang";
                                                                                    $username = "root";
                                                                                    $password = "";
                                                                                    $conn = mysqli_connect($servername, $username, $password, $database);
                                                                                    $sql_bahan = mysqli_query($conn, "SELECT * FROM tb_bahan") or die(mysqli_error($conn));
                                                                                    while ($data_bahan = mysqli_fetch_array($sql_bahan)) {
                                                                                        echo '<option value="' . $data_bahan['nama_bahan'] . '">' . $data_bahan['nama_bahan'] . '</option>';
                                                                                    }
                                                                                    ?> </select> <div class="invalid-feedback">
                                            <?php echo form_error('nama_bahan') ?>
                            </div>
                        </div>


                        <!-- <div class="col-sm-0 mr-auto">
                            <input type="text" class="form-control" placeholder="Jumlah" required id="jumlah" name="jumlah">
                        </div> -->

                        <div class="col-sm-0 mr-auto">

                            <input type="text" class="form" required placeholder="  Jumlah Komposisi" id="jumlah1" onkeyup="sum();" /> x
                            <input type="text" class="form" required placeholder="  Jumlah Produk Terjual" id="jumlah2" onkeyup="sum();" /> =
                            <input type="text" class="form" readonly placeholder="  Jumlah Komposisi Total" id="jumlah" name="jumlah" />
                        </div>

                        <div class="col-sm-0 mt-3" style="margin-left: auto;">
                            <button class="btn btn-success" type="submit">Kirim</button>


                        </div>

                </div>

                </form>
            </div>
        </div>
    </div>
</div>
</div>


<?php $this->load->view('partials/footer.php'); ?>
<script>
    function sum() {
        var txtFirstNumberValue = document.getElementById('txt1').value;
        var txtSecondNumberValue = document.getElementById('txt2').value;
        var result = parseInt(txtFirstNumberValue) * parseInt(txtSecondNumberValue);
        if (!isNaN(result)) {
            document.getElementById('txt3').value = result;
        }
    }
</script>
<?php $this->load->view('partials/js.php'); ?>
<script>
    $(function() {
        $('#only-number').on('keydown', '#jumlah1', function(e) {
            -1 !== $
                .inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) || /65|67|86|88/
                .test(e.keyCode) && (!0 === e.ctrlKey || !0 === e.metaKey) ||
                35 <= e.keyCode && 40 >= e.keyCode || (e.shiftKey || 48 > e.keyCode || 57 < e.keyCode) &&
                (96 > e.keyCode || 105 < e.keyCode) && e.preventDefault()
        });
    })
</script>
<script>
    $(function() {
        $('#only-number').on('keydown', '#jumlah2', function(e) {
            -1 !== $
                .inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) || /65|67|86|88/
                .test(e.keyCode) && (!0 === e.ctrlKey || !0 === e.metaKey) ||
                35 <= e.keyCode && 40 >= e.keyCode || (e.shiftKey || 48 > e.keyCode || 57 < e.keyCode) &&
                (96 > e.keyCode || 105 < e.keyCode) && e.preventDefault()
        });
    })
</script>
<script>
    function sum() {
        var txtFirstNumberValue = document.getElementById('jumlah1').value;
        var txtSecondNumberValue = document.getElementById('jumlah2').value;
        var result = parseInt(txtFirstNumberValue) * parseInt(txtSecondNumberValue);
        if (!isNaN(result)) {
            document.getElementById('jumlah').value = result;
        }
    }
</script>