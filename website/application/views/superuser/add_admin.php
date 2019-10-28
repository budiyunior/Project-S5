<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('superuser/menu.php'); ?>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Tambah Admin</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="index.html">Login</a>
            </li>
            <li class="breadcrumb-item">
                <a>Admin</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Tambah Admin</strong>
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
                    <h5>Tambah Data Admin</h5>
                </div>
                <div class="ibox-content">
                    <form method="post" action="<?= base_url('Menu/saveadmin') ?>" enctype="multipart/form-data">
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Id Admin</label>
                            <div class="col-sm-10"><input type="text" name="id_pengguna" placeholder="Id Admin" class="form-control"></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Nama Lengkap</label>
                            <div class="col-sm-10"><input type="text" name="nama" placeholder="Nama Lengkap" class="form-control"></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Tanggal Lahir</label>
                            <div class="col-sm-10"><input type="date" name="tgl_lahir" placeholder="Tanggal Lahir" class="form-control"></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Alamat</label>
                            <div class="col-sm-10"><input type="text" name="alamat" placeholder="Alamat" class="form-control"></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">No Telepon</label>
                            <div class="col-sm-10">
                                <input type="text" name="no_telp" placeholder="No telepon" class="form-control">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10">
                                <input type="text" name="email" placeholder="Email" class="form-control">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-10">
                                <input type="password" name="password" placeholder="Password" class="form-control">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-white btn-sm" type="submit">Cancel</button>
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