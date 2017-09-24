CREATE TABLE `ohrt`.`propertyfeedback` (
  `ID` INT NOT NULL,
  `AccountID` INT NOT NULL,
  `Comment` VARCHAR(45) NULL,
  `PropertyID` INT NOT NULL,
  `RegisterDate` DATE NOT NULL,
  `Status` INT NOT NULL,
  PRIMARY KEY (`ID`));
