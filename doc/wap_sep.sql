/*
 Navicat MySQL Data Transfer

 Source Server         : 107.180.56.180
 Source Server Type    : MySQL
 Source Server Version : 50636
 Source Host           : 107.180.56.180:3306
 Source Schema         : wap_sep

 Target Server Type    : MySQL
 Target Server Version : 50636
 File Encoding         : 65001

 Date: 26/09/2017 12:23:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apartment
-- ----------------------------
DROP TABLE IF EXISTS `apartment`;
CREATE TABLE `apartment`  (
  `ID` int(11) NOT NULL,
  `Floor` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of apartment
-- ----------------------------
INSERT INTO `apartment` VALUES (0, 1);
INSERT INTO `apartment` VALUES (3, 1);
INSERT INTO `apartment` VALUES (4, 2);
INSERT INTO `apartment` VALUES (6, 1);
INSERT INTO `apartment` VALUES (7, 1);
INSERT INTO `apartment` VALUES (8, 1);
INSERT INTO `apartment` VALUES (12, 1);
INSERT INTO `apartment` VALUES (13, 1);

-- ----------------------------
-- Table structure for appointmentstatus
-- ----------------------------
DROP TABLE IF EXISTS `appointmentstatus`;
CREATE TABLE `appointmentstatus`  (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of appointmentstatus
-- ----------------------------
INSERT INTO `appointmentstatus` VALUES (1, 'Received');
INSERT INTO `appointmentstatus` VALUES (2, 'Approved');

-- ----------------------------
-- Table structure for bookappointment
-- ----------------------------
DROP TABLE IF EXISTS `bookappointment`;
CREATE TABLE `bookappointment`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PropertyID` int(11) NOT NULL,
  `BuyerID` int(11) NOT NULL,
  `AppointmentDate` datetime(0) NULL DEFAULT NULL,
  `AppointmentComment` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AppointmentStatusID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bookappointment
-- ----------------------------
INSERT INTO `bookappointment` VALUES (1, 1, 1, '2017-06-11 21:19:30', 'a', 2);
INSERT INTO `bookappointment` VALUES (2, 6, 1, '2017-09-02 10:00:00', '123', 2);
INSERT INTO `bookappointment` VALUES (16, 2, 1, '2017-09-02 10:00:00', 'zxc', 1);
INSERT INTO `bookappointment` VALUES (17, 9, 1, '2017-09-02 10:00:00', '123', 1);

-- ----------------------------
-- Table structure for buyorder
-- ----------------------------
DROP TABLE IF EXISTS `buyorder`;
CREATE TABLE `buyorder`  (
  `ID` int(11) NOT NULL,
  `BuyPrice` decimal(8, 2) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of buyorder
-- ----------------------------
INSERT INTO `buyorder` VALUES (18, 180000.00);
INSERT INTO `buyorder` VALUES (22, 3.00);
INSERT INTO `buyorder` VALUES (23, 111.00);
INSERT INTO `buyorder` VALUES (29, 123456.00);
INSERT INTO `buyorder` VALUES (30, 100.00);

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `ID` int(11) NOT NULL,
  `Yard` decimal(8, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES (1, 100.00);
INSERT INTO `house` VALUES (2, 300.00);
INSERT INTO `house` VALUES (5, 123.00);
INSERT INTO `house` VALUES (9, 1.00);
INSERT INTO `house` VALUES (10, 1.00);
INSERT INTO `house` VALUES (11, 1.00);
INSERT INTO `house` VALUES (14, 2.00);

-- ----------------------------
-- Table structure for invoice
-- ----------------------------
DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) NOT NULL,
  `CreatedDate` datetime(0) NULL DEFAULT NULL,
  `Amount` decimal(8, 0) NULL DEFAULT NULL,
  `StatusID` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of invoice
-- ----------------------------
INSERT INTO `invoice` VALUES (11, 18, '2017-06-12 01:52:31', 180000, 2);
INSERT INTO `invoice` VALUES (12, 20, '2017-06-12 02:52:19', 3300, 2);
INSERT INTO `invoice` VALUES (13, 21, '2017-06-12 03:04:11', 1150, 1);
INSERT INTO `invoice` VALUES (14, 24, '2017-06-12 03:05:40', 4600, 2);
INSERT INTO `invoice` VALUES (15, 23, '2017-06-12 14:25:49', 111, 2);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AccountID` int(11) NOT NULL,
  `RegisterDate` date NOT NULL,
  `PropertyID` int(11) NOT NULL,
  `StatusID` int(11) NULL DEFAULT NULL,
  `SellerID` int(11) NULL DEFAULT NULL,
  `Comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (18, 1, '2017-06-11', 3, 3, 1, 'sell1');
INSERT INTO `order` VALUES (20, 1, '2017-06-11', 2, 4, 1, 'hi all');
INSERT INTO `order` VALUES (21, 3, '2017-06-11', 2, 2, 2, 'hi all');
INSERT INTO `order` VALUES (22, 3, '2017-06-11', 3, 1, 3, 'desc3');
INSERT INTO `order` VALUES (23, 1, '2017-06-12', 3, 3, 2, '1');
INSERT INTO `order` VALUES (24, 1, '2017-06-12', 4, 4, 2, 'test1');
INSERT INTO `order` VALUES (25, 2, '2017-09-25', 4, 1, 1, 'asgd');
INSERT INTO `order` VALUES (26, 2, '2017-09-25', 4, 1, 1, 'asdgasdghadfhadfh');
INSERT INTO `order` VALUES (27, 2, '2017-09-25', 3, 1, 3, 'khandaa test order');
INSERT INTO `order` VALUES (28, 1, '2017-09-25', 2, 1, 2, 'dfhfjfj');
INSERT INTO `order` VALUES (29, 1, '2017-09-25', 3, 1, 3, 'bat test order by khandaa');
INSERT INTO `order` VALUES (30, 1, '2017-09-25', 5, 1, 2, 'hi');

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceID` int(11) NOT NULL,
  `OrderID` int(11) NOT NULL,
  `Amount` decimal(8, 0) NULL DEFAULT NULL,
  `PaidTotal` decimal(8, 0) NULL DEFAULT NULL,
  `PaymentType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CardType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PaidDate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (3, 11, 18, 180000, 0, 'card', 'visa', '2017-06-12 02:03:46');
INSERT INTO `payment` VALUES (4, 12, 20, 3300, 0, 'card', 'visa', '2017-06-12 02:53:21');
INSERT INTO `payment` VALUES (5, 14, 24, 4600, 0, 'card', 'visa', '2017-06-12 03:06:09');
INSERT INTO `payment` VALUES (6, 15, 23, 111, 0, 'card', 'visa', '2017-06-12 14:26:21');

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `OwnerID` int(11) NOT NULL,
  `Bedroom` int(11) NULL DEFAULT NULL,
  `Bathroom` int(11) NULL DEFAULT NULL,
  `StatusID` int(11) NULL DEFAULT NULL,
  `ApprovedBy` int(11) NULL DEFAULT NULL,
  `RegisterDate` date NULL DEFAULT NULL,
  `ApprovedDate` date NULL DEFAULT NULL,
  `AgentAccount` int(11) NULL DEFAULT NULL,
  `UtilitiesCost` decimal(6, 2) NULL DEFAULT NULL,
  `PurposeKey` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PurposeID` int(11) NULL DEFAULT NULL,
  `Parking` int(11) NULL DEFAULT NULL,
  `Livingroom` int(11) NULL DEFAULT NULL,
  `Kitchen` int(11) NULL DEFAULT NULL,
  `Description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `GoogleMapPath` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES (1, 'name11', 'Address1', 1, 1, 1, 2, 1, '2017-06-07', '2017-06-11', 1, 1000.00, 'Sell', 1, 1, 1, 1, NULL, NULL);
INSERT INTO `property` VALUES (2, 'name22', 'Address2', 2, 2, 2, 2, 1, '2017-06-08', '2017-06-11', 2, 200.00, 'Rent', 1, 2, 2, 2, NULL, NULL);
INSERT INTO `property` VALUES (3, 'name33', 'Address31', 3, 3, 3, 3, 1, '2017-06-09', '2017-06-07', 3, 300.00, 'Sell', 2, 3, 3, 3, NULL, NULL);
INSERT INTO `property` VALUES (4, 'name44', 'Address4', 1, 4, 4, 4, 1, '2017-06-10', '2017-06-07', 3, 400.00, 'Rent', 2, 3, 4, 4, NULL, NULL);
INSERT INTO `property` VALUES (5, 'pro5', '1000N', 2, 1, 1, 2, 4, '2017-06-12', '2017-06-12', 3, 1.00, 'Sell', 3, 1, 1, 1, NULL, NULL);
INSERT INTO `property` VALUES (6, 'pro6', '1000N1', 2, 1, 1, 2, 1, '2017-06-12', '2017-09-25', 3, 1.00, 'Sell', 4, 1, 1, 1, 'test 2', NULL);
INSERT INTO `property` VALUES (7, 'Test 1', '1000N 4th Str', 1, 1, 1, 2, 1, '2017-09-25', '2017-09-25', NULL, 1.00, 'Rent', 5, 1, 2, 1, 'msg1', NULL);
INSERT INTO `property` VALUES (8, 'Test 2', '1000N 4th Str 2', 1, 1, 1, 2, 1, '2017-09-25', '2017-09-25', 3, 2.00, 'Sell', 5, 1, 2, 1, 'msg2', NULL);
INSERT INTO `property` VALUES (9, 'Test 3', '1000N 4th Str 3', 1, 1, 1, 1, 0, '2017-09-25', NULL, NULL, 2.00, 'Rent', 6, 1, 1, 1, 'msg3', NULL);
INSERT INTO `property` VALUES (10, 'Test 4', '1000N 4th Str 4', 1, 1, 1, 1, 0, '2017-09-25', NULL, 3, 4.00, 'Sell', 6, 1, 1, 1, 'msg4', NULL);
INSERT INTO `property` VALUES (11, 'Test 5', '1000N 4th Str 5', 1, 1, 1, 1, 0, '2017-09-25', NULL, 3, 5.00, 'Rent', 7, 1, 1, 1, 'msg5', NULL);
INSERT INTO `property` VALUES (12, 'Test 6', '1000N 4th Str 6', 1, 1, 1, 1, 0, '2017-09-25', NULL, 3, 1.00, 'Rent', 8, 1, 1, 1, 'msg6', NULL);
INSERT INTO `property` VALUES (13, 'Test 7', '1000N 4th Str 7', 1, 1, 1, 1, 0, '2017-09-25', NULL, NULL, 7.00, 'Rent', 9, 1, 1, 1, 'msg7', NULL);
INSERT INTO `property` VALUES (14, 'Test 8', '1000N 4th Str 8', 1, 8, 1, 2, 1, '2017-09-26', '2017-09-26', 3, 7.00, 'Rent', 10, 1, 1, 2, '800', NULL);

-- ----------------------------
-- Table structure for property_image
-- ----------------------------
DROP TABLE IF EXISTS `property_image`;
CREATE TABLE `property_image`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Property_ID` int(11) NOT NULL,
  `Url` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `id_UNIQUE`(`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of property_image
-- ----------------------------
INSERT INTO `property_image` VALUES (1, 1, '1_1.jpg');
INSERT INTO `property_image` VALUES (2, 1, '2_1.jpg');
INSERT INTO `property_image` VALUES (3, 1, '3_1.jpg');
INSERT INTO `property_image` VALUES (4, 1, '4_1.jpg');
INSERT INTO `property_image` VALUES (5, 2, '3_1.jpg');
INSERT INTO `property_image` VALUES (6, 2, '9_1.jpg');
INSERT INTO `property_image` VALUES (7, 2, '4_1.jpg');
INSERT INTO `property_image` VALUES (8, 2, '8_1.jpg');
INSERT INTO `property_image` VALUES (9, 3, '3_1.jpg');
INSERT INTO `property_image` VALUES (10, 3, '4_3.jpg');
INSERT INTO `property_image` VALUES (11, 4, '4_1.jpg');
INSERT INTO `property_image` VALUES (12, 4, '3_2.jpg');
INSERT INTO `property_image` VALUES (13, 0, '1_1.jpg');
INSERT INTO `property_image` VALUES (14, 0, '0_Desert.jpg');
INSERT INTO `property_image` VALUES (15, 0, '0_Hydrangeas.jpg');
INSERT INTO `property_image` VALUES (16, 5, '5_1.jpg');
INSERT INTO `property_image` VALUES (17, 6, '3_1.jpg');
INSERT INTO `property_image` VALUES (18, 6, '6_1.jpg');
INSERT INTO `property_image` VALUES (20, 0, '0_1.jpg');
INSERT INTO `property_image` VALUES (21, 7, '7_3.jpg');
INSERT INTO `property_image` VALUES (22, 8, '8_1.jpg');
INSERT INTO `property_image` VALUES (23, 9, '9_1.jpg');
INSERT INTO `property_image` VALUES (24, 10, '10_1.jpg');
INSERT INTO `property_image` VALUES (25, 11, '11_1.jpg');
INSERT INTO `property_image` VALUES (26, 12, '12_1.jpg');
INSERT INTO `property_image` VALUES (27, 12, '9_1.jpg');
INSERT INTO `property_image` VALUES (28, 13, '13_1.jpg');
INSERT INTO `property_image` VALUES (29, 14, '14_1.jpg');
INSERT INTO `property_image` VALUES (30, 14, '1_1.jpg');

-- ----------------------------
-- Table structure for propertyfeedback
-- ----------------------------
DROP TABLE IF EXISTS `propertyfeedback`;
CREATE TABLE `propertyfeedback`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AccountID` int(11) NOT NULL,
  `Comment` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PropertyID` int(11) NOT NULL,
  `RegisterDate` datetime(0) NOT NULL,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of propertyfeedback
-- ----------------------------
INSERT INTO `propertyfeedback` VALUES (1, 1, 'Comment', 1, '2017-06-07 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (2, 2, 'Comment 2', 1, '2017-06-08 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (3, 3, 'Comm', 2, '2017-06-08 10:25:36', 1);
INSERT INTO `propertyfeedback` VALUES (4, 2, 'hello world', 3, '2017-06-08 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (5, 1, '123', 1, '2017-06-08 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (6, 1, 'hi all', 1, '2017-06-08 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (9, 1, '123213', 1, '2017-06-08 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (10, 1, 'this is not your house.', 1, '2017-06-08 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (14, 1, 'bat', 1, '2017-09-25 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (15, 1, 'khandaa tesyt 1', 1, '2017-09-25 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (16, 1, 'hi', 5, '2017-09-25 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (17, 1, 'hi all\r\n', 5, '2017-09-25 00:00:00', 1);
INSERT INTO `propertyfeedback` VALUES (57, 1, '123', 14, '2017-09-26 11:22:29', 1);

-- ----------------------------
-- Table structure for propertystatus
-- ----------------------------
DROP TABLE IF EXISTS `propertystatus`;
CREATE TABLE `propertystatus`  (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of propertystatus
-- ----------------------------
INSERT INTO `propertystatus` VALUES (1, 'Pending');
INSERT INTO `propertystatus` VALUES (2, 'Approved');
INSERT INTO `propertystatus` VALUES (3, 'Sold');
INSERT INTO `propertystatus` VALUES (4, 'Rented');
INSERT INTO `propertystatus` VALUES (5, 'Cancelled');
INSERT INTO `propertystatus` VALUES (6, 'Deleted');

-- ----------------------------
-- Table structure for rentorder
-- ----------------------------
DROP TABLE IF EXISTS `rentorder`;
CREATE TABLE `rentorder`  (
  `ID` int(11) NOT NULL,
  `RentMonth` decimal(6, 2) NOT NULL,
  `PerMonthPrice` decimal(8, 2) NOT NULL,
  `Deposit` decimal(8, 2) NOT NULL,
  `Extendable` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rentorder
-- ----------------------------
INSERT INTO `rentorder` VALUES (20, 12.00, 250.00, 300.00, 0);
INSERT INTO `rentorder` VALUES (21, 3.00, 250.00, 400.00, 0);
INSERT INTO `rentorder` VALUES (24, 12.00, 350.00, 400.00, 0);
INSERT INTO `rentorder` VALUES (25, 2346.00, 2346.00, 24646.00, 0);
INSERT INTO `rentorder` VALUES (26, 2346.00, 2346.00, 24646.00, 0);
INSERT INTO `rentorder` VALUES (27, 2346.00, 120.00, 24646.00, 0);
INSERT INTO `rentorder` VALUES (28, 2346.00, 2346.00, 24646.00, 0);

-- ----------------------------
-- Table structure for rentpurpose
-- ----------------------------
DROP TABLE IF EXISTS `rentpurpose`;
CREATE TABLE `rentpurpose`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RentMonth` decimal(6, 2) NOT NULL,
  `PerMonthPrice` decimal(8, 2) NOT NULL,
  `Deposit` decimal(8, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of rentpurpose
-- ----------------------------
INSERT INTO `rentpurpose` VALUES (1, 12.00, 300.00, 400.00);
INSERT INTO `rentpurpose` VALUES (2, 24.00, 350.00, 500.00);
INSERT INTO `rentpurpose` VALUES (3, 1.00, 1.00, 1.00);
INSERT INTO `rentpurpose` VALUES (4, 1.00, 1.00, 1.00);
INSERT INTO `rentpurpose` VALUES (5, 1.00, 1.00, 1.00);
INSERT INTO `rentpurpose` VALUES (6, 1.00, 1.00, 1.00);
INSERT INTO `rentpurpose` VALUES (7, 1.00, 5.00, 5.00);
INSERT INTO `rentpurpose` VALUES (8, 1.00, 1.00, 6.00);
INSERT INTO `rentpurpose` VALUES (9, 1.00, 7.00, 1.00);
INSERT INTO `rentpurpose` VALUES (10, 3.00, 6.00, 1.00);

-- ----------------------------
-- Table structure for sellpurpose
-- ----------------------------
DROP TABLE IF EXISTS `sellpurpose`;
CREATE TABLE `sellpurpose`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SellPrice` decimal(8, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sellpurpose
-- ----------------------------
INSERT INTO `sellpurpose` VALUES (1, 100100.00);
INSERT INTO `sellpurpose` VALUES (2, 200200.00);
INSERT INTO `sellpurpose` VALUES (3, 1.00);
INSERT INTO `sellpurpose` VALUES (4, 1.00);
INSERT INTO `sellpurpose` VALUES (5, 1.00);
INSERT INTO `sellpurpose` VALUES (6, 1.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LastName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Mobile` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Role` smallint(1) NULL DEFAULT 1,
  `Address` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `ID_UNIQUE`(`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Battuvshin', ' Badarch', 'bat@mum.edu', '1234567789', 4, NULL, '123');
INSERT INTO `user` VALUES (2, 'Dulamkhand', 'Batjargal', 'khandaa@mum.edu', '123456789', 2, NULL, '123');
INSERT INTO `user` VALUES (3, 'Aldaraa', 'Ch', 'aldar@mum.edu', '123456788', 3, NULL, '123');
INSERT INTO `user` VALUES (4, 'admin', 'admin', 'admin@mum.edu', '123', 4, NULL, '123');
INSERT INTO `user` VALUES (5, 'Ferdis', 'Fernando', 'fmuriyesu@gmail.com', '123456789', 1, NULL, '123');
INSERT INTO `user` VALUES (6, 'Kaka', 'Kakak', 'mferdis3@gmail.com', '6412320064', 2, 'Kigali', 'Kakali');

SET FOREIGN_KEY_CHECKS = 1;
