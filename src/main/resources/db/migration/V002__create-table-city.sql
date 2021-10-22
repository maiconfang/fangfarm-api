
CREATE TABLE city (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(80) NOT NULL,
  state_id bigint NOT NULL,
  PRIMARY KEY (id),
  KEY fk_city_state (state_id),
  CONSTRAINT fk_city_state FOREIGN KEY (state_id) REFERENCES state (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8