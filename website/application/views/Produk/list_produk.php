<?php $this->load->view('partials/head.php'); ?>
<?php $this->load->view('partials/menu.php'); ?>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Data Produk</h2>
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<?= site_url('Menu') ?>">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">
                <strong>Data Produk</strong>
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
                    <h5>Data Produk</h5>
                    <h5><?= $this->session->flashdata('success') ?></h5>
                    <br/>
                    <a href="<?= site_url('Produk/simpan') ?>" class="btn btn-primary btn-sm">Tambah Produk</a>
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
                                    <th>Nama Makanan/Minuman</th>
                                    <th>Harga Satuan</th>
                                    <th>Gambar</th>
                                    <th>Keterangan</th>
                                    <th>Kategori</th>
                                    <th>Opsi</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php foreach ($produk as $produk) : ?>
                                    <tr class="gradeA">
                                        <td><?= $produk->nama_produk ?></td>
                                        <td><?= $produk->harga_satuan ?></td>
                                        <td><img src="<?php echo base_url('assets/img/foto_produk/' . $produk->gambar) ?>" width="64" /></td>
                                        <td><?= $produk->keterangan ?></td>
                                        <td><?= $produk->id_kategori ?></td>
                                        <td>
                                            <a href="<?php echo site_url('Produk/edit/' . $produk->id_produk) ?>" class="btn btn-small"><i class="fa fa-edit"></i> Edit</a> |
                                            <a onclick="deleteConfirm" href="<?php echo site_url('Produk/hapus/' . $produk->id_produk) ?>" class="btn btn-small text-danger"><i class="fa fa-trash"></i> Hapus</a>
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