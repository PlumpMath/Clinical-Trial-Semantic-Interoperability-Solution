CREATE USER 'testCV'@'localhost' IDENTIFIED BY 'testCV';
GRANT ALL ON `%testcv%`.* TO 'testCV'@'localhost';
GRANT ALL ON normalization_errors.* TO 'testCV'@'localhost';
GRANT ALL ON translations.* TO 'testCV'@'localhost';
GRANT ALL ON metathesaurus.* TO 'testCV'@'localhost';
GRANT CREATE ON *.* TO 'testCV'@'localhost';
FLUSH PRIVILEGES;
