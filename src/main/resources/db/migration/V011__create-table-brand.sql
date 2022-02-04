
CREATE TABLE brand (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(80) NOT NULL,
  model_id bigint NOT NULL,
  PRIMARY KEY (id),
  KEY fk_brand_model (model_id),
  CONSTRAINT fk_brand_model FOREIGN KEY (model_id) REFERENCES model (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8