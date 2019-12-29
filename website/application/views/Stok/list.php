<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Stok Bahan</h2>
        <?php if ($this->session->flashdata('success')) : ?>
            <div class="alert alert-success" role="alert">
                <?php echo $this->session->flashdata('success'); ?>
            </div>
        <?php endif; ?>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<?= site_url('Menu') ?>">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Data Stok Bahan</strong>
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
                    <h5>Data Stok Bahan</h5>
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
                                    <th>Nama Bahan</th>
                                    <th>Jumlah Bahan</th>
                                    <th>Satuan</th>
                                    <th>Keterangan</th>

                                    <th>Opsi</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php foreach ($bahan as $stok) : ?>
                                    <tr class="gradeA">

                                        <td><?php echo $stok->nama_bahan ?></td>
                                        <td><?php echo $stok->jumlah ?></td>
                                        <td><?php echo $stok->satuan ?></td>
                                        <td><?php echo $stok->Keterangan ?></td>

                                        <td>
                                            <a href="<?php echo site_url('stok/savestok/' . $stok->id_bahan) ?>" class="btn btn-small"><i class="fa fa-edit"></i> Tambah Jumlah Bahan</a>
                                            <a onclick="deleteConfirm('<?php echo site_url('stok/delete/' . $stok->id_bahan) ?>')" href="<?php echo site_url('stok/delete/' . $stok->id_bahan) ?>" class="btn btn-small text-danger"><i class="fa fa-trash"></i> Hapus</a>
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