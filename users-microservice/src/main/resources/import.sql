INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('admin','$2a$10$jAAoFFb0.mw5WBIT9PcVl.uaxl4Mc0rn4eHeIgzS5ZwhJcVjyU0zm',1, 'Admin', 'Full','admin@gmail.com');
INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('john','$2a$10$jAAoFFb0.mw5WBIT9PcVl.uaxl4Mc0rn4eHeIgzS5ZwhJcVjyU0zm',1, 'John', 'Doe','jhondoe@gmail.com');

INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');
INSERT INTO `roles` (name) VALUES ('ROLE_USER');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 2);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);
