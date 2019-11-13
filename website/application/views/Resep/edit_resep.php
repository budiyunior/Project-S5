<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Edit Resep</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="index.html">Dashboard</a>
            </li>
            <li class="breadcrumb-item">
                <a>Resep</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Edit Resep</strong>
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
                    <h5>Edit Data Resep</h5>
                </div>
                <div class="ibox-content">
                    <form method="post" action="<?= site_url('Produk/editresep') ?>" enctype="multipart/form-data">
                        <div class="form-group  row">
                            <div class="col-sm-10"><input type="text" name="id_resep" value="<?= $resep->id_resep ?>" placeholder="" hidden class="form-control"></div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Pilih Produk</label>
                            <div class="col-sm-10">
                                <select class="form-control m-b" name="id_produk">
                                    <?php foreach ($produk as $pro) : ?>
                                        <option value="<?= $pro->id_produk ?>"><?= $pro->nama_produk ?></option>
                                    <?php endforeach; ?>
                                </select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-md-2">Resep</label>
                            <div class="col-md-10"><textarea class="col-sm-12" name="resep" placeholder="Deskripsi"><?= $resep->resep ?></textarea></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <div class="col-sm-4 col-sm-offset-2">
                                <a class="btn btn-danger btn-sm" href="<?= site_url('Produk/resep') ?>">Cancel</a>
                                <button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-save"> Simpan Data</i></button>
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