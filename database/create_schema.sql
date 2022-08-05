CREATE TABLE client
(	
  id serial PRIMARY KEY,
  name varchar(20) NOT NULL,
  surname varchar(20) NOT NULL,
  patronymic varchar(20),
  phone varchar(15) NOT NULL,
  address varchar(50) NOT NULL,
  fax varchar(10),
  score varchar(15) NOT NULL,
  notes varchar(30)
);

CREATE TABLE manufacturer (
  id serial PRIMARY KEY,
  name varchar(30) NOT NULL,
  address varchar(100) NOT NULL,
  director varchar(30) NOT NULL,
  accountant varchar(30),
  requisites varchar(50) NOT NULL
);

CREATE TABLE product(
  id serial PRIMARY KEY,
  name varchar(30) NOT NULL,
  characteristic varchar(50) NOT NULL,
  priceOne integer NOT NULL,
  package varchar(40),
  batchDelivery varchar(60),
  amount integer NOT NULL,
  manufacturer integer NOT NULL REFERENCES manufacturer(id)

  CHECK(priceOne > 0)
  CHECK(amount >= 0)
);

CREATE TABLE contract (
  id serial PRIMARY KEY,
  date_contract date NOT NULL,
  about varchar(50),
  product integer NOT NULL REFERENCES product(id),
  amount integer NOT NULL,
  terms varchar(50),
  client integer REFERENCES client(id),
  price integer NOT NULL,
  isSale boolean DEFAULT true

  CHECK(price > 0)
  CHECK(amount > 0)
);

CREATE TABLE score(
  id serial PRIMARY KEY,
  name varchar(30) NOT NULL,
  number varchar(30) NOT NULL,
  contract integer NOT NULL REFERENCES contract(id),
  date_score date NOT NULL,
  sum integer NOT NULL,
  shipment_status boolean DEFAULT false,
  payment_status boolean DEFAULT false,

  CHECK(sum > 0)
);

CREATE OR REPLACE VIEW product_for_buying
AS
   SELECT * FROM product WHERE amount > 0;

CREATE OR REPLACE VIEW score_not_payment
AS
   SELECT * FROM score WHERE payment_status = false;

CREATE OR REPLACE VIEW score_sum_payment
AS
   SELECT id, name, number, sum, payment_status FROM score;

CREATE OR REPLACE VIEW contract_info
   AS
      SELECT * FROM contract;

CREATE OR REPLACE VIEW product_for_order
   AS
      SELECT DISTINCT product.*, manufacturer.name AS brand FROM contract JOIN product ON contract.product = product.id
      JOIN manufacturer on product.manufacturer = manufacturer.id;
CREATE OR REPLACE VIEW product_for_stock
   AS
      SELECT DISTINCT product.*, manufacturer.name AS brand FROM score JOIN contract ON score.contract = contract.id
       JOIN product ON contract.product = product.id  JOIN manufacturer ON product.manufacturer = manufacturer.id
       WHERE shipment_status = true;

CREATE OR REPLACE VIEW product_give_away
   AS
      SELECT DISTINCT product.*, manufacturer.name AS brand FROM score JOIN contract ON score.contract = contract.id
       JOIN product ON contract.product = product.id  JOIN manufacturer ON product.manufacturer = manufacturer.id
       WHERE shipment_status = true AND payment_status = true;

CREATE OR REPLACE VIEW client_for_product
   AS
      SELECT DISTINCT client.* FROM contract JOIN client ON contract.client = client.id
       JOIN product ON contract.product = product.id  JOIN manufacturer ON product.manufacturer = manufacturer.id
       WHERE product.name = 'adidas  crazychaos 2.0 ';