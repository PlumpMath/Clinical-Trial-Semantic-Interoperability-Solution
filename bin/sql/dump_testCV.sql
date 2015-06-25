-- MySQL dump 10.13  Distrib 5.6.15, for Win64 (x86_64)
--
-- Host: localhost    Database: testcv
-- ------------------------------------------------------
-- Server version	5.6.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

create schema testcv;

use testcv;

--
-- Table structure for table `act`
--

DROP TABLE IF EXISTS `act`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act` (
  `id` varchar(255) NOT NULL,
  `classCode` varchar(15) NOT NULL,
  `subClassCode` varchar(15) DEFAULT '',
  `moodCode` varchar(15) NOT NULL,
  `code` varchar(30) DEFAULT '',
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `actionNegationInd` tinyint(1) DEFAULT '0',
  `title` varchar(255) DEFAULT '',
  `text` longtext,
  `statusCode` varchar(15) DEFAULT '',
  `effectiveTime_start` datetime DEFAULT NULL,
  `effectiveTime_end` datetime DEFAULT NULL,
  `activityTime` datetime DEFAULT NULL,
  `availabilityTime` datetime DEFAULT '0000-00-00 00:00:00',
  `creationTime` datetime DEFAULT '0000-00-00 00:00:00',
  `modificationTime` datetime DEFAULT '0000-00-00 00:00:00',
  `uncertaintyCode` varchar(1) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `code` (`code`),
  KEY `codeOrig` (`codeOrig`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act`
--

LOCK TABLES `act` WRITE;
/*!40000 ALTER TABLE `act` DISABLE KEYS */;
INSERT INTO `act` VALUES ('1','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('10','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('11','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('12','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('2','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('3','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('4','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('5','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('6','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('7','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('8','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00',''),('9','OBS','','','106221001','2.16.840.1.113883.6.96','','',0,'Genetic finding',NULL,'',NULL,NULL,NULL,'0000-00-00 00:00:00','0000-00-00 00:00:00','0000-00-00 00:00:00','');
/*!40000 ALTER TABLE `act` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actmethodcode`
--

DROP TABLE IF EXISTS `actmethodcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actmethodcode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(50) NOT NULL,
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`,`code`),
  KEY `code` (`code`),
  KEY `codeOrig` (`codeOrig`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actmethodcode`
--

LOCK TABLES `actmethodcode` WRITE;
/*!40000 ALTER TABLE `actmethodcode` DISABLE KEYS */;
INSERT INTO `actmethodcode` VALUES ('12','426329006','2.16.840.1.113883.6.96','','','Fluorescence in situ hybridization'),('4','117617002','2.16.840.1.113883.6.96','','','Immunohistochemistry procedure (procedure)'),('8','426329006','2.16.840.1.113883.6.96','','','Fluorescence in situ hybridization');
/*!40000 ALTER TABLE `actmethodcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actobservationinterpretationcode`
--

DROP TABLE IF EXISTS `actobservationinterpretationcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actobservationinterpretationcode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(50) NOT NULL,
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`code`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actobservationinterpretationcode`
--

LOCK TABLES `actobservationinterpretationcode` WRITE;
/*!40000 ALTER TABLE `actobservationinterpretationcode` DISABLE KEYS */;
INSERT INTO `actobservationinterpretationcode` VALUES ('9','IND','','','',''),('1','LX','','','',''),('5','LX','','','',''),('10','NEG','','','',''),('11','NEG','','','',''),('12','NEG','','','',''),('2','NEG','','','',''),('3','NEG','','','',''),('7','NEG','','','',''),('8','NEG','','','',''),('4','POS','','','',''),('6','POS','','','','');
/*!40000 ALTER TABLE `actobservationinterpretationcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actobservationvalues`
--

DROP TABLE IF EXISTS `actobservationvalues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actobservationvalues` (
  `id` varchar(255) NOT NULL,
  `control` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(50) DEFAULT '',
  `valueType` varchar(50) DEFAULT '',
  `units` varchar(50) DEFAULT '',
  `referenceRangeMin` float DEFAULT NULL,
  `referenceRangeMax` float DEFAULT NULL,
  `code` varchar(50) DEFAULT '',
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`control`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actobservationvalues`
--

LOCK TABLES `actobservationvalues` WRITE;
/*!40000 ALTER TABLE `actobservationvalues` DISABLE KEYS */;
INSERT INTO `actobservationvalues` VALUES ('1',1,'8.6','','%',14,14,'','','','',''),('10',1,'0.7','','%',1,1,'','','','',''),('11',1,'0.22','','%',1,1,'','','','',''),('12',1,'1.2','','gene/CEP17 ratio',1.8,2.2,'','','','',''),('2',1,'0.7','','%',1,1,'','','','',''),('3',1,'0.25','','%',1,1,'','','','',''),('4',1,'4.5','','protein expression',3,NULL,'','','','',''),('5',1,'11.1','','%',14,14,'','','','',''),('6',1,'1.8','','%',1,1,'','','','',''),('7',1,'0.75','','%',1,1,'','','','',''),('8',1,'0.2','','gene/CEP17 ratio',1.8,2.2,'','','','',''),('9',1,'','','%',14,14,'','','','','');
/*!40000 ALTER TABLE `actobservationvalues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actprocedureapproachsitecode`
--

DROP TABLE IF EXISTS `actprocedureapproachsitecode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actprocedureapproachsitecode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(30) NOT NULL,
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`code`,`id`),
  KEY `code` (`code`),
  KEY `codeOrig` (`codeOrig`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actprocedureapproachsitecode`
--

LOCK TABLES `actprocedureapproachsitecode` WRITE;
/*!40000 ALTER TABLE `actprocedureapproachsitecode` DISABLE KEYS */;
/*!40000 ALTER TABLE `actprocedureapproachsitecode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actrelationship`
--

DROP TABLE IF EXISTS `actrelationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actrelationship` (
  `idA` varchar(255) NOT NULL,
  `idB` varchar(255) NOT NULL,
  `typeCode` varchar(15) NOT NULL,
  PRIMARY KEY (`idA`,`idB`,`typeCode`),
  KEY `has_act_actrelationship_b` (`idB`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actrelationship`
--

LOCK TABLES `actrelationship` WRITE;
/*!40000 ALTER TABLE `actrelationship` DISABLE KEYS */;
/*!40000 ALTER TABLE `actrelationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acttargetsitecode`
--

DROP TABLE IF EXISTS `acttargetsitecode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acttargetsitecode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(30) NOT NULL,
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`,`code`),
  KEY `code` (`code`),
  KEY `codeOrig` (`codeOrig`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acttargetsitecode`
--

LOCK TABLES `acttargetsitecode` WRITE;
/*!40000 ALTER TABLE `acttargetsitecode` DISABLE KEYS */;
/*!40000 ALTER TABLE `acttargetsitecode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cache`
--

DROP TABLE IF EXISTS `cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cache` (
  `concept_id` varchar(45) NOT NULL,
  `expanded_concepts` varchar(45) NOT NULL,
  `reasoning_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`concept_id`,`expanded_concepts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cache`
--

LOCK TABLES `cache` WRITE;
/*!40000 ALTER TABLE `cache` DISABLE KEYS */;
/*!40000 ALTER TABLE `cache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity`
--

DROP TABLE IF EXISTS `entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity` (
  `id` varchar(255) NOT NULL,
  `classCode` varchar(15) NOT NULL,
  `determinerCode` varchar(15) NOT NULL,
  `code` varchar(30) DEFAULT '',
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  `quantity` varchar(50) DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `desc` longtext,
  `statusCode` varchar(15) DEFAULT '',
  `creationTime` datetime DEFAULT NULL,
  `modificationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity`
--

LOCK TABLES `entity` WRITE;
/*!40000 ALTER TABLE `entity` DISABLE KEYS */;
INSERT INTO `entity` VALUES ('1','PSN','INSTANCE','337915000','2.16.840.1.113883.6.96','','','','','',NULL,'',NULL,NULL),('11','PSN','INSTANCE','337915000','2.16.840.1.113883.6.96','','','','','',NULL,'',NULL,NULL),('2','MAT','KIND','MKI67','2.16.840.1.113883.6.281','','','marker of proliferation Ki-67','','',NULL,'',NULL,NULL),('3','MAT','KIND','EREG','2.16.840.1.113883.6.281','','','epiregulin','','',NULL,'',NULL,NULL),('4','MAT','KIND','PGR','2.16.840.1.113883.6.281','','','progesterone receptor','','',NULL,'',NULL,NULL),('5','MAT','KIND','ERBB2','2.16.840.1.113883.6.281','','','v-erb-b2 avian erythroblastic leukemia viral oncogene homolog 2','','',NULL,'',NULL,NULL),('6','PSN','INSTANCE','337915000','2.16.840.1.113883.6.96','','','','','',NULL,'',NULL,NULL);
/*!40000 ALTER TABLE `entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exposure`
--

DROP TABLE IF EXISTS `exposure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exposure` (
  `id` varchar(255) NOT NULL,
  `routeCode` varchar(15) DEFAULT '',
  `exposureLevel` varchar(10) DEFAULT '',
  `exposureModeCode` varchar(15) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exposure`
--

LOCK TABLES `exposure` WRITE;
/*!40000 ALTER TABLE `exposure` DISABLE KEYS */;
/*!40000 ALTER TABLE `exposure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livingsubject`
--

DROP TABLE IF EXISTS `livingsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livingsubject` (
  `id` varchar(255) NOT NULL,
  `administrativeGenderCode` varchar(20) DEFAULT '',
  `birthTime` datetime DEFAULT NULL,
  `birthTimeTolerance` int(11) DEFAULT NULL,
  `deceasedInd` tinyint(1) DEFAULT NULL,
  `deceasedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livingsubject`
--

LOCK TABLES `livingsubject` WRITE;
/*!40000 ALTER TABLE `livingsubject` DISABLE KEYS */;
INSERT INTO `livingsubject` VALUES ('1','248152002','1983-02-28 00:00:00',NULL,NULL,NULL),('11','248152002','1963-07-28 00:00:00',NULL,NULL,NULL),('6','248152002','1963-07-28 00:00:00',NULL,NULL,NULL);
/*!40000 ALTER TABLE `livingsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observation`
--

DROP TABLE IF EXISTS `observation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `observation` (
  `id` varchar(255) NOT NULL,
  `valueNegationInd` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observation`
--

LOCK TABLES `observation` WRITE;
/*!40000 ALTER TABLE `observation` DISABLE KEYS */;
INSERT INTO `observation` VALUES ('1',0),('10',0),('11',0),('12',0),('2',0),('3',0),('4',0),('5',0),('6',0),('7',0),('8',0),('9',0);
/*!40000 ALTER TABLE `observation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participation` (
  `entityId` varchar(255) NOT NULL,
  `roleId` varchar(255) NOT NULL,
  `actId` varchar(255) NOT NULL,
  `typeCode` varchar(10) NOT NULL,
  `functionCode` varchar(12) DEFAULT '',
  `negationInd` tinyint(1) DEFAULT '0',
  `noteText` longtext,
  `time_start` datetime DEFAULT NULL,
  `time_end` datetime DEFAULT NULL,
  `modeCode` varchar(12) DEFAULT '',
  PRIMARY KEY (`actId`,`entityId`,`roleId`,`typeCode`),
  KEY `has_role_participation` (`entityId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participation`
--

LOCK TABLES `participation` WRITE;
/*!40000 ALTER TABLE `participation` DISABLE KEYS */;
INSERT INTO `participation` VALUES ('1','1','1','PART','',0,NULL,NULL,NULL,''),('2','2','1','PART','',0,NULL,NULL,NULL,''),('11','11','10','PART','',0,NULL,NULL,NULL,''),('3','3','10','PART','',0,NULL,NULL,NULL,''),('11','11','11','PART','',0,NULL,NULL,NULL,''),('4','4','11','PART','',0,NULL,NULL,NULL,''),('11','11','12','PART','',0,NULL,NULL,NULL,''),('5','5','12','PART','',0,NULL,NULL,NULL,''),('1','1','2','PART','',0,NULL,NULL,NULL,''),('3','3','2','PART','',0,NULL,NULL,NULL,''),('1','1','3','PART','',0,NULL,NULL,NULL,''),('4','4','3','PART','',0,NULL,NULL,NULL,''),('1','1','4','PART','',0,NULL,NULL,NULL,''),('5','5','4','PART','',0,NULL,NULL,NULL,''),('2','2','5','PART','',0,NULL,NULL,NULL,''),('6','6','5','PART','',0,NULL,NULL,NULL,''),('3','3','6','PART','',0,NULL,NULL,NULL,''),('6','6','6','PART','',0,NULL,NULL,NULL,''),('4','4','7','PART','',0,NULL,NULL,NULL,''),('6','6','7','PART','',0,NULL,NULL,NULL,''),('5','5','8','PART','',0,NULL,NULL,NULL,''),('6','6','8','PART','',0,NULL,NULL,NULL,''),('11','11','9','PART','',0,NULL,NULL,NULL,''),('2','2','9','PART','',0,NULL,NULL,NULL,'');
/*!40000 ALTER TABLE `participation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` varchar(255) NOT NULL,
  `raceCode` varchar(30) DEFAULT '',
  `name` varchar(100) DEFAULT '',
  `surname` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('1','','Skyler','White'),('11','','Penny','Jones'),('6','','Gemma','Morrow');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedures`
--

DROP TABLE IF EXISTS `procedures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedures` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedures`
--

LOCK TABLES `procedures` WRITE;
/*!40000 ALTER TABLE `procedures` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `entityId` varchar(255) NOT NULL,
  `id` varchar(255) NOT NULL,
  `classCode` varchar(15) NOT NULL,
  `code` varchar(50) DEFAULT '',
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  `negationInd` tinyint(1) DEFAULT '0',
  `name` varchar(255) DEFAULT '',
  `statusCode` varchar(15) DEFAULT '',
  `effectiveTime_start` datetime DEFAULT NULL,
  `effectiveTime_end` datetime DEFAULT NULL,
  `confidentialityCode` varchar(5) DEFAULT '',
  PRIMARY KEY (`entityId`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('1','1','PAT','','','','','',0,'','',NULL,NULL,''),('11','11','PAT','','','','','',0,'','',NULL,NULL,''),('2','2','SPEC','','','','','',0,'','',NULL,NULL,''),('3','3','SPEC','','','','','',0,'','',NULL,NULL,''),('4','4','SPEC','','','','','',0,'','',NULL,NULL,''),('5','5','SPEC','','','','','',0,'','',NULL,NULL,''),('6','6','PAT','','','','','',0,'','',NULL,NULL,'');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security`
--

DROP TABLE IF EXISTS `security`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security` (
  `securityVar` varchar(50) NOT NULL,
  `dataset` varchar(50) NOT NULL,
  `decision` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`securityVar`,`dataset`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security`
--

LOCK TABLES `security` WRITE;
/*!40000 ALTER TABLE `security` DISABLE KEYS */;
/*!40000 ALTER TABLE `security` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `substanceadministration`
--

DROP TABLE IF EXISTS `substanceadministration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `substanceadministration` (
  `id` varchar(255) NOT NULL,
  `routeCode` varchar(30) DEFAULT '',
  `routeCodeVocId` varchar(30) DEFAULT '',
  `routeCodeOrig` varchar(30) DEFAULT '',
  `routeCodeOrigVocId` varchar(30) DEFAULT '',
  `routeCodeTitle` varchar(255) DEFAULT '',
  `doseQuantity` varchar(15) DEFAULT '',
  `doseQuantityUnits` varchar(15) DEFAULT '',
  `rateQuantity` varchar(15) DEFAULT '',
  `rateQuantityUnits` varchar(15) DEFAULT '',
  `doseCheckQuantity` varchar(15) DEFAULT '',
  `doseCheckQuantityUnits` varchar(15) DEFAULT '',
  `periodIntervalTime` varchar(15) DEFAULT '',
  `periodIntervalUnits` varchar(15) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `substanceadministration`
--

LOCK TABLES `substanceadministration` WRITE;
/*!40000 ALTER TABLE `substanceadministration` DISABLE KEYS */;
/*!40000 ALTER TABLE `substanceadministration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_exposure`
--

DROP TABLE IF EXISTS `v_exposure`;
/*!50001 DROP VIEW IF EXISTS `v_exposure`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_exposure` (
  `id` tinyint NOT NULL,
  `classCode` tinyint NOT NULL,
  `subClassCode` tinyint NOT NULL,
  `moodCode` tinyint NOT NULL,
  `code` tinyint NOT NULL,
  `codeVocId` tinyint NOT NULL,
  `codeOrig` tinyint NOT NULL,
  `codeOrigVocId` tinyint NOT NULL,
  `actionNegationInd` tinyint NOT NULL,
  `title` tinyint NOT NULL,
  `text` tinyint NOT NULL,
  `statusCode` tinyint NOT NULL,
  `effectiveTime_start` tinyint NOT NULL,
  `effectiveTime_end` tinyint NOT NULL,
  `activityTime` tinyint NOT NULL,
  `availabilityTime` tinyint NOT NULL,
  `creationTime` tinyint NOT NULL,
  `modificationTime` tinyint NOT NULL,
  `uncertaintyCode` tinyint NOT NULL,
  `clinicalTrialId` tinyint NOT NULL,
  `routeCode` tinyint NOT NULL,
  `exposureLevel` tinyint NOT NULL,
  `exposureModeCode` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_livingsubject`
--

DROP TABLE IF EXISTS `v_livingsubject`;
/*!50001 DROP VIEW IF EXISTS `v_livingsubject`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_livingsubject` (
  `id` tinyint NOT NULL,
  `classCode` tinyint NOT NULL,
  `determinerCode` tinyint NOT NULL,
  `code` tinyint NOT NULL,
  `codeVocId` tinyint NOT NULL,
  `codeOrig` tinyint NOT NULL,
  `codeOrigVocId` tinyint NOT NULL,
  `title` tinyint NOT NULL,
  `quantity` tinyint NOT NULL,
  `name` tinyint NOT NULL,
  `desc` tinyint NOT NULL,
  `statusCode` tinyint NOT NULL,
  `administrativeGenderCode` tinyint NOT NULL,
  `creationTime` tinyint NOT NULL,
  `modificationTime` tinyint NOT NULL,
  `birthTime` tinyint NOT NULL,
  `birthTimeTolerance` tinyint NOT NULL,
  `deceasedInd` tinyint NOT NULL,
  `deceasedTime` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_observation`
--

DROP TABLE IF EXISTS `v_observation`;
/*!50001 DROP VIEW IF EXISTS `v_observation`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_observation` (
  `id` tinyint NOT NULL,
  `classCode` tinyint NOT NULL,
  `subClassCode` tinyint NOT NULL,
  `moodCode` tinyint NOT NULL,
  `code` tinyint NOT NULL,
  `codeVocId` tinyint NOT NULL,
  `codeOrig` tinyint NOT NULL,
  `codeOrigVocId` tinyint NOT NULL,
  `actionNegationInd` tinyint NOT NULL,
  `title` tinyint NOT NULL,
  `text` tinyint NOT NULL,
  `statusCode` tinyint NOT NULL,
  `effectiveTime_start` tinyint NOT NULL,
  `effectiveTime_end` tinyint NOT NULL,
  `activityTime` tinyint NOT NULL,
  `availabilityTime` tinyint NOT NULL,
  `creationTime` tinyint NOT NULL,
  `modificationTime` tinyint NOT NULL,
  `uncertaintyCode` tinyint NOT NULL,
  `valueNegationInd` tinyint NOT NULL,
  `clinicalTrialId` tinyint NOT NULL,
  `value` tinyint NOT NULL,
  `valueType` tinyint NOT NULL,
  `units` tinyint NOT NULL,
  `valueCode` tinyint NOT NULL,
  `valueCodeVocId` tinyint NOT NULL,
  `valueCodeOrig` tinyint NOT NULL,
  `valueCodeOrigVocId` tinyint NOT NULL,
  `valueTitle` tinyint NOT NULL,
  `referenceRangeMin` tinyint NOT NULL,
  `referenceRangeMax` tinyint NOT NULL,
  `interpretationCode` tinyint NOT NULL,
  `interpretationCodeTitle` tinyint NOT NULL,
  `interpretationCodeVocId` tinyint NOT NULL,
  `interpretationCodeOrig` tinyint NOT NULL,
  `interpretationCodeOrigVocId` tinyint NOT NULL,
  `methodCode` tinyint NOT NULL,
  `methodCodeTitle` tinyint NOT NULL,
  `methodCodeVocId` tinyint NOT NULL,
  `methodCodeOrig` tinyint NOT NULL,
  `methodCodeOrigVocId` tinyint NOT NULL,
  `targetSiteCode` tinyint NOT NULL,
  `targetSiteCodeTitle` tinyint NOT NULL,
  `targetSiteCodeVocId` tinyint NOT NULL,
  `targetSiteCodeOrig` tinyint NOT NULL,
  `targetSiteCodeOrigVocId` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_person`
--

DROP TABLE IF EXISTS `v_person`;
/*!50001 DROP VIEW IF EXISTS `v_person`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_person` (
  `id` tinyint NOT NULL,
  `classCode` tinyint NOT NULL,
  `determinerCode` tinyint NOT NULL,
  `code` tinyint NOT NULL,
  `codeVocId` tinyint NOT NULL,
  `codeOrig` tinyint NOT NULL,
  `codeOrigVocId` tinyint NOT NULL,
  `title` tinyint NOT NULL,
  `quantity` tinyint NOT NULL,
  `name` tinyint NOT NULL,
  `desc` tinyint NOT NULL,
  `statusCode` tinyint NOT NULL,
  `administrativeGenderCode` tinyint NOT NULL,
  `creationTime` tinyint NOT NULL,
  `modificationTime` tinyint NOT NULL,
  `birthTime` tinyint NOT NULL,
  `birthTimeTolerance` tinyint NOT NULL,
  `deceasedInd` tinyint NOT NULL,
  `deceasedTime` tinyint NOT NULL,
  `raceCode` tinyint NOT NULL,
  `personName` tinyint NOT NULL,
  `personSurname` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_procedure`
--

DROP TABLE IF EXISTS `v_procedure`;
/*!50001 DROP VIEW IF EXISTS `v_procedure`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_procedure` (
  `id` tinyint NOT NULL,
  `classCode` tinyint NOT NULL,
  `subClassCode` tinyint NOT NULL,
  `moodCode` tinyint NOT NULL,
  `code` tinyint NOT NULL,
  `codeVocId` tinyint NOT NULL,
  `codeOrig` tinyint NOT NULL,
  `codeOrigVocId` tinyint NOT NULL,
  `actionNegationInd` tinyint NOT NULL,
  `title` tinyint NOT NULL,
  `text` tinyint NOT NULL,
  `statusCode` tinyint NOT NULL,
  `effectiveTime_start` tinyint NOT NULL,
  `effectiveTime_end` tinyint NOT NULL,
  `activityTime` tinyint NOT NULL,
  `availabilityTime` tinyint NOT NULL,
  `creationTime` tinyint NOT NULL,
  `modificationTime` tinyint NOT NULL,
  `uncertaintyCode` tinyint NOT NULL,
  `clinicalTrialId` tinyint NOT NULL,
  `approachSiteCode` tinyint NOT NULL,
  `approachSiteCodeTitle` tinyint NOT NULL,
  `approachSiteCodeVocId` tinyint NOT NULL,
  `approachSiteCodeOrig` tinyint NOT NULL,
  `approachSiteCodeOrigVocId` tinyint NOT NULL,
  `methodCode` tinyint NOT NULL,
  `methodCodeTitle` tinyint NOT NULL,
  `methodCodeVocId` tinyint NOT NULL,
  `methodCodeOrig` tinyint NOT NULL,
  `methodCodeOrigVocId` tinyint NOT NULL,
  `targetSiteCode` tinyint NOT NULL,
  `targetSiteCodeTitle` tinyint NOT NULL,
  `targetSiteCodeVocId` tinyint NOT NULL,
  `targetSiteCodeOrig` tinyint NOT NULL,
  `targetSiteCodeOrigVocId` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_substanceadministration`
--

DROP TABLE IF EXISTS `v_substanceadministration`;
/*!50001 DROP VIEW IF EXISTS `v_substanceadministration`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_substanceadministration` (
  `id` tinyint NOT NULL,
  `classCode` tinyint NOT NULL,
  `subClassCode` tinyint NOT NULL,
  `moodCode` tinyint NOT NULL,
  `code` tinyint NOT NULL,
  `codeVocId` tinyint NOT NULL,
  `codeOrig` tinyint NOT NULL,
  `codeOrigVocId` tinyint NOT NULL,
  `actionNegationInd` tinyint NOT NULL,
  `title` tinyint NOT NULL,
  `text` tinyint NOT NULL,
  `statusCode` tinyint NOT NULL,
  `effectiveTime_start` tinyint NOT NULL,
  `effectiveTime_end` tinyint NOT NULL,
  `activityTime` tinyint NOT NULL,
  `availabilityTime` tinyint NOT NULL,
  `creationTime` tinyint NOT NULL,
  `modificationTime` tinyint NOT NULL,
  `uncertaintyCode` tinyint NOT NULL,
  `clinicalTrialId` tinyint NOT NULL,
  `approachSiteCode` tinyint NOT NULL,
  `approachSiteCodeTitle` tinyint NOT NULL,
  `approachSiteCodeVocId` tinyint NOT NULL,
  `approachSiteCodeOrig` tinyint NOT NULL,
  `approachSiteCodeOrigVocId` tinyint NOT NULL,
  `methodCode` tinyint NOT NULL,
  `methodCodeTitle` tinyint NOT NULL,
  `methodCodeVocId` tinyint NOT NULL,
  `methodCodeOrig` tinyint NOT NULL,
  `methodCodeOrigVocId` tinyint NOT NULL,
  `targetSiteCode` tinyint NOT NULL,
  `targetSiteCodeTitle` tinyint NOT NULL,
  `targetSiteCodeVocId` tinyint NOT NULL,
  `targetSiteCodeOrig` tinyint NOT NULL,
  `targetSiteCodeOrigVocId` tinyint NOT NULL,
  `routeCode` tinyint NOT NULL,
  `routeCodeVocId` tinyint NOT NULL,
  `routeCodeOrig` tinyint NOT NULL,
  `routeCodeOrigVocId` tinyint NOT NULL,
  `routeCodeTitle` tinyint NOT NULL,
  `doseQuantity` tinyint NOT NULL,
  `doseQuantityUnits` tinyint NOT NULL,
  `rateQuantity` tinyint NOT NULL,
  `rateQuantityUnits` tinyint NOT NULL,
  `doseCheckQuantity` tinyint NOT NULL,
  `doseCheckQuantityUnits` tinyint NOT NULL,
  `periodIntervalTime` tinyint NOT NULL,
  `periodIntervalUnits` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_exposure`
--

/*!50001 DROP TABLE IF EXISTS `v_exposure`*/;
/*!50001 DROP VIEW IF EXISTS `v_exposure`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */

/*!50001 VIEW `v_exposure` AS select `a`.`id` AS `id`,`a`.`classCode` AS `classCode`,`a`.`subClassCode` AS `subClassCode`,`a`.`moodCode` AS `moodCode`,`a`.`code` AS `code`,`a`.`codeVocId` AS `codeVocId`,`a`.`codeOrig` AS `codeOrig`,`a`.`codeOrigVocId` AS `codeOrigVocId`,`a`.`actionNegationInd` AS `actionNegationInd`,`a`.`title` AS `title`,`a`.`text` AS `text`,`a`.`statusCode` AS `statusCode`,`a`.`effectiveTime_start` AS `effectiveTime_start`,`a`.`effectiveTime_end` AS `effectiveTime_end`,`a`.`activityTime` AS `activityTime`,`a`.`availabilityTime` AS `availabilityTime`,`a`.`creationTime` AS `creationTime`,`a`.`modificationTime` AS `modificationTime`,`a`.`uncertaintyCode` AS `uncertaintyCode`,`ar`.`idB` AS `clinicalTrialId`,`e`.`routeCode` AS `routeCode`,`e`.`exposureLevel` AS `exposureLevel`,`e`.`exposureModeCode` AS `exposureModeCode` from ((`act` `a` left join `exposure` `e` on((`a`.`id` = `e`.`id`))) left join `actrelationship` `ar` on((`ar`.`idA` = `a`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_livingsubject`
--

/*!50001 DROP TABLE IF EXISTS `v_livingsubject`*/;
/*!50001 DROP VIEW IF EXISTS `v_livingsubject`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */

/*!50001 VIEW `v_livingsubject` AS select `e`.`id` AS `id`,`e`.`classCode` AS `classCode`,`e`.`determinerCode` AS `determinerCode`,`e`.`code` AS `code`,`e`.`codeVocId` AS `codeVocId`,`e`.`codeOrig` AS `codeOrig`,`e`.`codeOrigVocId` AS `codeOrigVocId`,`e`.`title` AS `title`,`e`.`quantity` AS `quantity`,`e`.`name` AS `name`,`e`.`desc` AS `desc`,`e`.`statusCode` AS `statusCode`,`l`.`administrativeGenderCode` AS `administrativeGenderCode`,`e`.`creationTime` AS `creationTime`,`e`.`modificationTime` AS `modificationTime`,`l`.`birthTime` AS `birthTime`,`l`.`birthTimeTolerance` AS `birthTimeTolerance`,`l`.`deceasedInd` AS `deceasedInd`,`l`.`deceasedTime` AS `deceasedTime` from (`entity` `e` left join `livingsubject` `l` on((`e`.`id` = `l`.`id`))) where (`e`.`classCode` = 'PSN') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_observation`
--

/*!50001 DROP TABLE IF EXISTS `v_observation`*/;
/*!50001 DROP VIEW IF EXISTS `v_observation`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */

/*!50001 VIEW `v_observation` AS select `a`.`id` AS `id`,`a`.`classCode` AS `classCode`,`a`.`subClassCode` AS `subClassCode`,`a`.`moodCode` AS `moodCode`,`a`.`code` AS `code`,`a`.`codeVocId` AS `codeVocId`,`a`.`codeOrig` AS `codeOrig`,`a`.`codeOrigVocId` AS `codeOrigVocId`,`a`.`actionNegationInd` AS `actionNegationInd`,`a`.`title` AS `title`,`a`.`text` AS `text`,`a`.`statusCode` AS `statusCode`,`a`.`effectiveTime_start` AS `effectiveTime_start`,`a`.`effectiveTime_end` AS `effectiveTime_end`,`a`.`activityTime` AS `activityTime`,`a`.`availabilityTime` AS `availabilityTime`,`a`.`creationTime` AS `creationTime`,`a`.`modificationTime` AS `modificationTime`,`a`.`uncertaintyCode` AS `uncertaintyCode`,`o`.`valueNegationInd` AS `valueNegationInd`,`ar`.`idB` AS `clinicalTrialId`,`aov`.`value` AS `value`,`aov`.`valueType` AS `valueType`,`aov`.`units` AS `units`,`aov`.`code` AS `valueCode`,`aov`.`codeVocId` AS `valueCodeVocId`,`aov`.`codeOrig` AS `valueCodeOrig`,`aov`.`codeOrigVocId` AS `valueCodeOrigVocId`,`aov`.`title` AS `valueTitle`,`aov`.`referenceRangeMin` AS `referenceRangeMin`,`aov`.`referenceRangeMax` AS `referenceRangeMax`,`aoic`.`code` AS `interpretationCode`,`aoic`.`title` AS `interpretationCodeTitle`,`aoic`.`codeVocId` AS `interpretationCodeVocId`,`aoic`.`codeOrig` AS `interpretationCodeOrig`,`aoic`.`codeOrigVocId` AS `interpretationCodeOrigVocId`,`amc`.`code` AS `methodCode`,`amc`.`title` AS `methodCodeTitle`,`amc`.`codeVocId` AS `methodCodeVocId`,`amc`.`codeOrig` AS `methodCodeOrig`,`amc`.`codeOrigVocId` AS `methodCodeOrigVocId`,`atsc`.`code` AS `targetSiteCode`,`atsc`.`title` AS `targetSiteCodeTitle`,`atsc`.`codeVocId` AS `targetSiteCodeVocId`,`atsc`.`codeOrig` AS `targetSiteCodeOrig`,`atsc`.`codeOrigVocId` AS `targetSiteCodeOrigVocId` from ((((((`act` `a` left join `observation` `o` on((`a`.`id` = `o`.`id`))) left join `actrelationship` `ar` on((`ar`.`idA` = `a`.`id`))) left join `actobservationvalues` `aov` on((`o`.`id` = `aov`.`id`))) left join `actobservationinterpretationcode` `aoic` on((`aoic`.`id` = `o`.`id`))) left join `acttargetsitecode` `atsc` on((`atsc`.`id` = `o`.`id`))) left join `actmethodcode` `amc` on((`amc`.`id` = `o`.`id`))) where (`a`.`classCode` = 'OBS') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_person`
--

/*!50001 DROP TABLE IF EXISTS `v_person`*/;
/*!50001 DROP VIEW IF EXISTS `v_person`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */

/*!50001 VIEW `v_person` AS select `e`.`id` AS `id`,`e`.`classCode` AS `classCode`,`e`.`determinerCode` AS `determinerCode`,`e`.`code` AS `code`,`e`.`codeVocId` AS `codeVocId`,`e`.`codeOrig` AS `codeOrig`,`e`.`codeOrigVocId` AS `codeOrigVocId`,`e`.`title` AS `title`,`e`.`quantity` AS `quantity`,`e`.`name` AS `name`,`e`.`desc` AS `desc`,`e`.`statusCode` AS `statusCode`,`l`.`administrativeGenderCode` AS `administrativeGenderCode`,`e`.`creationTime` AS `creationTime`,`e`.`modificationTime` AS `modificationTime`,`l`.`birthTime` AS `birthTime`,`l`.`birthTimeTolerance` AS `birthTimeTolerance`,`l`.`deceasedInd` AS `deceasedInd`,`l`.`deceasedTime` AS `deceasedTime`,`p`.`raceCode` AS `raceCode`,`p`.`name` AS `personName`,`p`.`surname` AS `personSurname` from ((`entity` `e` left join `livingsubject` `l` on((`e`.`id` = `l`.`id`))) left join `person` `p` on((`p`.`id` = `e`.`id`))) where (`e`.`classCode` = 'PSN') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_procedure`
--

/*!50001 DROP TABLE IF EXISTS `v_procedure`*/;
/*!50001 DROP VIEW IF EXISTS `v_procedure`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */

/*!50001 VIEW `v_procedure` AS select `a`.`id` AS `id`,`a`.`classCode` AS `classCode`,`a`.`subClassCode` AS `subClassCode`,`a`.`moodCode` AS `moodCode`,`a`.`code` AS `code`,`a`.`codeVocId` AS `codeVocId`,`a`.`codeOrig` AS `codeOrig`,`a`.`codeOrigVocId` AS `codeOrigVocId`,`a`.`actionNegationInd` AS `actionNegationInd`,`a`.`title` AS `title`,`a`.`text` AS `text`,`a`.`statusCode` AS `statusCode`,`a`.`effectiveTime_start` AS `effectiveTime_start`,`a`.`effectiveTime_end` AS `effectiveTime_end`,`a`.`activityTime` AS `activityTime`,`a`.`availabilityTime` AS `availabilityTime`,`a`.`creationTime` AS `creationTime`,`a`.`modificationTime` AS `modificationTime`,`a`.`uncertaintyCode` AS `uncertaintyCode`,`ar`.`idB` AS `clinicalTrialId`,`apasc`.`code` AS `approachSiteCode`,`apasc`.`title` AS `approachSiteCodeTitle`,`apasc`.`codeVocId` AS `approachSiteCodeVocId`,`apasc`.`codeOrig` AS `approachSiteCodeOrig`,`apasc`.`codeOrigVocId` AS `approachSiteCodeOrigVocId`,`amc`.`code` AS `methodCode`,`amc`.`title` AS `methodCodeTitle`,`amc`.`codeVocId` AS `methodCodeVocId`,`amc`.`codeOrig` AS `methodCodeOrig`,`amc`.`codeOrigVocId` AS `methodCodeOrigVocId`,`atsc`.`code` AS `targetSiteCode`,`atsc`.`title` AS `targetSiteCodeTitle`,`atsc`.`codeVocId` AS `targetSiteCodeVocId`,`atsc`.`codeOrig` AS `targetSiteCodeOrig`,`atsc`.`codeOrigVocId` AS `targetSiteCodeOrigVocId` from (((((`act` `a` left join `procedures` `p` on((`p`.`id` = `a`.`id`))) left join `actrelationship` `ar` on((`ar`.`idA` = `a`.`id`))) left join `actprocedureapproachsitecode` `apasc` on((`apasc`.`id` = `p`.`id`))) left join `acttargetsitecode` `atsc` on((`atsc`.`id` = `p`.`id`))) left join `actmethodcode` `amc` on((`p`.`id` = `amc`.`id`))) where (`a`.`classCode` = 'PROC') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_substanceadministration`
--

/*!50001 DROP TABLE IF EXISTS `v_substanceadministration`*/;
/*!50001 DROP VIEW IF EXISTS `v_substanceadministration`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */

/*!50001 VIEW `v_substanceadministration` AS select `a`.`id` AS `id`,`a`.`classCode` AS `classCode`,`a`.`subClassCode` AS `subClassCode`,`a`.`moodCode` AS `moodCode`,`a`.`code` AS `code`,`a`.`codeVocId` AS `codeVocId`,`a`.`codeOrig` AS `codeOrig`,`a`.`codeOrigVocId` AS `codeOrigVocId`,`a`.`actionNegationInd` AS `actionNegationInd`,`a`.`title` AS `title`,`a`.`text` AS `text`,`a`.`statusCode` AS `statusCode`,`a`.`effectiveTime_start` AS `effectiveTime_start`,`a`.`effectiveTime_end` AS `effectiveTime_end`,`a`.`activityTime` AS `activityTime`,`a`.`availabilityTime` AS `availabilityTime`,`a`.`creationTime` AS `creationTime`,`a`.`modificationTime` AS `modificationTime`,`a`.`uncertaintyCode` AS `uncertaintyCode`,`ar`.`idB` AS `clinicalTrialId`,`apasc`.`code` AS `approachSiteCode`,`apasc`.`title` AS `approachSiteCodeTitle`,`apasc`.`codeVocId` AS `approachSiteCodeVocId`,`apasc`.`codeOrig` AS `approachSiteCodeOrig`,`apasc`.`codeOrigVocId` AS `approachSiteCodeOrigVocId`,`amc`.`code` AS `methodCode`,`amc`.`title` AS `methodCodeTitle`,`amc`.`codeVocId` AS `methodCodeVocId`,`amc`.`codeOrig` AS `methodCodeOrig`,`amc`.`codeOrigVocId` AS `methodCodeOrigVocId`,`atsc`.`code` AS `targetSiteCode`,`atsc`.`title` AS `targetSiteCodeTitle`,`atsc`.`codeVocId` AS `targetSiteCodeVocId`,`atsc`.`codeOrig` AS `targetSiteCodeOrig`,`atsc`.`codeOrigVocId` AS `targetSiteCodeOrigVocId`,`s`.`routeCode` AS `routeCode`,`s`.`routeCodeVocId` AS `routeCodeVocId`,`s`.`routeCodeOrig` AS `routeCodeOrig`,`s`.`routeCodeOrigVocId` AS `routeCodeOrigVocId`,`s`.`routeCodeTitle` AS `routeCodeTitle`,`s`.`doseQuantity` AS `doseQuantity`,`s`.`doseQuantityUnits` AS `doseQuantityUnits`,`s`.`rateQuantity` AS `rateQuantity`,`s`.`rateQuantityUnits` AS `rateQuantityUnits`,`s`.`doseCheckQuantity` AS `doseCheckQuantity`,`s`.`doseCheckQuantityUnits` AS `doseCheckQuantityUnits`,`s`.`periodIntervalTime` AS `periodIntervalTime`,`s`.`periodIntervalUnits` AS `periodIntervalUnits` from ((((((`act` `a` left join `procedures` `p` on((`p`.`id` = `a`.`id`))) left join `substanceadministration` `s` on((`s`.`id` = `p`.`id`))) left join `actrelationship` `ar` on((`ar`.`idA` = `a`.`id`))) left join `actprocedureapproachsitecode` `apasc` on((`apasc`.`id` = `p`.`id`))) left join `acttargetsitecode` `atsc` on((`atsc`.`id` = `p`.`id`))) left join `actmethodcode` `amc` on((`p`.`id` = `amc`.`id`))) where (`a`.`classCode` = 'SBADM') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-18 15:46:53
