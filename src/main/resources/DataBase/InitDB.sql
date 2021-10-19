CREATE TABLE "socks"
(
    color      VARCHAR(64) NOT NULL,
    cottonPart INTEGER     NOT NULL,
    quantity   BIGINT      NOT NULL,
    PRIMARY KEY (color, cottonPart)
);