delimiter $$

CREATE TABLE `act` (
  `id` varchar(255) NOT NULL,
  `classCode` varchar(15) NOT NULL,
  `subClassCode` varchar(15) DEFAULT '',
  `moodCode` varchar(15) NOT NULL,
  `code` varchar(30) DEFAULT '',
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `actionNegationInd` tinyint(1) DEFAULT 0, 
  `title` varchar(255) DEFAULT '',
  `text` longtext, -- NO PUEDE TENER VALOR POR DEFECTO
  `statusCode` varchar(15) DEFAULT '',
  `effectiveTime_start` datetime NULL DEFAULT NULL, -- DEBE SER NULL PORQUE SI NO NO SE PUEDEN MANEJAR CON JDBC EN NORM PIPELINE
  `effectiveTime_end` datetime NULL DEFAULT NULL,
  `activityTime` datetime NULL DEFAULT NULL,
  `availabilityTime` datetime DEFAULT '0000-00-00',
  `creationTime` datetime DEFAULT '0000-00-00',
  `modificationTime` datetime DEFAULT '0000-00-00',
  `uncertaintyCode` varchar(1) DEFAULT '',
  PRIMARY KEY (`id`),
  INDEX (`code` ASC),
  INDEX (`codeOrig` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `actmethodcode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(50) NOT NULL,
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`,`code`),
  INDEX (`code` ASC),
  INDEX (`codeOrig` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `actobservationinterpretationcode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(50) NOT NULL,
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`code`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `actobservationvalues` (
  `id` varchar(255) NOT NULL,
  `control` bigint NOT NULL AUTO_INCREMENT,
  `value` varchar(50) DEFAULT '',
  `valueType` varchar(50) DEFAULT '',
  `units` varchar(50) DEFAULT '',
  `referenceRangeMin` float DEFAULT NULL,
  `referenceRangeMax` float DEFAULT NULL,
  `code` varchar(50) DEFAULT '',
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`control`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `actprocedureapproachsitecode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(30) NOT NULL,
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`code`,`id`),
  INDEX (`code` ASC),
  INDEX (`codeOrig` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `actrelationship` (
  `idA` varchar(255) NOT NULL,
  `idB` varchar(255) NOT NULL,
  `typeCode` varchar(15) NOT NULL,
  PRIMARY KEY (`idA`,`idB`,`typeCode`),
  KEY `has_act_actrelationship_b` (`idB`)
  -- CONSTRAINT `has_act_actrelationship_b` FOREIGN KEY (`idB`) REFERENCES `act` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  -- CONSTRAINT `has_act_actrelationship_a` FOREIGN KEY (`idA`) REFERENCES `act` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `acttargetsitecode` (
  `id` varchar(255) NOT NULL,
  `code` varchar(30) NOT NULL,
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`,`code`),
  INDEX (`code` ASC),
  INDEX (`codeOrig` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `entity` (
  `id` varchar(255) NOT NULL,
  `classCode` varchar(15) NOT NULL,
  `determinerCode` varchar(15) NOT NULL,
  `code` varchar(30) DEFAULT '',
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  `quantity` varchar(50) DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `desc` longtext,
  `statusCode` varchar(15) DEFAULT '',
  `creationTime` datetime DEFAULT NULL,
  `modificationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `exposure` (
  `id` varchar(255) NOT NULL,
  `routeCode` varchar(15) DEFAULT '',
  `exposureLevel` varchar(10) DEFAULT '',
  `exposureModeCode` varchar(15) DEFAULT '',
  PRIMARY KEY (`id`)
  -- CONSTRAINT `isa_exposure_act` FOREIGN KEY (`id`) REFERENCES `act` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$


CREATE TABLE `observation` (
  `id` varchar(255) NOT NULL,
  `valueNegationInd` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`)
  -- CONSTRAINT `isa_observation_act` FOREIGN KEY (`id`) REFERENCES `act` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `role` (
  `entityId` varchar(255) NOT NULL,
  `id` varchar(255) NOT NULL,
  `classCode` varchar(15) NOT NULL,
  `code` varchar(50) DEFAULT '',
  `codeVocId` varchar(30) DEFAULT '',
  `codeOrig` varchar(30) DEFAULT '',
  `codeOrigVocId` varchar(30) DEFAULT '',
  `title` varchar(255) DEFAULT '',
  `negationInd` tinyint(1) DEFAULT 0,
  `name` varchar(255) DEFAULT '',
  `statusCode` varchar(15) DEFAULT '',
  `effectiveTime_start` datetime DEFAULT NULL,
  `effectiveTime_end` datetime DEFAULT NULL,
  `confidentialityCode` varchar(5) DEFAULT '',
  PRIMARY KEY (`entityId`,`id`)
  -- CONSTRAINT `has_entity_role` FOREIGN KEY (`entityId`) REFERENCES `entity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `participation` (
  `entityId` varchar(255) NOT NULL,
  `roleId` varchar(255) NOT NULL,
  `actId` varchar(255) NOT NULL,
  `typeCode` varchar(10) NOT NULL,
  `functionCode` varchar(12) DEFAULT '',
  `negationInd` tinyint(1) DEFAULT 0,
  `noteText` longtext,
  `time_start` datetime DEFAULT NULL,
  `time_end` datetime DEFAULT NULL,
  `modeCode` varchar(12) DEFAULT '',
  PRIMARY KEY (`actId`,`entityId`,`roleId`,`typeCode`),
  KEY `has_role_participation` (`entityId`,`roleId`)
  -- CONSTRAINT `has_act_participation` FOREIGN KEY (`actId`) REFERENCES `act` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  -- CONSTRAINT `has_role_participation` FOREIGN KEY (`entityId`, `roleId`) REFERENCES `role` (`entityId`, `id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `livingsubject` (
  `id` varchar(255) NOT NULL,
  `administrativeGenderCode` varchar(20) DEFAULT '',
  `birthTime` datetime DEFAULT NULL,
  `birthTimeTolerance` integer DEFAULT NULL, -- QUÉ PONEMOS POR DEFECTO?   -> 0
  `deceasedInd` tinyint(1) DEFAULT NULL, 
  `deceasedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
  -- CONSTRAINT `isa_livingsubject_entity` FOREIGN KEY (`id`) REFERENCES `entity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `person` (
  `id` varchar(255) NOT NULL,
  `raceCode` varchar(30) DEFAULT '',
  `name` varchar(100) DEFAULT '',
  `surname` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`)
  -- CONSTRAINT `isa_person_livingsubject` FOREIGN KEY (`id`) REFERENCES `livingsubject` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


delimiter $$

CREATE TABLE `procedures` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
  -- CONSTRAINT `isa_procedure_act` FOREIGN KEY (`id`) REFERENCES `act` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `substanceadministration` (
  `id` varchar(255) NOT NULL,
  `routeCode` varchar(30) DEFAULT '',
  `routeCodeVocId` varchar(30) DEFAULT '',
  `routeCodeOrig` varchar(30) DEFAULT '',
  `routeCodeOrigVocId` varchar(30) DEFAULT '',
  `routeCodeTitle` varchar(255) DEFAULT '',
  `doseQuantity` varchar(15) DEFAULT '', 
  `doseQuantityUnits` varchar(15) DEFAULT '', 
  `rateQuantity` varchar(15) DEFAULT '',
  `rateQuantityUnits` varchar(15) DEFAULT '',
  `doseCheckQuantity` varchar(15) DEFAULT '',
  `doseCheckQuantityUnits` varchar(15) DEFAULT '',
  `periodIntervalTime` varchar(15) DEFAULT '',
  `periodIntervalUnits` varchar(15) DEFAULT '',
  PRIMARY KEY (`id`)
  -- CONSTRAINT `isa_substanceadministration_procedure` FOREIGN KEY (`id`) REFERENCES `procedures` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE VIEW `v_livingsubject` 
AS SELECT   `e`.`id` AS `id`,`e`.`classCode` AS `classCode`,`e`.`determinerCode` AS `determinerCode`,`e`.`code` AS `code`,
            `e`.`codeVocId` AS `codeVocId`, `e`.`codeOrig` AS `codeOrig`, `e`.`codeOrigVocId` AS `codeOrigVocId`, `e`.`title` AS `title`, `e`.`quantity` AS `quantity`,
            `e`.`name` AS `name`,`e`.`desc` AS `desc`,`e`.`statusCode` AS `statusCode`,`l`.`administrativeGenderCode` AS `administrativeGenderCode`,
            `e`.`creationTime` AS `creationTime`, `e`.`modificationTime` AS `modificationTime`,
            `l`.`birthTime` AS `birthTime`, `l`.`birthTimeTolerance` AS `birthTimeTolerance`,`l`.`deceasedInd` AS `deceasedInd`,`l`.`deceasedTime` AS `deceasedTime` 
FROM (`entity` `e` LEFT JOIN `livingsubject` `l` ON ((`e`.`id` = `l`.`id`))) WHERE `e`.`classCode` = "PSN" $$

delimiter $$

CREATE VIEW `v_person` 
AS SELECT   `e`.`id` AS `id`,`e`.`classCode` AS `classCode`,`e`.`determinerCode` AS `determinerCode`,`e`.`code` AS `code`,
            `e`.`codeVocId` AS `codeVocId`, `e`.`codeOrig` AS `codeOrig`, `e`.`codeOrigVocId` AS `codeOrigVocId`, `e`.`title` AS `title` ,`e`.`quantity` AS `quantity`,
            `e`.`name` AS `name`,`e`.`desc` AS `desc`,`e`.`statusCode` AS `statusCode`,`l`.`administrativeGenderCode` AS `administrativeGenderCode`,
            `e`.`creationTime` AS `creationTime`, `e`.`modificationTime` AS `modificationTime`,
            `l`.`birthTime` AS `birthTime`,`l`.`birthTimeTolerance` AS `birthTimeTolerance`,`l`.`deceasedInd` AS `deceasedInd`,
            `l`.`deceasedTime` AS `deceasedTime`,`p`.`raceCode` AS `raceCode`,`p`.`name` AS `personName`,`p`.`surname` AS `personSurname`
FROM ((`entity` `e` LEFT JOIN `livingsubject` `l` ON((`e`.`id` = `l`.`id`))) LEFT JOIN `person` `p` ON((`p`.`id` = `e`.`id`))) WHERE `e`.`classCode` = "PSN" $$

delimiter $$

CREATE VIEW `v_observation` 
AS SELECT   `a`.`id` AS `id`,`a`.`classCode` AS `classCode`,`a`.`subClassCode` AS `subClassCode`,`a`.`moodCode` AS `moodCode`,`a`.`code` AS `code`,
            `a`.`codeVocId` AS `codeVocId`,`a`.`codeOrig` AS `codeOrig`,`a`.`codeOrigVocId` AS `codeOrigVocId`,
            `a`.`actionNegationInd` AS `actionNegationInd`,`a`.`title` AS `title`, `a`.`text` AS `text`,
            `a`.`statusCode` AS `statusCode`,`a`.`effectiveTime_start` AS `effectiveTime_start`, `a`.`effectiveTime_end` AS `effectiveTime_end`, `a`.`activityTime` AS `activityTime`,
            `a`.`availabilityTime` AS `availabilityTime`, `a`.`creationTime` AS `creationTime`, `a`.`modificationTime` AS `modificationTime`,
			`a`.`uncertaintyCode` AS `uncertaintyCode`,
            `o`.`valueNegationInd` AS `valueNegationInd`,
			`ar`.`idB` AS `clinicalTrialId`,
            `aov`.`value` AS `value`,`aov`.`valueType` AS `valueType`,`aov`.`units` AS `units`,
			`aov`.`code` AS `valueCode`, `aov`.`codeVocId` AS `valueCodeVocId`,`aov`.`codeOrig` AS `valueCodeOrig`,
			`aov`.`codeOrigVocId` AS `valueCodeOrigVocId`, `aov`.`title` AS `valueTitle`,
            `aov`.`referenceRangeMin` AS `referenceRangeMin`,`aov`.`referenceRangeMax` AS `referenceRangeMax`,
            `aoic`.`code` AS `interpretationCode`, `aoic`.`title` AS `interpretationCodeTitle`,
			`aoic`.`codeVocId` AS `interpretationCodeVocId`, `aoic`.`codeOrig` AS `interpretationCodeOrig`, `aoic`.`codeOrigVocId` AS `interpretationCodeOrigVocId`, 
            `amc`.`code` AS `methodCode`, `amc`.`title` AS `methodCodeTitle`,
            `amc`.`codeVocId` AS `methodCodeVocId`, `amc`.`codeOrig` AS `methodCodeOrig`, `amc`.`codeOrigVocId` AS `methodCodeOrigVocId`,                        
            `atsc`.`code` AS `targetSiteCode`,`atsc`.`title` AS `targetSiteCodeTitle`,
            `atsc`.`codeVocId` AS `targetSiteCodeVocId`, `atsc`.`codeOrig` AS `targetSiteCodeOrig`, `atsc`.`codeOrigVocId` AS `targetSiteCodeOrigVocId`           
FROM ((((((`act` `a` LEFT JOIN `observation` `o` ON((`a`.`id` = `o`.`id`)))
		LEFT JOIN `actrelationship` `ar` ON((`ar`.`idA`=`a`.id)))
        LEFT JOIN `actobservationvalues` `aov` ON((`o`.`id` = `aov`.`id`))) 
        LEFT JOIN `actobservationinterpretationcode` `aoic` ON((`aoic`.`id` = `o`.`id`))) 
        LEFT JOIN `acttargetsitecode` `atsc` ON((`atsc`.`id` = `o`.`id`))) 
        LEFT JOIN `actmethodcode` `amc` ON((`amc`.`id` = `o`.`id`))) WHERE `a`.`classCode` = "OBS" $$
        
delimiter $$
        
CREATE VIEW `v_procedure` 
AS SELECT   `a`.`id` AS `id`,`a`.`classCode` AS `classCode`,`a`.`subClassCode` AS `subClassCode`,`a`.`moodCode` AS `moodCode`,`a`.`code` AS `code`,
            `a`.`codeVocId` AS `codeVocId`,`a`.`codeOrig` AS `codeOrig`,`a`.`codeOrigVocId` AS `codeOrigVocId`,
            `a`.`actionNegationInd` AS `actionNegationInd`,`a`.`title` AS `title`,  
            `a`.`text` AS `text`,`a`.`statusCode` AS `statusCode`,`a`.`effectiveTime_start` AS `effectiveTime_start`, `a`.`effectiveTime_end` AS `effectiveTime_end`,
            `a`.`activityTime` AS `activityTime`,`a`.`availabilityTime` AS `availabilityTime`,
            `a`.`creationTime` AS `creationTime`, `a`.`modificationTime` AS `modificationTime`,
			`a`.`uncertaintyCode` AS `uncertaintyCode`,
            `ar`.`idB` AS `clinicalTrialId`,
            `apasc`.`code` AS `approachSiteCode`,`apasc`.`title` AS `approachSiteCodeTitle`,
            `apasc`.`codeVocId` AS `approachSiteCodeVocId`, `apasc`.`codeOrig` AS `approachSiteCodeOrig`, `apasc`.`codeOrigVocId` AS `approachSiteCodeOrigVocId`, 
            `amc`.`code` AS `methodCode`, `amc`.`title` AS `methodCodeTitle`,
            `amc`.`codeVocId` AS `methodCodeVocId`, `amc`.`codeOrig` AS `methodCodeOrig`, `amc`.`codeOrigVocId` AS `methodCodeOrigVocId`,                        
            `atsc`.`code` AS `targetSiteCode`,`atsc`.`title` AS `targetSiteCodeTitle`,
            `atsc`.`codeVocId` AS `targetSiteCodeVocId`, `atsc`.`codeOrig` AS `targetSiteCodeOrig`, `atsc`.`codeOrigVocId` AS `targetSiteCodeOrigVocId`     
FROM (((((`act` `a` 
		LEFT JOIN `procedures` `p` ON((`p`.`id` = `a`.`id`))) 
        LEFT JOIN `actrelationship` `ar` ON((`ar`.`idA`=`a`.id)))
        LEFT JOIN `actprocedureapproachsitecode` `apasc` ON((`apasc`.`id` = `p`.`id`))) 
        LEFT JOIN `acttargetsitecode` `atsc` ON((`atsc`.`id` = `p`.`id`))) 
        LEFT JOIN `actmethodcode` `amc` ON((`p`.`id` = `amc`.`id`))) WHERE `a`.`classCode` = "PROC" $$

delimiter $$

CREATE VIEW `v_substanceadministration` 
AS SELECT   `a`.`id` AS `id`,`a`.`classCode` AS `classCode`,`a`.`subClassCode` AS `subClassCode`,`a`.`moodCode` AS `moodCode`,`a`.`code` AS `code`,
            `a`.`codeVocId` AS `codeVocId`,`a`.`codeOrig` AS `codeOrig`,`a`.`codeOrigVocId` AS `codeOrigVocId`,
            `a`.`actionNegationInd` AS `actionNegationInd`,`a`.`title` AS `title`,
            `a`.`text` AS `text`,`a`.`statusCode` AS `statusCode`,`a`.`effectiveTime_start` AS `effectiveTime_start`, `a`.`effectiveTime_end` AS `effectiveTime_end`,
            `a`.`activityTime` AS `activityTime`,`a`.`availabilityTime` AS `availabilityTime`,
            `a`.`creationTime` AS `creationTime`, `a`.`modificationTime` AS `modificationTime`,
			`a`.`uncertaintyCode` AS `uncertaintyCode`,
            `ar`.`idB` AS `clinicalTrialId`,
            `apasc`.`code` AS `approachSiteCode`,`apasc`.`title` AS `approachSiteCodeTitle`,
            `apasc`.`codeVocId` AS `approachSiteCodeVocId`, `apasc`.`codeOrig` AS `approachSiteCodeOrig`, `apasc`.`codeOrigVocId` AS `approachSiteCodeOrigVocId`, 
            `amc`.`code` AS `methodCode`, `amc`.`title` AS `methodCodeTitle`,
            `amc`.`codeVocId` AS `methodCodeVocId`, `amc`.`codeOrig` AS `methodCodeOrig`, `amc`.`codeOrigVocId` AS `methodCodeOrigVocId`,                        
            `atsc`.`code` AS `targetSiteCode`,`atsc`.`title` AS `targetSiteCodeTitle`,
            `atsc`.`codeVocId` AS `targetSiteCodeVocId`, `atsc`.`codeOrig` AS `targetSiteCodeOrig`, `atsc`.`codeOrigVocId` AS `targetSiteCodeOrigVocId`,    
            `s`.`routeCode` AS `routeCode`, `s`.`routeCodeVocId` AS `routeCodeVocId`, `s`.`routeCodeOrig` AS `routeCodeOrig`, `s`.`routeCodeOrigVocId` AS `routeCodeOrigVocId`, `s`.`routeCodeTitle` AS `routeCodeTitle`,
            `s`.`doseQuantity` AS `doseQuantity`,`s`.`doseQuantityUnits` AS `doseQuantityUnits`,`s`.`rateQuantity` AS `rateQuantity`,`s`.`rateQuantityUnits` AS `rateQuantityUnits`,
			`s`.`doseCheckQuantity` AS `doseCheckQuantity`,`s`.`doseCheckQuantityUnits` AS `doseCheckQuantityUnits`,
			`s`.`periodIntervalTime` AS `periodIntervalTime`, `s`.`periodIntervalUnits` AS `periodIntervalUnits`
FROM ((((((`act` `a` 
		LEFT JOIN `procedures` `p` ON((`p`.`id` = `a`.`id`))) 
		LEFT JOIN `substanceadministration` `s` ON((`s`.`id` = `p`.`id`))) 
		LEFT JOIN `actrelationship` `ar` ON((`ar`.`idA`=`a`.`id`)))
        LEFT JOIN `actprocedureapproachsitecode` `apasc` ON((`apasc`.`id` = `p`.`id`))) 
        LEFT JOIN `acttargetsitecode` `atsc` ON((`atsc`.`id` = `p`.`id`))) 
        LEFT JOIN `actmethodcode` `amc` ON((`p`.`id` = `amc`.`id`))) WHERE `a`.`classCode` = "SBADM" $$

delimiter $$

CREATE VIEW `v_exposure` 
AS SELECT   `a`.`id` AS `id`,`a`.`classCode` AS `classCode`,`a`.`subClassCode` AS `subClassCode`,`a`.`moodCode` AS `moodCode`,`a`.`code` AS `code`,
            `a`.`codeVocId` AS `codeVocId`,`a`.`codeOrig` AS `codeOrig`,`a`.`codeOrigVocId` AS `codeOrigVocId`,
            `a`.`actionNegationInd` AS `actionNegationInd`,`a`.`title` AS `title`, `a`.`text` AS `text`,
            `a`.`statusCode` AS `statusCode`,`a`.`effectiveTime_start` AS `effectiveTime_start`, `a`.`effectiveTime_end` AS `effectiveTime_end`, `a`.`activityTime` AS `activityTime`,
            `a`.`availabilityTime` AS `availabilityTime`, `a`.`creationTime` AS `creationTime`, `a`.`modificationTime` AS `modificationTime`,
            `a`.`uncertaintyCode` AS `uncertaintyCode`,
			`ar`.`idB` AS `clinicalTrialId`,
            `e`.`routeCode` AS `routeCode`, `e`.`exposureLevel` AS `exposureLevel`, `e`.`exposureModeCode` AS `exposureModeCode`
FROM (( `act` `a` 
		LEFT JOIN `exposure` `e` ON((`a`.`id` = `e`.`id`)))
		LEFT JOIN `actrelationship` `ar` ON((`ar`.`idA`=`a`.`id`)))$$

delimiter $$

CREATE TABLE `cache` ( 
	`concept_id` VARCHAR(45) NOT NULL , 
	`expanded_concepts` VARCHAR(45) NOT NULL , 
	`reasoning_type` VARCHAR(45) DEFAULT NULL , 
	PRIMARY KEY (`concept_id`, `expanded_concepts`) )$$
	
delimiter $$

CREATE TABLE `security` ( 
	`securityVar` VARCHAR(50) NOT NULL , 
	`dataset` VARCHAR(50) NOT NULL , 
	`decision` VARCHAR(50) DEFAULT NULL , 
	PRIMARY KEY (`securityVar`, `dataset`) )$$
