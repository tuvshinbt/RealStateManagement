CREATE TABLE `ohrt`.`property` (
  `ID` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(100) NOT NULL,
  `OwnerID` INT NOT NULL,
  `Bedroom` INT NULL,
  `Bathroom` INT NULL,
  `StatusID` INT NULL,
  `ApprovedBy` INT NULL,
  `RegisterDate` DATE NULL,
  `ApprovedDate` DATE NULL,
  `agentAccount` INT NULL,
  `UtilitiesCost` DECIMAL(6,2) NULL,
  PRIMARY KEY (`ID`));

ALTER TABLE `ohrt`.`property` 
CHANGE COLUMN `agentAccount` `AgentAccount` INT(11) NULL DEFAULT NULL ,
ADD COLUMN `PurposeKey` VARCHAR(45) NULL AFTER `UtilitiesCost`,
ADD COLUMN `PurposeID` INT NULL AFTER `PurposeKey`;
