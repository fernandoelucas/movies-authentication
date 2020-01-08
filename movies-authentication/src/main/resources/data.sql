
CREATE TABLE IF NOT EXISTS usersdb.application_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(100) NOT NULL,
  first_name varchar(25) DEFAULT NULL,
  last_name varchar(25) DEFAULT NULL,
  password varchar(200) NOT NULL,
  user_name varchar(25) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_gm3mj4xxff13xitbuth5ae19u (user_name)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE  IF NOT EXISTS usersdb.role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  role_name varchar(10) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE  IF NOT EXISTS usersdb.roles_users (
  users_id bigint(20) NOT NULL,
  roles_id bigint(20) NOT NULL,
  KEY FKet7kgs329rrx9su66utr4laft (roles_id),
  KEY FKeta5koiqgt7lxxq5lyg85ssfc (users_id),
  CONSTRAINT FKet7kgs329rrx9su66utr4laft FOREIGN KEY (roles_id) REFERENCES role (id),
  CONSTRAINT FKeta5koiqgt7lxxq5lyg85ssfc FOREIGN KEY (users_id) REFERENCES application_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT IGNORE INTO usersdb.role(id,role_name) VALUES(1,'ROLE_USER');
INSERT IGNORE INTO usersdb.role(id,role_name) VALUES(2, 'ROLE_ADMIN');


INSERT IGNORE INTO usersdb.application_user(id,email,first_name,last_name,password,user_name)
VALUES("1","admin@movies.com","Fernando","Lucas","$2a$10$Tra2wfz3ZZod1m8F.WhFlOF3dfgL6pr85puJHwZ87Hju8IIuBr9JC","admin");

INSERT IGNORE INTO usersdb.roles_users
(users_id,
roles_id
)
VALUES
(1,
2);

INSERT IGNORE INTO usersdb.application_user(id,email,first_name,last_name,password,user_name)
VALUES("2","fernandoelucas@movies.com","Fernando","Lucas","$2a$10$Tra2wfz3ZZod1m8F.WhFlOF3dfgL6pr85puJHwZ87Hju8IIuBr9JC","fernandoelucas");

INSERT IGNORE INTO usersdb.roles_users
(users_id,
roles_id)
VALUES
(2,
1);

