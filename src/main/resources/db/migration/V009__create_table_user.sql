CREATE TABLE usserr (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(80) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  dt_create datetime NOT NULL,
  dt_update datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8