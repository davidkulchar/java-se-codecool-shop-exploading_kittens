DROP TABLE IF EXISTS Product;

CREATE TABLE Product
(
  id SERIAL PRIMARY KEY,
  defaultPrice FLOAT,
  defaultCurrency VARCHAR(255),
  pic VARCHAR(255),
  name VARCHAR(255),
  description VARCHAR(255),
  supplier INTEGER REFERENCES Supplier,
  productCategory INTEGER REFERENCES ProductCategory
);

CREATE TABLE Supplier
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  description VARCHAR(255)
);

CREATE TABLE ProductCategory
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  description VARCHAR(255),
  department VARCHAR(255)
);

