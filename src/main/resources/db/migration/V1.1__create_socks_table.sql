DROP TABLE socks;

set mode MySQL;

CREATE TABLE socks (
     color      VARCHAR(64) NOT NULL,
     cottonPart INT NOT NULL,
     quantity   INT NOT NULL,
     UNIQUE (color, cottonPart)
);