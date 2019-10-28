<?php
include 'koneksi.php';
// menyimpan data id kedalam variabel
$id_mahasiswa   = $_GET['id_produk'];
// query SQL untuk insert data
$query="DELETE from produk where id_produk='$id_produk'";
mysqli_query($koneksi, $query);
// mengalihkan ke halaman index.php
header("location:index.php");
?>