<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Data Kepegawaian</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="index.html">Dahboard</a>
            </li>
            <li class="breadcrumb-item">
                <a>Kepegawaian</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Data Pegawai</strong>
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
                    <h5>Data Pegawai</h5>
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
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>Nama Lengkap</th>
                                    <th>Tanggal Lahir</th>
                                    <th>Alamat</th>
                                    <th>No telepon</th>
                                    <th>Email</th>
                                    <th>Opsi</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php foreach ($admin as $pegawai) : ?>
                                    <tr class="gradeA">

                                        <td><?php echo $pegawai->nama ?></td>
                                        <td><?php echo $pegawai->tgl_lahir ?></td>
                                        <td><?php echo $pegawai->alamat ?></td>
                                        <td><?php echo $pegawai->no_telp ?></td>
                                        <td><?php echo $pegawai->email ?></td>
                                        <td>
                                            <a href="<?php echo site_url('Pegawai/edit/' . $pegawai->id_pengguna) ?>" class="btn btn-small"><i class="fa fa-edit"></i> Edit</a> |
                                            <a onclick="deleteConfirm('<?php echo site_url('' . $pegawai->id_pengguna) ?>')" href="#!" class="btn btn-small text-danger"><i class="fa fa-trash"></i> Hapus</a>
                                        </td>
                                    </tr>
                                <?php endforeach; ?>
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