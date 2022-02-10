
CREATE TABLE vehicle (
  id bigint NOT NULL AUTO_INCREMENT,
  color varchar(100) NOT NULL,
  year varchar(50) NOT NULL,
  license_plate varchar(50) NOT NULL,
  fuel varchar(200) NOT NULL,
  description varchar(200) NOT NULL,
  dt_create datetime NOT NULL,
  dt_update datetime NOT NULL,
  brand_id bigint NOT NULL,
  PRIMARY KEY (id),
  KEY fk_vehicle_brand (brand_id),
  CONSTRAINT fk_vehicle_brand FOREIGN KEY (brand_id) REFERENCES brand (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8