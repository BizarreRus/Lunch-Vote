DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100001;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  registered DATE DEFAULT now(),
  enabled    BOOLEAN   DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE restaurants (
  id           INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name         VARCHAR(255) NOT NULL,
  num_of_votes INTEGER DEFAULT 0,
  date         DATE DEFAULT now()
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES users (id)
    ON DELETE CASCADE
);

CREATE TABLE menus
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name          VARCHAR(255) NOT NULL,
  restaurant_id INTEGER      NOT NULL,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurants (id)
    ON DELETE CASCADE
);

CREATE TABLE dishes
(
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR(255) NOT NULL,
  price   DOUBLE PRECISION       NOT NULL,
  menu_id INTEGER      NOT NULL,
  FOREIGN KEY ( menu_id ) REFERENCES menus (id)
    ON DELETE CASCADE
);

CREATE TABLE votes
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  vote_date     DATE    NOT NULL,
  FOREIGN KEY ( user_id ) REFERENCES users (id)
    ON DELETE CASCADE,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurants (id)
    ON DELETE CASCADE
);