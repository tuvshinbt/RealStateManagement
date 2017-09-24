CREATE TABLE `ohrt`.`rentorder` (
  `ID` INT NOT NULL,
  `RentMonth` DECIMAL(6,2) NOT NULL,
  `PerMonthPrice` DECIMAL(8,2) NOT NULL,
  `Deposit` DECIMAL(8,2) NOT NULL,
  `Extendable` INT NULL,
  PRIMARY KEY (`ID`));
