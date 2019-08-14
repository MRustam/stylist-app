DELETE FROM order_tab;
DELETE FROM user_tab;

INSERT INTO user_tab(id, first_name, last_name, email, phone, age, password, version, created, updated)
VALUES ('uId-1', 'User-1_first_name', 'User-1_last_name', 'user1@gmail.com', '+7(800)100-10-10', 34, '$2a$10$n8.9fTGIq05xuDx1DA.qBORTxXv7VykkBTmAARbxDoYNL0l35q5jm', 1, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797');
INSERT INTO user_tab(id, first_name, last_name, email, phone, age, password, version, created, updated)
VALUES ('uId-2', 'User-2_first_name', 'User-2_last_name', 'user2@gmail.com', '+7(800)200-20-20', 24, '$2a$10$n8.9fTGIq05xuDx1DA.qBORTxXv7VykkBTmAARbxDoYNL0l35q5jm', 1, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797');
INSERT INTO user_tab(id, first_name, last_name, email, phone, age, password, version, created, updated)
VALUES ('uId-3', 'User-3_first_name', 'User-3_last_name', 'user3@gmail.com', '+7(800)300-30-30', 44, '$2a$10$n8.9fTGIq05xuDx1DA.qBORTxXv7VykkBTmAARbxDoYNL0l35q5jm', 1, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797');

INSERT INTO order_tab(id, title, message, user_id, version, created, updated)
VALUES('oId-1', 'bay course', 'Hello Anna! I wonna by your course.', 'uId-1', 1, '2016-11-09T11:44:44.797', '2016-11-09T11:44:44.797');