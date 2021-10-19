CREATE DATABASE task;

CREATE TABLE color (`color_id` INT NOT NULL AUTO_INCREMENT, `color` VARCHAR(128) NOT NULL, PRIMARY KEY (`color_id`));

CREATE TABLE socks (
    `socks_id` INT NOT NULL AUTO_INCREMENT,
    `color` INT NOT NULL,
    `cotton_part` INT NOT NULL,
    `quantity` INT NOT NULL,
    foreign key (color) references color (color_id),
    PRIMARY KEY (`socks_id`));

