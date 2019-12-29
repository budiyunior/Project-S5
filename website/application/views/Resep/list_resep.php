<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Data Resep</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<?= site_url('Menu') ?>">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Data Resep</strong>
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
                    <br />
                    <h5><?= $this->session->flashdata('success') ?></h5>
                    <a href="<?= site_url('Produk/resep') ?>" class="btn btn-primary btn-sm">Tambah Resep</a>
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
                                    <th>Id Resep</th>
                                    <th>Nama Produk</th>
                                    <th>Resep</th>
                                    <th>Opsi</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php foreach ($resep as $rsp) : ?>
                                    <tr class="gradeA">
                                        <td><?php echo $rsp->id_resep ?></td>
                                        <td><?php echo $rsp->nama_produk ?></td>
                                        <td><?php echo $rsp->resep ?></td>
                                        <td>
                                            <a href="<?php echo site_url('Produk/editan/' . $rsp->id_resep) ?>" class="btn btn-small"><i class="fa fa-edit"></i> Edit</a> |
                                            <a onclick="deleteConfirm" href="<?php echo site_url('Produk/hapusresep/' . $rsp->id_resep) ?>" class="btn btn-small text-danger"><i class="fa fa-trash"></i> Hapus</a>
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