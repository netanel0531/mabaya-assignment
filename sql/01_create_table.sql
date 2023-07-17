-- Creation of products table
CREATE TABLE IF NOT EXISTS product (
  id SERIAL NOT NULL PRIMARY KEY,
  title VARCHAR NOT NULL,
  category VARCHAR NOT NULL,
  price NUMERIC(4, 2)
);