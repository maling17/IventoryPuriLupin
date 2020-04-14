# Host: localhost  (Version 5.5.5-10.1.38-MariaDB)
# Date: 2020-03-24 19:40:00
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "barang"
#

DROP TABLE IF EXISTS `barang`;
CREATE TABLE `barang` (
  `id_brg` int(4) NOT NULL AUTO_INCREMENT,
  `jenis_brg` varchar(20) NOT NULL,
  `stok` int(4) NOT NULL,
  PRIMARY KEY (`id_brg`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for table "barang"
#

INSERT INTO `barang` VALUES (1,'whole lupin',500),(2,'split lupin',350),(3,'flake lupin',150);

#
# Structure for table "detil_pengolahan"
#

DROP TABLE IF EXISTS `detil_pengolahan`;
CREATE TABLE `detil_pengolahan` (
  `id_brg` int(4) NOT NULL DEFAULT '0',
  `id_pengolahan` int(4) DEFAULT NULL,
  `jumlah_olah` int(4) DEFAULT NULL,
  `tgl_pengolahan` date DEFAULT NULL,
  KEY `id_brg` (`id_brg`),
  KEY `id_pengolahan` (`id_pengolahan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "detil_pengolahan"
#

INSERT INTO `detil_pengolahan` VALUES (1,2,100,'2019-07-11'),(2,2,50,'2019-07-11'),(3,2,30,'2019-07-11'),(2,3,100,'2019-07-12'),(3,3,50,'2019-07-12'),(1,3,200,'2019-07-12'),(3,4,50,'2019-07-13'),(2,4,100,'2019-07-13'),(1,4,200,'2019-07-13'),(2,5,50,'2019-07-12'),(3,5,20,'2019-07-12'),(1,5,100,'2019-07-12'),(1,6,500,'2019-07-12'),(2,6,250,'2019-07-12'),(3,6,100,'2019-07-12'),(2,7,300,'2019-07-12'),(1,7,500,'2019-07-12'),(3,7,100,'2019-07-12');

#
# Structure for table "detil_permintaan"
#

DROP TABLE IF EXISTS `detil_permintaan`;
CREATE TABLE `detil_permintaan` (
  `id_permintaan` int(4) NOT NULL,
  `id_brg` int(4) DEFAULT NULL,
  `jumlah_minta` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "detil_permintaan"
#

INSERT INTO `detil_permintaan` VALUES (2,2,30),(2,3,20),(3,2,50),(3,3,30),(4,2,100),(4,3,50),(5,2,100),(5,3,50),(6,2,50),(6,3,20),(7,2,100),(7,3,200),(8,2,350),(8,3,0),(9,2,100),(9,3,50);

#
# Structure for table "mitra"
#

DROP TABLE IF EXISTS `mitra`;
CREATE TABLE `mitra` (
  `id_mitra` int(4) NOT NULL AUTO_INCREMENT,
  `daerah_mitra` varchar(50) NOT NULL,
  `pic_mitra` varchar(50) NOT NULL,
  `tlp_mitra` varchar(13) NOT NULL,
  `alamat_mitra` varchar(200) NOT NULL,
  PRIMARY KEY (`id_mitra`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

#
# Data for table "mitra"
#

INSERT INTO `mitra` VALUES (1,'Jakarta','Waha','090909','Deplu'),(3,'Banten','Atik','5835753','budi luhur'),(4,'Bali','Ariesta','3573585','Kuta'),(6,'Bintaro','Misel','12344','Graha'),(7,'Medan','Yuyu','14045','jl simorangkir'),(8,'Budi Luhur','Agus','0921','Pertukangan, Jakarta'),(9,'Amsia','Misel','0912','Jakarta,CIledug');

#
# Structure for table "penerimaan"
#

DROP TABLE IF EXISTS `penerimaan`;
CREATE TABLE `penerimaan` (
  `id_penerimaan` int(4) NOT NULL AUTO_INCREMENT,
  `tgl_penerimaan` date NOT NULL DEFAULT '0000-00-00',
  `no_po` varchar(5) NOT NULL,
  `qty_penerimaan` int(4) DEFAULT NULL,
  `id_brg` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_penerimaan`),
  KEY `id_brg` (`id_brg`),
  CONSTRAINT `penerimaan_ibfk_1` FOREIGN KEY (`id_brg`) REFERENCES `barang` (`id_brg`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

#
# Data for table "penerimaan"
#

INSERT INTO `penerimaan` VALUES (1,'2019-07-11','1234',100,1),(2,'2019-07-11','12345',50,1),(3,'2019-07-13','',0,1),(4,'2019-07-12','14045',250,1),(5,'2019-07-12','12345',100,1),(6,'2019-07-12','12345',700,1),(7,'2019-07-12','12345',500,1);

#
# Structure for table "pengolahan"
#

DROP TABLE IF EXISTS `pengolahan`;
CREATE TABLE `pengolahan` (
  `id_pengolahan` int(4) NOT NULL AUTO_INCREMENT,
  `tgl_pengolahan` date NOT NULL DEFAULT '0000-00-00',
  `w_awal` int(4) DEFAULT NULL,
  `s_awal` int(4) DEFAULT NULL,
  `f_awal` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_pengolahan`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

#
# Data for table "pengolahan"
#

INSERT INTO `pengolahan` VALUES (1,'0000-00-00',NULL,NULL,NULL),(2,'2019-07-11',500,250,150),(3,'2019-07-12',450,250,150),(4,'2019-07-13',500,250,150),(5,'2019-07-12',400,200,130),(6,'2019-07-12',1000,250,150),(7,'2019-07-12',1000,50,50);

#
# Structure for table "permintaan"
#

DROP TABLE IF EXISTS `permintaan`;
CREATE TABLE `permintaan` (
  `id_permintaan` int(4) NOT NULL AUTO_INCREMENT,
  `tgl_permintaan` date NOT NULL DEFAULT '0000-00-00',
  `tujuan` varchar(50) NOT NULL,
  `id_mitra` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_permintaan`),
  KEY `id_mitra` (`id_mitra`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

#
# Data for table "permintaan"
#

INSERT INTO `permintaan` VALUES (1,'0000-00-00','',NULL),(2,'2019-07-11','Bali',4),(3,'2019-07-11','Banten',3),(4,'2019-07-11','Medan',7),(5,'2019-07-12','Medan',7),(6,'2019-07-11','Medan',7),(8,'2019-07-11','Amsia',9),(9,'2019-07-12','Medan',7);

#
# Structure for table "surat_jalan"
#

DROP TABLE IF EXISTS `surat_jalan`;
CREATE TABLE `surat_jalan` (
  `id_sj` int(4) NOT NULL AUTO_INCREMENT,
  `tgl_sj` date DEFAULT NULL,
  `tujuan` varchar(50) DEFAULT NULL,
  `id_permintaan` int(4) DEFAULT NULL,
  `split_sj` int(4) DEFAULT NULL,
  `flake_sj` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_sj`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

#
# Data for table "surat_jalan"
#

INSERT INTO `surat_jalan` VALUES (2,'2019-07-11','Bali',2,30,20),(3,'2019-07-11','Banten',3,50,30),(4,'2019-07-11','Medan',4,100,50),(5,'2019-07-12','Medan',5,100,50),(6,'2019-07-12','Medan',6,50,20),(8,'2019-07-11','Amsia',8,350,0);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_user` int(4) NOT NULL AUTO_INCREMENT,
  `nm_user` varchar(50) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `password` varchar(10) NOT NULL,
  `tlp_user` varchar(13) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'waha',' manajer','1234','0987');
