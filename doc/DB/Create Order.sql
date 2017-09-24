CREATE TABLE `ohrt`.`order` (
  `ID` INT NOT NULL,
  `AccountID` INT NOT NULL,
  `RegisterDate` DATE NOT NULL,
  `PropertyID` INT NOT NULL,
  `StatusID` INT NULL,
  `SellerID` INT NULL,
  PRIMARY KEY (`ID`));
