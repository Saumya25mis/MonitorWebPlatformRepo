# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.34)
# Database: monitordb3a
# Generation Time: 2015-09-07 05:30:38 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table staffProject
# ------------------------------------------------------------

DROP TABLE IF EXISTS `staffProject`;

CREATE TABLE `staffProject` (
  `staffProjectID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `staffID` int(10) unsigned NOT NULL,
  `projectID` int(10) unsigned NOT NULL,
  `dateAssigned` datetime NOT NULL,
  `activeFlag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`staffProjectID`),
  KEY `index2` (`staffID`),
  KEY `index3` (`projectID`),
  KEY `fxx1` (`staffID`),
  KEY `fxx2` (`projectID`),
  CONSTRAINT `fxx1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fxx2` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `staffProject` WRITE;
/*!40000 ALTER TABLE `staffProject` DISABLE KEYS */;

INSERT INTO `staffProject` (`staffProjectID`, `staffID`, `projectID`, `dateAssigned`, `activeFlag`)
VALUES
	(1,1,5,'2015-09-05 00:00:00',1),
	(2,1,6,'2015-09-05 00:00:00',1),
	(3,1,7,'2015-09-05 00:00:00',1),
	(4,1,8,'2015-09-05 00:00:00',1),
	(5,1,9,'2015-09-05 00:00:00',1),
	(6,1,10,'2015-09-05 00:00:00',1),
	(7,1,11,'2015-09-05 00:00:00',1),
	(8,1,12,'2015-09-05 00:00:00',1),
	(9,1,13,'2015-09-05 00:00:00',1),
	(10,1,14,'2015-09-05 00:00:00',1),
	(11,1,15,'2015-09-05 00:00:00',1),
	(12,1,16,'2015-09-05 00:00:00',1),
	(13,1,17,'2015-09-05 00:00:00',1),
	(14,1,18,'2015-09-05 00:00:00',1),
	(15,1,19,'2015-09-05 00:00:00',1),
	(16,1,20,'2015-09-05 00:00:00',1),
	(17,1,21,'2015-09-05 00:00:00',1),
	(18,1,22,'2015-09-05 00:00:00',1),
	(19,1,22,'2015-09-05 00:00:00',1),
	(20,1,23,'2015-09-05 00:00:00',1),
	(21,1,24,'2015-09-05 00:00:00',1),
	(22,1,25,'2015-09-05 00:00:00',1),
	(23,1,26,'2015-09-05 00:00:00',1),
	(24,1,27,'2015-09-05 00:00:00',1),
	(25,1,28,'2015-09-05 00:00:00',1),
	(26,1,29,'2015-09-05 00:00:00',1),
	(27,1,30,'2015-09-05 00:00:00',1);

/*!40000 ALTER TABLE `staffProject` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
