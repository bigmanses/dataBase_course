
Table clients {
  id int
  name varchar
  surname varchar
  patronymic varchar
  phone varchar
  address varchar
  fax varchar
  score varchar
  notes varchar
}

Table contract {
  id int
  date date
  about varchar
  id_product int
  amount int
  terms varchar
  id_client int
  price int
  sale_or_supply varchar
}

Table manufacturer {
  id int
  name varchar
  address varchar
  director varchar
  accountant varchar
  products varchar
  requisites varchar
}

Table product{
  id int
  name varchar
  characteristic varchar
  priceOne int
  package varchar
  batchDelivery varchar
  amount int
  id_manufacturer int
}

Table score{
  id_score int
  name varchar
  number varchar
  id_contract int
  date int
  sum int
  shipment_status boolean
  payment_status boolean
}
Ref: contract.id_client > clients.id
Ref: manufacturer.id < product.id_manufacturer 
Ref: contract.id_product > product.id
Ref: score.id_contract - contract.id


