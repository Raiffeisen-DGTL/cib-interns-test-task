CREATE TABLE IF NOT EXISTS `Socks` (
 
    `Id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Color` varchar(50) NOT NULL,
    `Cotton_Part` int NOT NULL,
    `Quantity` int NOT NULL CHECK Quantity >= 1
);

INSERT INTO Socks VALUES (1, 'red', 50, 10);
INSERT INTO Socks VALUES (2, 'red', 80, 15);
INSERT INTO Socks VALUES (3, 'black', 15, 4);