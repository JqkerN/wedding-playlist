CREATE TABLE `wedding`.`tracks`(
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
title varchar(255) UNIQUE
);

CREATE TABLE `wedding`.`guests`(
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
userId int NOT NULL,
fullName varchar(255) NOT NULL,
FOREIGN KEY (userId) REFERENCES users(id)
);

CREATE TABLE `wedding`.`votes`(
id int NOT NULL AUTO_INCREMENT,
trackId int NOT NULL,
guestId int NOT NULL,
KEY(id),
PRIMARY KEY(trackId, guestId),
FOREIGN KEY (trackId) REFERENCES tracks(id),
FOREIGN KEY (guestId) REFERENCES guests(id)
);

CREATE TABLE `wedding`.`users`(
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role VARCHAR(255) NOT NULL
);
















CREATE TABLE `tracks`(
id int PRIMARY KEY AUTO_INCREMENT,
title varchar(255) UNIQUE
);

CREATE TABLE `guests`(
id int PRIMARY KEY AUTO_INCREMENT,
fullName varchar(255)
);

CREATE TABLE `votes`(
id int NOT NULL AUTO_INCREMENT,
trackId int,
guestId int,
PRIMARY KEY(trackId, guestId),
FOREIGN KEY (trackId) REFERENCES tracks(id),
FOREIGN KEY (guestId) REFERENCES guests(id)
);



