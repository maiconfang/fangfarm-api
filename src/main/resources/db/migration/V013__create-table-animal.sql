
CREATE TABLE animal (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(500) NOT NULL,
  identification varchar(200) NOT NULL,
  sex varchar(50) NOT NULL,
  age bigint NOT NULL,
  breed varchar(200) NOT NULL,
  dt_birthday datetime NOT NULL,
  dt_create datetime NOT NULL,
  dt_update datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8