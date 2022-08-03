CREATE TABLE client
(	
  id serial PRIMARY KEY,
  name varchar(20) NOT NULL,
  surname varchar(20) NOT NULL,
  patronymic varchar(20),
  phone varchar(15) NOT NULL,
  address varchar(30) NOT NULL,
  fax varchar(10),
  score varchar(15) NOT NULL,
  notes varchar(30)
);

CREATE TABLE manufacturer (
  id serial PRIMARY KEY,
  name varchar(10) NOT NULL,
  address varchar(30) NOT NULL,
  director varchar(15) NOT NULL,
  accountant varchar(15),
  requisites varchar(10) NOT NULL
);

CREATE TABLE product(
  id serial PRIMARY KEY,
  name varchar(15) NOT NULL,
  characteristic varchar(30) NOT NULL,
  priceOne integer NOT NULL,
  package varchar(20),
  batchDelivery varchar(30),
  amount integer NOT NULL,
  manufacturer integer NOT NULL REFERENCES manufacturer(id)

  CHECK(priceOne > 0)
  CHECK(amount >= 0)
);

CREATE TABLE contract (
  id serial PRIMARY KEY,
  date_contract date NOT NULL,
  about varchar(30),
  product integer NOT NULL REFERENCES product(id),
  amount integer NOT NULL,
  terms varchar(30),
  client integer REFERENCES client(id),
  price integer NOT NULL,
  sale_or_supply varchar(10) NOT NULL

  CHECK(price > 0)
  CHECK(amount > 0)
);

CREATE TABLE score(
  id serial PRIMARY KEY,
  name varchar(15) NOT NULL,
  number varchar(15) NOT NULL,
  contract integer NOT NULL REFERENCES contract(id),
  date_score date NOT NULL,
  sum integer NOT NULL,
  shipment_status boolean DEFAULT false,
  payment_status boolean DEFAULT false,

  CHECK(sum > 0)
);

