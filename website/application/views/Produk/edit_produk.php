<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Edit Produk</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="index.html">Dashboard</a>
            </li>
            <li class="breadcrumb-item">
                <a>Produk Kafe</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Edit Produk</strong>
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
                    <h5>Edit Data Produk</h5>
                </div>
                <div class="ibox-content">
                    <form method="post" action="<?= site_url('Produk/editproduk') ?>" enctype="multipart/form-data">
                        <div class="form-group  row">
                            <div class="col-sm-10"><input type="text" name="id_produk" value="<?= $produk->id_produk ?>" placeholder="" hidden class="form-control"></div>
                        </div>

                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Nama Makanan/Minuman</label>
                            <div class="col-sm-10"><input type="text" name="nama_produk" placeholder="Nama Makanan/Minuman" class="form-control" value="<?= $produk->nama_produk ?>"></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Harga Satuan</label>
                            <div class="col-sm-10">
                                <input type="text" name="harga_satuan" placeholder="Harga" class="form-control" value="<?= $produk->harga_satuan ?>">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Foto</label>
                            <div class="col-sm-8 ml-3">
                                <div class="custome-file" style="margin-left: 10px;">
                                    <input type="file" name="gambar" class="custom-file-input" value="<?= $produk->gambar ?>">
                                    <label class="custom-file-label"><?= $produk->gambar ?></label>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Keterangan</label>
                            <div class="col-sm-10">
                                <input type="text" name="keterangan" placeholder="Keterangan" class="form-control" value="<?= $produk->keterangan ?>">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Kategori Produk</label>
                            <div class="col-sm-10">
                                <select class="form-control m-b" <?php echo form_error('id_kategori') ? 'is-invalid' : '' ?> name="id_kategori" id="id_kategori" value="<?php echo $produk->id_kategori ?>">
                                <option value="">--Pilih Kategori--</option>
                                    <?php
                                    $servername ="localhost";
                                    $database ="cakwang";
                                    $username ="root";
                                    $password ="";
                                    $conn = mysqli_connect($servername, $username, $password, $database);
                                    $sql_akses = mysqli_query ($conn, "SELECT * FROM tb_kategori") or die (mysqli_error($conn));
                                    while($data_akses = mysqli_fetch_array($sql_akses)){
                                        echo '<option value="'.$data_akses['id_kategori'].'">'.$data_akses['kategori'].'</option>';
                                    }
                                ?></select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-white btn-sm" type="submit">Cancel</button>
                                <button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-save"> Update Data</i></button>
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