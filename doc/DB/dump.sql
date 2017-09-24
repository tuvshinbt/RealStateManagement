-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mpp1
-- ------------------------------------------------------
-- Server version	5.7.14

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
INSERT INTO `apartment` VALUES (3,1),(4,2),(6,1),(7,4);
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointmentstatus`
--

DROP TABLE IF EXISTS `appointmentstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointmentstatus` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointmentstatus`
--

LOCK TABLES `appointmentstatus` WRITE;
/*!40000 ALTER TABLE `appointmentstatus` DISABLE KEYS */;
INSERT INTO `appointmentstatus` VALUES (1,'Received'),(2,'Approved');
/*!40000 ALTER TABLE `appointmentstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookappointment`
--

DROP TABLE IF EXISTS `bookappointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookappointment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PropertyID` int(11) NOT NULL,
  `BuyerID` int(11) NOT NULL,
  `AppointmentDate` datetime DEFAULT NULL,
  `AppointmentComment` varchar(45) DEFAULT NULL,
  `AppointmentStatusID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookappointment`
--

LOCK TABLES `bookappointment` WRITE;
/*!40000 ALTER TABLE `bookappointment` DISABLE KEYS */;
INSERT INTO `bookappointment` VALUES (1,1,1,'2017-06-11 21:19:30','a',2),(2,7,1,'2017-06-13 11:11:00','Hello',2),(3,7,1,'2017-06-12 11:11:00','Helloo',2),(4,7,1,'2017-06-12 11:11:00','Helloo 12',2);
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
INSERT INTO `buyorder` VALUES (18,180000.00),(22,3.00),(23,111.00),(26,290000.00);
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
INSERT INTO `house` VALUES (1,100.00),(2,300.00),(5,123.00),(8,120.00);
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) NOT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `Amount` decimal(8,0) DEFAULT NULL,
  `StatusID` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (11,18,'2017-06-12 01:52:31',180000,2),(12,20,'2017-06-12 02:52:19',3300,2),(13,21,'2017-06-12 03:04:11',1150,1),(14,24,'2017-06-12 03:05:40',4600,2),(15,25,'2017-06-12 04:04:54',26000,2),(16,26,'2017-06-12 04:08:50',290000,2);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AccountID` int(11) NOT NULL,
  `RegisterDate` date NOT NULL,
  `PropertyID` int(11) NOT NULL,
  `StatusID` int(11) DEFAULT NULL,
  `SellerID` int(11) DEFAULT NULL,
  `Comment` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (18,1,'2017-06-11',3,3,1,'sell1'),(20,1,'2017-06-11',2,4,1,'hi all'),(21,3,'2017-06-11',2,2,2,'hi all'),(22,3,'2017-06-11',3,1,3,'desc3'),(23,1,'2017-06-12',3,1,3,'1'),(24,1,'2017-06-12',4,4,2,'test1'),(25,1,'2017-06-12',7,4,2,'This is offer'),(26,1,'2017-06-12',8,3,2,'Can you give me $10k off');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceID` int(11) NOT NULL,
  `OrderID` int(11) NOT NULL,
  `Amount` decimal(8,0) DEFAULT NULL,
  `PaidTotal` decimal(8,0) DEFAULT NULL,
  `PaymentType` varchar(50) DEFAULT NULL,
  `CardType` varchar(50) DEFAULT NULL,
  `PaidDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (3,11,18,180000,0,'card','visa','2017-06-12 02:03:46'),(4,12,20,3300,0,'card','visa','2017-06-12 02:53:21'),(5,14,24,4600,0,'card','visa','2017-06-12 03:06:09'),(6,15,25,26000,0,'card','master','2017-06-12 04:05:07'),(7,16,26,290000,0,'card','master','2017-06-12 04:08:59');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
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
  `Parking` int(11) DEFAULT NULL,
  `Livingroom` int(11) DEFAULT NULL,
  `Kitchen` int(11) DEFAULT NULL,
  `Description` text,
  `GoogleMapPath` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (1,'name1','Address1',1,1,1,2,1,'2017-06-07','2017-06-11',1,1000.00,'Sell',1,1,1,1,NULL,NULL),(2,'name2','Address2',2,2,2,2,2,'2017-06-08','2017-06-12',2,200.00,'Rent',1,2,2,2,NULL,NULL),(3,'name3','Address31',3,3,3,2,1,'2017-06-09','2017-06-07',3,300.00,'Sell',2,3,3,3,NULL,NULL),(4,'name4','Address4',1,4,4,4,1,'2017-06-10','2017-06-07',3,400.00,'Rent',2,3,4,4,NULL,NULL),(5,'pro5','1000N',2,1,1,2,4,'2017-06-12','2017-06-12',3,1.00,'Sell',3,1,1,1,NULL,NULL),(6,'pro6','1000N1',2,1,1,1,0,'2017-06-12',NULL,3,1.00,'Sell',4,1,1,1,'test 2',NULL),(7,'Nice house','1000 N 4th St MR 251',2,2,2,4,4,'2017-06-12','2017-06-12',3,0.00,'Rent',4,1,1,1,'Nice house description',NULL),(8,'This is for sell','1000 N 4th St MR 251',2,2,2,3,4,'2017-06-12','2017-06-12',3,200.00,'Sell',5,1,1,1,'Hello don\'t hesitate to buy',NULL);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property_image`
--

DROP TABLE IF EXISTS `property_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_image` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Property_ID` int(11) NOT NULL,
  `Url` varchar(225) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `id_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property_image`
--

LOCK TABLES `property_image` WRITE;
/*!40000 ALTER TABLE `property_image` DISABLE KEYS */;
INSERT INTO `property_image` VALUES (1,1,'2_5.jpg'),(2,1,'2_5.jpg'),(3,1,'2_5.jpg'),(4,1,'2_5.jpg'),(5,2,'2_5.jpg'),(6,2,'2_5.jpg'),(7,2,'2_5.jpg'),(8,2,'2_5.jpg'),(9,3,'4_3.jpg'),(10,3,'4_3.jpg'),(11,4,'3_2.jpg'),(12,4,'3_2.jpg'),(13,0,'0_Chrysanthemum.jpg'),(14,0,'0_Desert.jpg'),(15,0,'0_Hydrangeas.jpg'),(16,5,'5_Penguins.jpg'),(17,6,'6_Tulips.jpg'),(18,6,'6_Koala.jpg'),(19,6,'6_Chrysanthemum.jpg'),(20,7,'7_5.jpg'),(21,7,'7_3.jpg'),(22,8,'8_2.jpg'),(23,8,'8_1.jpg'),(24,8,'8_3.jpg'),(25,8,'8_4.jpg');
/*!40000 ALTER TABLE `property_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propertyfeedback`
--

DROP TABLE IF EXISTS `propertyfeedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propertyfeedback` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AccountID` int(11) NOT NULL,
  `Comment` varchar(45) DEFAULT NULL,
  `PropertyID` int(11) NOT NULL,
  `RegisterDate` date NOT NULL,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propertyfeedback`
--

LOCK TABLES `propertyfeedback` WRITE;
/*!40000 ALTER TABLE `propertyfeedback` DISABLE KEYS */;
INSERT INTO `propertyfeedback` VALUES (1,1,'Comment',1,'2017-06-07',1),(2,2,'Comment 2',1,'2017-06-08',1),(3,3,'Comm',2,'2017-06-08',1),(4,2,'hello world',3,'2017-06-08',1),(5,1,'123',1,'2017-06-08',1),(6,1,'hi all',1,'2017-06-08',1),(7,1,'123',2,'2017-06-08',1),(8,1,'345',2,'2017-06-08',1),(9,1,'123213',1,'2017-06-08',1),(10,1,'this is not your house.',1,'2017-06-08',1),(11,1,'123213',2,'2017-06-12',1),(12,1,'asdsad',2,'2017-06-12',1),(13,1,'asdsad',2,'2017-06-12',1),(14,2,'Hello',7,'2017-06-12',1),(15,1,'Hello',8,'2017-06-12',1),(16,2,'Hello how are you',8,'2017-06-12',1);
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
INSERT INTO `propertystatus` VALUES (1,'Pending'),(2,'Approved'),(3,'Sold'),(4,'Rented'),(5,'Cancelled'),(6,'Deleted');
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
INSERT INTO `rentorder` VALUES (20,12.00,250.00,300.00,0),(21,3.00,250.00,400.00,0),(24,12.00,350.00,400.00,0),(25,24.00,1000.00,2000.00,0);
/*!40000 ALTER TABLE `rentorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentpurpose`
--

DROP TABLE IF EXISTS `rentpurpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rentpurpose` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RentMonth` decimal(6,2) NOT NULL,
  `PerMonthPrice` decimal(8,2) NOT NULL,
  `Deposit` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentpurpose`
--

LOCK TABLES `rentpurpose` WRITE;
/*!40000 ALTER TABLE `rentpurpose` DISABLE KEYS */;
INSERT INTO `rentpurpose` VALUES (1,12.00,300.00,400.00),(2,24.00,350.00,500.00),(4,0.00,0.00,0.00);
/*!40000 ALTER TABLE `rentpurpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellpurpose`
--

DROP TABLE IF EXISTS `sellpurpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sellpurpose` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SellPrice` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellpurpose`
--

LOCK TABLES `sellpurpose` WRITE;
/*!40000 ALTER TABLE `sellpurpose` DISABLE KEYS */;
INSERT INTO `sellpurpose` VALUES (1,100100.00),(2,200200.00),(3,1.00),(4,1.00),(5,300000.00);
/*!40000 ALTER TABLE `sellpurpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Mobile` varchar(45) DEFAULT NULL,
  `Role` smallint(1) DEFAULT '1',
  `Address` varchar(225) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Battuvshin',' Badarch','bat@mum.edu','1234567789',1,NULL,'123'),(2,'Amar','Dawaasuren','amar@mum.edu','123456789',2,NULL,'124'),(3,'Ganba','G','ganba@mum.edu','123456788',3,NULL,'125'),(4,'admin','admin','admin@mum.edu','123',4,NULL,'126');
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

-- Dump completed on 2017-06-12  4:30:14
