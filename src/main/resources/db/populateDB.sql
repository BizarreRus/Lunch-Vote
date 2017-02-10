DELETE FROM USER_ROLES;
DELETE FROM VOTES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
DELETE FROM MENUS;
DELETE FROM DISHES;

INSERT INTO USERS (ID, NAME, PASSWORD, EMAIL) VALUES
  (1, 'User', 'password', 'user@yandex.ru'),
  (2, 'Admin', 'admin', 'admin@gmail.com');

INSERT INTO USER_ROLES (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_USER', 2),
  ('ROLE_ADMIN', 2);

INSERT INTO RESTAURANTS (ID, NAME, NUM_OF_VOTES, DATE) VALUES
  (3, 'Pizzeria del cazzo', 2, '2016-12-31'),
  (4, 'Trattoria della fava', 10, '2016-12-31'),
  (5, 'SPQR', 1, '2016-12-31'),
  (6, 'Ristorante Isolabella', 15, '2016-12-31');

INSERT INTO MENUS (ID, NAME, RESTAURANT_ID) VALUES
  (8, 'Первое меню', 3),
  (9, 'Второе меню', 3);

INSERT INTO DISHES (ID, NAME, PRICE, MENU_ID) VALUES
  (10, 'Котлета', 15.0, 8),
  (11, 'Пюре', 7.0, 8),
  (12, 'Подлива', 2.5, 8),
  (13, 'Овсянка', 4.0, 9),
  (14, 'Мясной стейк', 9.0, 9),
  (15, 'Супчик', 5.0, 9);

INSERT INTO VOTES (ID, USER_ID, RESTAURANT_ID, VOTE_DATE) VALUES
  (20, 1, 3, '2016-12-31'),
  (21, 2, 4, '2016-12-31');