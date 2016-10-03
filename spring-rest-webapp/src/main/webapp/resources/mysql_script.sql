-- Create database
create database spring_example

-- connect to the new database
use spring_example;

-- Create audit_request table
create table audit_request(
	request_id INT NOT NULL AUTO_INCREMENT, 
	request VARCHAR(200) NOT NULL, 
	requested_date DATE, 
	PRIMARY KEY (request_id));

-- Create animals table
create table clients(
	client_id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	address VARCHAR(25),
	PRIMARY KEY (client_id)
);
 
 