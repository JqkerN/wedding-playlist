CREATE TABLE `wedding`.`tracks`(
id int PRIMARY KEY AUTO_INCREMENT,
title varchar(255) UNIQUE
);

CREATE TABLE `wedding`.`guests`(
id int PRIMARY KEY AUTO_INCREMENT,
fullName varchar(255)
);

CREATE TABLE `wedding`.`votes`(
id int NOT NULL AUTO_INCREMENT,
trackId int,
guestId int,
PRIMARY KEY(trackId, guestId),
FOREIGN KEY (trackId) REFERENCES tracks(id),
FOREIGN KEY (guestId) REFERENCES guests(id)
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



