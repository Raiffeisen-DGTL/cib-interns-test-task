CREATE TABLE IF NOT EXISTS Socks
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    color      VARCHAR(50) NOT NULL,
    cottonPart BIGINT(100) NOT NULL,
    quantity   DECIMAL     NOT NULL

);