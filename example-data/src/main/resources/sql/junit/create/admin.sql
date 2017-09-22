CREATE TABLE T_MANAGER (
 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 username VARCHAR(20) NOT NULL,
 password CHAR(64),
 fullname VARCHAR(20) NOT NULL,
 top TINYINT DEFAULT 0 NOT NULL,
 disabled TINYINT DEFAULT 0 NOT NULL,
 create_time TIMESTAMP NOT NULL
);


CREATE TABLE T_ROLE (
 id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(32) NOT NULL,
 remark VARCHAR(200),
 ordinal BIGINT NOT NULL,
 permission_string VARCHAR(4000)
);


CREATE TABLE T_MANAGER_R_ROLE (
  manager_id int NOT NULL,
  role_id int NOT NULL,
  PRIMARY KEY (manager_id, role_id),
  KEY idx_role_id (role_id),
  KEY idx_manager_id (manager_id),
  FOREIGN KEY (manager_id) REFERENCES t_manager (id),
  FOREIGN KEY (role_id) REFERENCES t_role (id) ON DELETE CASCADE
);

CREATE TABLE T_ACTION_LOG (
 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 manager_id INT NOT NULL,
 create_time TIMESTAMP NOT NULL,
 action_string VARCHAR(4000) NOT NULL,
 
 FOREIGN KEY (manager_id) REFERENCES T_MANAGER (id)
);
