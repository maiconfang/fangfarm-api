
CREATE TABLE employee (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  rg varchar(50) NOT NULL,
  cpf varchar(50) NOT NULL,
  dt_create datetime NOT NULL,
  dt_update datetime NOT NULL,
  address_city_id bigint DEFAULT NULL,
  address_zip varchar(9) DEFAULT NULL,
  address_type varchar(100) DEFAULT NULL,
  address_number varchar(20) DEFAULT NULL,
  address_complement varchar(60) DEFAULT NULL,
  address_block varchar(60) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_employee_city (address_city_id),
  CONSTRAINT fk_employee_city FOREIGN KEY (address_city_id) REFERENCES city (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

