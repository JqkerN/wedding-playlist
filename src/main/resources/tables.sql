CREATE TABLE `wedding`.`songs`(
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
title varchar(255) UNIQUE,
artist varchar(255),
albumCover varchar(255)
);

CREATE TABLE `wedding`.`users`(
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role VARCHAR(255) NOT NULL
);

CREATE TABLE `wedding`.`guests`(
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
userId int NOT NULL,
fullName varchar(255) NOT NULL,
FOREIGN KEY (userId) REFERENCES users(id)
);

CREATE TABLE `wedding`.`votes`(
id int NOT NULL AUTO_INCREMENT,
songId int NOT NULL,
guestId int NOT NULL,
KEY(id),
PRIMARY KEY(songId, guestId),
FOREIGN KEY (songId) REFERENCES songs(id),
FOREIGN KEY (guestId) REFERENCES guests(id)
);


INSERT INTO wedding.users(username, password, role) VALUES("admin", "$2a$10$iaG3FgPOkaklqknAoaByWuTfQWuk7d2ueGQ0E/qRdQsScJB56OS8O", "ADMIN");


