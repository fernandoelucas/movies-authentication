
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

