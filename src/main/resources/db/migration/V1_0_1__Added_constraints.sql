ALTER TABLE socks ADD CONSTRAINT positive_quantity CHECK (quantity >= 0);
ALTER TABLE socks ADD CONSTRAINT positive_cotton_part CHECK (cotton_part >= 0);
ALTER TABLE socks ADD CONSTRAINT less_than_hundred_cotton_part CHECK (cotton_part <= 100);