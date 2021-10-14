select * from SOCK;

CREATE TABLE `sock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) NOT NULL,
  `cotton_part` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT  into SOCK values (1, 'red', 20, 40);