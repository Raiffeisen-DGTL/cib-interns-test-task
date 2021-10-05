-- ensure that the table with this name is removed before creating a new one.
DROP TABLE IF EXISTS sock_types;

-- Create tg_user table
CREATE TABLE sock_types (
   id BIGINT PRIMARY KEY,
   color VARCHAR(10),
   cotton_part INTEGER,
   quantity INTEGER
);