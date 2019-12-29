<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Tambah Jumlah Bahan</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="index.html">Tambah Jumlah Bahan</a>
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
                    <h5>Tambah Jumlah Bahan</h5>
                </div>
                <div class="ibox-content">
                    <form method="post" action="<?= base_url('stok/savestok') ?>" enctype="multipart/form-data">
                        <div class="hr-line-dashed"></div>
                        <input type="hidden" name="id_bahan" value="<?php echo $bahan->id_bahan ?>" />
                        <input type="hidden" name="satuan" value="<?php echo $bahan->satuan ?>" />

                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Nama Bahan</label>
                            <div class="col-sm-10">
                                <input type="text" name="nama_bahan" placeholder="" id="nama_bahan" readonly value="<?php echo $bahan->nama_bahan ?>" class="form-control <?php echo form_error('nama_bahan') ? 'is-invalid' : '' ?>">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="form-group row" id="only-number">
                            <label class="col-sm-2 col-form-label">Tambah Jumlah Bahan</label>
                            <div class="col-sm-10">
                                <input type="text" id="jumlah" name="jumlah" placeholder="Jumlah Bahan Yang Ingin Ditambahkan" required class="form-control">
                                <?= form_error('jumlah', '<small class="text-danger pl-3">', '</small>') ?>
                            </div>

                            <div class="hr-line-dashed"></div>
                            <div class="form-group row">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <a class="btn btn-white btn-sm mb-1 mt-4" href="<?php echo site_url('stok/') ?>"><i>Cancel</i></a>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-4 mt-4 ml-2 col-sm-offset-2">
                                    <button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-save"> Tambah!</i></button>
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