<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('Hakakses_Pegawai/menu.php'); ?>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Tambah Bahan</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="index.html"></a>
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
                    <h5>Tambah Bahan</h5>
                </div>
                <div class="ibox-content">
                    <form method="post" action="<?= base_url('Stok/simpanbahan') ?>" enctype="multipart/form-data">
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Nama Bahan</label>
                            <div class="col-sm-10">
                                <input type="text" id="nama_bahan" name="nama_bahan" placeholder="Nama Bahan" class="form-control" value="<?= set_value('nama_bahan'); ?>">
                                <?= form_error('nama_bahan', '<small class="text-danger pl-3">', '</small>') ?>
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>
                        <div class="form-group row" id="only-number">
                            <label class="col-sm-2 col-form-label">Jumlah Stok</label>
                            <div class="col-sm-10">
                                <input type="text" id="jumlah" name="jumlah" placeholder="Jumlah Stok" class="form-control" value="<?= set_value('jumlah'); ?>">
                                <?= form_error('jumlah', '<small class="text-danger pl-3">', '</small>') ?>
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Satuan</label>
                            <div class="col-sm-10">
                                <input type="text" id="jumlah" name="satuan" placeholder="Satuan" class="form-control" value="<?= set_value('jumlah'); ?>">
                                <?= form_error('satuan', '<small class="text-danger pl-3">', '</small>') ?>
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Keterangan</label>
                            <div class="col-sm-10">
                                <input type="text" id="keterangan" name="keterangan" placeholder="Keterangan" class="form-control" value="<?= set_value('keterangan'); ?>">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group row">
                            <div class="col-sm-4 ml-3 col-sm-offset-2">
                                <br>
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

<script>
    $(function() {
        $('#only-number').on('keydown', '#jumlah', function(e) {
            -1 !== $
                .inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) || /65|67|86|88/
                .test(e.keyCode) && (!0 === e.ctrlKey || !0 === e.metaKey) ||
                35 <= e.keyCode && 40 >= e.keyCode || (e.shiftKey || 48 > e.keyCode || 57 < e.keyCode) &&
                (96 > e.keyCode || 105 < e.keyCode) && e.preventDefault()
        });
    })
</script>