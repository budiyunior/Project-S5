<?php
$koneksi =  mysqli_connect("localhost", "root", "", "cakwang1");
$delete  = mysqli_query($koneksi, "DELETE FROM tb_transaksi WHERE id_transasksi= '".$_GET['id']."'");
if ($delete) {
	header ('location:laporan_penjualan.php');
}else{
	echo 'Gagal Hapus';
}
?>