CREATE TABLE socks (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	color VARCHAR ( 255 ) NOT NULL,
	cotton_part INT ( 3 ) NOT NULL CHECK ( cotton_part BETWEEN 0 AND 100 ),
	quantity INT NOT NULL CHECK ( quantity > 0 ),
	created_at TIMESTAMP 
)