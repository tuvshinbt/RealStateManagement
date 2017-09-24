CREATE TABLE `ohrt`.`bookappointment` (
  `ID` INT NOT NULL,
  `PropertyID` INT NOT NULL,
  `BuyerID` INT NOT NULL,
  `AppointmentDate` DATE NULL,
  `AppointmentComment` VARCHAR(45) NULL,
  `AppointmentStatusID` INT NULL,
  PRIMARY KEY (`ID`));
