DROP TABLE user_roles
IF EXISTS;
DROP TABLE dishes
IF EXISTS;
DROP TABLE menus
IF EXISTS;
DROP TABLE votes
IF EXISTS;
DROP TABLE restaurants
IF EXISTS;
DROP TABLE users
IF EXISTS;

CREATE TABLE users
(
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  registered DATE DEFAULT now(),
  enabled    BOOLEAN   DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE restaurants (
  id           INTEGER IDENTITY PRIMARY KEY,
  name         VARCHAR(255) NOT NULL,
  num_of_votes INTEGER DEFAULT 0,
  date         DATE         NOT NULL
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
  id            INTEGER IDENTITY PRIMARY KEY,
  name          VARCHAR(255) NOT NULL,
  restaurant_id INTEGER      NOT NULL,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurants (id)
    ON DELETE CASCADE
);

CREATE TABLE dishes
(
  id      INTEGER IDENTITY PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  price   DOUBLE       NOT NULL,
  menu_id INTEGER      NOT NULL,
  FOREIGN KEY ( menu_id ) REFERENCES menus (id)
    ON DELETE CASCADE
);

CREATE TABLE votes
(
  id            INTEGER IDENTITY PRIMARY KEY,
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  vote_date     DATE    NOT NULL,
  FOREIGN KEY ( user_id ) REFERENCES users (id)
    ON DELETE CASCADE,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurants (id)
    ON DELETE CASCADE
);