# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.34)
# Database: monitordb3a
# Generation Time: 2015-06-24 11:13:16 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table chat
# ------------------------------------------------------------

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chatID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `staffID` int(10) unsigned DEFAULT NULL,
  `monitorID` int(10) unsigned DEFAULT NULL,
  `dateStarted` datetime NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `projectID` int(11) unsigned DEFAULT NULL,
  `chatName` varchar(100) DEFAULT NULL,
  `avatarNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`chatID`),
  KEY `companyStaffID` (`staffID`),
  KEY `dateStarted` (`dateStarted`),
  KEY `projectID` (`projectID`),
  KEY `index5` (`monitorID`),
  KEY `xds` (`monitorID`),
  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE,
  CONSTRAINT `chat_ibfk_2` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE,
  CONSTRAINT `xds` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table chatMember
# ------------------------------------------------------------

DROP TABLE IF EXISTS `chatMember`;

CREATE TABLE `chatMember` (
  `chatMemberID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `chatID` int(10) unsigned NOT NULL,
  `staffID` int(10) unsigned DEFAULT NULL,
  `monitorID` int(10) unsigned DEFAULT NULL,
  `dateJoined` datetime NOT NULL,
  PRIMARY KEY (`chatMemberID`),
  KEY `chatID` (`chatID`),
  KEY `companyStaffID` (`staffID`),
  KEY `index4` (`monitorID`),
  KEY `xzz` (`monitorID`),
  CONSTRAINT `chatmember_ibfk_1` FOREIGN KEY (`chatID`) REFERENCES `chat` (`chatID`) ON DELETE CASCADE,
  CONSTRAINT `chatmember_ibfk_2` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE,
  CONSTRAINT `xzz` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table chatMessage
# ------------------------------------------------------------

DROP TABLE IF EXISTS `chatMessage`;

CREATE TABLE `chatMessage` (
  `chatMessageID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `chatID` int(10) unsigned NOT NULL,
  `staffID` int(10) unsigned DEFAULT NULL,
  `message` text NOT NULL,
  `dateSent` datetime NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `pictureFileName` varchar(255) DEFAULT NULL,
  `monitorID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`chatMessageID`),
  KEY `chatID` (`chatID`),
  KEY `stf` (`staffID`),
  KEY `dateSent` (`dateSent`),
  KEY `index5` (`monitorID`),
  KEY `fk1009` (`monitorID`),
  CONSTRAINT `chatmessage_ibfk_1` FOREIGN KEY (`chatID`) REFERENCES `chat` (`chatID`) ON DELETE CASCADE,
  CONSTRAINT `chatmessage_ibfk_2` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE,
  CONSTRAINT `fk1009` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table company
# ------------------------------------------------------------

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `companyID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyName` varchar(255) NOT NULL,
  `address` text,
  `email` varchar(255) DEFAULT NULL,
  `cellphone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`companyID`),
  UNIQUE KEY `index3` (`companyName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;

INSERT INTO `company` (`companyID`, `companyName`, `address`, `email`, `cellphone`)
VALUES
	(30,'Gauteng Infrastructure','Johannesburg','info@gauteng.gov','099 999 9999');

/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table errorStore
# ------------------------------------------------------------

DROP TABLE IF EXISTS `errorStore`;

CREATE TABLE `errorStore` (
  `errorStoreID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `statusCode` int(11) DEFAULT NULL,
  `message` text,
  `dateOccured` datetime DEFAULT NULL,
  `origin` varchar(255) DEFAULT '',
  PRIMARY KEY (`errorStoreID`),
  KEY `dateOccured` (`dateOccured`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `errorStore` WRITE;
/*!40000 ALTER TABLE `errorStore` DISABLE KEYS */;

INSERT INTO `errorStore` (`errorStoreID`, `statusCode`, `message`, `dateOccured`, `origin`)
VALUES
	(1,0,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-05 20:59:06','Cloud Messaging Service'),
	(2,0,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-06 00:16:18','Cloud Messaging Service'),
	(3,0,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-06 00:21:04','Cloud Messaging Service'),
	(4,0,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-06 00:26:40','Cloud Messaging Service'),
	(5,0,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-06 02:28:14','Cloud Messaging Service'),
	(6,33,'#### No devices found to send messages to.','2015-06-06 12:27:25','GoogleCloudMessagingRegistrar'),
	(7,33,'#### No devices found to send messages to.','2015-06-06 12:39:06','GoogleCloudMessagingRegistrar'),
	(8,33,'#### No devices found to send messages to.','2015-06-06 12:39:40','GoogleCloudMessagingRegistrar'),
	(9,33,'#### No devices found to send messages to.','2015-06-06 12:39:58','GoogleCloudMessagingRegistrar'),
	(10,33,'#### No devices found to send messages to.','2015-06-06 12:52:00','GoogleCloudMessagingRegistrar'),
	(11,33,'#### No devices found to send messages to.','2015-06-06 12:52:07','GoogleCloudMessagingRegistrar'),
	(12,33,'#### No devices found to send messages to.','2015-06-06 12:52:39','GoogleCloudMessagingRegistrar'),
	(13,33,'#### No devices found to send messages to.','2015-06-06 12:53:00','GoogleCloudMessagingRegistrar'),
	(14,33,'#### No devices found to send messages to.','2015-06-06 12:55:53','GoogleCloudMessagingRegistrar'),
	(15,33,'#### No devices found to send messages to.','2015-06-06 12:58:10','GoogleCloudMessagingRegistrar'),
	(16,33,'#### No devices found to send messages to.','2015-06-06 12:59:35','GoogleCloudMessagingRegistrar'),
	(17,33,'#### No devices found to send messages to.','2015-06-06 12:59:45','GoogleCloudMessagingRegistrar'),
	(18,105,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-06 13:22:19','GoogleCloudMessagingRegistrar'),
	(19,105,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-06 15:28:59','GoogleCloudMessagingRegistrar'),
	(20,105,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-07 00:17:01','GoogleCloudMessagingRegistrar'),
	(21,105,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-07 01:31:32','GoogleCloudMessagingRegistrar'),
	(22,105,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-07 01:31:33','GoogleCloudMessagingRegistrar'),
	(23,105,'Google Cloud Messaging device registered to Google successfully\nThis device can now participate in push messaging','2015-06-07 01:31:59','GoogleCloudMessagingRegistrar');

/*!40000 ALTER TABLE `errorStore` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table errorStoreAndroid
# ------------------------------------------------------------

DROP TABLE IF EXISTS `errorStoreAndroid`;

CREATE TABLE `errorStoreAndroid` (
  `errorStoreAndroidID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned DEFAULT NULL,
  `staffID` int(10) unsigned DEFAULT NULL,
  `errorDate` datetime DEFAULT NULL,
  `packageName` varchar(150) DEFAULT '',
  `appVersionName` varchar(10) DEFAULT '',
  `appVersionCode` varchar(10) DEFAULT '',
  `brand` varchar(100) DEFAULT '',
  `phoneModel` varchar(100) DEFAULT '',
  `androidVersion` varchar(20) DEFAULT '',
  `stackTrace` text,
  `logCat` text,
  `monitorID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`errorStoreAndroidID`),
  KEY `companyID` (`companyID`),
  KEY `staffID` (`staffID`),
  KEY `monitorID` (`monitorID`),
  CONSTRAINT `errorstoreandroid_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE,
  CONSTRAINT `errorstoreandroid_ibfk_2` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE,
  CONSTRAINT `errorstoreandroid_ibfk_3` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table gcmDevice
# ------------------------------------------------------------

DROP TABLE IF EXISTS `gcmDevice`;

CREATE TABLE `gcmDevice` (
  `gcmDeviceID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `staffID` int(10) unsigned DEFAULT NULL,
  `registrationID` text NOT NULL,
  `companyID` int(10) unsigned DEFAULT NULL,
  `projectID` int(10) unsigned DEFAULT NULL,
  `monitorID` int(10) unsigned DEFAULT NULL,
  `manufacturer` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `product` varchar(100) DEFAULT NULL,
  `messageCount` int(10) unsigned DEFAULT NULL,
  `dateRegistered` datetime NOT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `androidVersion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`gcmDeviceID`),
  KEY `companyStaffID` (`staffID`),
  KEY `companyID` (`companyID`),
  KEY `projectSiteID` (`projectID`),
  KEY `index5` (`monitorID`),
  KEY `ghfhh` (`monitorID`),
  CONSTRAINT `gcmdevice_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE,
  CONSTRAINT `gcmdevice_ibfk_2` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE,
  CONSTRAINT `gcmdevice_ibfk_3` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE,
  CONSTRAINT `ghfhh` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `gcmDevice` WRITE;
/*!40000 ALTER TABLE `gcmDevice` DISABLE KEYS */;

INSERT INTO `gcmDevice` (`gcmDeviceID`, `staffID`, `registrationID`, `companyID`, `projectID`, `monitorID`, `manufacturer`, `model`, `product`, `messageCount`, `dateRegistered`, `serialNumber`, `androidVersion`)
VALUES
	(1,NULL,'APA91bHDIBK5m7PiDFTILJ2IFctLzBP-x5-Ki-UJLfmXb0KkVCXQLEOZNpwd5AIaU0oRf0t5sEmEPhhA6dZwaXD1WOQGtT7oTTIWRPLGfSgtvORAqEW3QpKG6Ev6F1P9g4p0S4iBPRQtlQ6O2WMjq8DdclngG8j2jg',30,NULL,83,'motorola','Nexus 6','shamu',0,'2015-06-05 21:04:27','ZX1G426WVX','5.1.1'),
	(2,NULL,'APA91bEOa1PRjLk4Kn5yVH2PWKPJVz4IrI4jjjPJsCash8bsDfFl5x1MZE09MI_2JQf27YcOr8sA-VuP0vNwjgIxqHaxwCR67E2lvsUlW60aNrkBykS9sE8uGDL-2qMhez-FUUqMjOnA2JWvZONo5KWqXWvWJfYSiA',30,NULL,83,'LGE','Nexus 5','hammerhead',0,'2015-06-06 00:27:49','072778f8281dab9b','5.1.1'),
	(3,NULL,'APA91bEzV6zP267Eq7CVzDaRE8BfwZGqS6VZiOP6PXBHNS48LUppchasJhS_KVnhgZN543wprSTn3q0zGCMi9fsPwVyD7HF4UY6ZjOCRbLTRsb9mcjuVPSgC1y0GCNAGVWV_ruAT0zKQjrMKObtdj7cgtbLWhjeE5Q',30,NULL,83,'LGE','Nexus 5','hammerhead',0,'2015-06-06 13:22:35','072778f8281dab9b','5.1.1'),
	(4,NULL,'APA91bGpuMy6p3mu7NfklNNZ2qqxphiGq12aAIX2XIACF1nT0cBbpqXUCxTEzJZlLb7reCw7Gc782P6W29QibxeAyC18gCx7mx6ZbDEAf5cju6s-XMQIJRBdtm9t1Q7RQXOGyoyyF6PfR_kuXX-BowyVATUDrqLboQ',30,NULL,83,'LGE','Nexus 5','hammerhead',0,'2015-06-06 15:29:09','072778f8281dab9b','5.1.1'),
	(5,NULL,'APA91bEgsDtPe3gRqw_KOPYq33v2lpkKLx4-mf8kqDMAt6BdE-iR6tAPZQk2-YwCvAJf12Y86Byiy0l6TSvM38rWrwRW4ZDs940J7xgvCHQ7HDTLPVWwzoYBLnvMDCZJYXBNhsFazB2nxsqmzCwuVARQU-Xor7uX7A',30,NULL,83,'LGE','Nexus 5','hammerhead',0,'2015-06-07 00:17:14','072778f8281dab9b','5.1.1'),
	(6,NULL,'APA91bEw2UjJ_w3vdG2tVwGdT8Hfs6bHmym3BuZhgBfhAiFk4Jqm5iFi9rEdyuwdC3jBmFRTV3BJRdrR3Knng94668I0tpkFERZKudzmz3Ah00Es5VOctU4cCD8DKd5mRQPiZhPiluL3usG-syGFAOj5PYfNiYHQFQ',30,NULL,83,'LGE','Nexus 5','hammerhead',0,'2015-06-07 01:32:21','072778f8281dab9b','5.1.1'),
	(7,NULL,'APA91bGFGsF9SsqyP5GfNvOlElwiNQ9ehGHGDeEGWc68jh4K2WpEkDH7gadsGsPHsNvfdHLpKf-YZ1wYJwfw7TDkuH5VpwLuEkJcm0EHqyVbkGFmJyLKo9JLtSAPowjDKQa36FYg_-I1KwKxT-zqh9zd-57klVFmHQ',30,NULL,83,'LGE','Nexus 5',NULL,0,'2015-06-09 04:03:17','072778f8281dab9b','5.1.1'),
	(8,NULL,'APA91bEFyenCVVjdDZUd8fzGy7WeJIBOuTDDkHOStJl3MBu_YM6ZYvMM-In63ggm8B28Ej9ER-_aTwNN5aL3BMThfoa20Mj8mXyMR1sHuRbfbMQ7gc1IYkRjBx1xzCx51k4rBz1iZI3q2QS-hqaoLW2Ww2WMJIBnCQ',30,NULL,83,'LGE','Nexus 5',NULL,0,'2015-06-10 05:55:22','072778f8281dab9b','5.1.1');

/*!40000 ALTER TABLE `gcmDevice` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table locationTracker
# ------------------------------------------------------------

DROP TABLE IF EXISTS `locationTracker`;

CREATE TABLE `locationTracker` (
  `locationTrackerID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `staffID` int(10) unsigned DEFAULT NULL,
  `dateTracked` datetime NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `accuracy` float NOT NULL,
  `geocodedAddress` text,
  `dateAdded` datetime DEFAULT NULL,
  `dateTrackedLong` bigint(20) DEFAULT NULL,
  `monitorID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`locationTrackerID`),
  KEY `companyStaffID` (`staffID`),
  KEY `dateTracked` (`dateTracked`),
  KEY `monitorID` (`monitorID`),
  CONSTRAINT `locationtracker_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE,
  CONSTRAINT `locationtracker_ibfk_2` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `locationTracker` WRITE;
/*!40000 ALTER TABLE `locationTracker` DISABLE KEYS */;

INSERT INTO `locationTracker` (`locationTrackerID`, `staffID`, `dateTracked`, `latitude`, `longitude`, `accuracy`, `geocodedAddress`, `dateAdded`, `dateTrackedLong`, `monitorID`)
VALUES
	(1,NULL,'2015-06-10 06:44:49',-25.7605803,27.8526213,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 06:44:51',1433911491082,NULL),
	(2,NULL,'2015-06-10 06:47:54',-25.7605845,27.8526204,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 06:47:56',1433911676060,NULL),
	(3,NULL,'2015-06-10 06:58:47',-25.760582,27.8526208,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 07:02:07',1433912527954,NULL),
	(4,NULL,'2015-06-10 07:02:07',-25.7605822,27.8526174,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 07:02:08',1433912528204,NULL),
	(5,NULL,'2015-06-10 07:16:54',-25.7605804,27.8526217,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 07:16:56',1433913416705,NULL),
	(6,NULL,'2015-06-10 07:18:46',-25.7605835,27.8526218,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 07:18:47',1433913527562,NULL),
	(7,NULL,'2015-06-10 07:34:02',-25.7605809,27.8526219,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 07:34:03',1433914443691,NULL),
	(8,NULL,'2015-06-10 07:48:52',-25.7605805,27.8526216,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 07:48:53',1433915333395,NULL),
	(9,NULL,'2015-06-10 08:03:40',-25.7605814,27.8526171,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 08:03:41',1433916221904,NULL),
	(10,NULL,'2015-06-10 08:15:30',-25.7605836,27.8526189,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 08:15:32',1433916932442,NULL),
	(11,NULL,'2015-06-10 08:27:39',-25.7605807,27.8526193,11.547,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 08:27:40',1433917660508,NULL),
	(12,NULL,'2015-06-10 08:43:26',-25.7605793,27.852618,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 08:43:27',1433918607548,NULL),
	(13,NULL,'2015-06-10 08:58:11',-25.7605839,27.8526208,11.547,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 08:58:12',1433919492700,NULL),
	(14,NULL,'2015-06-10 09:12:56',-25.7605795,27.8526221,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 09:12:58',1433920378711,NULL),
	(15,NULL,'2015-06-10 09:20:24',-25.7605805,27.8526228,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 09:20:26',1433920826506,NULL),
	(16,NULL,'2015-06-10 09:24:52',-25.7605836,27.8526187,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 09:24:54',1433921094364,NULL),
	(17,NULL,'2015-06-10 09:38:50',-25.7605816,27.8526204,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 09:38:51',1433921931966,NULL),
	(18,NULL,'2015-06-10 09:53:48',-25.7605836,27.8526173,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-10 09:53:49',1433922829077,NULL),
	(19,NULL,'2015-06-11 09:41:26',-25.7605792,27.852618,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-11 09:56:20',1434009380167,NULL),
	(20,NULL,'2015-06-11 09:56:18',-25.7605202,27.8525541,10,'28 Kingfisher Dr, Hartbeespoort, 0216','2015-06-11 09:56:20',1434009380660,NULL),
	(21,NULL,'2015-06-11 09:57:41',-25.7605796,27.852618,14.142,'30 Kingfisher Dr, Hartbeespoort, 0216','2015-06-11 09:57:43',1434009463295,NULL);

/*!40000 ALTER TABLE `locationTracker` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table monitor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `monitor`;

CREATE TABLE `monitor` (
  `monitorID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `cellphone` varchar(50) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `activeFlag` int(2) DEFAULT NULL,
  `pin` varchar(10) DEFAULT NULL,
  `appInvitationDate` datetime DEFAULT NULL,
  `dateRegistered` datetime NOT NULL,
  `IDNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`monitorID`),
  UNIQUE KEY `index2` (`companyID`,`email`),
  KEY `index3` (`companyID`),
  KEY `fk003` (`companyID`),
  CONSTRAINT `fk0030` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `monitor` WRITE;
/*!40000 ALTER TABLE `monitor` DISABLE KEYS */;

INSERT INTO `monitor` (`monitorID`, `companyID`, `firstName`, `lastName`, `cellphone`, `email`, `activeFlag`, `pin`, `appInvitationDate`, `dateRegistered`, `IDNumber`)
VALUES
	(82,30,'St. Vincent','Malenga','072 312 7738','malengadev@gmail.com',1,'1234',NULL,'2015-05-31 06:51:15','93737373789'),
	(83,30,'Aubrey','Malabie','071 219 3543','aubrey@mlab.co.za',1,'1234',NULL,'2015-06-05 00:00:00','87936497646');

/*!40000 ALTER TABLE `monitor` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table monitorProject
# ------------------------------------------------------------

DROP TABLE IF EXISTS `monitorProject`;

CREATE TABLE `monitorProject` (
  `monitorProjectID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `monitorID` int(10) unsigned NOT NULL,
  `projectID` int(10) unsigned NOT NULL,
  `dateAssigned` datetime NOT NULL,
  `activeFlag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`monitorProjectID`),
  KEY `index2` (`monitorID`),
  KEY `index3` (`projectID`),
  KEY `fcc1` (`monitorID`),
  KEY `fcc2` (`projectID`),
  CONSTRAINT `fcc1` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fcc2` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `monitorProject` WRITE;
/*!40000 ALTER TABLE `monitorProject` DISABLE KEYS */;

INSERT INTO `monitorProject` (`monitorProjectID`, `monitorID`, `projectID`, `dateAssigned`, `activeFlag`)
VALUES
	(1,82,7874,'2015-05-31 00:00:00',NULL),
	(2,82,7875,'2015-05-31 00:00:00',NULL),
	(3,82,7876,'2015-05-31 00:00:00',NULL),
	(4,82,7877,'2015-05-31 00:00:00',NULL),
	(5,82,7878,'2015-05-31 00:00:00',NULL),
	(6,82,7879,'2015-05-31 00:00:00',NULL),
	(7,82,7880,'2015-05-31 00:00:00',NULL),
	(8,83,7874,'2015-05-31 00:00:00',NULL),
	(9,83,7875,'2015-05-31 00:00:00',NULL),
	(10,83,7876,'2015-05-31 00:00:00',NULL),
	(11,83,7877,'2015-05-31 00:00:00',NULL),
	(12,83,7878,'2015-05-31 00:00:00',NULL),
	(13,83,7879,'2015-05-31 00:00:00',NULL),
	(14,83,7880,'2015-05-31 00:00:00',NULL);

/*!40000 ALTER TABLE `monitorProject` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table photoUpload
# ------------------------------------------------------------

DROP TABLE IF EXISTS `photoUpload`;

CREATE TABLE `photoUpload` (
  `photoUploadID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned NOT NULL,
  `projectID` int(10) unsigned DEFAULT NULL,
  `staffID` int(10) unsigned DEFAULT NULL,
  `projectTaskID` int(10) unsigned DEFAULT NULL,
  `monitorID` int(10) unsigned DEFAULT NULL,
  `pictureType` int(11) NOT NULL,
  `dateTaken` datetime NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `accuracy` float DEFAULT NULL,
  `uri` varchar(400) DEFAULT NULL,
  `thumbFlag` int(11) DEFAULT NULL,
  `dateUploaded` datetime DEFAULT NULL,
  `thumbFilePath` varchar(255) DEFAULT NULL,
  `staffPicture` int(11) DEFAULT NULL,
  `secureUrl` varchar(400) DEFAULT NULL,
  `eTag` varchar(400) DEFAULT NULL,
  `signature` varchar(400) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `bytes` int(11) DEFAULT NULL,
  PRIMARY KEY (`photoUploadID`),
  KEY `companyID` (`companyID`),
  KEY `projectID` (`projectID`),
  KEY `projectSiteTaskID` (`projectTaskID`),
  KEY `companyStaffID` (`staffID`),
  KEY `monitorID` (`monitorID`),
  CONSTRAINT `photoupload_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `photoupload_ibfk_5` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE,
  CONSTRAINT `photoupload_ibfk_6` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE,
  CONSTRAINT `photoupload_ibfk_7` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE,
  CONSTRAINT `photoupload_ibfk_8` FOREIGN KEY (`projectTaskID`) REFERENCES `projectTask` (`projectTaskID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `photoUpload` WRITE;
/*!40000 ALTER TABLE `photoUpload` DISABLE KEYS */;

INSERT INTO `photoUpload` (`photoUploadID`, `companyID`, `projectID`, `staffID`, `projectTaskID`, `monitorID`, `pictureType`, `dateTaken`, `latitude`, `longitude`, `accuracy`, `uri`, `thumbFlag`, `dateUploaded`, `thumbFilePath`, `staffPicture`, `secureUrl`, `eTag`, `signature`, `width`, `height`, `bytes`)
VALUES
	(1,30,7874,NULL,5,NULL,2,'2015-06-09 04:07:44',-25.7605822,27.8526194,14.142,'http://res.cloudinary.com/bohatmx/image/upload/v1433846253/reilsinwwpdiu2yjtxs6.jpg',1,'2015-06-09 12:37:33',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433846253/reilsinwwpdiu2yjtxs6.jpg','836bd1a5748196d52debb900df587bcc','cedeef82331918bc65fe0911e9834fcd58cac84f',734,979,72755),
	(2,30,7874,NULL,5,NULL,2,'2015-06-09 04:08:03',-25.7605819,27.8526211,10,'http://res.cloudinary.com/bohatmx/image/upload/v1433846257/b145ccqteuyuwu6bvdoo.jpg',1,'2015-06-09 12:37:37',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433846257/b145ccqteuyuwu6bvdoo.jpg','f8d8f55f416bb3645467fd5104869aee','1416e6072a731f4a2c31d872795e07c20e146780',734,979,132728),
	(3,30,7874,NULL,5,NULL,2,'2015-06-09 04:08:22',-25.7605783,27.8526194,10,'http://res.cloudinary.com/bohatmx/image/upload/v1433846263/tubfon31a5hcjbkqnzh3.jpg',1,'2015-06-09 12:37:44',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433846263/tubfon31a5hcjbkqnzh3.jpg','23f92a76f6b6cea8334a07fa7879fa62','be0753f94cd2e45269c3daa48409dff87348105a',734,979,282361),
	(4,30,7874,NULL,2,NULL,2,'2015-06-09 12:42:13',-25.7605839,27.8526194,14.142,'http://res.cloudinary.com/bohatmx/image/upload/v1433847440/iiocuchmvc0tedvovg9e.jpg',1,'2015-06-09 12:57:24',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433847440/iiocuchmvc0tedvovg9e.jpg','2c6276f8747de163d78364391b8d54a8','47132e2a38ce93cebb6573b6ea1c516f29961e9e',734,979,136784),
	(5,30,7874,NULL,2,NULL,2,'2015-06-09 12:42:42',-25.7605159,27.8524769,96,'http://res.cloudinary.com/bohatmx/image/upload/v1433847449/v203hlzeaqpzaucdljbh.jpg',1,'2015-06-09 12:57:30',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433847449/v203hlzeaqpzaucdljbh.jpg','b6368916799a9a6a29532baf45709920','e98433d26e6977998af16d9b69bea653e4e91f80',734,979,118617),
	(6,30,7874,NULL,2,NULL,2,'2015-06-09 12:42:56',-25.7605825,27.8526187,20,'http://res.cloudinary.com/bohatmx/image/upload/v1433847454/hf1yuva7ixcdgqeqq1g4.jpg',1,'2015-06-09 12:57:34',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433847454/hf1yuva7ixcdgqeqq1g4.jpg','7db8ad627be877a99bf910eae7741819','75a8e640252f02a0a8361a81147a805da0c03406',734,979,124754),
	(7,30,7874,NULL,5,NULL,2,'2015-06-09 12:58:10',-25.7605795,27.8526173,14.142,'http://res.cloudinary.com/bohatmx/image/upload/v1433847497/ky6xjxf6p6a4jokbjwem.jpg',1,'2015-06-09 12:58:18',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433847497/ky6xjxf6p6a4jokbjwem.jpg','5b114c61a4d1f311d06dfba1f87ef78b','7ee724c1301352c14d27443e7bda4c918728c240',734,979,154385),
	(8,30,7874,NULL,5,NULL,2,'2015-06-09 13:02:02',-25.7605838,27.8526255,20,'http://res.cloudinary.com/bohatmx/image/upload/v1433847725/a7kxutrgoaykbmjej4vg.jpg',1,'2015-06-09 13:02:06',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433847725/a7kxutrgoaykbmjej4vg.jpg','beede84aa9b2c3212d4222a2220dd7e7','393f87aef4c0afc91c8ea7e34be09e896beed74e',734,979,129205),
	(9,30,7874,NULL,6,NULL,2,'2015-06-09 14:07:19',-25.7605294,27.8524675,30,'http://res.cloudinary.com/bohatmx/image/upload/v1433851643/akt5gjezwfmaou283rbv.jpg',1,'2015-06-09 14:07:23',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433851643/akt5gjezwfmaou283rbv.jpg','66915820fd70324bcb8e8629edfd27a9','d9c43241122e873f86422f0f338f6da71a9e272f',734,979,114886),
	(10,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:19',-25.7605788,27.8526008,12.362,'http://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg',1,'2015-06-09 18:03:13',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg','aeea609a0101c9235550d1264da4f638','a669acf15373dbaa7720f40eb97fc0af8e940f22',734,979,123610),
	(11,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:19',-25.7605788,27.8526008,12.362,'http://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg',1,'2015-06-09 18:03:13',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg','aeea609a0101c9235550d1264da4f638','a669acf15373dbaa7720f40eb97fc0af8e940f22',734,979,123610),
	(12,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:19',-25.7605788,27.8526008,12.362,'http://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg',1,'2015-06-09 18:03:13',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg','aeea609a0101c9235550d1264da4f638','a669acf15373dbaa7720f40eb97fc0af8e940f22',734,979,123610),
	(13,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:19',-25.7605788,27.8526008,12.362,'http://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg',1,'2015-06-09 18:03:13',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg','aeea609a0101c9235550d1264da4f638','a669acf15373dbaa7720f40eb97fc0af8e940f22',734,979,123610),
	(14,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:19',-25.7605788,27.8526008,12.362,'http://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg',1,'2015-06-09 18:03:13',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865793/ftwidzbywlxmjg7e3kcx.jpg','aeea609a0101c9235550d1264da4f638','a669acf15373dbaa7720f40eb97fc0af8e940f22',734,979,123610),
	(15,30,7874,NULL,9,NULL,2,'2015-06-09 17:57:58',-25.7605422,27.8527638,6,'http://res.cloudinary.com/bohatmx/image/upload/v1433865879/zs0hhqrqzgdq8v2shiiu.jpg',1,'2015-06-09 18:04:39',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865879/zs0hhqrqzgdq8v2shiiu.jpg','cb4d3f9b944644c7ee443184ae7ecc88','5904c0cf34d237e0af18e2efbd089f05343774cb',734,979,64359),
	(16,30,7874,NULL,3,NULL,2,'2015-06-09 16:58:37',-25.762756,27.8523943,9,'http://res.cloudinary.com/bohatmx/image/upload/v1433865882/nkdsxycmat3buhc1vmcz.jpg',1,'2015-06-09 18:04:42',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865882/nkdsxycmat3buhc1vmcz.jpg','ace32bde1c31779bdece1819bfeccd73','b6c3ae80348f26eaec8e6ac9a9ed5fa790ff03a6',979,734,75670),
	(17,30,7874,NULL,3,NULL,2,'2015-06-09 17:59:05',-25.7605823,27.8526206,16.263,'http://res.cloudinary.com/bohatmx/image/upload/v1433865891/ajjdilj30a66zkodca6y.jpg',1,'2015-06-09 18:04:51',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865891/ajjdilj30a66zkodca6y.jpg','f0a117e3a083010f6e38df0802f294c9','765fdd3658c1bab611b726a9322da1e42a22ac78',734,979,110984),
	(18,30,7874,NULL,3,NULL,2,'2015-06-09 16:59:12',-25.7632732,27.8523313,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865898/xwhard6iixhi1xkvsugs.jpg',1,'2015-06-09 18:04:57',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865898/xwhard6iixhi1xkvsugs.jpg','4096511c14f682420a76a310cf69ad2d','5db6e9c7dee85418d93c9b8d169d4cedefbbda70',979,734,116071),
	(19,30,7874,NULL,3,NULL,2,'2015-06-09 17:59:52',-25.7606094,27.8526365,4,'http://res.cloudinary.com/bohatmx/image/upload/v1433865900/uwrv4vj0nueeezecwe1z.jpg',1,'2015-06-09 18:05:00',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865900/uwrv4vj0nueeezecwe1z.jpg','0a9e03f4067263dc4ce1376fab749537','7c298f9a1a221ccb608e1c6b5796ef4fc794c175',734,979,147918),
	(20,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:31',-25.7622007,27.85253,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865900/i3uoq31lvjpfrcfzlece.jpg',1,'2015-06-09 18:05:00',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865900/i3uoq31lvjpfrcfzlece.jpg','7ad6ecc9fb7bd827b140d6527913b861','fb71a5e512e97f2522b88cc805ee4966516262ea',979,734,177408),
	(21,30,7874,NULL,3,NULL,2,'2015-06-09 16:58:07',-25.7625454,27.8521913,7,'http://res.cloudinary.com/bohatmx/image/upload/v1433865902/crvokhgbprqne3gw8jrk.jpg',1,'2015-06-09 18:05:02',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865902/crvokhgbprqne3gw8jrk.jpg','07f97107777dfa1cc6cc54f77d9660b8','c6f2f9aec32025a08fcf1d9b2498a84e1cafdbd4',734,979,265869),
	(22,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:42',-25.7622406,27.8525285,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865902/atdu7d6z9ns9uvcaprum.jpg',1,'2015-06-09 18:05:02',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865902/atdu7d6z9ns9uvcaprum.jpg','fb43f498df86bb6f4d0b5a7ce0ed54dc','14384a8aa28ed451ce6dce1e019cbbe9b23c4618',979,734,198170),
	(23,30,7874,NULL,3,NULL,2,'2015-06-09 16:57:12',-25.7622519,27.8524936,14,'http://res.cloudinary.com/bohatmx/image/upload/v1433865903/worloocaausasi8jpvfa.jpg',1,'2015-06-09 18:05:03',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865903/worloocaausasi8jpvfa.jpg','e99efebe27e140c5e17ce698506ac769','b0924809ddf3f4eb3f117545e5cdf41ba2cf2f7c',979,734,204209),
	(24,30,7874,NULL,3,NULL,2,'2015-06-09 18:00:11',-25.7605806,27.8525679,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865908/mj3phgwqlbloyx2dfxrx.jpg',1,'2015-06-09 18:05:08',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865908/mj3phgwqlbloyx2dfxrx.jpg','fac421c7de7dc90248d28f160389dee6','d8a68c23d8e333d77ce110fe185b28f67b6db480',734,979,162087),
	(25,30,7874,NULL,3,NULL,2,'2015-06-09 18:00:29',-25.7605603,27.8525033,15,'http://res.cloudinary.com/bohatmx/image/upload/v1433865911/lrexukhrfh245rpvcnqk.jpg',1,'2015-06-09 18:05:10',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865911/lrexukhrfh245rpvcnqk.jpg','4a557e96567a50354f962b4e4e83860d','befd495315ea1bd83800fc658b432ef40075c5c6',734,979,137834),
	(26,30,7874,NULL,3,NULL,2,'2015-06-09 18:00:44',-25.7605652,27.852489,7,'http://res.cloudinary.com/bohatmx/image/upload/v1433865912/p55wxfe2s716asmqejlq.jpg',1,'2015-06-09 18:05:12',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865912/p55wxfe2s716asmqejlq.jpg','6a9cb47df47005ef301fb7f53ccd1006','3f0a443b5677dc6f0206b9026320be00158a4217',734,979,173969),
	(27,30,7874,NULL,5,NULL,2,'2015-06-09 18:03:05',-25.7605978,27.8525064,9,'http://res.cloudinary.com/bohatmx/image/upload/v1433865914/n73yhkxjtymczb4en1oh.jpg',1,'2015-06-09 18:05:14',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865914/n73yhkxjtymczb4en1oh.jpg','eae9ea2b5194d3bc93a7574f5fd73ddb','67d1da553b7b5904c2fd1ff0d104e2261c5a9ef7',734,979,152821),
	(28,30,7874,NULL,5,NULL,2,'2015-06-09 18:02:45',-25.7605841,27.8525044,24,'http://res.cloudinary.com/bohatmx/image/upload/v1433865914/ene0gqrez6lz38avkys1.jpg',1,'2015-06-09 18:05:14',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865914/ene0gqrez6lz38avkys1.jpg','06d5f382b69da76be484c6672ef4de0b','7f778c1c7b71ef82fdccd2af1ff0f47262f3cd54',734,979,180193),
	(29,30,7874,NULL,5,NULL,2,'2015-06-09 18:02:54',-25.7605915,27.8525049,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865917/lmjchc07qpec7qec3lnh.jpg',1,'2015-06-09 18:05:17',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865917/lmjchc07qpec7qec3lnh.jpg','61c5dcd0941ece253f0551275a53048e','65ad64d6b6c4e4aa485d2217a70d995bd4e7d468',734,979,170990),
	(30,30,7874,NULL,5,NULL,2,'2015-06-09 18:02:54',-25.7605915,27.8525049,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865919/dy8it0m7rqxfnzduboz9.jpg',1,'2015-06-09 18:05:19',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865919/dy8it0m7rqxfnzduboz9.jpg','61c5dcd0941ece253f0551275a53048e','b41c3cdd3c5fe56d7d02015f86201d59b4367ef5',734,979,170990),
	(31,30,7874,NULL,3,NULL,2,'2015-06-09 18:00:44',-25.7605652,27.852489,7,'http://res.cloudinary.com/bohatmx/image/upload/v1433865923/vc13e5zcc1q2fvfqygom.jpg',1,'2015-06-09 18:05:23',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865923/vc13e5zcc1q2fvfqygom.jpg','6a9cb47df47005ef301fb7f53ccd1006','fb75866d144a45c90caca2a2674dc57a82422b44',734,979,173969),
	(32,30,7874,NULL,3,NULL,2,'2015-06-09 18:00:29',-25.7605603,27.8525033,15,'http://res.cloudinary.com/bohatmx/image/upload/v1433865928/o3imwpl2q8w23qttfo7u.jpg',1,'2015-06-09 18:05:27',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865928/o3imwpl2q8w23qttfo7u.jpg','4a557e96567a50354f962b4e4e83860d','b5dd7d9c39a94937965691d0111f70e3dd62c477',734,979,137834),
	(33,30,7874,NULL,5,NULL,2,'2015-06-09 18:02:45',-25.7605841,27.8525044,24,'http://res.cloudinary.com/bohatmx/image/upload/v1433865928/nbvw5xjpuhcmvyymqjdr.jpg',1,'2015-06-09 18:05:27',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865928/nbvw5xjpuhcmvyymqjdr.jpg','06d5f382b69da76be484c6672ef4de0b','091f006d085841771582b3fd7f906be636a6b5fc',734,979,180193),
	(34,30,7874,NULL,3,NULL,2,'2015-06-09 18:00:11',-25.7605806,27.8525679,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865933/fszpbwpc1kmfjtvoqvsd.jpg',1,'2015-06-09 18:05:32',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865933/fszpbwpc1kmfjtvoqvsd.jpg','fac421c7de7dc90248d28f160389dee6','6d9bbf4b7dc70a099412233cf1d078e615b0d1d7',734,979,162087),
	(35,30,7874,NULL,3,NULL,2,'2015-06-09 17:59:52',-25.7606094,27.8526365,4,'http://res.cloudinary.com/bohatmx/image/upload/v1433865933/bpztl7plal4ipvuaashd.jpg',1,'2015-06-09 18:05:33',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865933/bpztl7plal4ipvuaashd.jpg','0a9e03f4067263dc4ce1376fab749537','f2c3fd9b6d047cbfc318955a17d819d8f5d12b27',734,979,147918),
	(36,30,7874,NULL,3,NULL,2,'2015-06-09 16:59:12',-25.7632732,27.8523313,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865937/c0czw5ellesatmkasptx.jpg',1,'2015-06-09 18:05:37',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865937/c0czw5ellesatmkasptx.jpg','4096511c14f682420a76a310cf69ad2d','8692d64c3514a885990ea5d49eca1bfbedc6d94f',979,734,116071),
	(37,30,7874,NULL,3,NULL,2,'2015-06-09 17:59:05',-25.7605823,27.8526206,16.263,'http://res.cloudinary.com/bohatmx/image/upload/v1433865937/ua7eowmat65reuxprklj.jpg',1,'2015-06-09 18:05:37',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865937/ua7eowmat65reuxprklj.jpg','f0a117e3a083010f6e38df0802f294c9','a11b1027261917dac619f963e9aae0b86c72794d',734,979,110984),
	(38,30,7874,NULL,3,NULL,2,'2015-06-09 16:58:37',-25.762756,27.8523943,9,'http://res.cloudinary.com/bohatmx/image/upload/v1433865940/i1f5pumqtqx0vhmk335o.jpg',1,'2015-06-09 18:05:40',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865940/i1f5pumqtqx0vhmk335o.jpg','ace32bde1c31779bdece1819bfeccd73','95a0fede9ee920ac4c447e192c3e3f65de8c559c',979,734,75670),
	(39,30,7874,NULL,3,NULL,2,'2015-06-09 16:58:07',-25.7625454,27.8521913,7,'http://res.cloudinary.com/bohatmx/image/upload/v1433865946/vvvmajdhnq82nw69b87a.jpg',1,'2015-06-09 18:05:45',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865946/vvvmajdhnq82nw69b87a.jpg','07f97107777dfa1cc6cc54f77d9660b8','1fe712a7ab94ebe4fad46cc6872b341629b08a56',734,979,265869),
	(40,30,7874,NULL,3,NULL,2,'2015-06-09 16:57:12',-25.7622519,27.8524936,14,'http://res.cloudinary.com/bohatmx/image/upload/v1433865950/tc7tbday2pqwf2wf5p5v.jpg',1,'2015-06-09 18:05:49',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865950/tc7tbday2pqwf2wf5p5v.jpg','e99efebe27e140c5e17ce698506ac769','a87f81fb13701a04196d7e0c7587c3f27715a201',979,734,204209),
	(41,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:42',-25.7622406,27.8525285,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865950/el0usow0fmabled3naip.jpg',1,'2015-06-09 18:05:50',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865950/el0usow0fmabled3naip.jpg','fb43f498df86bb6f4d0b5a7ce0ed54dc','afdfdeb6ebc6ed022b06c38ed253dd087d45fbb7',979,734,198170),
	(42,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:31',-25.7622007,27.85253,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433865954/meqapsopivwe4pfzlip1.jpg',1,'2015-06-09 18:05:54',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865954/meqapsopivwe4pfzlip1.jpg','7ad6ecc9fb7bd827b140d6527913b861','da2dbbcf7a83f749948c6d84f56909d48875daad',979,734,177408),
	(43,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:19',-25.7605788,27.8526008,12.362,'http://res.cloudinary.com/bohatmx/image/upload/v1433865956/pqvbqo8lngy3c6w3eph3.jpg',1,'2015-06-09 18:05:56',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433865956/pqvbqo8lngy3c6w3eph3.jpg','aeea609a0101c9235550d1264da4f638','3a943671d19fb1a65b151f86f680c4e48f732193',734,979,123610),
	(44,30,7874,NULL,5,NULL,2,'2015-06-09 18:02:54',-25.7605915,27.8525049,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433866033/sitkziajw9xs3alitpq8.jpg',1,'2015-06-09 18:07:13',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866033/sitkziajw9xs3alitpq8.jpg','61c5dcd0941ece253f0551275a53048e','e07bd20c4564add84205c5154a283be19d2dba28',734,979,170990),
	(45,30,7874,NULL,5,NULL,2,'2015-06-09 18:02:45',-25.7605841,27.8525044,24,'http://res.cloudinary.com/bohatmx/image/upload/v1433866038/iz146c7kgl7vurlolgjo.jpg',1,'2015-06-09 18:07:18',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866038/iz146c7kgl7vurlolgjo.jpg','06d5f382b69da76be484c6672ef4de0b','5dad99a21420c5e9d30485fb814380eb13561356',734,979,180193),
	(46,30,7874,NULL,3,NULL,2,'2015-06-09 18:00:11',-25.7605806,27.8525679,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433866042/fjibmedj3ynzuwx7e76h.jpg',1,'2015-06-09 18:07:22',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866042/fjibmedj3ynzuwx7e76h.jpg','fac421c7de7dc90248d28f160389dee6','42cace313d49c57576946b0a555074fe910ca417',734,979,162087),
	(47,30,7874,NULL,3,NULL,2,'2015-06-09 17:59:52',-25.7606094,27.8526365,4,'http://res.cloudinary.com/bohatmx/image/upload/v1433866046/qqwwrkss7kbauzscqq4r.jpg',1,'2015-06-09 18:07:25',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866046/qqwwrkss7kbauzscqq4r.jpg','0a9e03f4067263dc4ce1376fab749537','1c30f5e868b5a1beef2c4f3f8ddf88c659bf3ca4',734,979,147918),
	(48,30,7874,NULL,3,NULL,2,'2015-06-09 17:59:05',-25.7605823,27.8526206,16.263,'http://res.cloudinary.com/bohatmx/image/upload/v1433866049/iyexs4vp9wbkmu9lrwrv.jpg',1,'2015-06-09 18:07:28',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866049/iyexs4vp9wbkmu9lrwrv.jpg','f0a117e3a083010f6e38df0802f294c9','6eeb4cbce7e4164bb1d2d1fd04c8c472f716550f',734,979,110984),
	(49,30,7874,NULL,3,NULL,2,'2015-06-09 16:58:37',-25.762756,27.8523943,9,'http://res.cloudinary.com/bohatmx/image/upload/v1433866051/gvcrqv7vnycanfjxluyq.jpg',1,'2015-06-09 18:07:31',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866051/gvcrqv7vnycanfjxluyq.jpg','ace32bde1c31779bdece1819bfeccd73','457e9bd3736c0e1fc1ab834007ec70a2ae2ba253',979,734,75670),
	(50,30,7874,NULL,3,NULL,2,'2015-06-09 16:58:07',-25.7625454,27.8521913,7,'http://res.cloudinary.com/bohatmx/image/upload/v1433866057/u8qssrwh4giwtljuhqes.jpg',1,'2015-06-09 18:07:36',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866057/u8qssrwh4giwtljuhqes.jpg','07f97107777dfa1cc6cc54f77d9660b8','f5ba7da96b03d7f7e9b252a4898fb9d11d40bb82',734,979,265869),
	(51,30,7874,NULL,3,NULL,2,'2015-06-09 16:57:12',-25.7622519,27.8524936,14,'http://res.cloudinary.com/bohatmx/image/upload/v1433866061/udlh9aomsn2zxdbtywgg.jpg',1,'2015-06-09 18:07:40',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866061/udlh9aomsn2zxdbtywgg.jpg','e99efebe27e140c5e17ce698506ac769','f3de5c3b1b463f12f6af8129568c5f0341bafaa3',979,734,204209),
	(52,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:42',-25.7622406,27.8525285,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433866067/gcwgfi5kgjbmnvxkfsfb.jpg',1,'2015-06-09 18:07:47',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866067/gcwgfi5kgjbmnvxkfsfb.jpg','fb43f498df86bb6f4d0b5a7ce0ed54dc','ade29d1cc07ff8cac95e32d508be647ef1c577fa',979,734,198170),
	(53,30,7874,NULL,3,NULL,2,'2015-06-09 16:56:31',-25.7622007,27.85253,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433866071/gz3dhehckcxd4g0h01zt.jpg',1,'2015-06-09 18:07:51',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433866071/gz3dhehckcxd4g0h01zt.jpg','7ad6ecc9fb7bd827b140d6527913b861','d071422f9c0c7baf8368d8ed27092bf8a2be4711',979,734,177408),
	(54,30,7874,NULL,5,NULL,2,'2015-06-09 19:47:24',-25.7606149,27.8527816,96,'http://res.cloudinary.com/bohatmx/image/upload/v1433872051/n5gytg7j9pagp9jzawcw.jpg',1,'2015-06-09 19:47:30',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433872051/n5gytg7j9pagp9jzawcw.jpg','0e9b533ec000965eb86b06472fa66303','9874e8ad08869be879df71500de4f0976100b978',734,979,151193),
	(55,30,7875,NULL,12,NULL,2,'2015-06-10 07:04:38',-25.7606166,27.8525195,96,'http://res.cloudinary.com/bohatmx/image/upload/v1433912683/ditxs5lhbcdz9nfhh54u.jpg',1,'2015-06-10 07:04:43',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912683/ditxs5lhbcdz9nfhh54u.jpg','3d9d8c0a827bc2e30e0eb5e8bf44f317','5b18dc680acd50e30119e718e54417599d3b8c66',734,979,118146),
	(56,30,7875,NULL,12,NULL,2,'2015-06-10 07:06:04',-25.760565,27.8526105,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433912768/ybucalgqonmdp5ojh6rq.jpg',1,'2015-06-10 07:06:08',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912768/ybucalgqonmdp5ojh6rq.jpg','557e4ef10c01aabdb2c7a748b251dcd9','4fb75f4bdfb42fe28728c0faceb047ceeb65f429',734,979,117358),
	(57,30,7875,NULL,12,NULL,2,'2015-06-10 07:06:15',-25.7605829,27.8526211,14.798,'http://res.cloudinary.com/bohatmx/image/upload/v1433912778/admkhvupcxxoy1dqmjzo.jpg',1,'2015-06-10 07:06:17',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912778/admkhvupcxxoy1dqmjzo.jpg','de5cbd58804bf0214fe2d59b281b1183','1607ff3b5c3e6e0e4d5da6c66cca5d8d4e5ac246',979,734,99866),
	(58,30,7875,NULL,12,NULL,2,'2015-06-10 07:06:40',-25.760582,27.8526201,10.102,'http://res.cloudinary.com/bohatmx/image/upload/v1433912806/cgusqyzxd1ydx9nhnze7.jpg',1,'2015-06-10 07:06:46',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912806/cgusqyzxd1ydx9nhnze7.jpg','92903ac1c34ac9676493aa9ea86a8950','c1c8f77f26c62045b3f6ee2b23691e2c357da558',734,979,215315),
	(59,30,7875,NULL,12,NULL,2,'2015-06-10 07:06:51',-25.7605158,27.8526573,39.416,'http://res.cloudinary.com/bohatmx/image/upload/v1433912817/x5ipuai9jllcy98ehhxf.jpg',1,'2015-06-10 07:06:57',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912817/x5ipuai9jllcy98ehhxf.jpg','ddd36ec574dea0341533dbacf9fc41fe','048997c2ca69c89abd8483b485089bceddcae515',979,734,209368),
	(60,30,7875,NULL,12,NULL,2,'2015-06-10 07:07:00',-25.7605157,27.8526542,27.988,'http://res.cloudinary.com/bohatmx/image/upload/v1433912823/tqbwvannamfta5nndan5.jpg',1,'2015-06-10 07:07:03',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912823/tqbwvannamfta5nndan5.jpg','f897636f5ba570cbf9edb3267ca940d7','f524f6ec2437bfcb318225ffd0bc9597b7a867d3',734,979,137793),
	(61,30,7875,NULL,12,NULL,2,'2015-06-10 07:07:10',-25.7605839,27.8526445,8,'http://res.cloudinary.com/bohatmx/image/upload/v1433912835/zdtmif6lb0wvrn4taxza.jpg',1,'2015-06-10 07:07:15',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912835/zdtmif6lb0wvrn4taxza.jpg','98d8e37fefa5ba166ab32ccb92da34a7','29875bc5b210d9d8e2bd811d6a226a3e236fc123',979,734,212132),
	(62,30,7875,NULL,13,NULL,2,'2015-06-10 07:07:39',-25.7605913,27.8526283,3.9,'http://res.cloudinary.com/bohatmx/image/upload/v1433912864/dnjtv5sentyg6ihrzmz5.jpg',1,'2015-06-10 07:07:43',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912864/dnjtv5sentyg6ihrzmz5.jpg','0a2314c856ec8ce166889919fc1a1a13','0896bf2f3a4ee5e1c1654585eef519539ba1e43f',734,979,154656),
	(63,30,7875,NULL,13,NULL,2,'2015-06-10 07:08:02',-25.7605857,27.8526625,9,'http://res.cloudinary.com/bohatmx/image/upload/v1433912886/nybyv85zewzbtxugmgwo.jpg',1,'2015-06-10 07:08:05',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912886/nybyv85zewzbtxugmgwo.jpg','900cc7b99b95679e961717f23da5bd8b','4c3ddb68a4863fac0a0e24a332257d118f79ff70',734,979,115079),
	(64,30,7875,NULL,13,NULL,2,'2015-06-10 07:08:31',-25.7605634,27.8526294,10,'http://res.cloudinary.com/bohatmx/image/upload/v1433912915/yu0bep2ozgxxgvjgpkap.jpg',1,'2015-06-10 07:08:35',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433912915/yu0bep2ozgxxgvjgpkap.jpg','e3527cc79cb9b94c8d537fd6d9f5f8ab','38e9aa854725005136aef8cc8c6b63a50d5ea443',734,979,123782),
	(65,30,7875,NULL,14,NULL,2,'2015-06-10 07:19:48',-25.76058,27.8526217,11.547,'http://res.cloudinary.com/bohatmx/image/upload/v1433913593/re3ulubnoutgpsiwnwez.jpg',1,'2015-06-10 07:19:53',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433913593/re3ulubnoutgpsiwnwez.jpg','f5416bff025b6c133a26a7c6a60f6736','4fe96f7eb973e524fea7ac0b3a337ce07b2740ec',734,979,116414),
	(66,30,7876,NULL,21,NULL,2,'2015-06-10 08:34:59',-25.7605815,27.8526203,11.547,'http://res.cloudinary.com/bohatmx/image/upload/v1433918105/uryqgmpbnyjeuul7xfxv.jpg',1,'2015-06-10 08:35:04',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433918105/uryqgmpbnyjeuul7xfxv.jpg','18410a371492e6c47daecc624efdd64e','43327ed9386085e5b73f89e8732b76d21f2ba2c4',979,734,148068),
	(67,30,7876,NULL,21,NULL,2,'2015-06-10 08:35:16',-25.7605823,27.85262,10,'http://res.cloudinary.com/bohatmx/image/upload/v1433918120/cohwlptqoioqynxmcm4b.jpg',1,'2015-06-10 08:35:20',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433918120/cohwlptqoioqynxmcm4b.jpg','37178afd76175ad6aff0a3989db73d9d','4db51c3f27db14ded69d45905d79b9209416926d',734,979,117356),
	(68,30,7874,NULL,2,NULL,2,'2015-06-10 08:36:19',-25.7605796,27.852618,10.319,'http://res.cloudinary.com/bohatmx/image/upload/v1433918182/kecnwyrqbz45bpxeo3fp.jpg',1,'2015-06-10 08:36:22',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433918182/kecnwyrqbz45bpxeo3fp.jpg','7401e0118cd44c8d1a3630ee1a2037a7','4bdc83d0697cd549aa4ccba7054f6953f84b6000',734,979,131636),
	(69,30,7874,NULL,1,NULL,2,'2015-06-10 09:27:32',-25.7605648,27.8526408,24,'http://res.cloudinary.com/bohatmx/image/upload/v1433921257/ngxtqro92grrmwnecycc.jpg',1,'2015-06-10 09:27:37',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433921257/ngxtqro92grrmwnecycc.jpg','aaaa231fd86765888d861680b1b028d7','e77f93102d429ee880714c6982357f12d2357dde',734,979,136085),
	(70,30,7874,NULL,1,NULL,2,'2015-06-10 09:28:17',-25.7605441,27.8526424,26,'http://res.cloudinary.com/bohatmx/image/upload/v1433921301/lhdwkm96wv48olsoqxw6.jpg',1,'2015-06-10 09:28:21',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433921301/lhdwkm96wv48olsoqxw6.jpg','7b32580450a1a9bff2adb7ceae5cd091','df352545da6f01acacc92bafa513183af18fc22c',734,979,177068),
	(71,30,7874,NULL,1,NULL,2,'2015-06-10 09:28:51',-25.7605218,27.8526298,13,'http://res.cloudinary.com/bohatmx/image/upload/v1433921334/uf0nnvbi46svttzwriy3.jpg',1,'2015-06-10 09:28:54',NULL,NULL,'https://res.cloudinary.com/bohatmx/image/upload/v1433921334/uf0nnvbi46svttzwriy3.jpg','baf7f4640f4ddec534748bab03b6c18e','de6194272a8b1be1b4e4587210069f23927d5a7d',734,979,151303);

/*!40000 ALTER TABLE `photoUpload` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table portfolio
# ------------------------------------------------------------

DROP TABLE IF EXISTS `portfolio`;

CREATE TABLE `portfolio` (
  `portfolioID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned NOT NULL,
  `portfolioName` varchar(255) NOT NULL,
  `dateRegistered` datetime NOT NULL,
  PRIMARY KEY (`portfolioID`),
  KEY `index2` (`companyID`),
  KEY `fkr` (`companyID`),
  CONSTRAINT `fkr` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `portfolio` WRITE;
/*!40000 ALTER TABLE `portfolio` DISABLE KEYS */;

INSERT INTO `portfolio` (`portfolioID`, `companyID`, `portfolioName`, `dateRegistered`)
VALUES
	(1,30,'Thsepo 500 000 Portfolio','2015-05-31 00:00:00');

/*!40000 ALTER TABLE `portfolio` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table programme
# ------------------------------------------------------------

DROP TABLE IF EXISTS `programme`;

CREATE TABLE `programme` (
  `programmeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `programmeName` varchar(255) NOT NULL,
  `description` text,
  `dateRegistered` datetime NOT NULL,
  `portfolioID` int(10) unsigned NOT NULL,
  `completeFlag` int(11) DEFAULT NULL,
  PRIMARY KEY (`programmeID`),
  KEY `fk005` (`portfolioID`),
  KEY `portfolioID` (`portfolioID`),
  CONSTRAINT `programme_ibfk_1` FOREIGN KEY (`portfolioID`) REFERENCES `portfolio` (`portfolioID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `programme` WRITE;
/*!40000 ALTER TABLE `programme` DISABLE KEYS */;

INSERT INTO `programme` (`programmeID`, `programmeName`, `description`, `dateRegistered`, `portfolioID`, `completeFlag`)
VALUES
	(63,'School Construction Programme',NULL,'2015-05-31 00:00:00',1,NULL),
	(66,'Community Centre Construction Programme',NULL,'2015-05-31 00:00:00',1,NULL),
	(67,'Health Centre Construction Programme',NULL,'2015-05-31 00:00:00',1,NULL),
	(68,'Road Maintenance Programme',NULL,'2015-06-01 00:00:00',1,NULL);

/*!40000 ALTER TABLE `programme` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `projectID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned NOT NULL,
  `programmeID` int(10) unsigned DEFAULT NULL,
  `projectName` varchar(255) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `accuracy` float DEFAULT NULL,
  `activeFlag` tinyint(1) DEFAULT NULL,
  `locationConfirmed` tinyint(1) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `description` text,
  `dateRegistered` datetime NOT NULL,
  PRIMARY KEY (`projectID`),
  UNIQUE KEY `index2` (`companyID`,`projectName`),
  KEY `fk006` (`companyID`),
  KEY `index4` (`companyID`),
  KEY `index5` (`programmeID`),
  KEY `fk007` (`companyID`),
  CONSTRAINT `fk007` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`programmeID`) REFERENCES `programme` (`programmeID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`projectID`, `companyID`, `programmeID`, `projectName`, `latitude`, `longitude`, `accuracy`, `activeFlag`, `locationConfirmed`, `address`, `description`, `dateRegistered`)
VALUES
	(7874,30,63,'Naledi Technology School',-26.23926715428153,27.869326807558537,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7875,30,63,'Diepkloof School of the Arts',-26.259062378692565,27.954092994332314,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7876,30,63,'Jabavu Technology School',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7877,30,63,'Chiawelo Culinary Arts School',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7878,30,63,'Gauteng Electronics College',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7879,30,63,'Midrand College of Technology',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7880,30,63,'Tshwane 3D Printing Academy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7881,30,66,'Mamelodi East Community Centre',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7882,30,66,'Thembisa West Community Centre',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7883,30,66,'Orange Farm Community Centre',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7884,30,66,'Atteridgeville Community Centre',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7885,30,66,'Orlando East Community Centre',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7886,30,66,'Orlando West Community Centre',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7887,30,66,'Dube Community Centre',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7888,30,66,'Spruitview Community Centre',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table projectStatus
# ------------------------------------------------------------

DROP TABLE IF EXISTS `projectStatus`;

CREATE TABLE `projectStatus` (
  `projectStatusID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectID` int(10) unsigned NOT NULL,
  `projectStatusTypeID` int(10) unsigned NOT NULL,
  `statusDate` datetime DEFAULT NULL,
  `dateUpdated` datetime NOT NULL,
  `staffID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`projectStatusID`),
  KEY `index2` (`projectID`),
  KEY `index3` (`projectStatusTypeID`),
  KEY `fk202` (`projectID`),
  KEY `fk203` (`projectStatusTypeID`),
  KEY `companyStaffID` (`staffID`),
  CONSTRAINT `fk2020` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk2030` FOREIGN KEY (`projectStatusTypeID`) REFERENCES `projectStatusType` (`projectStatusTypeID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `projectsitetaskstatus_ibfk_10` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table projectStatusType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `projectStatusType`;

CREATE TABLE `projectStatusType` (
  `projectStatusTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned NOT NULL,
  `projectStatusName` varchar(100) NOT NULL,
  `statusColor` tinyint(4) unsigned DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectStatusTypeID`),
  UNIQUE KEY `companyID_2` (`companyID`,`projectStatusName`),
  KEY `companyID` (`companyID`),
  CONSTRAINT `taskstatus_ibfk_10` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `projectStatusType` WRITE;
/*!40000 ALTER TABLE `projectStatusType` DISABLE KEYS */;

INSERT INTO `projectStatusType` (`projectStatusTypeID`, `companyID`, `projectStatusName`, `statusColor`, `rating`)
VALUES
	(68,30,'Completed',3,1000),
	(69,30,'Not Started Yet',2,-10),
	(70,30,'Delayed - Materials',1,-10),
	(71,30,'Delayed - Weather',2,-10),
	(72,30,'Delayed - Staff',2,-10),
	(73,30,'Delayed - Strike',1,-20),
	(74,30,'Abandonded',1,-50),
	(75,30,'Cancelled',1,-50),
	(76,30,'Behind Schedule',2,-20),
	(77,30,'Ahead Of Schedule',3,100),
	(78,30,'Within Budget',3,100),
	(79,30,'Over Budget',1,-100),
	(80,30,'Awaiting HandOver',3,100),
	(81,30,'On Schedule',3,20);

/*!40000 ALTER TABLE `projectStatusType` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table projectTask
# ------------------------------------------------------------

DROP TABLE IF EXISTS `projectTask`;

CREATE TABLE `projectTask` (
  `projectTaskID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectID` int(10) unsigned NOT NULL,
  `taskID` int(10) unsigned NOT NULL,
  `dateRegistered` datetime NOT NULL,
  PRIMARY KEY (`projectTaskID`),
  UNIQUE KEY `projectID` (`projectID`,`taskID`),
  KEY `fk0012` (`projectID`),
  KEY `taskID` (`taskID`),
  CONSTRAINT `fk0012` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `projectsitetask_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `projectTask` WRITE;
/*!40000 ALTER TABLE `projectTask` DISABLE KEYS */;

INSERT INTO `projectTask` (`projectTaskID`, `projectID`, `taskID`, `dateRegistered`)
VALUES
	(1,7874,73,'2015-05-31 00:00:00'),
	(2,7874,74,'2015-05-31 00:00:00'),
	(3,7874,75,'2015-05-31 00:00:00'),
	(5,7874,76,'2015-05-31 00:00:00'),
	(6,7874,77,'2015-05-31 00:00:00'),
	(7,7874,78,'2015-05-31 00:00:00'),
	(8,7874,79,'2015-05-31 00:00:00'),
	(9,7874,80,'2015-05-31 00:00:00'),
	(12,7875,73,'2015-05-31 00:00:00'),
	(13,7875,74,'2015-05-31 00:00:00'),
	(14,7875,75,'2015-05-31 00:00:00'),
	(16,7875,76,'2015-05-31 00:00:00'),
	(17,7875,77,'2015-05-31 00:00:00'),
	(18,7875,78,'2015-05-31 00:00:00'),
	(19,7875,79,'2015-05-31 00:00:00'),
	(20,7875,80,'2015-05-31 00:00:00'),
	(21,7876,73,'2015-05-31 00:00:00'),
	(22,7876,74,'2015-05-31 00:00:00'),
	(23,7876,75,'2015-05-31 00:00:00'),
	(24,7876,76,'2015-05-31 00:00:00'),
	(25,7876,77,'2015-05-31 00:00:00'),
	(26,7876,78,'2015-05-31 00:00:00'),
	(27,7876,79,'2015-05-31 00:00:00'),
	(28,7876,80,'2015-05-31 00:00:00'),
	(29,7877,73,'2015-05-31 00:00:00'),
	(30,7877,74,'2015-05-31 00:00:00'),
	(31,7877,75,'2015-05-31 00:00:00'),
	(32,7877,76,'2015-05-31 00:00:00'),
	(33,7877,77,'2015-05-31 00:00:00'),
	(34,7877,78,'2015-05-31 00:00:00'),
	(35,7877,79,'2015-05-31 00:00:00'),
	(36,7877,80,'2015-05-31 00:00:00'),
	(37,7878,73,'2015-05-31 00:00:00'),
	(38,7878,74,'2015-05-31 00:00:00'),
	(39,7878,75,'2015-05-31 00:00:00'),
	(40,7878,76,'2015-05-31 00:00:00'),
	(41,7878,77,'2015-05-31 00:00:00'),
	(42,7878,78,'2015-05-31 00:00:00'),
	(43,7878,79,'2015-05-31 00:00:00'),
	(44,7878,80,'2015-05-31 00:00:00'),
	(45,7879,73,'2015-05-31 00:00:00'),
	(46,7879,74,'2015-05-31 00:00:00'),
	(47,7879,75,'2015-05-31 00:00:00'),
	(48,7879,76,'2015-05-31 00:00:00'),
	(49,7879,77,'2015-05-31 00:00:00'),
	(50,7879,78,'2015-05-31 00:00:00'),
	(51,7879,79,'2015-05-31 00:00:00'),
	(52,7879,80,'2015-05-31 00:00:00');

/*!40000 ALTER TABLE `projectTask` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table projectTaskStatus
# ------------------------------------------------------------

DROP TABLE IF EXISTS `projectTaskStatus`;

CREATE TABLE `projectTaskStatus` (
  `projectTaskStatusID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectTaskID` int(10) unsigned NOT NULL,
  `taskStatusTypeID` int(10) unsigned NOT NULL,
  `statusDate` datetime DEFAULT NULL,
  `dateUpdated` datetime NOT NULL,
  `staffID` int(10) unsigned DEFAULT NULL,
  `monitorID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`projectTaskStatusID`),
  KEY `index2` (`projectTaskID`),
  KEY `index3` (`taskStatusTypeID`),
  KEY `fk202` (`projectTaskID`),
  KEY `fk203` (`taskStatusTypeID`),
  KEY `companyStaffID` (`staffID`),
  KEY `index7` (`monitorID`),
  KEY `fk209` (`monitorID`),
  CONSTRAINT `fk202` FOREIGN KEY (`projectTaskID`) REFERENCES `projectTask` (`projectTaskID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk203` FOREIGN KEY (`taskStatusTypeID`) REFERENCES `taskStatusType` (`taskStatusTypeID`) ON UPDATE NO ACTION,
  CONSTRAINT `fk209` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `projectsitetaskstatus_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `projectTaskStatus` WRITE;
/*!40000 ALTER TABLE `projectTaskStatus` DISABLE KEYS */;

INSERT INTO `projectTaskStatus` (`projectTaskStatusID`, `projectTaskID`, `taskStatusTypeID`, `statusDate`, `dateUpdated`, `staffID`, `monitorID`)
VALUES
	(1,3,70,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(2,6,74,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(3,8,74,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(4,9,72,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(5,5,73,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(6,6,69,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(7,9,72,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(8,2,69,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(9,3,70,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(10,9,71,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(11,8,72,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(12,1,69,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(13,1,70,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(14,6,74,'2015-06-07 19:18:04','2015-06-07 19:18:04',NULL,83),
	(15,3,72,'2015-06-07 19:50:31','2015-06-07 19:50:31',NULL,83),
	(16,2,70,'2015-06-07 20:08:21','2015-06-07 21:33:55',NULL,83),
	(17,5,70,'2015-06-07 21:53:31','2015-06-07 21:57:14',NULL,83),
	(18,1,73,'2015-06-07 21:57:28','2015-06-07 22:01:01',NULL,83),
	(19,9,71,'2015-06-07 22:01:12','2015-06-07 22:13:47',NULL,83),
	(20,1,75,'2015-06-07 22:14:01','2015-06-07 22:25:36',NULL,83),
	(21,12,72,'2015-06-07 22:26:17','2015-06-07 22:37:53',NULL,83),
	(22,1,76,'2015-06-07 22:44:20','2015-06-07 22:48:13',NULL,83),
	(23,1,69,'2015-06-07 22:50:51','2015-06-07 22:54:19',NULL,83),
	(24,8,76,'2015-06-08 05:32:19','2015-06-08 09:33:32',NULL,83),
	(25,9,72,'2015-06-08 05:59:24','2015-06-08 09:33:32',NULL,83),
	(26,5,71,'2015-06-08 05:37:05','2015-06-08 09:33:32',NULL,83),
	(27,5,69,'2015-06-09 04:07:31','2015-06-09 11:00:20',NULL,83),
	(28,2,70,'2015-06-09 12:38:59','2015-06-09 12:57:16',NULL,83),
	(29,5,71,'2015-06-09 12:57:48','2015-06-09 13:16:28',NULL,83),
	(30,3,69,'2015-06-09 13:16:43','2015-06-09 13:19:33',NULL,83),
	(31,21,72,'2015-06-09 13:37:45','2015-06-09 14:03:01',NULL,83),
	(32,3,69,'2015-06-09 16:55:55','2015-06-09 18:07:08',NULL,83),
	(33,6,70,'2015-06-09 14:06:03','2015-06-09 18:07:08',NULL,83),
	(34,3,74,'2015-06-09 16:57:51','2015-06-09 18:07:08',NULL,83),
	(35,9,72,'2015-06-09 17:57:19','2015-06-09 18:07:08',NULL,83),
	(36,3,70,'2015-06-09 17:58:41','2015-06-09 18:07:08',NULL,83),
	(37,5,71,'2015-06-09 18:02:34','2015-06-09 18:07:08',NULL,83),
	(38,2,69,'2015-06-09 19:19:50','2015-06-09 19:42:29',NULL,83),
	(39,2,69,'2015-06-09 19:19:50','2015-06-09 19:46:30',NULL,83),
	(40,2,69,'2015-06-09 19:19:50','2015-06-09 19:46:37',NULL,83),
	(41,2,69,'2015-06-09 19:19:50','2015-06-09 19:46:42',NULL,83),
	(42,2,69,'2015-06-09 19:19:50','2015-06-09 19:46:48',NULL,83),
	(43,5,74,'2015-06-09 19:46:47','2015-06-09 19:46:48',NULL,83),
	(44,2,69,'2015-06-09 19:19:50','2015-06-09 19:48:32',NULL,83),
	(45,5,74,'2015-06-09 19:46:47','2015-06-09 19:48:32',NULL,83),
	(46,5,74,'2015-06-09 19:46:47','2015-06-09 19:48:53',NULL,83),
	(47,2,69,'2015-06-09 19:19:50','2015-06-09 19:48:53',NULL,83),
	(48,6,71,'2015-06-09 19:48:59','2015-06-09 19:49:01',NULL,83),
	(49,2,69,'2015-06-09 19:19:50','2015-06-09 19:49:01',NULL,83),
	(50,5,74,'2015-06-09 19:46:47','2015-06-09 19:49:01',NULL,83),
	(51,5,74,'2015-06-09 19:46:47','2015-06-09 19:49:12',NULL,83),
	(52,6,71,'2015-06-09 19:48:59','2015-06-09 19:49:12',NULL,83),
	(53,2,69,'2015-06-09 19:19:50','2015-06-09 19:49:12',NULL,83),
	(54,6,71,'2015-06-09 19:48:59','2015-06-09 19:49:18',NULL,83),
	(55,2,69,'2015-06-09 19:19:50','2015-06-09 19:49:18',NULL,83),
	(56,5,74,'2015-06-09 19:46:47','2015-06-09 19:49:18',NULL,83),
	(57,6,71,'2015-06-09 19:48:59','2015-06-09 19:49:20',NULL,83),
	(58,5,74,'2015-06-09 19:46:47','2015-06-09 19:49:20',NULL,83),
	(59,2,69,'2015-06-09 19:19:50','2015-06-09 19:49:20',NULL,83),
	(60,5,74,'2015-06-09 19:46:47','2015-06-09 19:52:56',NULL,83),
	(61,6,71,'2015-06-09 19:48:59','2015-06-09 19:52:56',NULL,83),
	(62,2,69,'2015-06-09 19:19:50','2015-06-09 19:52:56',NULL,83),
	(63,5,74,'2015-06-09 19:46:47','2015-06-09 19:53:25',NULL,83),
	(64,6,71,'2015-06-09 19:48:59','2015-06-09 19:53:25',NULL,83),
	(65,2,69,'2015-06-09 19:19:50','2015-06-09 19:53:25',NULL,83),
	(66,2,69,'2015-06-09 19:19:50','2015-06-09 19:56:52',NULL,83),
	(67,5,74,'2015-06-09 19:46:47','2015-06-09 19:56:52',NULL,83),
	(68,6,71,'2015-06-09 19:48:59','2015-06-09 19:56:52',NULL,83),
	(69,6,71,'2015-06-09 19:48:59','2015-06-09 20:04:49',NULL,83),
	(70,2,69,'2015-06-09 19:19:50','2015-06-09 20:04:49',NULL,83),
	(71,5,74,'2015-06-09 19:46:47','2015-06-09 20:04:49',NULL,83),
	(72,13,71,'2015-06-09 19:56:59','2015-06-09 20:04:49',NULL,83),
	(73,5,74,'2015-06-09 19:46:47','2015-06-09 20:06:23',NULL,83),
	(74,2,69,'2015-06-09 19:19:50','2015-06-09 20:06:23',NULL,83),
	(75,13,71,'2015-06-09 19:56:59','2015-06-09 20:06:23',NULL,83),
	(76,6,71,'2015-06-09 19:48:59','2015-06-09 20:06:23',NULL,83),
	(77,6,71,'2015-06-09 19:48:59','2015-06-09 21:38:31',NULL,83),
	(78,2,69,'2015-06-09 19:19:50','2015-06-09 21:38:31',NULL,83),
	(79,5,74,'2015-06-09 19:46:47','2015-06-09 21:38:31',NULL,83),
	(80,13,71,'2015-06-09 19:56:59','2015-06-09 21:38:31',NULL,83),
	(81,12,69,'2015-06-10 06:50:30','2015-06-10 07:01:43',NULL,83),
	(82,12,71,'2015-06-10 07:04:00','2015-06-10 07:04:00',NULL,83),
	(83,13,70,'2015-06-10 07:07:27','2015-06-10 07:07:28',NULL,83),
	(84,14,76,'2015-06-10 07:19:26','2015-06-10 07:19:27',NULL,83),
	(85,21,69,'2015-06-10 08:34:33','2015-06-10 08:35:34',NULL,83),
	(86,2,68,'2015-06-10 08:35:55','2015-06-10 08:35:56',NULL,83),
	(87,1,68,'2015-06-10 09:26:27','2015-06-10 09:26:28',NULL,83),
	(88,2,70,'2015-06-10 09:29:22','2015-06-10 09:29:23',NULL,83);

/*!40000 ALTER TABLE `projectTaskStatus` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table staff
# ------------------------------------------------------------

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `staffID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `cellphone` varchar(50) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `activeFlag` int(2) DEFAULT NULL,
  `pin` varchar(10) DEFAULT NULL,
  `appInvitationDate` datetime DEFAULT NULL,
  `dateRegistered` datetime NOT NULL,
  `staffTypeID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`staffID`),
  UNIQUE KEY `index2` (`companyID`,`email`),
  KEY `index3` (`companyID`),
  KEY `fk003` (`companyID`),
  KEY `index5` (`staffTypeID`),
  KEY `fk003a` (`staffTypeID`),
  CONSTRAINT `fk003` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk003a` FOREIGN KEY (`staffTypeID`) REFERENCES `staffType` (`staffTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;

INSERT INTO `staff` (`staffID`, `companyID`, `firstName`, `lastName`, `cellphone`, `email`, `activeFlag`, `pin`, `appInvitationDate`, `dateRegistered`, `staffTypeID`)
VALUES
	(1,30,'Jack','Ndlovu','098 374 3777','aubrey.malabie',1,'1234',NULL,'2015-06-06 00:00:00',4);

/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;


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



# Dump of table staffType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `staffType`;

CREATE TABLE `staffType` (
  `staffTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `staffTypeName` varchar(45) NOT NULL,
  PRIMARY KEY (`staffTypeID`),
  UNIQUE KEY `index2` (`staffTypeName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `staffType` WRITE;
/*!40000 ALTER TABLE `staffType` DISABLE KEYS */;

INSERT INTO `staffType` (`staffTypeID`, `staffTypeName`)
VALUES
	(1,'Company Exec'),
	(2,'Portfolio Exec'),
	(3,'Programme Director'),
	(4,'Project Manager');

/*!40000 ALTER TABLE `staffType` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table subTask
# ------------------------------------------------------------

DROP TABLE IF EXISTS `subTask`;

CREATE TABLE `subTask` (
  `subTaskID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `subTaskName` varchar(255) NOT NULL DEFAULT '',
  `subTaskNumber` int(11) DEFAULT NULL,
  `description` text,
  `taskID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`subTaskID`),
  KEY `taskID` (`taskID`),
  CONSTRAINT `subtask_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table task
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `taskID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned NOT NULL,
  `taskName` varchar(255) NOT NULL,
  `taskNumber` int(11) DEFAULT NULL,
  `description` text,
  `taskTypeID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`taskID`),
  KEY `companyID` (`companyID`),
  KEY `index3` (`taskTypeID`),
  KEY `fknns` (`taskTypeID`),
  CONSTRAINT `fknns` FOREIGN KEY (`taskTypeID`) REFERENCES `taskType` (`taskTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;

INSERT INTO `task` (`taskID`, `companyID`, `taskName`, `taskNumber`, `description`, `taskTypeID`)
VALUES
	(73,30,'Build Foundation',2,NULL,1),
	(74,30,'Prepare Building Site',1,NULL,1),
	(75,30,'Construct External Walls',3,NULL,1),
	(76,30,'Install Roofing',4,NULL,1),
	(77,30,'Install Electrical Fittings',5,NULL,1),
	(78,30,'Install Plumbing',6,NULL,1),
	(79,30,'Wall Plaster & Painting',7,NULL,1),
	(80,30,'Internal Finishes',8,NULL,1);

/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table taskStatusType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `taskStatusType`;

CREATE TABLE `taskStatusType` (
  `taskStatusTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned NOT NULL,
  `taskStatusTypeName` varchar(100) NOT NULL,
  `statusColor` tinyint(4) unsigned DEFAULT NULL,
  PRIMARY KEY (`taskStatusTypeID`),
  UNIQUE KEY `companyID_2` (`companyID`,`taskStatusTypeName`),
  KEY `companyID` (`companyID`),
  CONSTRAINT `taskstatus_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `taskStatusType` WRITE;
/*!40000 ALTER TABLE `taskStatusType` DISABLE KEYS */;

INSERT INTO `taskStatusType` (`taskStatusTypeID`, `companyID`, `taskStatusTypeName`, `statusColor`)
VALUES
	(68,30,'Completed',3),
	(69,30,'Delayed - Materials',2),
	(70,30,'Delayed - Staff',2),
	(71,30,'Delayed - Strike',1),
	(72,30,'Not Started Yet',2),
	(73,30,'Abandoned',1),
	(74,30,'Incidence of Vandalism',1),
	(75,30,'Theft of Materials',1),
	(76,30,'Delayed - Weather',2);

/*!40000 ALTER TABLE `taskStatusType` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table taskType
# ------------------------------------------------------------

DROP TABLE IF EXISTS `taskType`;

CREATE TABLE `taskType` (
  `taskTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `taskTypeName` varchar(45) NOT NULL,
  `companyID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`taskTypeID`),
  UNIQUE KEY `index2` (`companyID`,`taskTypeName`),
  KEY `fknbv` (`companyID`),
  CONSTRAINT `fknbv` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `taskType` WRITE;
/*!40000 ALTER TABLE `taskType` DISABLE KEYS */;

INSERT INTO `taskType` (`taskTypeID`, `taskTypeName`, `companyID`)
VALUES
	(1,'Construction',30),
	(2,'Electrical',30),
	(8,'Fit & Finish',30),
	(9,'Glazing',30),
	(7,'Landscaping',30),
	(3,'Maintenance',30),
	(6,'Plumbing',30),
	(5,'Road Construction',30),
	(4,'Sanitation',30);

/*!40000 ALTER TABLE `taskType` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
