/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : ohrt

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2017-09-23 18:42:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `apartment`
-- ----------------------------
DROP TABLE IF EXISTS `apartment`;
CREATE TABLE `apartment` (
  `ID` int(11) NOT NULL,
  `Floor` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apartment
-- ----------------------------
INSERT INTO `apartment` VALUES ('3', '1');
INSERT INTO `apartment` VALUES ('4', '2');
INSERT INTO `apartment` VALUES ('6', '1');

-- ----------------------------
-- Table structure for `appointmentstatus`
-- ----------------------------
DROP TABLE IF EXISTS `appointmentstatus`;
CREATE TABLE `appointmentstatus` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appointmentstatus
-- ----------------------------
INSERT INTO `appointmentstatus` VALUES ('1', 'Received');
INSERT INTO `appointmentstatus` VALUES ('2', 'Approved');

-- ----------------------------
-- Table structure for `bookappointment`
-- ----------------------------
DROP TABLE IF EXISTS `bookappointment`;
CREATE TABLE `bookappointment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PropertyID` int(11) NOT NULL,
  `BuyerID` int(11) NOT NULL,
  `AppointmentDate` datetime DEFAULT NULL,
  `AppointmentComment` varchar(45) DEFAULT NULL,
  `AppointmentStatusID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookappointment
-- ----------------------------
INSERT INTO `bookappointment` VALUES ('1', '1', '1', '2017-06-11 21:19:30', 'a', '1');

-- ----------------------------
-- Table structure for `buyorder`
-- ----------------------------
DROP TABLE IF EXISTS `buyorder`;
CREATE TABLE `buyorder` (
  `ID` int(11) NOT NULL,
  `BuyPrice` decimal(8,2) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyorder
-- ----------------------------
INSERT INTO `buyorder` VALUES ('18', '180000.00');
INSERT INTO `buyorder` VALUES ('22', '3.00');
INSERT INTO `buyorder` VALUES ('23', '111.00');

-- ----------------------------
-- Table structure for `house`
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `ID` int(11) NOT NULL,
  `Yard` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('1', '100.00');
INSERT INTO `house` VALUES ('2', '300.00');
INSERT INTO `house` VALUES ('5', '123.00');

-- ----------------------------
-- Table structure for `invoice`
-- ----------------------------
DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) NOT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `Amount` decimal(8,0) DEFAULT NULL,
  `StatusID` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invoice
-- ----------------------------
INSERT INTO `invoice` VALUES ('11', '18', '2017-06-12 01:52:31', '180000', '2');
INSERT INTO `invoice` VALUES ('12', '20', '2017-06-12 02:52:19', '3300', '2');
INSERT INTO `invoice` VALUES ('13', '21', '2017-06-12 03:04:11', '1150', '1');
INSERT INTO `invoice` VALUES ('14', '24', '2017-06-12 03:05:40', '4600', '2');
INSERT INTO `invoice` VALUES ('15', '23', '2017-06-12 14:25:49', '111', '2');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AccountID` int(11) NOT NULL,
  `RegisterDate` date NOT NULL,
  `PropertyID` int(11) NOT NULL,
  `StatusID` int(11) DEFAULT NULL,
  `SellerID` int(11) DEFAULT NULL,
  `Comment` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('18', '1', '2017-06-11', '3', '3', '1', 'sell1');
INSERT INTO `order` VALUES ('20', '1', '2017-06-11', '2', '4', '1', 'hi all');
INSERT INTO `order` VALUES ('21', '3', '2017-06-11', '2', '2', '2', 'hi all');
INSERT INTO `order` VALUES ('22', '3', '2017-06-11', '3', '1', '3', 'desc3');
INSERT INTO `order` VALUES ('23', '1', '2017-06-12', '3', '3', '2', '1');
INSERT INTO `order` VALUES ('24', '1', '2017-06-12', '4', '4', '2', 'test1');

-- ----------------------------
-- Table structure for `payment`
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES ('3', '11', '18', '180000', '0', 'card', 'visa', '2017-06-12 02:03:46');
INSERT INTO `payment` VALUES ('4', '12', '20', '3300', '0', 'card', 'visa', '2017-06-12 02:53:21');
INSERT INTO `payment` VALUES ('5', '14', '24', '4600', '0', 'card', 'visa', '2017-06-12 03:06:09');
INSERT INTO `payment` VALUES ('6', '15', '23', '111', '0', 'card', 'visa', '2017-06-12 14:26:21');

-- ----------------------------
-- Table structure for `property`
-- ----------------------------
DROP TABLE IF EXISTS `property`;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES ('1', 'name1', 'Address1', '1', '1', '1', '2', '1', '2017-06-07', '2017-06-11', '1', '1000.00', 'Sell', '1', '1', '1', '1', null, null);
INSERT INTO `property` VALUES ('2', 'name2', 'Address2', '2', '2', '2', '2', '1', '2017-06-08', '2017-06-11', '2', '200.00', 'Rent', '1', '2', '2', '2', null, null);
INSERT INTO `property` VALUES ('3', 'name3', 'Address31', '3', '3', '3', '3', '1', '2017-06-09', '2017-06-07', '3', '300.00', 'Sell', '2', '3', '3', '3', null, null);
INSERT INTO `property` VALUES ('4', 'name4', 'Address4', '1', '4', '4', '4', '1', '2017-06-10', '2017-06-07', '3', '400.00', 'Rent', '2', '3', '4', '4', null, null);
INSERT INTO `property` VALUES ('5', 'pro5', '1000N', '2', '1', '1', '2', '4', '2017-06-12', '2017-06-12', '3', '1.00', 'Sell', '3', '1', '1', '1', null, null);
INSERT INTO `property` VALUES ('6', 'pro6', '1000N1', '2', '1', '1', '1', '0', '2017-06-12', null, '3', '1.00', 'Sell', '4', '1', '1', '1', 'test 2', null);

-- ----------------------------
-- Table structure for `propertyfeedback`
-- ----------------------------
DROP TABLE IF EXISTS `propertyfeedback`;
CREATE TABLE `propertyfeedback` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AccountID` int(11) NOT NULL,
  `Comment` varchar(45) DEFAULT NULL,
  `PropertyID` int(11) NOT NULL,
  `RegisterDate` date NOT NULL,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of propertyfeedback
-- ----------------------------
INSERT INTO `propertyfeedback` VALUES ('1', '1', 'Comment', '1', '2017-06-07', '1');
INSERT INTO `propertyfeedback` VALUES ('2', '2', 'Comment 2', '1', '2017-06-08', '1');
INSERT INTO `propertyfeedback` VALUES ('3', '3', 'Comm', '2', '2017-06-08', '1');
INSERT INTO `propertyfeedback` VALUES ('4', '2', 'hello world', '3', '2017-06-08', '1');
INSERT INTO `propertyfeedback` VALUES ('5', '1', '123', '1', '2017-06-08', '1');
INSERT INTO `propertyfeedback` VALUES ('6', '1', 'hi all', '1', '2017-06-08', '1');
INSERT INTO `propertyfeedback` VALUES ('7', '1', '123', '2', '2017-06-08', '1');
INSERT INTO `propertyfeedback` VALUES ('8', '1', '345', '2', '2017-06-08', '1');
INSERT INTO `propertyfeedback` VALUES ('9', '1', '123213', '1', '2017-06-08', '1');
INSERT INTO `propertyfeedback` VALUES ('10', '1', 'this is not your house.', '1', '2017-06-08', '1');
INSERT INTO `propertyfeedback` VALUES ('11', '1', '123213', '2', '2017-06-12', '1');
INSERT INTO `propertyfeedback` VALUES ('12', '1', 'asdsad', '2', '2017-06-12', '1');
INSERT INTO `propertyfeedback` VALUES ('13', '1', 'asdsad', '2', '2017-06-12', '1');

-- ----------------------------
-- Table structure for `propertystatus`
-- ----------------------------
DROP TABLE IF EXISTS `propertystatus`;
CREATE TABLE `propertystatus` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of propertystatus
-- ----------------------------
INSERT INTO `propertystatus` VALUES ('1', 'Pending');
INSERT INTO `propertystatus` VALUES ('2', 'Approved');
INSERT INTO `propertystatus` VALUES ('3', 'Sold');
INSERT INTO `propertystatus` VALUES ('4', 'Rented');
INSERT INTO `propertystatus` VALUES ('5', 'Cancelled');
INSERT INTO `propertystatus` VALUES ('6', 'Deleted');

-- ----------------------------
-- Table structure for `property_image`
-- ----------------------------
DROP TABLE IF EXISTS `property_image`;
CREATE TABLE `property_image` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Property_ID` int(11) NOT NULL,
  `Url` varchar(225) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `id_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of property_image
-- ----------------------------
INSERT INTO `property_image` VALUES ('1', '1', '2_5.jpg');
INSERT INTO `property_image` VALUES ('2', '1', '2_5.jpg');
INSERT INTO `property_image` VALUES ('3', '1', '2_5.jpg');
INSERT INTO `property_image` VALUES ('4', '1', '2_5.jpg');
INSERT INTO `property_image` VALUES ('5', '2', '2_5.jpg');
INSERT INTO `property_image` VALUES ('6', '2', '2_5.jpg');
INSERT INTO `property_image` VALUES ('7', '2', '2_5.jpg');
INSERT INTO `property_image` VALUES ('8', '2', '2_5.jpg');
INSERT INTO `property_image` VALUES ('9', '3', '4_3.jpg');
INSERT INTO `property_image` VALUES ('10', '3', '4_3.jpg');
INSERT INTO `property_image` VALUES ('11', '4', '3_2.jpg');
INSERT INTO `property_image` VALUES ('12', '4', '3_2.jpg');
INSERT INTO `property_image` VALUES ('13', '0', '0_Chrysanthemum.jpg');
INSERT INTO `property_image` VALUES ('14', '0', '0_Desert.jpg');
INSERT INTO `property_image` VALUES ('15', '0', '0_Hydrangeas.jpg');
INSERT INTO `property_image` VALUES ('16', '5', '5_Penguins.jpg');
INSERT INTO `property_image` VALUES ('17', '6', '6_Tulips.jpg');
INSERT INTO `property_image` VALUES ('18', '6', '6_Koala.jpg');
INSERT INTO `property_image` VALUES ('19', '6', '6_Chrysanthemum.jpg');

-- ----------------------------
-- Table structure for `rentorder`
-- ----------------------------
DROP TABLE IF EXISTS `rentorder`;
CREATE TABLE `rentorder` (
  `ID` int(11) NOT NULL,
  `RentMonth` decimal(6,2) NOT NULL,
  `PerMonthPrice` decimal(8,2) NOT NULL,
  `Deposit` decimal(8,2) NOT NULL,
  `Extendable` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rentorder
-- ----------------------------
INSERT INTO `rentorder` VALUES ('20', '12.00', '250.00', '300.00', '0');
INSERT INTO `rentorder` VALUES ('21', '3.00', '250.00', '400.00', '0');
INSERT INTO `rentorder` VALUES ('24', '12.00', '350.00', '400.00', '0');

-- ----------------------------
-- Table structure for `rentpurpose`
-- ----------------------------
DROP TABLE IF EXISTS `rentpurpose`;
CREATE TABLE `rentpurpose` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RentMonth` decimal(6,2) NOT NULL,
  `PerMonthPrice` decimal(8,2) NOT NULL,
  `Deposit` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rentpurpose
-- ----------------------------
INSERT INTO `rentpurpose` VALUES ('1', '12.00', '300.00', '400.00');
INSERT INTO `rentpurpose` VALUES ('2', '24.00', '350.00', '500.00');

-- ----------------------------
-- Table structure for `sellpurpose`
-- ----------------------------
DROP TABLE IF EXISTS `sellpurpose`;
CREATE TABLE `sellpurpose` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SellPrice` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellpurpose
-- ----------------------------
INSERT INTO `sellpurpose` VALUES ('1', '100100.00');
INSERT INTO `sellpurpose` VALUES ('2', '200200.00');
INSERT INTO `sellpurpose` VALUES ('3', '1.00');
INSERT INTO `sellpurpose` VALUES ('4', '1.00');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
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

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Battuvshin', ' Badarch', 'bat@mum.edu', '1234567789', '1', null, '123');
INSERT INTO `user` VALUES ('2', 'Amar', 'Dawaasuren', 'amar@mum.edu', '123456789', '2', null, '124');
INSERT INTO `user` VALUES ('3', 'Ganba', 'G', 'ganba@mum.edu', '123456788', '3', null, '125');
INSERT INTO `user` VALUES ('4', 'admin', 'admin', 'admin@mum.edu', '123', '4', null, '126');
