-- USER ROLE
INSERT INTO app_role (id, role_name, description) VALUES (1, 'ROLE_STANDARD', 'Standard AppUser');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ROLE_ADMIN', 'Admin AppUser');

-- APP USER
INSERT INTO app_user (id, first_name, last_name, password, user_name ,email,last_pass_reset_date)
VALUES (1, 'İsa', 'Öztürk', '$2a$10$uH8hGTYmdIC/qiSbuFkY1ustH0.YcbdaFRYooDqIQhG8r14T/QtNu', 'sisa','sisa@gmail.com',null);
INSERT INTO app_user (id, first_name, last_name, password, user_name,email,last_pass_reset_date)
VALUES (2, 'Admin', 'Admin', '$2a$10$HG0tQJHWZZen.kerZYz1rePqDx8EjI7LO.pDJOjF3udpWPTbfODF2', 'admin','admin@gmail.com',null);

--APP USER/USER ROLE MAPPING
INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);

-- Cities
INSERT INTO city(id, name) VALUES (1, 'Rize');
INSERT INTO city(id, name) VALUES (2, 'Artvin');
INSERT INTO city(id, name) VALUES (3, 'Antalya');
INSERT INTO city(id, name) VALUES (4, 'İzmir');
INSERT INTO city(id, name) VALUES (5, 'Muğla');
INSERT INTO city(id, name) VALUES (6, 'Gazi Antep');
INSERT INTO city(id, name) VALUES (7, 'Mardin');
INSERT INTO city(id, name) VALUES (8, 'Hatay');