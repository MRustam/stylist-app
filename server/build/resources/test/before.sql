DELETE FROM user_role;
DELETE FROM favor_tab;
DELETE FROM app_user;

INSERT INTO app_user (id, first_name, last_name, email, phone, age, password, version, created, updated, enabled)
VALUES ('uId-1', 'User-1_first_name', 'User-1_last_name', 'user1@gmail.com', '+7(800)100-10-10', 34,
        '$2a$10$n8.9fTGIq05xuDx1DA.qBORTxXv7VykkBTmAARbxDoYNL0l35q5jm', 1, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797', true);
INSERT INTO app_user (id, first_name, last_name, email, phone, age, password, version, created, updated, enabled)
VALUES ('uId-2', 'User-2_first_name', 'User-2_last_name', 'user2@gmail.com', '+7(800)200-20-20', 24,
        '$2a$10$n8.9fTGIq05xuDx1DA.qBORTxXv7VykkBTmAARbxDoYNL0l35q5jm', 1, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797', true);
INSERT INTO app_user (id, first_name, last_name, email, phone, age, password, version, created, updated, enabled)
VALUES ('uId-3', 'User-3_first_name', 'User-3_last_name', 'user3@gmail.com', '+7(800)300-30-30', 44,
        '$2a$10$n8.9fTGIq05xuDx1DA.qBORTxXv7VykkBTmAARbxDoYNL0l35q5jm', 1, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797', true);


INSERT INTO favor_tab(id, title, description, user_id, version, created, updated)
VALUES('oId-1', 'Разбор гардероба.', 'Онлайн разбор гардероба', 'uId-1', 1, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797');