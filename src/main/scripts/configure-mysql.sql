# connect to mysql and run as root user
#Create Databases
CREATE DATABASE pio_dev;
CREATE DATABASE pio_prod;

#Create database service accounts
CREATE USER 'pio_dev_user'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'pio_prod_user'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'pio_dev_user'@'%' IDENTIFIED BY 'password';
CREATE USER 'pio_prod_user'@'%' IDENTIFIED BY 'password';

#Database grants
GRANT SELECT ON pio_dev.* to 'pio_dev_user'@'localhost';
GRANT INSERT ON pio_dev.* to 'pio_dev_user'@'localhost';
GRANT DELETE ON pio_dev.* to 'pio_dev_user'@'localhost';
GRANT UPDATE ON pio_dev.* to 'pio_dev_user'@'localhost';
GRANT SELECT ON pio_prod.* to 'pio_prod_user'@'localhost';
GRANT INSERT ON pio_prod.* to 'pio_prod_user'@'localhost';
GRANT DELETE ON pio_prod.* to 'pio_prod_user'@'localhost';
GRANT UPDATE ON pio_prod.* to 'pio_prod_user'@'localhost';
GRANT SELECT ON pio_dev.* to 'pio_dev_user'@'%';
GRANT INSERT ON pio_dev.* to 'pio_dev_user'@'%';
GRANT DELETE ON pio_dev.* to 'pio_dev_user'@'%';
GRANT UPDATE ON pio_dev.* to 'pio_dev_user'@'%';
GRANT SELECT ON pio_prod.* to 'pio_prod_user'@'%';
GRANT INSERT ON pio_prod.* to 'pio_prod_user'@'%';
GRANT DELETE ON pio_prod.* to 'pio_prod_user'@'%';
GRANT UPDATE ON pio_prod.* to 'pio_prod_user'@'%';