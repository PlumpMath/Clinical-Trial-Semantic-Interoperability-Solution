CREATE SCHEMA normalization_errors;

USE normalization_errors;

CREATE TABLE `errors` (
  `idMsg` varchar(255) NOT NULL,
  `id` varchar(255) NOT NULL,
  `code` varchar(30) DEFAULT '',
  `codeVocId` varchar(30) DEFAULT '',
  `translationError` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idMsg`,`id`)
)

