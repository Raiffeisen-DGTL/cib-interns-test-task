CREATE TABLE IF NOT EXISTS socks(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    version     INT          NOT NULL DEFAULT 1,
    color       VARCHAR(100) NOT NULL,
    cotton_part INT          NOT NULL,
    quantity    INT          NOT NULL,
    UNIQUE KEY socks_color_cotton_part_uniq(color, cotton_part)
);