DELETE FROM USER_ROLES;
DELETE FROM VOTES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;
DELETE FROM MENUS;
DELETE FROM DISHES;
ALTER SEQUENCE global_seq RESTART WITH 100001;

INSERT INTO USERS (NAME, PASSWORD, EMAIL) VALUES
  ('User', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni', 'user@yandex.ru'),
  ('Admin', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju', 'admin@gmail.com');

INSERT INTO RESTAURANTS (NAME, NUM_OF_VOTES, DATE) VALUES
  ('Ristorante Isolabella', 7, '2016-12-31'),
  ('Ristorante Da Agostino', 13, '2016-12-31'),
  ('Trattoria Pizzeria SPQR', 9, '2016-12-31');

INSERT INTO MENUS (NAME, RESTAURANT_ID) VALUES
  ('Menu turistico', 100003),
  ('Menu di pesce', 100004),
  ('Menu di carne', 100004),
  ('Menu del giorno', 100005);

INSERT INTO DISHES (NAME, PRICE, MENU_ID) VALUES
  ('Paccheri al ragù di cernia', 252.0, 100006),
  ('Pesce spada alla siciliana', 336.0, 100006),
  ('Piselli stufati con pancetta', 98.0, 100006),

  ('Linguine al nero di seppia', 336.0, 100007),
  ('Involtini di spada al sugo con capperi', 448.0, 100007),
  ('Patate novelle al forno', 84.0, 100007),

  ('Spaghetti alla carbonara', 224.0, 100008),
  ('Bocconcini di tacchino e pancetta', 420.0, 100008),
  ('Insalata capricciosa', 84.0, 100008),

  ('Crocchette di patate e salmone con salsa allo yogurt', 168.0, 100009),
  ('Lasagne con scampi pomodorini e pesto di fave', 364.0, 100009),
  ('Baba'' al rum', 154.0, 100009);

INSERT INTO RESTAURANTS (NAME, NUM_OF_VOTES) VALUES
  ('Ресторан MAFIA', 0),
  ('Ресторан Panorama Lounge', 0);


INSERT INTO MENUS (NAME, RESTAURANT_ID) VALUES
  ('Бизнес ланч', 100022),
  ('Меню дня', 100023);

INSERT INTO DISHES (NAME, PRICE, MENU_ID) VALUES
  ('Суп пратайоло', 45.0, 100024),
  ('Рис с курицей карри', 55.0, 100024),
  ('Салат с индейкой', 35.0, 100024),

  ('Чилийский рыбный суп', 55.0, 100025),
  ('Речная форель с салатом и маринованной брынзой', 65.0, 100025),
  ('Жареный картофель с лесными грибами', 40.0, 100025);

INSERT INTO USER_ROLES (role, user_id) VALUES
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100002);

INSERT INTO VOTES (USER_ID, RESTAURANT_ID, VOTE_DATE) VALUES
  (100001, 100003, '2016-12-31'),
  (100002, 100004, '2016-12-31');
