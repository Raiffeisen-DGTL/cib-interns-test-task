DROP TABLE IF EXISTS socks;

CREATE TABLE socks
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    color VARCHAR(250) NOT NULL,
    cottonPart int NOT NULL,
    amount int NOT NULL
);