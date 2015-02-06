delimiter $$

CREATE TABLE `translation` (
  `codeOrig` varchar(30) NOT NULL,
  `codeOrigVocId` varchar(30) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `codeVocId` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`codeOrig`,`codeOrigVocId`) )$$
