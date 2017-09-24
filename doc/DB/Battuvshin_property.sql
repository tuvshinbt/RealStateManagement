ALTER TABLE property ADD Parking int(11) DEFAULT NULL;
ALTER TABLE property ADD Livingroom int(11) DEFAULT NULL;
ALTER TABLE property ADD Kitchen int(11) DEFAULT NULL;

ALTER TABLE property ADD Description text;
ALTER TABLE property ADD GoogleMapPath text;


=========
ALTER TABLE propertyfeedback MODIFY COLUMN ID INT auto_increment;


========= 2017.6.11
ALTER TABLE `order` MODIFY COLUMN ID INT auto_increment;
ALTER TABLE `order` ADD Comment text AFTER SellerID;
ALTER TABLE bookappointment MODIFY COLUMN ID INT auto_increment;



CREATE TABLE `invoice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) NOT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `Amount` decimal(8,0) DEFAULT NULL,
  `StatusID` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;



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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
