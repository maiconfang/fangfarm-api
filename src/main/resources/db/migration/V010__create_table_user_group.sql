
CREATE TABLE usserr_grouppp (
  usserr_id bigint NOT NULL,
  grouppp_id bigint NOT NULL,
  PRIMARY KEY (usserr_id,grouppp_id),
  KEY fk_usserr_grouppp_grouppp (grouppp_id),
  CONSTRAINT fk_usserr_grouppp_grouppp FOREIGN KEY (grouppp_id) REFERENCES grouppp (id),
  CONSTRAINT fk_usserr_grouppp_usserr FOREIGN KEY (usserr_id) REFERENCES usserr (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8