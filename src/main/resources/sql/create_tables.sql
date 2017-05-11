DROP TABLE IF EXISTS product, supplier, product_category;

CREATE TABLE supplier
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  description VARCHAR(255)
);

CREATE TABLE product_category
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  description VARCHAR(255),
  department VARCHAR(255)
);

CREATE TABLE product
(
  id SERIAL PRIMARY KEY,
  default_price FLOAT,
  default_currency VARCHAR(255),
  pic VARCHAR(255),
  name VARCHAR(255),
  description VARCHAR(255),
  supplier INTEGER REFERENCES supplier,
  product_category INTEGER REFERENCES product_category
);

