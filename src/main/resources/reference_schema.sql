USE SocksServiceDB;

CREATE TABLE color (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE socks (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    cotton_part TINYINT NOT NULL,
    quantity INT UNSIGNED NOT NULL,
    color_id INT NOT NULL,
    foreign key (color_id) references color(id)
)