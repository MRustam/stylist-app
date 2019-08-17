INSERT INTO app_user (id, first_name, last_name, email, phone, age, password, version, created, updated, enabled)
VALUES ('uId-1', 'User-1_first_name', 'User-1_last_name', 'user1@gmail.com', '+7(800)100-10-10', 34,
        '$2a$10$n8.9fTGIq05xuDx1DA.qBORTxXv7VykkBTmAARbxDoYNL0l35q5jm', 0, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797', true);
INSERT INTO app_user (id, first_name, last_name, email, phone, age, password, version, created, updated, enabled)
VALUES ('uId-2', 'User-2_first_name', 'User-2_last_name', 'user2@gmail.com', '+7(800)100-10-10', 24,
        '$2a$10$n8.9fTGIq05xuDx1DA.qBORTxXv7VykkBTmAARbxDoYNL0l35q5jm', 0, '2016-11-05T11:44:44.797', '2016-11-05T11:44:44.797', true);
INSERT INTO app_user (id, first_name, last_name, email, phone, age, password, version, created, updated, enabled)
VALUES ('uId-3', 'User-3_first_name', 'User-3_last_name', 'user3@gmail.com', '+7(800)100-10-10', 24,
        '$2a$10$n8.9fTGIq05xuDx1DA.qBORTxXv7VykkBTmAARbxDoYNL0l35q5jm', 0, '2016-11-05T11:44:44.797', '2016-11-05T11:44:44.797', true);

INSERT INTO role (id, name, version)
VALUES ('rId-1', 'ROLE_ADMIN', 0);
INSERT INTO role (id, name, version)
VALUES ('rId-2', 'ROLE_USER', 0);
INSERT INTO role (id, name, version)
VALUES ('rId-3', 'ROLE_CONSUMER', 0);

INSERT INTO user_role (user_id, role_id)
VALUES ('uId-1', 'rId-1');
INSERT INTO user_role (user_id, role_id)
VALUES ('uId-2', 'rId-2');
INSERT INTO user_role (user_id, role_id)
VALUES ('uId-2', 'rId-3');
INSERT INTO user_role (user_id, role_id)
VALUES ('uId-3', 'rId-2');

INSERT INTO favor_tab (id, title, description, price, popularity, created, updated, version)
VALUES ('oId-1', 'Разбор гардероба офлайн', 'Bla-bla', 2000.00, 15, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797', 0);
INSERT INTO favor_tab (id, title, description, price, popularity, created, updated, version)
VALUES ('oId-2', 'Разбор гардероба онлайн', 'Bla-bla', 2000.00, 17, '2016-10-09T11:44:44.797', '2016-11-09T11:44:44.797', 0);
INSERT INTO favor_tab (id, title, description, price, popularity, created, updated, version)
VALUES ('oId-3', 'Капсула', 'Bla-bla', 2000.00, 17, '2016-09-09T11:44:44.797', '2016-11-09T11:44:44.797', 0);
INSERT INTO favor_tab (id, title, description, price, popularity, created, updated, version)
VALUES ('oId-4', 'Консультация', 'Bla-bla', 2000.00, 17, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797', 0);

