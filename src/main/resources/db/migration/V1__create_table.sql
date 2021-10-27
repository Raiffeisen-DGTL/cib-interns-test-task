CREATE TABLE IF NOT EXISTS Socks
(
    color      VARCHAR(50) NOT NULL,
    cottonPart INTEGER     NOT NULL,
    quantity   BIGINT      NOT NULL,
    constraint pk_color_cottonpart PRIMARY KEY (color, cottonPart)
);
