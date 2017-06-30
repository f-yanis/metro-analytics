CREATE DATABASE metro_db;
USE metro_db;

CREATE TABLE metro_stations (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name_station VARCHAR(30)
);

CREATE TABLE types_of_tickets (
	id INT AUTO_INCREMENT PRIMARY KEY,
    type_ticket VARCHAR(50)
);

CREATE TABLE tickets (
	card_number INT AUTO_INCREMENT PRIMARY KEY,
    type_ticket INT,
    balance INT,
    FOREIGN KEY (type_ticket) REFERENCES types_of_tickets (id)
);

CREATE TABLE passes (
	id INT AUTO_INCREMENT PRIMARY KEY,
    card_number INT,
    metro_station INT,
    data_passe DATETIME,
    FOREIGN KEY (card_number) REFERENCES tickets (card_number),
    FOREIGN KEY (metro_station) REFERENCES metro_stations (id)
);

CREATE TABLE ticket_sales (
	id INT AUTO_INCREMENT PRIMARY KEY,
    metro_station INT,
    card_number INT,
    data_sale DATETIME,
    FOREIGN KEY (metro_station) REFERENCES metro_stations (id),
    FOREIGN KEY (card_number) REFERENCES tickets (card_number) 
);