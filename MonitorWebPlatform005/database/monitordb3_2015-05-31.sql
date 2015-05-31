# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.34)
# Database: monitordb3
# Generation Time: 2015-05-31 07:46:19 +0000
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
  CONSTRAINT `errorstoreandroid_ibfk_3` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE,
  CONSTRAINT `errorstoreandroid_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE,
  CONSTRAINT `errorstoreandroid_ibfk_2` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE
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
  CONSTRAINT `locationtracker_ibfk_2` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE,
  CONSTRAINT `locationtracker_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
	(82,30,'St. Vincent','Malenga','072 312 7738','malengadev@gmail.com',1,'1234',NULL,'2015-05-31 06:51:15','93737373789');

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
	(7,82,7880,'2015-05-31 00:00:00',NULL);

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
  `uri` varchar(255) DEFAULT NULL,
  `thumbFlag` int(11) DEFAULT NULL,
  `dateUploaded` datetime DEFAULT NULL,
  `thumbFilePath` varchar(255) DEFAULT NULL,
  `staffPicture` int(11) DEFAULT NULL,
  PRIMARY KEY (`photoUploadID`),
  KEY `companyID` (`companyID`),
  KEY `projectID` (`projectID`),
  KEY `projectSiteTaskID` (`projectTaskID`),
  KEY `companyStaffID` (`staffID`),
  KEY `monitorID` (`monitorID`),
  CONSTRAINT `photoupload_ibfk_7` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE,
  CONSTRAINT `photoupload_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `photoupload_ibfk_4` FOREIGN KEY (`projectTaskID`) REFERENCES `projectTask` (`projectTaskID`) ON DELETE CASCADE,
  CONSTRAINT `photoupload_ibfk_5` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE,
  CONSTRAINT `photoupload_ibfk_6` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`programmeID`) REFERENCES `programme` (`programmeID`) ON DELETE CASCADE,
  CONSTRAINT `fk007` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`projectID`, `companyID`, `programmeID`, `projectName`, `latitude`, `longitude`, `accuracy`, `activeFlag`, `locationConfirmed`, `address`, `description`, `dateRegistered`)
VALUES
	(7874,30,63,'Naledi Technology School',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
	(7875,30,63,'Diepkloof School of the Arts',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-05-31 00:00:00'),
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
  KEY `fk0012` (`projectID`),
  KEY `taskID` (`taskID`),
  CONSTRAINT `fk0012` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `projectsitetask_ibfk_1` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
  CONSTRAINT `projectsitetaskstatus_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE,
  CONSTRAINT `fk209` FOREIGN KEY (`monitorID`) REFERENCES `monitor` (`monitorID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE,
  CONSTRAINT `fknns` FOREIGN KEY (`taskTypeID`) REFERENCES `taskType` (`taskTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
	(3,'Maintenance',30),
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
