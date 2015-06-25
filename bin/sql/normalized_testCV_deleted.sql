CREATE SCHEMA normalized_testcv_deleted;

USE normalized_testcv_deleted; 

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
  `modificationTime` datetime NOT NULL DEFAULT '0000-00-00',
  `uncertaintyCode` varchar(1) DEFAULT '',
  PRIMARY KEY (`id`,`modificationTime`),
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
  `modificationTime` datetime DEFAULT '0000-00-00',
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
