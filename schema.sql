DROP TABLE IF EXISTS sock;

CREATE TABLE sock (
    color VARCHAR(20) NOT NULL,
    cotton_part INTEGER CHECK (0 <= sock.cotton_part and sock.cotton_part <= 100),
    quantity INTEGER CHECK (sock.quantity > 0),

    CHECK (color <> ''),

    PRIMARY KEY(color, cotton_part)
);