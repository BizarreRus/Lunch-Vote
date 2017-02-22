DELETE FROM USER_ROLES;
DELETE FROM VOTES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
DELETE FROM MENUS;
DELETE FROM DISHES;

INSERT INTO USERS (ID, NAME, PASSWORD, EMAIL) VALUES
  (1, 'User', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni', 'user@yandex.ru'),
  (2, 'Admin', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju', 'admin@gmail.com');

INSERT INTO USER_ROLES (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_USER', 2),
  ('ROLE_ADMIN', 2);

INSERT INTO RESTAURANTS (ID, NAME, NUM_OF_VOTES, DATE) VALUES
  (3, 'Ristorante Isolabella', 7, '2016-12-31'),
  (4, 'Ristorante Da Agostino', 13, '2016-12-31'),
  (5, 'Trattoria Pizzeria SPQR', 9, '2016-12-31');

INSERT INTO MENUS (ID, NAME, RESTAURANT_ID) VALUES
  (6, 'Menu turistico', 3),
  (7, 'Menu di pesce', 4),
  (8, 'Menu di carne', 4),
  (9, 'Menu del giorno', 5);

INSERT INTO DISHES (ID, NAME, PRICE, MENU_ID) VALUES
  (10, 'Paccheri al ragù di cernia', 252.0, 6),
  (11, 'Pesce spada alla siciliana', 336.0, 6),
  (12, 'Piselli stufati con pancetta', 98.0, 6),

  (13, 'Linguine al nero di seppia', 336.0, 7),
  (14, 'Involtini di spada al sugo con capperi', 448.0, 7),
  (15, 'Patate novelle al forno', 84.0, 7),

  (16, 'Spaghetti alla carbonara', 224.0, 8),
  (17, 'Bocconcini di tacchino e pancetta', 420.0, 8),
  (18, 'Insalata capricciosa', 84.0, 8),

  (19, 'Crocchette di patate e salmone con salsa allo yogurt', 168.0, 9),
  (20, 'Lasagne con scampi pomodorini e pesto di fave', 364.0, 9),
  (21, 'Baba'' al rum', 154.0, 9);

INSERT INTO VOTES (ID, USER_ID, RESTAURANT_ID, VOTE_DATE) VALUES
  (20, 1, 3, '2016-12-31'),
  (21, 2, 4, '2016-12-31');


INSERT INTO RESTAURANTS (ID, NAME, NUM_OF_VOTES) VALUES
  (22, 'Ресторан MAFIA', 0),
  (23, 'Ресторан Panorama Lounge', 0);


INSERT INTO MENUS (ID, NAME, RESTAURANT_ID) VALUES
  (24, 'Бизнес ланч', 22),
  (25, 'Меню дня', 23);

INSERT INTO DISHES (ID, NAME, PRICE, MENU_ID) VALUES
  (26, 'Суп пратайоло', 45.0, 24),
  (27, 'Рис с курицей карри', 55.0, 24),
  (28, 'Салат с индейкой', 35.0, 24),

  (29, 'Чилийский рыбный суп', 55.0, 25),
  (30, 'Речная форель с салатом и маринованной брынзой', 65.0, 25),
  (31, 'Жареный картофель с лесными грибами', 40.0, 25);