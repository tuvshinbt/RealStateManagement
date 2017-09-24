-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ohrt
-- ------------------------------------------------------
-- Server version	5.7.18-log

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

--
-- Table structure for table `apartment`
--

DROP TABLE IF EXISTS `apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apartment` (
  `ID` int(11) NOT NULL,
  `Floor` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approintmentstatus`
--

DROP TABLE IF EXISTS `approintmentstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approintmentstatus` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approintmentstatus`
--

LOCK TABLES `approintmentstatus` WRITE;
/*!40000 ALTER TABLE `approintmentstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `approintmentstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookappointment`
--

DROP TABLE IF EXISTS `bookappointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookappointment` (
  `ID` int(11) NOT NULL,
  `PropertyID` int(11) NOT NULL,
  `BuyerID` int(11) NOT NULL,
  `AppointmentDate` date DEFAULT NULL,
  `AppointmentComment` varchar(45) DEFAULT NULL,
  `AppointmentStatusID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookappointment`
--

LOCK TABLES `bookappointment` WRITE;
/*!40000 ALTER TABLE `bookappointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookappointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyorder`
--

DROP TABLE IF EXISTS `buyorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyorder` (
  `ID` int(11) NOT NULL,
  `BuyPrice` decimal(8,2) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyorder`
--

LOCK TABLES `buyorder` WRITE;
/*!40000 ALTER TABLE `buyorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `buyorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house` (
  `ID` int(11) NOT NULL,
  `Yard` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `ID` int(11) NOT NULL,
  `AccountID` int(11) NOT NULL,
  `RegisterDate` date NOT NULL,
  `PropertyID` int(11) NOT NULL,
  `StatusID` int(11) DEFAULT NULL,
  `SellerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `OwnerID` int(11) NOT NULL,
  `Bedroom` int(11) DEFAULT NULL,
  `Bathroom` int(11) DEFAULT NULL,
  `StatusID` int(11) DEFAULT NULL,
  `ApprovedBy` int(11) DEFAULT NULL,
  `RegisterDate` date DEFAULT NULL,
  `ApprovedDate` date DEFAULT NULL,
  `AgentAccount` int(11) DEFAULT NULL,
  `UtilitiesCost` decimal(6,2) DEFAULT NULL,
  `PurposeKey` varchar(45) DEFAULT NULL,
  `PurposeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propertyfeedback`
--

DROP TABLE IF EXISTS `propertyfeedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propertyfeedback` (
  `ID` int(11) NOT NULL,
  `AccountID` int(11) NOT NULL,
  `Comment` varchar(45) DEFAULT NULL,
  `PropertyID` int(11) NOT NULL,
  `RegisterDate` date NOT NULL,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propertyfeedback`
--

LOCK TABLES `propertyfeedback` WRITE;
/*!40000 ALTER TABLE `propertyfeedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `propertyfeedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propertystatus`
--

DROP TABLE IF EXISTS `propertystatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propertystatus` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propertystatus`
--

LOCK TABLES `propertystatus` WRITE;
/*!40000 ALTER TABLE `propertystatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `propertystatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentorder`
--

DROP TABLE IF EXISTS `rentorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rentorder` (
  `ID` int(11) NOT NULL,
  `RentMonth` decimal(6,2) NOT NULL,
  `PerMonthPrice` decimal(8,2) NOT NULL,
  `Deposit` decimal(8,2) NOT NULL,
  `Extendable` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentorder`
--

LOCK TABLES `rentorder` WRITE;
/*!40000 ALTER TABLE `rentorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `rentorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentpurpose`
--

DROP TABLE IF EXISTS `rentpurpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rentpurpose` (
  `ID` int(11) NOT NULL,
  `RentMonth` decimal(6,2) DEFAULT NULL,
  `PerMonthPrice` decimal(8,2) DEFAULT NULL,
  `Deposit` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentpurpose`
--

LOCK TABLES `rentpurpose` WRITE;
/*!40000 ALTER TABLE `rentpurpose` DISABLE KEYS */;
/*!40000 ALTER TABLE `rentpurpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellpurpose`
--

DROP TABLE IF EXISTS `sellpurpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sellpurpose` (
  `ID` int(11) NOT NULL,
  `BuyPrice` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellpurpose`
--

LOCK TABLES `sellpurpose` WRITE;
/*!40000 ALTER TABLE `sellpurpose` DISABLE KEYS */;
/*!40000 ALTER TABLE `sellpurpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `LoginName` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `BirthDate` date DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Mobile` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `LoginName_UNIQUE` (`LoginName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-06 10:48:01
