DROP TABLE IF EXISTS movie;

CREATE SEQUENCE IF NOT EXISTS movie_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE IF NOT EXISTS movie (
  id int8 NOT NULL DEFAULT nextval('movie_id_seq'),
  title VARCHAR (255),
  name VARCHAR (255),
  year int4,
  genres VARCHAR (255),
  director VARCHAR (255),
  country VARCHAR (255),
  minutes int4,
  PRIMARY KEY (id));