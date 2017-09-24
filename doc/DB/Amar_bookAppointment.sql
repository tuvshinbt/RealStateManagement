ALTER TABLE `ohrt`.`bookappointment` 
CHANGE COLUMN `AppointmentDate` `AppointmentDate` DATETIME NULL DEFAULT NULL ;

ALTER TABLE `ohrt`.`user` 
DROP COLUMN `LoginName`,
CHANGE COLUMN `ID` `ID` INT(11) NOT NULL AUTO_INCREMENT ,
ADD COLUMN `Role` SMALLINT(1) NULL DEFAULT 1 AFTER `Mobile`,
ADD COLUMN `Address` VARCHAR(225) NULL AFTER `Role`,
ADD COLUMN `Password` VARCHAR(45) NULL AFTER `Address`,
DROP INDEX `LoginName_UNIQUE` ;

ALTER TABLE `ohrt`.`approintmentstatus` 
RENAME TO  `ohrt`.`appointmentstatus` ;


INSERT INTO `ohrt`.`appointmentstatus`
(`ID`,
`Name`)
VALUES
(1,
'Received');


INSERT INTO `ohrt`.`appointmentstatus`
(`ID`,
`Name`)
VALUES
(2,
'Approved');