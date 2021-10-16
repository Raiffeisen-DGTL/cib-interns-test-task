CREATE TABLE IF NOT EXISTS depossocks(
    id serial PRIMARY KEY,
    color VARCHAR(50) NOT NULL CHECK(color !=''),
    cottonpart INT NOT NULL CHECK(cottonpart >0 AND cottonpart < 100),
    quantity INT NOT NULL CHECK(quantity>0)
);