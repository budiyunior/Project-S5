-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2019 at 10:51 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.0.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `custom_outfit`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` varchar(8) NOT NULL,
  `nama_barang` varchar(50) DEFAULT NULL,
  `ukuran` varchar(3) DEFAULT NULL,
  `harga` varchar(9) DEFAULT NULL,
  `id_jenis_barang` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `ukuran`, `harga`, `id_jenis_barang`) VALUES
('B0000001', 'Baju Olahraga SB', 'XL', 'Rp.300000', 'KA001'),
('C0000001', 'Celana Olahraga Sepak Bola', 'L', 'Rp.100000', 'CE001');

--
-- Triggers `barang`
--
DELIMITER $$
CREATE TRIGGER `trig_update_barang` AFTER UPDATE ON `barang` FOR EACH ROW BEGIN
	INSERT INTO history_barang
	VALUES (now() ,OLD.id_barang,OLD.ukuran,USER());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` varchar(8) DEFAULT NULL,
  `id_barang` varchar(8) DEFAULT NULL,
  `quantity` int(4) DEFAULT NULL,
  `subtotal` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hakakses`
--

CREATE TABLE `hakakses` (
  `id_hak_akses` int(11) NOT NULL,
  `nama_hak_akses` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `history_barang`
--

CREATE TABLE `history_barang` (
  `waktu` datetime DEFAULT NULL,
  `id_barang` varchar(8) DEFAULT NULL,
  `ukuran` varchar(3) DEFAULT NULL,
  `oleh` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `history_barang`
--

INSERT INTO `history_barang` (`waktu`, `id_barang`, `ukuran`, `oleh`) VALUES
('2019-05-03 12:23:39', 'B0000001', 'XL', 'root@local');

-- --------------------------------------------------------

--
-- Table structure for table `jenis_barang`
--

CREATE TABLE `jenis_barang` (
  `id_jenis_barang` varchar(5) NOT NULL,
  `nama_jenis_barang` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jenis_barang`
--

INSERT INTO `jenis_barang` (`id_jenis_barang`, `nama_jenis_barang`) VALUES
('CE001', 'Celana Olahraga'),
('KA001', 'Kaos Olahraga'),
('TO001', 'Topi');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` varchar(8) NOT NULL,
  `nama_pengguna` varchar(30) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `katasandi` varchar(30) DEFAULT NULL,
  `alamat` varchar(20) DEFAULT NULL,
  `kode_pos` varchar(5) DEFAULT NULL,
  `no_telp` varchar(13) DEFAULT NULL,
  `id_hak_akses` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(8) NOT NULL,
  `tgl_order` date DEFAULT NULL,
  `waktu_order` varchar(10) DEFAULT NULL,
  `tgl_ambil` date DEFAULT NULL,
  `grand_total` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `id_jenis_barang` (`id_jenis_barang`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `hakakses`
--
ALTER TABLE `hakakses`
  ADD PRIMARY KEY (`id_hak_akses`);

--
-- Indexes for table `history_barang`
--
ALTER TABLE `history_barang`
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `jenis_barang`
--
ALTER TABLE `jenis_barang`
  ADD PRIMARY KEY (`id_jenis_barang`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`),
  ADD KEY `id_hak_akses` (`id_hak_akses`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hakakses`
--
ALTER TABLE `hakakses`
  MODIFY `id_hak_akses` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`id_jenis_barang`) REFERENCES `jenis_barang` (`id_jenis_barang`);

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `history_barang`
--
ALTER TABLE `history_barang`
  ADD CONSTRAINT `history_barang_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD CONSTRAINT `pengguna_ibfk_1` FOREIGN KEY (`id_hak_akses`) REFERENCES `hakakses` (`id_hak_akses`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
