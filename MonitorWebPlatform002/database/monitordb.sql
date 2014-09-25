-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Sep 22, 2014 at 03:26 PM
-- Server version: 5.5.34
-- PHP Version: 5.5.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `monitordb`
--

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `companyID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`companyID`),
  UNIQUE KEY `index3` (`companyName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`companyID`, `companyName`) VALUES
(1, 'Hackers Construction'),
(2, 'Pecanwood Builders Inc');

-- --------------------------------------------------------

--
-- Table structure for table `companyStaff`
--

CREATE TABLE `companyStaff` (
  `companyStaffID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `cellphone` varchar(50) DEFAULT NULL,
  `companyID` int(10) unsigned NOT NULL,
  `companyStaffTypeID` int(10) unsigned NOT NULL,
  `activeFlag` int(2) DEFAULT NULL,
  `pin` int(10) DEFAULT NULL,
  PRIMARY KEY (`companyStaffID`),
  UNIQUE KEY `index2` (`email`),
  KEY `index3` (`companyID`),
  KEY `fk003` (`companyID`),
  KEY `index5` (`companyStaffTypeID`),
  KEY `fk0015` (`companyStaffTypeID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `companyStaff`
--

INSERT INTO `companyStaff` (`companyStaffID`, `firstName`, `lastName`, `email`, `cellphone`, `companyID`, `companyStaffTypeID`, `activeFlag`, `pin`) VALUES
(1, 'Aubrey', 'Malabie', 'malengatiger@gmail.com', NULL, 1, 1, 0, 123456),
(2, 'Bryan', 'Maluleke', 'malengadev@gmail.com', NULL, 1, 2, 0, 123456),
(3, 'Mmaphefo', 'Mofokeng', 'aubrey@mlab.co.za', NULL, 1, 2, 0, 123456),
(4, 'Danielle', 'Khoza', 'danu@mlab.co.za', NULL, 1, 2, 0, 12345),
(5, 'Mfanakithi', 'Dodoma', 'mfana@gmail.com', NULL, 1, 2, 0, 12345),
(6, 'George', 'Hlungwane', 'geogh@gmail.com', NULL, 1, 2, 0, 12345),
(7, 'Robert', 'Bonga', 'robbonga@gmail.com', NULL, 1, 2, 0, 12345),
(8, 'Daniel', 'Bonga', 'danbonga@gmail.com', NULL, 1, 2, 0, 12345),
(9, 'Thabo', 'Mongoma', 'thabmo@gmail.com', NULL, 1, 2, 0, 12345),
(10, 'Francinah', 'Jones', 'franj@gmail.com', NULL, 1, 2, 0, 12345),
(12, 'Jeremiah', 'Jones', 'jerryjones@gmail.com', NULL, 1, 2, 0, 12345),
(13, 'Sifiso', 'GrootBoom', 'Sifiso@gmail.com', NULL, 1, 2, 0, 12345),
(14, 'David', 'Khongoane', 'davk@gerrym.mail', NULL, 1, 2, 0, 42537),
(15, 'Janet', 'Fumani', 'janetf@hmail.com', NULL, 1, 2, 0, 2938),
(16, 'Moratwe', 'Mofokeng', 'moratwe@mof.com', NULL, 1, 2, 0, 52635),
(17, 'Thabiso', 'Legoete', 'thabs@golf.com', NULL, 1, 2, 0, 2836),
(18, 'Thulare', 'Sono', 'thlson@gmail.com', NULL, 1, 2, 0, 62736),
(19, 'Aubrey', 'Nzimande', 'aubsz@gmail.com', NULL, 1, 2, 0, NULL),
(20, 'Jackson', 'Nkuna', 'jacksnn@gmail.com', NULL, 1, 2, 0, NULL),
(21, 'Thomas', 'Bekizulu', 'thz@gmail.com', NULL, 1, 2, 0, NULL),
(22, 'Wendy', 'Masipa', 'thsween@gmail.com', NULL, 1, 2, 0, NULL),
(23, 'Rebecca', 'Motaung', 'rebecca@hhna.com', NULL, 1, 2, 0, NULL),
(24, 'Samukelo', 'Smith', 'smithsam@gmail.com', NULL, 1, 2, 0, NULL),
(25, 'Siyabonga', 'Majozi', 'sybongamaj@gmail.com', NULL, 1, 2, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `companyStaffType`
--

CREATE TABLE `companyStaffType` (
  `companyStaffTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyStaffTypeName` varchar(100) NOT NULL,
  PRIMARY KEY (`companyStaffTypeID`),
  UNIQUE KEY `index2` (`companyStaffTypeName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `companyStaffType`
--

INSERT INTO `companyStaffType` (`companyStaffTypeID`, `companyStaffTypeName`) VALUES
(1, 'Administrator'),
(3, 'Executive Staff'),
(2, 'Project Site Staff');

-- --------------------------------------------------------

--
-- Table structure for table `errorStore`
--

CREATE TABLE `errorStore` (
  `errorStoreID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `statusCode` int(11) NOT NULL,
  `message` text NOT NULL,
  `dateOccured` datetime NOT NULL,
  `origin` varchar(255) NOT NULL,
  PRIMARY KEY (`errorStoreID`),
  KEY `dateOccured` (`dateOccured`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `errorStoreAndroid`
--

CREATE TABLE `errorStoreAndroid` (
  `errorStoreAndroidID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyID` int(10) unsigned DEFAULT NULL,
  `errorDate` datetime NOT NULL,
  `packageName` varchar(150) NOT NULL,
  `appVersionName` varchar(10) NOT NULL,
  `appVersionCode` varchar(10) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `phoneModel` varchar(100) NOT NULL,
  `androidVersion` varchar(20) NOT NULL,
  `stackTrace` text NOT NULL,
  `logCat` text NOT NULL,
  PRIMARY KEY (`errorStoreAndroidID`),
  KEY `companyID` (`companyID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `errorStoreAndroid`
--

INSERT INTO `errorStoreAndroid` (`errorStoreAndroidID`, `companyID`, `errorDate`, `packageName`, `appVersionName`, `appVersionCode`, `brand`, `phoneModel`, `androidVersion`, `stackTrace`, `logCat`) VALUES
(1, NULL, '2014-09-21 14:26:31', 'com.boha.monitor.admin', '1.0', '1', 'samsung', 'GT-I9500', '4.4.2', 'java.lang.ClassCastException: com.com.boha.monitor.library.MainPagerActivity@429ace50 must implement ProjectSiteStaffListListener\n	at com.com.boha.monitor.library.fragments.ProjectSiteStaffListFragment.onAttach(ProjectSiteStaffListFragment.java:115)\n	at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:894)\n	at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1115)\n	at android.support.v4.app.BackStackRecord.run(BackStackRecord.java:682)\n	at android.support.v4.app.FragmentManagerImpl.execPendingActions(FragmentManager.java:1478)\n	at android.support.v4.app.FragmentManagerImpl.executePendingTransactions(FragmentManager.java:478)\n	at android.support.v4.app.FragmentStatePagerAdapter.finishUpdate(FragmentStatePagerAdapter.java:163)\n	at android.support.v4.view.ViewPager.populate(ViewPager.java:1068)\n	at android.support.v4.view.ViewPager.populate(ViewPager.java:914)\n	at android.support.v4.view.ViewPager.setAdapter(ViewPager.java:442)\n	at com.com.boha.monitor.library.MainPagerActivity.initializeAdapter(MainPagerActivity.java:204)\n	at com.com.boha.monitor.library.MainPagerActivity.buildPages(MainPagerActivity.java:198)\n	at com.com.boha.monitor.library.MainPagerActivity.access$000(MainPagerActivity.java:35)\n	at com.com.boha.monitor.library.MainPagerActivity$1$1.run(MainPagerActivity.java:88)\n	at android.os.Handler.handleCallback(Handler.java:733)\n	at android.os.Handler.dispatchMessage(Handler.java:95)\n	at android.os.Looper.loop(Looper.java:157)\n	at android.app.ActivityThread.main(ActivityThread.java:5356)\n	at java.lang.reflect.Method.invokeNative(Native Method)\n	at java.lang.reflect.Method.invoke(Method.java:515)\n	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1265)\n	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1081)\n	at dalvik.system.NativeStart.main(Native Method)\n', '--------- beginning of /dev/log/main\n09-21 14:26:31.488 D/dalvikvm(22754): Late-enabling CheckJNI\n09-21 14:26:31.608 D/MonApp  (22754): ############################ onCreate MonApp has started ---------------->\n09-21 14:26:31.618 D/ACRA    (22754): ACRA is enabled for com.boha.monitor.admin, intializing...\n09-21 14:26:31.633 D/ACRA    (22754): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 14:26:31.633 E/MonApp  (22754): ###### ACRA Crash Reporting has been initiated\n09-21 14:26:31.633 E/MonApp  (22754): initializing Volley Networking ...\n09-21 14:26:31.643 I/MonApp  (22754): ********** Yebo! Volley Networking has been initialized, cache size: 16384 KB\n09-21 14:26:31.668 E/MonApp  (22754): ###### Glide has been initialised\n09-21 14:26:31.958 W/ApplicationPackageManager(22754): getCSCPackageItemText()\n09-21 14:26:32.223 D/OpenGLRenderer(22754): Enabling debug mode 0\n09-21 14:26:32.253 W/WebCheck(22754): ###### WIFI AVAILABLE on check\n09-21 14:26:32.253 W/WebCheck(22754): ###### WIFI CONNECTED on check\n09-21 14:26:32.253 W/WebCheck(22754): ###### MOBILE_NETWORK AVAILABLE on check\n09-21 14:26:32.253 D/WebCheck(22754): @@@ MOBILE_NETWORK NOT CONNECTED on check\n09-21 14:26:32.253 D/WebCheck(22754): ###### Network check completed in 2 ms\n09-21 14:26:32.258 D/TimerUtil(22754): ########## Websocket Session Timer starting .....\n09-21 14:26:32.308 D/com.com.boha.monitor.library.util.WebSocketUtil(22754): ### #### -------------> starting mWebSocketClient.connect ...\n09-21 14:26:32.353 W/com.com.boha.monitor.library.util.WebSocketUtil(22754): ########## WEBSOCKET Opened: Web Socket Protocol Handshake\n09-21 14:26:32.448 D/com.com.boha.monitor.library.util.WebSocketUtil(22754): ########### web socket request sent after onOpen\n09-21 14:26:32.448 D/com.com.boha.monitor.library.util.WebSocketUtil(22754): {"companyID":1,"requestType":108}\n09-21 14:26:32.453 W/TimerUtil(22754): ########## Websocket Session Timer KILLED\n09-21 14:26:32.453 I/com.com.boha.monitor.library.util.WebSocketUtil(22754): ########## onMessage, length: 357 elapsed: 0.191 seconds\n09-21 14:26:32.453 I/com.com.boha.monitor.library.util.WebSocketUtil(22754): {"statusCode":0,"sessionID":"8d19a47f-0395-4eb0-8de5-08e909cfec62","taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[]}\n09-21 14:26:32.608 I/com.com.boha.monitor.library.util.WebSocketUtil(22754): ########## onMessage ByteBuffer capacity: 3227\n09-21 14:26:32.623 I/System.out(22754): ###----> Unpack zip zippedFile, elapsed ms: 13\n09-21 14:26:32.623 E/com.com.boha.monitor.library.util.WebSocketUtil(22754): ############# onMessage, unpacked length: 43298 elapsed: 0.347 seconds\n09-21 14:26:32.623 E/com.com.boha.monitor.library.util.WebSocketUtil(22754): {"statusCode":0,"taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[],"company":{"companyID":1,"companyName":"Hackers Construction","projectList":[{"projectID":1,"projectName":"RDP Housing Project","description":"RDP Housing Project in the Nort  West Province near the town of Klerksdorp. 3 000 houses are to be built on the site of the Vermaak Farm P8098","dateRegistered":1411164000000,"completeFlag":0,"companyID":1,"projectSiteList":[{"projectSiteID":8,"projectSiteName":"Community Center","activeFlag":0,"projectSiteTaskList":[],"projectID":1,"projectSiteStaffList":[{"projectSiteStaffID":20,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":8,"activeFlag":0,"firstName":"Daniel","lastName":"Bonga","email":"danbonga@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":18,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":2,"activeFlag":0,"firstName":"Bryan","lastName":"Maluleke","email":"malengadev@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":19,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":3,"activeFlag":0,"firstName":"Mmaphefo","lastName":"Mofokeng","email":"aubrey@mlab.co.za","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}}],"imageFileNameList":[]},{"projectSiteID":7,"projectSiteName":"Electrical Substation #1","activeFlag":0,"projectSiteTaskList":[{"projectSiteTaskID":48,"taskName":"Complete the earthworks","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":8,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2},{"projectSiteTaskStatusID":12,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":53,"taskName":"Connect nearby houses in test","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":17,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":53,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":51,"taskName":"Install Base Generators","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":11,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":15,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":18,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":3,"taskStatusName":"Aborted"},"projectSiteTaskID":51,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":49,"taskName":"Install concrete foundation","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":9,"dateUpdated":1410527700000,"taskStatus":{"taskS\n09-21 14:26:32.788 D/dalvikvm(22754): GC_FOR_ALLOC freed 2043K, 16% free 17792K/21088K, paused 19ms, total 22ms\n09-21 14:26:32.818 D/AbsListView(22754): Get MotionRecognitionManager\n09-21 14:26:32.833 D/AbsListView(22754): unregisterIRListener() is called \n09-21 14:26:32.838 D/AndroidRuntime(22754): Shutting down VM\n09-21 14:26:32.838 W/dalvikvm(22754): threadid=1: thread exiting with uncaught exception (group=0x417c2c08)\n09-21 14:26:32.838 E/ACRA    (22754): ACRA caught a ClassCastException exception for com.boha.monitor.admin. Building report.\n09-21 14:26:32.838 D/ACRA    (22754): Using custom Report Fields\n09-21 14:26:32.848 I/ACRA    (22754): READ_LOGS granted! ACRA can include LogCat and DropBox data.\n09-21 14:26:32.873 D/ACRA    (22754): Retrieving logcat output...\n'),
(2, NULL, '2014-09-21 14:43:42', 'com.boha.monitor.admin', '1.0', '1', 'samsung', 'GT-I9500', '4.4.2', 'java.lang.NullPointerException\n	at android.widget.ArrayAdapter.init(ArrayAdapter.java:310)\n	at android.widget.ArrayAdapter.<init>(ArrayAdapter.java:153)\n	at com.com.boha.monitor.library.adapters.StaffAdapter.<init>(StaffAdapter.java:31)\n	at com.com.boha.monitor.library.fragments.ProjectSiteStaffListFragment.onCreateView(ProjectSiteStaffListFragment.java:101)\n	at android.support.v4.app.Fragment.performCreateView(Fragment.java:1500)\n	at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:938)\n	at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1115)\n	at android.support.v4.app.BackStackRecord.run(BackStackRecord.java:682)\n	at android.support.v4.app.FragmentManagerImpl.execPendingActions(FragmentManager.java:1478)\n	at android.support.v4.app.FragmentManagerImpl.executePendingTransactions(FragmentManager.java:478)\n	at android.support.v4.app.FragmentStatePagerAdapter.finishUpdate(FragmentStatePagerAdapter.java:163)\n	at android.support.v4.view.ViewPager.populate(ViewPager.java:1068)\n	at android.support.v4.view.ViewPager.populate(ViewPager.java:914)\n	at android.support.v4.view.ViewPager.setAdapter(ViewPager.java:442)\n	at com.com.boha.monitor.library.MainPagerActivity.initializeAdapter(MainPagerActivity.java:205)\n	at com.com.boha.monitor.library.MainPagerActivity.buildPages(MainPagerActivity.java:199)\n	at com.com.boha.monitor.library.MainPagerActivity.access$000(MainPagerActivity.java:36)\n	at com.com.boha.monitor.library.MainPagerActivity$1$1.run(MainPagerActivity.java:89)\n	at android.os.Handler.handleCallback(Handler.java:733)\n	at android.os.Handler.dispatchMessage(Handler.java:95)\n	at android.os.Looper.loop(Looper.java:157)\n	at android.app.ActivityThread.main(ActivityThread.java:5356)\n	at java.lang.reflect.Method.invokeNative(Native Method)\n	at java.lang.reflect.Method.invoke(Method.java:515)\n	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1265)\n	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1081)\n	at dalvik.system.NativeStart.main(Native Method)\n', '--------- beginning of /dev/log/main\n09-21 14:43:42.196 D/dalvikvm(26040): Late-enabling CheckJNI\n09-21 14:43:42.316 D/MonApp  (26040): ############################ onCreate MonApp has started ---------------->\n09-21 14:43:42.321 D/ACRA    (26040): ACRA is enabled for com.boha.monitor.admin, intializing...\n09-21 14:43:42.336 D/ACRA    (26040): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 14:43:42.336 E/MonApp  (26040): ###### ACRA Crash Reporting has been initiated\n09-21 14:43:42.336 E/MonApp  (26040): initializing Volley Networking ...\n09-21 14:43:42.351 I/MonApp  (26040): ********** Yebo! Volley Networking has been initialized, cache size: 16384 KB\n09-21 14:43:42.371 E/MonApp  (26040): ###### Glide has been initialised\n09-21 14:43:42.401 W/ApplicationPackageManager(26040): getCSCPackageItemText()\n09-21 14:43:42.831 D/OpenGLRenderer(26040): Enabling debug mode 0\n09-21 14:43:42.876 W/WebCheck(26040): ###### WIFI AVAILABLE on check\n09-21 14:43:42.876 W/WebCheck(26040): ###### WIFI CONNECTED on check\n09-21 14:43:42.876 W/WebCheck(26040): ###### MOBILE_NETWORK AVAILABLE on check\n09-21 14:43:42.876 D/WebCheck(26040): @@@ MOBILE_NETWORK NOT CONNECTED on check\n09-21 14:43:42.876 D/WebCheck(26040): ###### Network check completed in 9 ms\n09-21 14:43:42.881 D/TimerUtil(26040): ########## Websocket Session Timer starting .....\n09-21 14:43:42.926 D/com.com.boha.monitor.library.util.WebSocketUtil(26040): ### #### -------------> starting mWebSocketClient.connect ...\n09-21 14:43:42.966 W/com.com.boha.monitor.library.util.WebSocketUtil(26040): ########## WEBSOCKET Opened: Web Socket Protocol Handshake\n09-21 14:43:43.016 D/com.com.boha.monitor.library.util.WebSocketUtil(26040): ########### web socket request sent after onOpen\n09-21 14:43:43.016 D/com.com.boha.monitor.library.util.WebSocketUtil(26040): {"companyID":1,"requestType":108}\n09-21 14:43:43.016 W/TimerUtil(26040): ########## Websocket Session Timer KILLED\n09-21 14:43:43.016 I/com.com.boha.monitor.library.util.WebSocketUtil(26040): ########## onMessage, length: 357 elapsed: 0.134 seconds\n09-21 14:43:43.016 I/com.com.boha.monitor.library.util.WebSocketUtil(26040): {"statusCode":0,"sessionID":"88ab3d96-c2a0-4446-a56c-788f20ab6258","taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[]}\n09-21 14:43:43.211 I/com.com.boha.monitor.library.util.WebSocketUtil(26040): ########## onMessage ByteBuffer capacity: 3227\n09-21 14:43:43.221 I/System.out(26040): ###----> Unpack zip zippedFile, elapsed ms: 9\n09-21 14:43:43.226 E/com.com.boha.monitor.library.util.WebSocketUtil(26040): ############# onMessage, unpacked length: 43298 elapsed: 0.33 seconds\n09-21 14:43:43.226 E/com.com.boha.monitor.library.util.WebSocketUtil(26040): {"statusCode":0,"taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[],"company":{"companyID":1,"companyName":"Hackers Construction","projectList":[{"projectID":1,"projectName":"RDP Housing Project","description":"RDP Housing Project in the Nort  West Province near the town of Klerksdorp. 3 000 houses are to be built on the site of the Vermaak Farm P8098","dateRegistered":1411164000000,"completeFlag":0,"companyID":1,"projectSiteList":[{"projectSiteID":8,"projectSiteName":"Community Center","activeFlag":0,"projectSiteTaskList":[],"projectID":1,"projectSiteStaffList":[{"projectSiteStaffID":20,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":8,"activeFlag":0,"firstName":"Daniel","lastName":"Bonga","email":"danbonga@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":18,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":2,"activeFlag":0,"firstName":"Bryan","lastName":"Maluleke","email":"malengadev@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":19,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":3,"activeFlag":0,"firstName":"Mmaphefo","lastName":"Mofokeng","email":"aubrey@mlab.co.za","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}}],"imageFileNameList":[]},{"projectSiteID":7,"projectSiteName":"Electrical Substation #1","activeFlag":0,"projectSiteTaskList":[{"projectSiteTaskID":48,"taskName":"Complete the earthworks","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":8,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2},{"projectSiteTaskStatusID":12,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":53,"taskName":"Connect nearby houses in test","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":17,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":53,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":51,"taskName":"Install Base Generators","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":11,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":15,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":18,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":3,"taskStatusName":"Aborted"},"projectSiteTaskID":51,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":49,"taskName":"Install concrete foundation","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":9,"dateUpdated":1410527700000,"taskStatus":{"taskSt\n09-21 14:43:43.411 D/dalvikvm(26040): GC_FOR_ALLOC freed 2046K, 16% free 17790K/21088K, paused 27ms, total 27ms\n09-21 14:43:43.456 D/AbsListView(26040): Get MotionRecognitionManager\n09-21 14:43:43.476 D/AbsListView(26040): unregisterIRListener() is called \n09-21 14:43:43.486 D/AbsListView(26040): Get MotionRecognitionManager\n09-21 14:43:43.491 D/AndroidRuntime(26040): Shutting down VM\n09-21 14:43:43.491 W/dalvikvm(26040): threadid=1: thread exiting with uncaught exception (group=0x417c2c08)\n09-21 14:43:43.491 E/ACRA    (26040): ACRA caught a NullPointerException exception for com.boha.monitor.admin. Building report.\n09-21 14:43:43.491 D/ACRA    (26040): Using custom Report Fields\n09-21 14:43:43.501 I/ACRA    (26040): READ_LOGS granted! ACRA can include LogCat and DropBox data.\n09-21 14:43:43.521 D/ACRA    (26040): Retrieving logcat output...\n'),
(3, NULL, '2014-09-21 14:45:23', 'com.boha.monitor.admin', '1.0', '1', 'samsung', 'GT-I9500', '4.4.2', 'java.lang.NullPointerException\n	at com.com.boha.monitor.library.adapters.StaffAdapter.getView(StaffAdapter.java:88)\n	at android.widget.AbsListView.obtainView(AbsListView.java:2720)\n	at android.widget.ListView.makeAndAddView(ListView.java:1801)\n	at android.widget.ListView.fillDown(ListView.java:697)\n	at android.widget.ListView.fillFromTop(ListView.java:763)\n	at android.widget.ListView.layoutChildren(ListView.java:1641)\n	at android.widget.AbsListView.onLayout(AbsListView.java:2533)\n	at android.view.View.layout(View.java:15655)\n	at android.view.ViewGroup.layout(ViewGroup.java:4856)\n	at android.widget.FrameLayout.layoutChildren(FrameLayout.java:453)\n	at android.widget.FrameLayout.onLayout(FrameLayout.java:388)\n	at android.view.View.layout(View.java:15655)\n	at android.view.ViewGroup.layout(ViewGroup.java:4856)\n	at android.widget.FrameLayout.layoutChildren(FrameLayout.java:453)\n	at android.widget.FrameLayout.onLayout(FrameLayout.java:388)\n	at android.view.View.layout(View.java:15655)\n	at android.view.ViewGroup.layout(ViewGroup.java:4856)\n	at android.support.v4.view.ViewPager.onLayout(ViewPager.java:1589)\n	at android.view.View.layout(View.java:15655)\n	at android.view.ViewGroup.layout(ViewGroup.java:4856)\n	at android.widget.RelativeLayout.onLayout(RelativeLayout.java:1055)\n	at android.view.View.layout(View.java:15655)\n	at android.view.ViewGroup.layout(ViewGroup.java:4856)\n	at android.widget.FrameLayout.layoutChildren(FrameLayout.java:453)\n	at android.widget.FrameLayout.onLayout(FrameLayout.java:388)\n	at android.view.View.layout(View.java:15655)\n	at android.view.ViewGroup.layout(ViewGroup.java:4856)\n	at com.android.internal.widget.ActionBarOverlayLayout.onLayout(ActionBarOverlayLayout.java:438)\n	at android.view.View.layout(View.java:15655)\n	at android.view.ViewGroup.layout(ViewGroup.java:4856)\n	at android.widget.FrameLayout.layoutChildren(FrameLayout.java:453)\n	at android.widget.FrameLayout.onLayout(FrameLayout.java:388)\n	at android.view.View.layout(View.java:15655)\n	at android.view.ViewGroup.layout(ViewGroup.java:4856)\n	at android.view.ViewRootImpl.performLayout(ViewRootImpl.java:2285)\n	at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:2005)\n	at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1235)\n	at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:6472)\n	at android.view.Choreographer$CallbackRecord.run(Choreographer.java:803)\n	at android.view.Choreographer.doCallbacks(Choreographer.java:603)\n	at android.view.Choreographer.doFrame(Choreographer.java:573)\n	at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:789)\n	at android.os.Handler.handleCallback(Handler.java:733)\n	at android.os.Handler.dispatchMessage(Handler.java:95)\n	at android.os.Looper.loop(Looper.java:157)\n	at android.app.ActivityThread.main(ActivityThread.java:5356)\n	at java.lang.reflect.Method.invokeNative(Native Method)\n	at java.lang.reflect.Method.invoke(Method.java:515)\n	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1265)\n	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1081)\n	at dalvik.system.NativeStart.main(Native Method)\n', '--------- beginning of /dev/log/main\n09-21 14:45:23.195 D/dalvikvm(26537): Late-enabling CheckJNI\n09-21 14:45:23.315 D/MonApp  (26537): ############################ onCreate MonApp has started ---------------->\n09-21 14:45:23.330 D/ACRA    (26537): ACRA is enabled for com.boha.monitor.admin, intializing...\n09-21 14:45:23.345 D/ACRA    (26537): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 14:45:23.345 E/MonApp  (26537): ###### ACRA Crash Reporting has been initiated\n09-21 14:45:23.345 E/MonApp  (26537): initializing Volley Networking ...\n09-21 14:45:23.360 I/MonApp  (26537): ********** Yebo! Volley Networking has been initialized, cache size: 16384 KB\n09-21 14:45:23.395 E/MonApp  (26537): ###### Glide has been initialised\n09-21 14:45:23.400 W/ApplicationPackageManager(26537): getCSCPackageItemText()\n09-21 14:45:23.670 D/OpenGLRenderer(26537): Enabling debug mode 0\n09-21 14:45:23.695 W/WebCheck(26537): ###### WIFI AVAILABLE on check\n09-21 14:45:23.695 W/WebCheck(26537): ###### WIFI CONNECTED on check\n09-21 14:45:23.695 W/WebCheck(26537): ###### MOBILE_NETWORK AVAILABLE on check\n09-21 14:45:23.695 D/WebCheck(26537): @@@ MOBILE_NETWORK NOT CONNECTED on check\n09-21 14:45:23.695 D/WebCheck(26537): ###### Network check completed in 4 ms\n09-21 14:45:23.700 D/TimerUtil(26537): ########## Websocket Session Timer starting .....\n09-21 14:45:23.745 D/com.com.boha.monitor.library.util.WebSocketUtil(26537): ### #### -------------> starting mWebSocketClient.connect ...\n09-21 14:45:23.805 W/com.com.boha.monitor.library.util.WebSocketUtil(26537): ########## WEBSOCKET Opened: Web Socket Protocol Handshake\n09-21 14:45:23.900 D/com.com.boha.monitor.library.util.WebSocketUtil(26537): ########### web socket request sent after onOpen\n09-21 14:45:23.900 D/com.com.boha.monitor.library.util.WebSocketUtil(26537): {"companyID":1,"requestType":108}\n09-21 14:45:23.900 W/TimerUtil(26537): ########## Websocket Session Timer KILLED\n09-21 14:45:23.900 I/com.com.boha.monitor.library.util.WebSocketUtil(26537): ########## onMessage, length: 357 elapsed: 0.201 seconds\n09-21 14:45:23.900 I/com.com.boha.monitor.library.util.WebSocketUtil(26537): {"statusCode":0,"sessionID":"830bcce9-e70f-4a78-a9cd-73cb79637704","taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[]}\n09-21 14:45:24.055 I/com.com.boha.monitor.library.util.WebSocketUtil(26537): ########## onMessage ByteBuffer capacity: 3227\n09-21 14:45:24.070 I/System.out(26537): ###----> Unpack zip zippedFile, elapsed ms: 11\n09-21 14:45:24.070 E/com.com.boha.monitor.library.util.WebSocketUtil(26537): ############# onMessage, unpacked length: 43298 elapsed: 0.356 seconds\n09-21 14:45:24.070 E/com.com.boha.monitor.library.util.WebSocketUtil(26537): {"statusCode":0,"taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[],"company":{"companyID":1,"companyName":"Hackers Construction","projectList":[{"projectID":1,"projectName":"RDP Housing Project","description":"RDP Housing Project in the Nort  West Province near the town of Klerksdorp. 3 000 houses are to be built on the site of the Vermaak Farm P8098","dateRegistered":1411164000000,"completeFlag":0,"companyID":1,"projectSiteList":[{"projectSiteID":8,"projectSiteName":"Community Center","activeFlag":0,"projectSiteTaskList":[],"projectID":1,"projectSiteStaffList":[{"projectSiteStaffID":20,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":8,"activeFlag":0,"firstName":"Daniel","lastName":"Bonga","email":"danbonga@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":18,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":2,"activeFlag":0,"firstName":"Bryan","lastName":"Maluleke","email":"malengadev@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":19,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":3,"activeFlag":0,"firstName":"Mmaphefo","lastName":"Mofokeng","email":"aubrey@mlab.co.za","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}}],"imageFileNameList":[]},{"projectSiteID":7,"projectSiteName":"Electrical Substation #1","activeFlag":0,"projectSiteTaskList":[{"projectSiteTaskID":48,"taskName":"Complete the earthworks","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":8,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2},{"projectSiteTaskStatusID":12,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":53,"taskName":"Connect nearby houses in test","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":17,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":53,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":51,"taskName":"Install Base Generators","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":11,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":15,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":18,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":3,"taskStatusName":"Aborted"},"projectSiteTaskID":51,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":49,"taskName":"Install concrete foundation","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":9,"dateUpdated":1410527700000,"taskStatus":{"taskS\n09-21 14:45:24.210 D/dalvikvm(26537): GC_FOR_ALLOC freed 2050K, 16% free 17786K/21088K, paused 19ms, total 20ms\n09-21 14:45:24.240 D/AbsListView(26537): Get MotionRecognitionManager\n09-21 14:45:24.250 D/AbsListView(26537): unregisterIRListener() is called \n09-21 14:45:24.255 D/AbsListView(26537): Get MotionRecognitionManager\n09-21 14:45:24.255 D/AbsListView(26537): unregisterIRListener() is called \n09-21 14:45:24.325 D/AbsListView(26537): unregisterIRListener() is called \n09-21 14:45:24.365 D/dalvikvm(26537): GC_FOR_ALLOC freed 199K, 16% free 17855K/21088K, paused 16ms, total 16ms\n09-21 14:45:24.385 I/dalvikvm-heap(26537): Grow heap (frag case) to 29.205MB for 11059216-byte allocation\n09-21 14:45:24.400 D/dalvikvm(26537): GC_FOR_ALLOC freed 3K, 11% free 28652K/31892K, paused 16ms, total 16ms\n09-21 14:45:24.435 D/AndroidRuntime(26537): Shutting down VM\n09-21 14:45:24.435 W/dalvikvm(26537): threadid=1: thread exiting with uncaught exception (group=0x417c2c08)\n09-21 14:45:24.435 E/ACRA    (26537): ACRA caught a NullPointerException exception for com.boha.monitor.admin. Building report.\n09-21 14:45:24.435 D/ACRA    (26537): Using custom Report Fields\n09-21 14:45:24.450 I/ACRA    (26537): READ_LOGS granted! ACRA can include LogCat and DropBox data.\n09-21 14:45:24.500 D/ACRA    (26537): Retrieving logcat output...\n'),
(4, NULL, '2014-09-21 20:21:32', 'com.boha.monitor.admin', '1.0', '1', 'samsung', 'GT-I9500', '4.4.2', 'android.util.AndroidRuntimeException: Animators may only be run on Looper threads\n	at android.animation.ValueAnimator.start(ValueAnimator.java:924)\n	at android.animation.ValueAnimator.start(ValueAnimator.java:946)\n	at android.animation.ObjectAnimator.start(ObjectAnimator.java:465)\n	at com.com.boha.monitor.library.util.Util.animateTextScaleY(Util.java:115)\n	at com.com.boha.monitor.library.fragments.ProjectListFragment$1.run(ProjectListFragment.java:99)\n	at java.util.Timer$TimerImpl.run(Timer.java:284)\n', '--------- beginning of /dev/log/main\n09-21 20:21:30.821 D/dalvikvm( 3896): Late-enabling CheckJNI\n09-21 20:21:30.911 D/MonApp  ( 3896): ############################ onCreate MonApp has started ---------------->\n09-21 20:21:30.921 D/ACRA    ( 3896): ACRA is enabled for com.boha.monitor.admin, intializing...\n09-21 20:21:30.936 D/ACRA    ( 3896): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 20:21:30.941 E/MonApp  ( 3896): ###### ACRA Crash Reporting has been initiated\n09-21 20:21:30.941 E/MonApp  ( 3896): initializing Volley Networking ...\n09-21 20:21:30.956 I/MonApp  ( 3896): ********** Yebo! Volley Networking has been initialized, cache size: 16384 KB\n09-21 20:21:30.976 E/MonApp  ( 3896): ###### Glide has been initialised\n09-21 20:21:30.986 W/ApplicationPackageManager( 3896): getCSCPackageItemText()\n09-21 20:21:31.541 D/OpenGLRenderer( 3896): Enabling debug mode 0\n09-21 20:21:31.566 D/CacheUtil( 3896): ################ getting cached data ..................\n09-21 20:21:31.566 D/CacheUtil( 3896): ########### doInBackground: getting cached data ....\n09-21 20:21:31.921 E/CacheUtil( 3896): $$$$$$$$$$$$ cached data retrieved\n09-21 20:21:31.941 D/AbsListView( 3896): Get MotionRecognitionManager\n09-21 20:21:31.951 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:31.961 D/AbsListView( 3896): Get MotionRecognitionManager\n09-21 20:21:31.966 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:31.971 W/WebCheck( 3896): ###### WIFI AVAILABLE on check\n09-21 20:21:31.971 W/WebCheck( 3896): ###### WIFI CONNECTED on check\n09-21 20:21:31.971 W/WebCheck( 3896): ###### MOBILE_NETWORK AVAILABLE on check\n09-21 20:21:31.971 D/WebCheck( 3896): @@@ MOBILE_NETWORK NOT CONNECTED on check\n09-21 20:21:31.971 D/WebCheck( 3896): ###### Network check completed in 2 ms\n09-21 20:21:31.981 D/TimerUtil( 3896): ########## Websocket Session Timer starting .....\n09-21 20:21:32.046 D/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ### #### -------------> starting mWebSocketClient.connect ...\n09-21 20:21:32.131 W/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ########## WEBSOCKET Opened: Web Socket Protocol Handshake\n09-21 20:21:32.146 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.196 D/dalvikvm( 3896): GC_FOR_ALLOC freed 1839K, 15% free 17981K/21076K, paused 22ms, total 22ms\n09-21 20:21:32.231 I/dalvikvm-heap( 3896): Grow heap (frag case) to 29.328MB for 11059216-byte allocation\n09-21 20:21:32.246 D/dalvikvm( 3896): GC_FOR_ALLOC freed 13K, 10% free 28767K/31880K, paused 16ms, total 16ms\n09-21 20:21:32.276 D/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ########### web socket request sent after onOpen\n09-21 20:21:32.276 D/com.com.boha.monitor.library.util.WebSocketUtil( 3896): {"companyID":1,"requestType":108}\n09-21 20:21:32.276 W/TimerUtil( 3896): ########## Websocket Session Timer KILLED\n09-21 20:21:32.276 I/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ########## onMessage, length: 357 elapsed: 0.297 seconds\n09-21 20:21:32.276 I/com.com.boha.monitor.library.util.WebSocketUtil( 3896): {"statusCode":0,"sessionID":"563a3d6f-7e81-4c22-8eab-8a5e4df6103a","taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[]}\n09-21 20:21:32.361 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.361 D/ProgressBar( 3896): updateDrawableBounds: left = 0\n09-21 20:21:32.361 D/ProgressBar( 3896): updateDrawableBounds: top = 0\n09-21 20:21:32.361 D/ProgressBar( 3896): updateDrawableBounds: right = 48\n09-21 20:21:32.361 D/ProgressBar( 3896): updateDrawableBounds: bottom = 48\n09-21 20:21:32.436 I/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ########## onMessage ByteBuffer capacity: 3227\n09-21 20:21:32.451 W/dalvikvm( 3896): threadid=17: thread exiting with uncaught exception (group=0x417c2c08)\n09-21 20:21:32.456 I/System.out( 3896): ###----> Unpack zip zippedFile, elapsed ms: 11\n09-21 20:21:32.456 E/ACRA    ( 3896): ACRA caught a AndroidRuntimeException exception for com.boha.monitor.admin. Building report.\n09-21 20:21:32.456 D/ACRA    ( 3896): Using custom Report Fields\n09-21 20:21:32.456 E/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ############# onMessage, unpacked length: 43298 elapsed: 0.459 seconds\n09-21 20:21:32.456 E/com.com.boha.monitor.library.util.WebSocketUtil( 3896): {"statusCode":0,"taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[],"company":{"companyID":1,"companyName":"Hackers Construction","projectList":[{"projectID":1,"projectName":"RDP Housing Project","description":"RDP Housing Project in the Nort  West Province near the town of Klerksdorp. 3 000 houses are to be built on the site of the Vermaak Farm P8098","dateRegistered":1411164000000,"completeFlag":0,"companyID":1,"projectSiteList":[{"projectSiteID":8,"projectSiteName":"Community Center","activeFlag":0,"projectSiteTaskList":[],"projectID":1,"projectSiteStaffList":[{"projectSiteStaffID":20,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":8,"activeFlag":0,"firstName":"Daniel","lastName":"Bonga","email":"danbonga@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":18,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":2,"activeFlag":0,"firstName":"Bryan","lastName":"Maluleke","email":"malengadev@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":19,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":3,"activeFlag":0,"firstName":"Mmaphefo","lastName":"Mofokeng","email":"aubrey@mlab.co.za","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}}],"imageFileNameList":[]},{"projectSiteID":7,"projectSiteName":"Electrical Substation #1","activeFlag":0,"projectSiteTaskList":[{"projectSiteTaskID":48,"taskName":"Complete the earthworks","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":8,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2},{"projectSiteTaskStatusID":12,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":53,"taskName":"Connect nearby houses in test","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":17,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":53,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":51,"taskName":"Install Base Generators","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":11,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":15,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":18,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":3,"taskStatusName":"Aborted"},"projectSiteTaskID":51,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":49,"taskName":"Install concrete foundation","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":9,"dateUpdated":1410527700000,"taskStatus":{"taskS\n09-21 20:21:32.466 I/ACRA    ( 3896): READ_LOGS granted! ACRA can include LogCat and DropBox data.\n09-21 20:21:32.501 D/ACRA    ( 3896): Retrieving logcat output...\n');
INSERT INTO `errorStoreAndroid` (`errorStoreAndroidID`, `companyID`, `errorDate`, `packageName`, `appVersionName`, `appVersionCode`, `brand`, `phoneModel`, `androidVersion`, `stackTrace`, `logCat`) VALUES
(5, NULL, '2014-09-21 20:21:33', 'com.boha.monitor.admin', '1.0', '1', 'samsung', 'GT-I9500', '4.4.2', 'android.util.AndroidRuntimeException: Animators may only be run on Looper threads\n	at android.animation.ValueAnimator.start(ValueAnimator.java:924)\n	at android.animation.ValueAnimator.start(ValueAnimator.java:946)\n	at android.animation.ObjectAnimator.start(ObjectAnimator.java:465)\n	at com.com.boha.monitor.library.util.Util.animateTextScaleY(Util.java:115)\n	at com.com.boha.monitor.library.fragments.ProjectListFragment$1.run(ProjectListFragment.java:99)\n	at java.util.Timer$TimerImpl.run(Timer.java:284)\n', '--------- beginning of /dev/log/main\n09-21 20:21:30.821 D/dalvikvm( 3896): Late-enabling CheckJNI\n09-21 20:21:30.911 D/MonApp  ( 3896): ############################ onCreate MonApp has started ---------------->\n09-21 20:21:30.921 D/ACRA    ( 3896): ACRA is enabled for com.boha.monitor.admin, intializing...\n09-21 20:21:30.936 D/ACRA    ( 3896): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 20:21:30.941 E/MonApp  ( 3896): ###### ACRA Crash Reporting has been initiated\n09-21 20:21:30.941 E/MonApp  ( 3896): initializing Volley Networking ...\n09-21 20:21:30.956 I/MonApp  ( 3896): ********** Yebo! Volley Networking has been initialized, cache size: 16384 KB\n09-21 20:21:30.976 E/MonApp  ( 3896): ###### Glide has been initialised\n09-21 20:21:30.986 W/ApplicationPackageManager( 3896): getCSCPackageItemText()\n09-21 20:21:31.541 D/OpenGLRenderer( 3896): Enabling debug mode 0\n09-21 20:21:31.566 D/CacheUtil( 3896): ################ getting cached data ..................\n09-21 20:21:31.566 D/CacheUtil( 3896): ########### doInBackground: getting cached data ....\n09-21 20:21:31.921 E/CacheUtil( 3896): $$$$$$$$$$$$ cached data retrieved\n09-21 20:21:31.941 D/AbsListView( 3896): Get MotionRecognitionManager\n09-21 20:21:31.951 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:31.961 D/AbsListView( 3896): Get MotionRecognitionManager\n09-21 20:21:31.966 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:31.971 W/WebCheck( 3896): ###### WIFI AVAILABLE on check\n09-21 20:21:31.971 W/WebCheck( 3896): ###### WIFI CONNECTED on check\n09-21 20:21:31.971 W/WebCheck( 3896): ###### MOBILE_NETWORK AVAILABLE on check\n09-21 20:21:31.971 D/WebCheck( 3896): @@@ MOBILE_NETWORK NOT CONNECTED on check\n09-21 20:21:31.971 D/WebCheck( 3896): ###### Network check completed in 2 ms\n09-21 20:21:31.981 D/TimerUtil( 3896): ########## Websocket Session Timer starting .....\n09-21 20:21:32.046 D/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ### #### -------------> starting mWebSocketClient.connect ...\n09-21 20:21:32.131 W/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ########## WEBSOCKET Opened: Web Socket Protocol Handshake\n09-21 20:21:32.146 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.196 D/dalvikvm( 3896): GC_FOR_ALLOC freed 1839K, 15% free 17981K/21076K, paused 22ms, total 22ms\n09-21 20:21:32.231 I/dalvikvm-heap( 3896): Grow heap (frag case) to 29.328MB for 11059216-byte allocation\n09-21 20:21:32.246 D/dalvikvm( 3896): GC_FOR_ALLOC freed 13K, 10% free 28767K/31880K, paused 16ms, total 16ms\n09-21 20:21:32.276 D/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ########### web socket request sent after onOpen\n09-21 20:21:32.276 D/com.com.boha.monitor.library.util.WebSocketUtil( 3896): {"companyID":1,"requestType":108}\n09-21 20:21:32.276 W/TimerUtil( 3896): ########## Websocket Session Timer KILLED\n09-21 20:21:32.276 I/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ########## onMessage, length: 357 elapsed: 0.297 seconds\n09-21 20:21:32.276 I/com.com.boha.monitor.library.util.WebSocketUtil( 3896): {"statusCode":0,"sessionID":"563a3d6f-7e81-4c22-8eab-8a5e4df6103a","taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[]}\n09-21 20:21:32.361 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.361 D/ProgressBar( 3896): updateDrawableBounds: left = 0\n09-21 20:21:32.361 D/ProgressBar( 3896): updateDrawableBounds: top = 0\n09-21 20:21:32.361 D/ProgressBar( 3896): updateDrawableBounds: right = 48\n09-21 20:21:32.361 D/ProgressBar( 3896): updateDrawableBounds: bottom = 48\n09-21 20:21:32.436 I/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ########## onMessage ByteBuffer capacity: 3227\n09-21 20:21:32.451 W/dalvikvm( 3896): threadid=17: thread exiting with uncaught exception (group=0x417c2c08)\n09-21 20:21:32.456 I/System.out( 3896): ###----> Unpack zip zippedFile, elapsed ms: 11\n09-21 20:21:32.456 E/ACRA    ( 3896): ACRA caught a AndroidRuntimeException exception for com.boha.monitor.admin. Building report.\n09-21 20:21:32.456 D/ACRA    ( 3896): Using custom Report Fields\n09-21 20:21:32.456 E/com.com.boha.monitor.library.util.WebSocketUtil( 3896): ############# onMessage, unpacked length: 43298 elapsed: 0.459 seconds\n09-21 20:21:32.456 E/com.com.boha.monitor.library.util.WebSocketUtil( 3896): {"statusCode":0,"taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[],"company":{"companyID":1,"companyName":"Hackers Construction","projectList":[{"projectID":1,"projectName":"RDP Housing Project","description":"RDP Housing Project in the Nort  West Province near the town of Klerksdorp. 3 000 houses are to be built on the site of the Vermaak Farm P8098","dateRegistered":1411164000000,"completeFlag":0,"companyID":1,"projectSiteList":[{"projectSiteID":8,"projectSiteName":"Community Center","activeFlag":0,"projectSiteTaskList":[],"projectID":1,"projectSiteStaffList":[{"projectSiteStaffID":20,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":8,"activeFlag":0,"firstName":"Daniel","lastName":"Bonga","email":"danbonga@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":18,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":2,"activeFlag":0,"firstName":"Bryan","lastName":"Maluleke","email":"malengadev@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":19,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":3,"activeFlag":0,"firstName":"Mmaphefo","lastName":"Mofokeng","email":"aubrey@mlab.co.za","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}}],"imageFileNameList":[]},{"projectSiteID":7,"projectSiteName":"Electrical Substation #1","activeFlag":0,"projectSiteTaskList":[{"projectSiteTaskID":48,"taskName":"Complete the earthworks","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":8,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2},{"projectSiteTaskStatusID":12,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":53,"taskName":"Connect nearby houses in test","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":17,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":53,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":51,"taskName":"Install Base Generators","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":11,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":15,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":18,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":3,"taskStatusName":"Aborted"},"projectSiteTaskID":51,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":49,"taskName":"Install concrete foundation","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":9,"dateUpdated":1410527700000,"taskStatus":{"taskS\n09-21 20:21:32.466 I/ACRA    ( 3896): READ_LOGS granted! ACRA can include LogCat and DropBox data.\n09-21 20:21:32.501 D/ACRA    ( 3896): Retrieving logcat output...\n09-21 20:21:32.516 D/ACRA    ( 3896): Writing crash report file 1411323692000.stacktrace.\n09-21 20:21:32.526 D/ACRA    ( 3896): About to start ReportSenderWorker from #handleException\n09-21 20:21:32.526 D/ACRA    ( 3896): Mark all pending reports as approved.\n09-21 20:21:32.526 D/ACRA    ( 3896): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 20:21:32.526 D/ACRA    ( 3896): Waiting for Toast + worker...\n09-21 20:21:32.526 D/ACRA    ( 3896): #checkAndSendReports - start\n09-21 20:21:32.526 D/ACRA    ( 3896): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 20:21:32.526 I/ACRA    ( 3896): Sending file 1411323692000-approved.stacktrace\n09-21 20:21:32.561 D/AbsListView( 3896): onDetachedFromWindow\n09-21 20:21:32.561 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.566 D/AbsListView( 3896): onDetachedFromWindow\n09-21 20:21:32.566 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.566 D/ACRA    ( 3896): Connect to http://192.168.1.111:8050/mwp/crash\n09-21 20:21:32.586 D/AbsListView( 3896): Get MotionRecognitionManager\n09-21 20:21:32.591 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.601 D/AbsListView( 3896): Get MotionRecognitionManager\n09-21 20:21:32.606 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.671 D/ACRA    ( 3896): Sending request to http://192.168.1.111:8050/mwp/crash\n09-21 20:21:32.736 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.811 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:32.891 I/System.out( 3896): Thread-32336 calls detatch()\n09-21 20:21:32.891 D/ACRA    ( 3896): #checkAndSendReports - finish\n09-21 20:21:32.911 E/CacheUtil( 3896): ......Data cache json written to disk,  - path: /data/data/com.boha.monitor.admin/files/companydata.json - length: 43298\n09-21 20:21:32.926 D/ACRA    ( 3896): Wait for Toast + worker ended. Kill Application ? true\n--------- beginning of /dev/log/system\n09-21 20:21:32.931 E/AndroidRuntime( 3896): FATAL EXCEPTION: Timer-0\n09-21 20:21:32.931 E/AndroidRuntime( 3896): Process: com.boha.monitor.admin, PID: 3896\n09-21 20:21:32.931 E/AndroidRuntime( 3896): android.util.AndroidRuntimeException: Animators may only be run on Looper threads\n09-21 20:21:32.931 E/AndroidRuntime( 3896): 	at android.animation.ValueAnimator.start(ValueAnimator.java:924)\n09-21 20:21:32.931 E/AndroidRuntime( 3896): 	at android.animation.ValueAnimator.start(ValueAnimator.java:946)\n09-21 20:21:32.931 E/AndroidRuntime( 3896): 	at android.animation.ObjectAnimator.start(ObjectAnimator.java:465)\n09-21 20:21:32.931 E/AndroidRuntime( 3896): 	at com.com.boha.monitor.library.util.Util.animateTextScaleY(Util.java:115)\n09-21 20:21:32.931 E/AndroidRuntime( 3896): 	at com.com.boha.monitor.library.fragments.ProjectListFragment$1.run(ProjectListFragment.java:99)\n09-21 20:21:32.931 E/AndroidRuntime( 3896): 	at java.util.Timer$TimerImpl.run(Timer.java:284)\n09-21 20:21:33.106 W/dalvikvm( 3896): threadid=17: thread exiting with uncaught exception (group=0x417c2c08)\n09-21 20:21:33.106 E/ACRA    ( 3896): ACRA caught a AndroidRuntimeException exception for com.boha.monitor.admin. Building report.\n09-21 20:21:33.106 D/ACRA    ( 3896): Using custom Report Fields\n09-21 20:21:33.111 I/ACRA    ( 3896): READ_LOGS granted! ACRA can include LogCat and DropBox data.\n09-21 20:21:33.181 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:33.181 D/AbsListView( 3896): unregisterIRListener() is called \n09-21 20:21:33.186 D/ACRA    ( 3896): Retrieving logcat output...\n'),
(6, NULL, '2014-09-21 20:23:34', 'com.boha.monitor.admin', '1.0', '1', 'samsung', 'GT-I9500', '4.4.2', 'android.util.AndroidRuntimeException: Animators may only be run on Looper threads\n	at android.animation.ValueAnimator.start(ValueAnimator.java:924)\n	at android.animation.ValueAnimator.start(ValueAnimator.java:946)\n	at android.animation.ObjectAnimator.start(ObjectAnimator.java:465)\n	at com.com.boha.monitor.library.util.Util.animateTextScaleY(Util.java:115)\n	at com.com.boha.monitor.library.fragments.ProjectListFragment$1$1.run(ProjectListFragment.java:103)\n	at java.util.Timer$TimerImpl.run(Timer.java:284)\n', '--------- beginning of /dev/log/main\n09-21 20:23:31.971 D/dalvikvm( 4574): Late-enabling CheckJNI\n09-21 20:23:32.096 D/MonApp  ( 4574): ############################ onCreate MonApp has started ---------------->\n09-21 20:23:32.106 D/ACRA    ( 4574): ACRA is enabled for com.boha.monitor.admin, intializing...\n09-21 20:23:32.121 D/ACRA    ( 4574): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 20:23:32.121 E/MonApp  ( 4574): ###### ACRA Crash Reporting has been initiated\n09-21 20:23:32.121 E/MonApp  ( 4574): initializing Volley Networking ...\n09-21 20:23:32.136 I/MonApp  ( 4574): ********** Yebo! Volley Networking has been initialized, cache size: 16384 KB\n09-21 20:23:32.161 E/MonApp  ( 4574): ###### Glide has been initialised\n09-21 20:23:32.176 W/ApplicationPackageManager( 4574): getCSCPackageItemText()\n09-21 20:23:32.621 D/OpenGLRenderer( 4574): Enabling debug mode 0\n09-21 20:23:32.646 D/CacheUtil( 4574): ################ getting cached data ..................\n09-21 20:23:32.646 D/CacheUtil( 4574): ########### doInBackground: getting cached data ....\n09-21 20:23:32.891 E/CacheUtil( 4574): $$$$$$$$$$$$ cached data retrieved\n09-21 20:23:33.066 D/AbsListView( 4574): Get MotionRecognitionManager\n09-21 20:23:33.086 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.096 D/AbsListView( 4574): Get MotionRecognitionManager\n09-21 20:23:33.106 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.111 W/WebCheck( 4574): ###### WIFI AVAILABLE on check\n09-21 20:23:33.111 W/WebCheck( 4574): ###### WIFI CONNECTED on check\n09-21 20:23:33.111 W/WebCheck( 4574): ###### MOBILE_NETWORK AVAILABLE on check\n09-21 20:23:33.111 D/WebCheck( 4574): @@@ MOBILE_NETWORK NOT CONNECTED on check\n09-21 20:23:33.111 D/WebCheck( 4574): ###### Network check completed in 4 ms\n09-21 20:23:33.126 D/TimerUtil( 4574): ########## Websocket Session Timer starting .....\n09-21 20:23:33.191 D/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ### #### -------------> starting mWebSocketClient.connect ...\n09-21 20:23:33.281 W/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ########## WEBSOCKET Opened: Web Socket Protocol Handshake\n09-21 20:23:33.316 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.341 D/dalvikvm( 4574): GC_FOR_ALLOC freed 1860K, 15% free 17976K/21088K, paused 21ms, total 24ms\n09-21 20:23:33.366 D/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ########### web socket request sent after onOpen\n09-21 20:23:33.366 D/com.com.boha.monitor.library.util.WebSocketUtil( 4574): {"companyID":1,"requestType":108}\n09-21 20:23:33.366 W/TimerUtil( 4574): ########## Websocket Session Timer KILLED\n09-21 20:23:33.366 I/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ########## onMessage, length: 357 elapsed: 0.242 seconds\n09-21 20:23:33.366 I/com.com.boha.monitor.library.util.WebSocketUtil( 4574): {"statusCode":0,"sessionID":"b0d7cc0a-802f-4342-b3dc-0004943c3477","taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[]}\n09-21 20:23:33.386 D/dalvikvm( 4574): GC_FOR_ALLOC freed 158K, 15% free 17989K/21088K, paused 16ms, total 17ms\n09-21 20:23:33.406 I/dalvikvm-heap( 4574): Grow heap (frag case) to 29.335MB for 11059216-byte allocation\n09-21 20:23:33.426 D/dalvikvm( 4574): GC_FOR_ALLOC freed 1K, 10% free 28788K/31892K, paused 20ms, total 20ms\n09-21 20:23:33.526 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.526 D/ProgressBar( 4574): updateDrawableBounds: left = 0\n09-21 20:23:33.526 D/ProgressBar( 4574): updateDrawableBounds: top = 0\n09-21 20:23:33.526 D/ProgressBar( 4574): updateDrawableBounds: right = 48\n09-21 20:23:33.526 D/ProgressBar( 4574): updateDrawableBounds: bottom = 48\n09-21 20:23:33.551 I/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ########## onMessage ByteBuffer capacity: 3227\n09-21 20:23:33.566 I/System.out( 4574): ###----> Unpack zip zippedFile, elapsed ms: 15\n09-21 20:23:33.566 E/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ############# onMessage, unpacked length: 43298 elapsed: 0.426 seconds\n09-21 20:23:33.566 E/com.com.boha.monitor.library.util.WebSocketUtil( 4574): {"statusCode":0,"taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[],"company":{"companyID":1,"companyName":"Hackers Construction","projectList":[{"projectID":1,"projectName":"RDP Housing Project","description":"RDP Housing Project in the Nort  West Province near the town of Klerksdorp. 3 000 houses are to be built on the site of the Vermaak Farm P8098","dateRegistered":1411164000000,"completeFlag":0,"companyID":1,"projectSiteList":[{"projectSiteID":8,"projectSiteName":"Community Center","activeFlag":0,"projectSiteTaskList":[],"projectID":1,"projectSiteStaffList":[{"projectSiteStaffID":20,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":8,"activeFlag":0,"firstName":"Daniel","lastName":"Bonga","email":"danbonga@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":18,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":2,"activeFlag":0,"firstName":"Bryan","lastName":"Maluleke","email":"malengadev@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":19,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":3,"activeFlag":0,"firstName":"Mmaphefo","lastName":"Mofokeng","email":"aubrey@mlab.co.za","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}}],"imageFileNameList":[]},{"projectSiteID":7,"projectSiteName":"Electrical Substation #1","activeFlag":0,"projectSiteTaskList":[{"projectSiteTaskID":48,"taskName":"Complete the earthworks","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":8,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2},{"projectSiteTaskStatusID":12,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":53,"taskName":"Connect nearby houses in test","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":17,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":53,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":51,"taskName":"Install Base Generators","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":11,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":15,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":18,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":3,"taskStatusName":"Aborted"},"projectSiteTaskID":51,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":49,"taskName":"Install concrete foundation","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":9,"dateUpdated":1410527700000,"taskStatus":{"taskS\n09-21 20:23:33.586 W/dalvikvm( 4574): threadid=17: thread exiting with uncaught exception (group=0x417c2c08)\n09-21 20:23:33.586 E/ACRA    ( 4574): ACRA caught a AndroidRuntimeException exception for com.boha.monitor.admin. Building report.\n09-21 20:23:33.586 D/ACRA    ( 4574): Using custom Report Fields\n09-21 20:23:33.596 I/ACRA    ( 4574): READ_LOGS granted! ACRA can include LogCat and DropBox data.\n09-21 20:23:33.631 D/ACRA    ( 4574): Retrieving logcat output...\n'),
(7, NULL, '2014-09-21 20:23:34', 'com.boha.monitor.admin', '1.0', '1', 'samsung', 'GT-I9500', '4.4.2', 'android.util.AndroidRuntimeException: Animators may only be run on Looper threads\n	at android.animation.ValueAnimator.start(ValueAnimator.java:924)\n	at android.animation.ValueAnimator.start(ValueAnimator.java:946)\n	at android.animation.ObjectAnimator.start(ObjectAnimator.java:465)\n	at com.com.boha.monitor.library.util.Util.animateTextScaleY(Util.java:115)\n	at com.com.boha.monitor.library.fragments.ProjectListFragment$1$1.run(ProjectListFragment.java:103)\n	at java.util.Timer$TimerImpl.run(Timer.java:284)\n', '--------- beginning of /dev/log/main\n09-21 20:23:31.971 D/dalvikvm( 4574): Late-enabling CheckJNI\n09-21 20:23:32.096 D/MonApp  ( 4574): ############################ onCreate MonApp has started ---------------->\n09-21 20:23:32.106 D/ACRA    ( 4574): ACRA is enabled for com.boha.monitor.admin, intializing...\n09-21 20:23:32.121 D/ACRA    ( 4574): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 20:23:32.121 E/MonApp  ( 4574): ###### ACRA Crash Reporting has been initiated\n09-21 20:23:32.121 E/MonApp  ( 4574): initializing Volley Networking ...\n09-21 20:23:32.136 I/MonApp  ( 4574): ********** Yebo! Volley Networking has been initialized, cache size: 16384 KB\n09-21 20:23:32.161 E/MonApp  ( 4574): ###### Glide has been initialised\n09-21 20:23:32.176 W/ApplicationPackageManager( 4574): getCSCPackageItemText()\n09-21 20:23:32.621 D/OpenGLRenderer( 4574): Enabling debug mode 0\n09-21 20:23:32.646 D/CacheUtil( 4574): ################ getting cached data ..................\n09-21 20:23:32.646 D/CacheUtil( 4574): ########### doInBackground: getting cached data ....\n09-21 20:23:32.891 E/CacheUtil( 4574): $$$$$$$$$$$$ cached data retrieved\n09-21 20:23:33.066 D/AbsListView( 4574): Get MotionRecognitionManager\n09-21 20:23:33.086 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.096 D/AbsListView( 4574): Get MotionRecognitionManager\n09-21 20:23:33.106 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.111 W/WebCheck( 4574): ###### WIFI AVAILABLE on check\n09-21 20:23:33.111 W/WebCheck( 4574): ###### WIFI CONNECTED on check\n09-21 20:23:33.111 W/WebCheck( 4574): ###### MOBILE_NETWORK AVAILABLE on check\n09-21 20:23:33.111 D/WebCheck( 4574): @@@ MOBILE_NETWORK NOT CONNECTED on check\n09-21 20:23:33.111 D/WebCheck( 4574): ###### Network check completed in 4 ms\n09-21 20:23:33.126 D/TimerUtil( 4574): ########## Websocket Session Timer starting .....\n09-21 20:23:33.191 D/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ### #### -------------> starting mWebSocketClient.connect ...\n09-21 20:23:33.281 W/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ########## WEBSOCKET Opened: Web Socket Protocol Handshake\n09-21 20:23:33.316 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.341 D/dalvikvm( 4574): GC_FOR_ALLOC freed 1860K, 15% free 17976K/21088K, paused 21ms, total 24ms\n09-21 20:23:33.366 D/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ########### web socket request sent after onOpen\n09-21 20:23:33.366 D/com.com.boha.monitor.library.util.WebSocketUtil( 4574): {"companyID":1,"requestType":108}\n09-21 20:23:33.366 W/TimerUtil( 4574): ########## Websocket Session Timer KILLED\n09-21 20:23:33.366 I/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ########## onMessage, length: 357 elapsed: 0.242 seconds\n09-21 20:23:33.366 I/com.com.boha.monitor.library.util.WebSocketUtil( 4574): {"statusCode":0,"sessionID":"b0d7cc0a-802f-4342-b3dc-0004943c3477","taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[]}\n09-21 20:23:33.386 D/dalvikvm( 4574): GC_FOR_ALLOC freed 158K, 15% free 17989K/21088K, paused 16ms, total 17ms\n09-21 20:23:33.406 I/dalvikvm-heap( 4574): Grow heap (frag case) to 29.335MB for 11059216-byte allocation\n09-21 20:23:33.426 D/dalvikvm( 4574): GC_FOR_ALLOC freed 1K, 10% free 28788K/31892K, paused 20ms, total 20ms\n09-21 20:23:33.526 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.526 D/ProgressBar( 4574): updateDrawableBounds: left = 0\n09-21 20:23:33.526 D/ProgressBar( 4574): updateDrawableBounds: top = 0\n09-21 20:23:33.526 D/ProgressBar( 4574): updateDrawableBounds: right = 48\n09-21 20:23:33.526 D/ProgressBar( 4574): updateDrawableBounds: bottom = 48\n09-21 20:23:33.551 I/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ########## onMessage ByteBuffer capacity: 3227\n09-21 20:23:33.566 I/System.out( 4574): ###----> Unpack zip zippedFile, elapsed ms: 15\n09-21 20:23:33.566 E/com.com.boha.monitor.library.util.WebSocketUtil( 4574): ############# onMessage, unpacked length: 43298 elapsed: 0.426 seconds\n09-21 20:23:33.566 E/com.com.boha.monitor.library.util.WebSocketUtil( 4574): {"statusCode":0,"taskStatusList":[],"projectStatusTypeList":[],"projectSiteList":[],"projectList":[],"companyStaffList":[],"projectSiteStaffList":[],"companyStaffTypeList":[],"projectDiaryRecordList":[],"projectSiteTaskList":[],"projectSiteTaskStatusList":[],"errorStoreList":[],"errorStoreAndroidList":[],"company":{"companyID":1,"companyName":"Hackers Construction","projectList":[{"projectID":1,"projectName":"RDP Housing Project","description":"RDP Housing Project in the Nort  West Province near the town of Klerksdorp. 3 000 houses are to be built on the site of the Vermaak Farm P8098","dateRegistered":1411164000000,"completeFlag":0,"companyID":1,"projectSiteList":[{"projectSiteID":8,"projectSiteName":"Community Center","activeFlag":0,"projectSiteTaskList":[],"projectID":1,"projectSiteStaffList":[{"projectSiteStaffID":20,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":8,"activeFlag":0,"firstName":"Daniel","lastName":"Bonga","email":"danbonga@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":18,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":2,"activeFlag":0,"firstName":"Bryan","lastName":"Maluleke","email":"malengadev@gmail.com","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}},{"projectSiteStaffID":19,"dateRegistered":1411164000000,"projectDiaryRecordList":[],"projectSiteTaskStatusList":[],"projectSiteID":8,"companyStaff":{"companyStaffID":3,"activeFlag":0,"firstName":"Mmaphefo","lastName":"Mofokeng","email":"aubrey@mlab.co.za","companyName":"Hackers Construction","companyStaffType":{"companyStaffTypeID":2,"companyStaffTypeName":"Project Site Staff"},"companyID":1,"projectSiteStaffList":[]}}],"imageFileNameList":[]},{"projectSiteID":7,"projectSiteName":"Electrical Substation #1","activeFlag":0,"projectSiteTaskList":[{"projectSiteTaskID":48,"taskName":"Complete the earthworks","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":8,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2},{"projectSiteTaskStatusID":12,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":48,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":53,"taskName":"Connect nearby houses in test","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":17,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":53,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":51,"taskName":"Install Base Generators","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":11,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":5,"taskStatusName":"Delayed - Staff"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":15,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":6,"taskStatusName":"Delayed - No Materials"},"projectSiteTaskID":51,"projectSiteStaffID":2},{"projectSiteTaskStatusID":18,"dateUpdated":1410527700000,"taskStatus":{"taskStatusID":3,"taskStatusName":"Aborted"},"projectSiteTaskID":51,"projectSiteStaffID":2}],"imageFileNameList":[]},{"projectSiteTaskID":49,"taskName":"Install concrete foundation","dateRegistered":1411164000000,"projectSiteID":7,"projectSiteTaskStatusList":[{"projectSiteTaskStatusID":9,"dateUpdated":1410527700000,"taskStatus":{"taskS\n09-21 20:23:33.586 W/dalvikvm( 4574): threadid=17: thread exiting with uncaught exception (group=0x417c2c08)\n09-21 20:23:33.586 E/ACRA    ( 4574): ACRA caught a AndroidRuntimeException exception for com.boha.monitor.admin. Building report.\n09-21 20:23:33.586 D/ACRA    ( 4574): Using custom Report Fields\n09-21 20:23:33.596 I/ACRA    ( 4574): READ_LOGS granted! ACRA can include LogCat and DropBox data.\n09-21 20:23:33.631 D/ACRA    ( 4574): Retrieving logcat output...\n09-21 20:23:33.641 D/ACRA    ( 4574): Writing crash report file 1411323813000.stacktrace.\n09-21 20:23:33.656 D/ACRA    ( 4574): About to start ReportSenderWorker from #handleException\n09-21 20:23:33.656 D/ACRA    ( 4574): Mark all pending reports as approved.\n09-21 20:23:33.656 D/ACRA    ( 4574): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 20:23:33.656 D/ACRA    ( 4574): Waiting for Toast + worker...\n09-21 20:23:33.656 D/ACRA    ( 4574): #checkAndSendReports - start\n09-21 20:23:33.656 D/ACRA    ( 4574): Looking for error files in /data/data/com.boha.monitor.admin/files\n09-21 20:23:33.656 I/ACRA    ( 4574): Sending file 1411323813000-approved.stacktrace\n09-21 20:23:33.681 D/ACRA    ( 4574): Connect to http://192.168.1.111:8050/mwp/crash\n09-21 20:23:33.701 D/AbsListView( 4574): onDetachedFromWindow\n09-21 20:23:33.701 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.701 D/AbsListView( 4574): onDetachedFromWindow\n09-21 20:23:33.701 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.711 D/AbsListView( 4574): Get MotionRecognitionManager\n09-21 20:23:33.711 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.721 D/AbsListView( 4574): Get MotionRecognitionManager\n09-21 20:23:33.726 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.761 D/ACRA    ( 4574): Sending request to http://192.168.1.111:8050/mwp/crash\n09-21 20:23:33.811 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.841 E/CacheUtil( 4574): ......Data cache json written to disk,  - path: /data/data/com.boha.monitor.admin/files/companydata.json - length: 43298\n09-21 20:23:33.856 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:33.881 I/System.out( 4574): Thread-32391 calls detatch()\n09-21 20:23:33.881 D/ACRA    ( 4574): #checkAndSendReports - finish\n09-21 20:23:33.956 D/ACRA    ( 4574): Wait for Toast + worker ended. Kill Application ? true\n--------- beginning of /dev/log/system\n09-21 20:23:33.956 E/AndroidRuntime( 4574): FATAL EXCEPTION: Timer-0\n09-21 20:23:33.956 E/AndroidRuntime( 4574): Process: com.boha.monitor.admin, PID: 4574\n09-21 20:23:33.956 E/AndroidRuntime( 4574): android.util.AndroidRuntimeException: Animators may only be run on Looper threads\n09-21 20:23:33.956 E/AndroidRuntime( 4574): 	at android.animation.ValueAnimator.start(ValueAnimator.java:924)\n09-21 20:23:33.956 E/AndroidRuntime( 4574): 	at android.animation.ValueAnimator.start(ValueAnimator.java:946)\n09-21 20:23:33.956 E/AndroidRuntime( 4574): 	at android.animation.ObjectAnimator.start(ObjectAnimator.java:465)\n09-21 20:23:33.956 E/AndroidRuntime( 4574): 	at com.com.boha.monitor.library.util.Util.animateTextScaleY(Util.java:115)\n09-21 20:23:33.956 E/AndroidRuntime( 4574): 	at com.com.boha.monitor.library.fragments.ProjectListFragment$1$1.run(ProjectListFragment.java:103)\n09-21 20:23:33.956 E/AndroidRuntime( 4574): 	at java.util.Timer$TimerImpl.run(Timer.java:284)\n09-21 20:23:34.166 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:34.166 D/AbsListView( 4574): unregisterIRListener() is called \n09-21 20:23:34.226 W/dalvikvm( 4574): threadid=17: thread exiting with uncaught exception (group=0x417c2c08)\n09-21 20:23:34.226 E/ACRA    ( 4574): ACRA caught a AndroidRuntimeException exception for com.boha.monitor.admin. Building report.\n09-21 20:23:34.226 D/ACRA    ( 4574): Using custom Report Fields\n09-21 20:23:34.226 I/ACRA    ( 4574): READ_LOGS granted! ACRA can include LogCat and DropBox data.\n09-21 20:23:34.266 D/ACRA    ( 4574): Retrieving logcat output...\n');

-- --------------------------------------------------------

--
-- Table structure for table `executive`
--

CREATE TABLE `executive` (
  `executiveID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL DEFAULT '',
  `lastName` varchar(50) NOT NULL DEFAULT '',
  `email` varchar(140) NOT NULL DEFAULT '',
  `companyID` int(10) unsigned NOT NULL,
  `dateRegistered` datetime NOT NULL,
  `pin` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`executiveID`),
  UNIQUE KEY `email` (`email`),
  KEY `companyID` (`companyID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `gcmDevice`
--

CREATE TABLE `gcmDevice` (
  `gcmDeviceID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyStaffID` int(10) unsigned DEFAULT NULL,
  `registrationID` text NOT NULL,
  `manufacturer` varchar(100) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `product` varchar(100) DEFAULT NULL,
  `messageCount` int(10) unsigned DEFAULT NULL,
  `dateRegistered` datetime NOT NULL,
  `serialNumber` varchar(255) DEFAULT NULL,
  `companyID` int(10) unsigned NOT NULL,
  `projectSiteID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`gcmDeviceID`),
  KEY `companyStaffID` (`companyStaffID`),
  KEY `companyID` (`companyID`),
  KEY `projectSiteID` (`projectSiteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `projectID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255) NOT NULL,
  `description` text,
  `dateRegistered` datetime NOT NULL,
  `companyID` int(10) unsigned NOT NULL,
  `completeFlag` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectID`),
  UNIQUE KEY `index2` (`companyID`,`projectName`),
  KEY `fk005` (`companyID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`projectID`, `projectName`, `description`, `dateRegistered`, `companyID`, `completeFlag`) VALUES
(1, 'RDP Housing Project', 'RDP Housing Project in the Nort  West Province near the town of Klerksdorp. 3 000 houses are to be built on the site of the Vermaak Farm P8098', '2014-09-20 00:00:00', 1, 0),
(2, 'Rustenburg Mall Project', 'Mega mall coming up in Rustenburg. 200,000 square metres. A large construction project!', '2014-09-19 00:00:00', 1, 0),
(3, 'Midrand Housing Estate', 'Upmarket housing development on a 3 000 ha estate. Features 3, 4 and 5 bedroom homes and an apartment complex.', '2014-09-20 00:00:00', 1, 0),
(4, 'University Campus Project', 'New University of Mpumalanga campus on a 1000 ha site on the edge of Nelspruit. Has a total of 65 diffrent buildings and structures.', '2014-09-12 00:00:00', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `projectDiaryRecord`
--

CREATE TABLE `projectDiaryRecord` (
  `projectDiaryRecordID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectSiteStaffID` int(10) unsigned NOT NULL,
  `diaryDate` datetime NOT NULL,
  `projectStatusTypeID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`projectDiaryRecordID`),
  KEY `index2a1` (`projectStatusTypeID`),
  KEY `index7zx1` (`projectSiteStaffID`),
  KEY `fk101` (`projectSiteStaffID`),
  KEY `fk102` (`projectStatusTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `projectSite`
--

CREATE TABLE `projectSite` (
  `projectSiteID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectSiteName` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `projectID` int(10) unsigned NOT NULL,
  `activeFlag` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectSiteID`),
  UNIQUE KEY `index2` (`projectID`,`projectSiteName`),
  KEY `fk006` (`projectID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `projectSite`
--

INSERT INTO `projectSite` (`projectSiteID`, `projectSiteName`, `latitude`, `longitude`, `projectID`, `activeFlag`) VALUES
(1, 'House #1001', NULL, NULL, 1, 0),
(2, 'House #1002', NULL, NULL, 1, 0),
(4, 'House #1003', NULL, NULL, 1, 0),
(5, 'House #1004', NULL, NULL, 1, 0),
(6, 'House #1005', NULL, NULL, 1, 0),
(7, 'Electrical Substation #1', NULL, NULL, 1, 0),
(8, 'Community Center', NULL, NULL, 1, 0),
(9, 'School #101', NULL, NULL, 1, 0),
(10, 'School #202', NULL, NULL, 1, 0),
(13, 'House 100 - 3 Beds', NULL, NULL, 3, 0),
(14, 'House 101 - 4 Beds', NULL, NULL, 3, 0),
(15, 'House 102 - 4 Beds', NULL, NULL, 3, 0),
(16, 'House 103 - 5 Beds', NULL, NULL, 3, 0),
(17, 'House 104 - 5 Beds', NULL, NULL, 3, 0),
(18, 'House 105 - 3 Beds', NULL, NULL, 3, 0),
(19, 'House 106 - 3 Beds', NULL, NULL, 3, 0),
(20, 'Apartment Complex', NULL, NULL, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `projectSiteStaff`
--

CREATE TABLE `projectSiteStaff` (
  `projectSiteStaffID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectSiteID` int(10) unsigned NOT NULL,
  `companyStaffID` int(10) unsigned NOT NULL,
  `dateRegistered` datetime NOT NULL,
  `activeFlag` int(2) DEFAULT NULL,
  `pin` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`projectSiteStaffID`),
  UNIQUE KEY `index2` (`projectSiteID`,`companyStaffID`),
  KEY `fk007` (`companyStaffID`),
  KEY `fk007b` (`projectSiteID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=66 ;

--
-- Dumping data for table `projectSiteStaff`
--

INSERT INTO `projectSiteStaff` (`projectSiteStaffID`, `projectSiteID`, `companyStaffID`, `dateRegistered`, `activeFlag`, `pin`) VALUES
(1, 1, 2, '2014-09-19 00:00:00', 0, '123456'),
(2, 1, 3, '2014-09-20 00:00:00', 0, '123456'),
(3, 1, 4, '2014-09-20 00:00:00', 0, '123456'),
(4, 2, 5, '2014-09-20 00:00:00', 0, '12345'),
(6, 2, 6, '2014-09-20 00:00:00', 0, '12345'),
(10, 4, 6, '2014-09-20 00:00:00', 0, '33445'),
(11, 4, 2, '2014-09-20 00:00:00', 0, '54654'),
(12, 4, 4, '2014-09-20 00:00:00', 0, '24545'),
(13, 7, 3, '2014-09-20 00:00:00', 0, '34545'),
(14, 7, 2, '2014-09-20 00:00:00', 0, '453445'),
(15, 7, 4, '2014-09-20 00:00:00', 0, '56756'),
(16, 7, 6, '2014-09-20 00:00:00', 0, '112334'),
(17, 7, 7, '2014-09-20 00:00:00', 0, '34256'),
(18, 8, 2, '2014-09-20 00:00:00', 0, '998657'),
(19, 8, 3, '2014-09-20 00:00:00', 0, '657656'),
(20, 8, 8, '2014-09-20 00:00:00', 0, '34567'),
(21, 9, 2, '2014-09-20 00:00:00', 0, '546775'),
(24, 4, 9, '2014-09-20 00:00:00', 0, '212132'),
(25, 4, 10, '2014-09-20 00:00:00', 0, '212132'),
(29, 9, 3, '2014-09-20 00:00:00', 0, '44534'),
(30, 9, 5, '2014-09-20 00:00:00', 0, '44534'),
(31, 9, 6, '2014-09-20 00:00:00', 0, '44534'),
(32, 9, 7, '2014-09-20 00:00:00', 0, '44534'),
(33, 9, 8, '2014-09-20 00:00:00', 0, '44534'),
(34, 9, 9, '2014-09-20 00:00:00', 0, '44534'),
(35, 9, 10, '2014-09-20 00:00:00', 0, '44534'),
(38, 10, 2, '2014-09-20 00:00:00', 0, '72637'),
(40, 10, 12, '2014-09-20 00:00:00', 0, '72678'),
(41, 10, 13, '2014-09-20 00:00:00', 0, '253666'),
(42, 10, 6, '2014-09-20 00:00:00', 0, '53637'),
(43, 10, 14, '2014-09-20 00:00:00', 0, '726376'),
(44, 10, 15, '2014-09-20 00:00:00', 0, '726358'),
(47, 10, 16, '2014-09-20 00:00:00', 0, '286386'),
(48, 10, 17, '2014-09-20 00:00:00', 0, '19827'),
(49, 13, 21, '2014-09-12 00:00:00', 0, '45454'),
(50, 13, 22, '2014-09-12 11:11:00', 0, '65547'),
(51, 13, 23, '2014-09-12 11:11:00', 0, '223323'),
(52, 13, 2, '2014-09-12 11:11:00', 0, '323232'),
(53, 14, 2, '2014-09-12 11:11:00', 0, '65565'),
(54, 14, 24, '2014-09-12 11:11:00', 0, '111222'),
(55, 14, 25, '2014-09-12 11:11:00', 0, '57475'),
(58, 14, 4, '2014-09-12 11:11:00', 0, '76576'),
(59, 15, 2, '2014-09-12 11:11:00', 0, '867565'),
(60, 15, 23, '2014-09-12 11:11:00', 0, '13465'),
(61, 16, 2, '2014-09-12 11:11:00', 0, '342543'),
(62, 16, 21, '2014-09-12 11:11:00', 0, '654675'),
(63, 17, 4, '2014-09-12 11:11:00', 0, '76567'),
(64, 17, 20, '2014-09-12 11:11:00', 0, '436543'),
(65, 17, 19, '2014-09-12 11:11:00', 0, '76876');

-- --------------------------------------------------------

--
-- Table structure for table `projectSiteTask`
--

CREATE TABLE `projectSiteTask` (
  `projectSiteTaskID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectSiteID` int(10) unsigned NOT NULL,
  `taskName` varchar(45) NOT NULL,
  `taskDescription` text,
  `dateRegistered` datetime NOT NULL,
  PRIMARY KEY (`projectSiteTaskID`),
  UNIQUE KEY `index2` (`projectSiteID`,`taskName`),
  KEY `fk0012` (`projectSiteID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=54 ;

--
-- Dumping data for table `projectSiteTask`
--

INSERT INTO `projectSiteTask` (`projectSiteTaskID`, `projectSiteID`, `taskName`, `taskDescription`, `dateRegistered`) VALUES
(1, 1, 'Complete the concrete foundation', NULL, '2014-09-20 00:00:00'),
(2, 1, 'Build the external walls to window height', NULL, '2014-09-20 00:00:00'),
(4, 1, 'Install window frames', NULL, '2014-09-20 00:00:00'),
(5, 1, 'Install internal door frames', NULL, '2014-09-20 00:00:00'),
(6, 1, 'Complete external walls', NULL, '2014-09-20 00:00:00'),
(7, 1, 'Install Electrical infrastructure', NULL, '2014-09-20 00:00:00'),
(8, 1, 'Install Plumbing infrastructure', NULL, '2014-09-20 00:00:00'),
(9, 1, 'Install Roof support structure', NULL, '2014-09-20 00:00:00'),
(11, 1, 'Install Roof Tiles', NULL, '2014-09-20 00:00:00'),
(12, 2, 'Complete the concrete foundation', NULL, '2014-09-20 00:00:00'),
(13, 2, 'Build the external walls to window height', NULL, '2014-09-20 00:00:00'),
(14, 2, 'Install window frames', NULL, '2014-09-20 00:00:00'),
(15, 2, 'Install internal door frames', NULL, '2014-09-20 00:00:00'),
(16, 2, 'Complete external walls', NULL, '2014-09-20 00:00:00'),
(17, 2, 'Install Electrical infrastructure', NULL, '2014-09-20 00:00:00'),
(18, 2, 'Install Plumbing infrastructure', NULL, '2014-09-20 00:00:00'),
(19, 2, 'Install Roof support structure', NULL, '2014-09-20 00:00:00'),
(20, 2, 'Install Roof Tiles', NULL, '2014-09-20 00:00:00'),
(21, 4, 'Complete the concrete foundation', NULL, '2014-09-20 00:00:00'),
(22, 4, 'Build the external walls to window height', NULL, '2014-09-20 00:00:00'),
(23, 4, 'Install window frames', NULL, '2014-09-20 00:00:00'),
(24, 4, 'Install internal door frames', NULL, '2014-09-20 00:00:00'),
(25, 4, 'Complete external walls', NULL, '2014-09-20 00:00:00'),
(26, 4, 'Install Electrical infrastructure', NULL, '2014-09-20 00:00:00'),
(27, 4, 'Install Plumbing infrastructure', NULL, '2014-09-20 00:00:00'),
(28, 4, 'Install Roof support structure', NULL, '2014-09-20 00:00:00'),
(29, 4, 'Install Roof Tiles', NULL, '2014-09-20 00:00:00'),
(30, 5, 'Complete the concrete foundation', NULL, '2014-09-20 00:00:00'),
(31, 5, 'Build the external walls to window height', NULL, '2014-09-20 00:00:00'),
(32, 5, 'Install window frames', NULL, '2014-09-20 00:00:00'),
(33, 5, 'Install internal door frames', NULL, '2014-09-20 00:00:00'),
(34, 5, 'Complete external walls', NULL, '2014-09-20 00:00:00'),
(35, 5, 'Install Electrical infrastructure', NULL, '2014-09-20 00:00:00'),
(36, 5, 'Install Plumbing infrastructure', NULL, '2014-09-20 00:00:00'),
(37, 5, 'Install Roof support structure', NULL, '2014-09-20 00:00:00'),
(38, 5, 'Install Roof Tiles', NULL, '2014-09-20 00:00:00'),
(39, 6, 'Complete the concrete foundation', NULL, '2014-09-20 00:00:00'),
(40, 6, 'Build the external walls to window height', NULL, '2014-09-20 00:00:00'),
(41, 6, 'Install window frames', NULL, '2014-09-20 00:00:00'),
(42, 6, 'Install internal door frames', NULL, '2014-09-20 00:00:00'),
(43, 6, 'Complete external walls', NULL, '2014-09-20 00:00:00'),
(44, 6, 'Install Electrical infrastructure', NULL, '2014-09-20 00:00:00'),
(45, 6, 'Install Plumbing infrastructure', NULL, '2014-09-20 00:00:00'),
(46, 6, 'Install Roof support structure', NULL, '2014-09-20 00:00:00'),
(47, 6, 'Install Roof Tiles', NULL, '2014-09-20 00:00:00'),
(48, 7, 'Complete the earthworks', NULL, '2014-09-20 00:00:00'),
(49, 7, 'Install concrete foundation', NULL, '2014-09-20 00:00:00'),
(50, 7, 'Install Electrical infrastructure', NULL, '2014-09-20 00:00:00'),
(51, 7, 'Install Base Generators', NULL, '2014-09-20 00:00:00'),
(52, 7, 'Test Grid Connection', NULL, '2014-09-20 00:00:00'),
(53, 7, 'Connect nearby houses in test', NULL, '2014-09-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `projectSiteTaskStatus`
--

CREATE TABLE `projectSiteTaskStatus` (
  `projectSiteTaskStatusID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectSiteTaskID` int(10) unsigned NOT NULL,
  `taskStatusID` int(10) unsigned NOT NULL,
  `projectSiteStaffID` int(10) unsigned NOT NULL,
  `dateUpdated` datetime NOT NULL,
  PRIMARY KEY (`projectSiteTaskStatusID`),
  KEY `index2` (`projectSiteTaskID`),
  KEY `index3` (`taskStatusID`),
  KEY `index4` (`projectSiteStaffID`),
  KEY `fk201` (`projectSiteStaffID`),
  KEY `fk202` (`projectSiteTaskID`),
  KEY `fk203` (`taskStatusID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `projectSiteTaskStatus`
--

INSERT INTO `projectSiteTaskStatus` (`projectSiteTaskStatusID`, `projectSiteTaskID`, `taskStatusID`, `projectSiteStaffID`, `dateUpdated`) VALUES
(3, 1, 1, 2, '2014-09-12 15:15:00'),
(4, 2, 2, 2, '2014-09-12 15:15:00'),
(5, 2, 3, 2, '2014-09-12 15:15:00'),
(8, 48, 5, 2, '2014-09-12 15:15:00'),
(9, 49, 5, 2, '2014-09-12 15:15:00'),
(10, 50, 5, 2, '2014-09-12 15:15:00'),
(11, 51, 5, 2, '2014-09-12 15:15:00'),
(12, 48, 5, 2, '2014-09-12 15:15:00'),
(13, 49, 5, 2, '2014-09-12 15:15:00'),
(14, 50, 6, 2, '2014-09-12 15:15:00'),
(15, 51, 6, 2, '2014-09-12 15:15:00'),
(16, 52, 6, 2, '2014-09-12 15:15:00'),
(17, 53, 6, 2, '2014-09-12 15:15:00'),
(18, 51, 3, 2, '2014-09-12 15:15:00'),
(19, 51, 2, 2, '2014-09-01 12:15:00'),
(20, 52, 3, 2, '2014-09-01 12:15:00'),
(21, 48, 2, 2, '2014-09-01 12:15:00');

-- --------------------------------------------------------

--
-- Table structure for table `projectStatusType`
--

CREATE TABLE `projectStatusType` (
  `projectStatusTypeID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `projectStatusName` varchar(100) NOT NULL,
  PRIMARY KEY (`projectStatusTypeID`),
  UNIQUE KEY `projectStatusName` (`projectStatusName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `projectStatusType`
--

INSERT INTO `projectStatusType` (`projectStatusTypeID`, `projectStatusName`) VALUES
(10, 'Project Ahead of Schedule'),
(4, 'Project Behind Schedule'),
(13, 'Project Material Not Available'),
(3, 'Project Ongoing Normally'),
(14, 'Project Site Not Accessible'),
(12, 'Project Staff on Strike'),
(11, 'Project Stopped on account of Weather');

-- --------------------------------------------------------

--
-- Table structure for table `taskStatus`
--

CREATE TABLE `taskStatus` (
  `taskStatusID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `taskStatusName` varchar(100) NOT NULL,
  PRIMARY KEY (`taskStatusID`),
  UNIQUE KEY `index2` (`taskStatusName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `taskStatus`
--

INSERT INTO `taskStatus` (`taskStatusID`, `taskStatusName`) VALUES
(3, 'Aborted'),
(4, 'Cancelled'),
(2, 'Completed'),
(6, 'Delayed - No Materials'),
(5, 'Delayed - Staff'),
(1, 'Incomplete');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `companyStaff`
--
ALTER TABLE `companyStaff`
  ADD CONSTRAINT `fk0015` FOREIGN KEY (`companyStaffTypeID`) REFERENCES `companyStaffType` (`companyStaffTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk003` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `errorStoreAndroid`
--
ALTER TABLE `errorStoreAndroid`
  ADD CONSTRAINT `errorstoreandroid_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE;

--
-- Constraints for table `executive`
--
ALTER TABLE `executive`
  ADD CONSTRAINT `executive_ibfk_1` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE;

--
-- Constraints for table `gcmDevice`
--
ALTER TABLE `gcmDevice`
  ADD CONSTRAINT `gcmdevice_ibfk_3` FOREIGN KEY (`projectSiteID`) REFERENCES `projectSite` (`projectSiteID`) ON DELETE CASCADE,
  ADD CONSTRAINT `gcmdevice_ibfk_1` FOREIGN KEY (`companyStaffID`) REFERENCES `companyStaff` (`companyStaffID`) ON DELETE CASCADE,
  ADD CONSTRAINT `gcmdevice_ibfk_2` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE;

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `fk005` FOREIGN KEY (`companyID`) REFERENCES `company` (`companyID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `projectDiaryRecord`
--
ALTER TABLE `projectDiaryRecord`
  ADD CONSTRAINT `fk101` FOREIGN KEY (`projectSiteStaffID`) REFERENCES `projectSiteStaff` (`projectSiteStaffID`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk102` FOREIGN KEY (`projectStatusTypeID`) REFERENCES `projectStatusType` (`projectStatusTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `projectSite`
--
ALTER TABLE `projectSite`
  ADD CONSTRAINT `fk006` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `projectSiteStaff`
--
ALTER TABLE `projectSiteStaff`
  ADD CONSTRAINT `fk007` FOREIGN KEY (`companyStaffID`) REFERENCES `companyStaff` (`companyStaffID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk007b` FOREIGN KEY (`projectSiteID`) REFERENCES `projectSite` (`projectSiteID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `projectSiteTask`
--
ALTER TABLE `projectSiteTask`
  ADD CONSTRAINT `fk0012` FOREIGN KEY (`projectSiteID`) REFERENCES `projectSite` (`projectSiteID`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `projectSiteTaskStatus`
--
ALTER TABLE `projectSiteTaskStatus`
  ADD CONSTRAINT `fk201` FOREIGN KEY (`projectSiteStaffID`) REFERENCES `projectSiteStaff` (`projectSiteStaffID`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk202` FOREIGN KEY (`projectSiteTaskID`) REFERENCES `projectSiteTask` (`projectSiteTaskID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk203` FOREIGN KEY (`taskStatusID`) REFERENCES `taskStatus` (`taskStatusID`) ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
