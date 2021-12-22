-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 21, 2021 at 10:09 AM
-- Server version: 10.5.12-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id18053770_tugaspbbdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `absent`
--

CREATE TABLE `absent` (
  `id` int(11) NOT NULL,
  `id_akun` int(11) NOT NULL,
  `nama` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `posisi` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `tipe_ijin` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `jenis_ijin` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `alasan` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `ijin_mulai` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ijin_selesai` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `bukti_ijin` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `currentDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `id_attendance` int(11) NOT NULL,
  `id_akun` int(11) NOT NULL,
  `namaUser` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `jabatanUser` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `currentDateTime` datetime NOT NULL,
  `workFromHome` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `signature` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `kondisi` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `currentDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`id_attendance`, `id_akun`, `namaUser`, `jabatanUser`, `currentDateTime`, `workFromHome`, `status`, `photo`, `signature`, `kondisi`, `currentDate`) VALUES
(35, 1, 'Muhammad Arifiansyah', 'Mahasiswa', '2021-12-20 08:11:07', 'Ya', 'Datang Terlambat', 'myImage1', 'mySignature1', 'Datang', '2021-12-20'),
(36, 1, 'Muhammad Arifiansyah', 'Mahasiswa', '2021-12-20 08:11:23', 'Ya', 'Pulang Awal', 'myImage2', 'mySignature2', 'Pulang', '2021-12-20'),
(37, 1, 'Muhammad Arifiansyah', 'Mahasiswa', '2021-12-21 06:08:00', 'Ya', 'Datang Terlambat', 'myImage3', 'mySignature3', 'Datang', '2021-12-21'),
(38, 1, 'Muhammad Arifiansyah', 'Mahasiswa', '2021-12-21 06:15:26', 'Ya', 'Pulang Awal', 'myImage4', 'mySignature4', 'Pulang', '2021-12-21');

-- --------------------------------------------------------

--
-- Table structure for table `coba`
--

CREATE TABLE `coba` (
  `id_coba` int(11) NOT NULL,
  `cek` varchar(1000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `coba`
--

INSERT INTO `coba` (`id_coba`, `cek`) VALUES
(1, '1;Salman;Mahasiswa;09:43:02;2021-12-06;Ya;tepatwaktu;'),
(2, 'hai');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `image_id` int(11) NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `location` varchar(64) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medcheck`
--

CREATE TABLE `medcheck` (
  `id_medcheck` int(11) NOT NULL,
  `id_akun` int(11) NOT NULL,
  `Demam` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Batuk_Pilek` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Tenggorokan_Sakit` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Hidung_Tersumbat` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Nyeri_Kepala` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Sesak_Nafas` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Lemas` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Hilang_Penciuman` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Diare` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Kontak_ODP` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `Kondisi_Tubuh` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Kelayakan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `currentDate` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `medcheck`
--

INSERT INTO `medcheck` (`id_medcheck`, `id_akun`, `Demam`, `Batuk_Pilek`, `Tenggorokan_Sakit`, `Hidung_Tersumbat`, `Nyeri_Kepala`, `Sesak_Nafas`, `Lemas`, `Hilang_Penciuman`, `Diare`, `Kontak_ODP`, `Kondisi_Tubuh`, `Kelayakan`, `currentDate`) VALUES
(224, 1, 'Ya', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Ya', 'Sehat', 'Layak untuk mengikuti kegiatan offline hari ini.', '2021-12-19'),
(225, 1, 'Ya', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Ya', 'Sehat', 'Layak untuk mengikuti kegiatan offline hari ini.', '2021-12-20'),
(226, 1, 'Ya', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Tidak', 'Ya', 'Sehat', 'Layak untuk mengikuti kegiatan offline hari ini.', '2021-12-21');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `id` int(11) NOT NULL,
  `nama` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `posisi` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `profil_picture` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `nama`, `posisi`, `email`, `password`, `profil_picture`) VALUES
(1, 'Muhammad Arifiansyah', 'Mahasiswa', 'fiansyahmm@gmail.com', 'arif1234', 'myProfilImage1'),
(42, '', '', 'example@gmail.com', '', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `nama` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `datang_awal` time NOT NULL,
  `datang` time NOT NULL,
  `pulang` time NOT NULL,
  `pulang_akhir` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`id`, `nama`, `datang_awal`, `datang`, `pulang`, `pulang_akhir`) VALUES
(1, 'Reguler', '06:30:00', '07:30:00', '16:00:00', '19:00:00'),
(2, 'Jadwal Puasa', '07:30:00', '08:00:00', '15:00:00', '16:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absent`
--
ALTER TABLE `absent`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id_attendance`);

--
-- Indexes for table `coba`
--
ALTER TABLE `coba`
  ADD PRIMARY KEY (`id_coba`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`image_id`);

--
-- Indexes for table `medcheck`
--
ALTER TABLE `medcheck`
  ADD PRIMARY KEY (`id_medcheck`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absent`
--
ALTER TABLE `absent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `id_attendance` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `coba`
--
ALTER TABLE `coba`
  MODIFY `id_coba` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `image_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `medcheck`
--
ALTER TABLE `medcheck`
  MODIFY `id_medcheck` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=227;

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
