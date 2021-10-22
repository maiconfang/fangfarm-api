
CREATE TABLE grouppp_permission (
  grouppp_id bigint NOT NULL,
  permission_id bigint NOT NULL,
  PRIMARY KEY (grouppp_id,permission_id),
  KEY fk_grouppp_permission_permission (permission_id),
  CONSTRAINT fk_grouppp_permission_grouppp FOREIGN KEY (grouppp_id) REFERENCES grouppp (id),
  CONSTRAINT fk_grouppp_permission_permission FOREIGN KEY (permission_id) REFERENCES permission (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8