CREATE TABLE `weeding-playlist`.`tracks`(
id int PRIMARY KEY AUTO_INCREMENT,
title varchar(255) UNIQUE
);

CREATE TABLE `weeding-playlist`.`guests`(
id int PRIMARY KEY AUTO_INCREMENT,
fullName varchar(255)
);

CREATE TABLE `weeding-playlist`.`votes`(
id int PRIMARY KEY AUTO_INCREMENT,
trackId int,
guestId int,
PRIMARY KEY(trackId, guestId),
FOREIGN KEY (trackId) REFERENCES tracks(id),
FOREIGN KEY (guestId) REFERENCES guests(id)
);
