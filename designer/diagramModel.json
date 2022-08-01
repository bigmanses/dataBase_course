
Table clients {
  id int
  name varchar
  surname varchar
  patronymic varchar
  phone varchar
  adress varchar
  score varchar
  Notes varchar
}

Table contract {
  id int
  data int
  about varchar
  patronymic varchar
  id_product int
  amount int
  terms varchar
  name_provider varchar
  id_client int
  id_manufacturer int
  price int
}

Table manufacturer {
  id int
  name varchar
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
}

Enum shipment_status {
  ready_for_shipment
  shipped
  not_shipped
}
Enum payment_status{
  not_paid
  paid
}

Table score{
  id_score int
  name varchar
  number varchar
  id_contract int
  date int
  sum int
  shipment_status enum
  payment_status enum
}
Ref: contract.id_client - clients.id
Ref: contract.id_manufacturer - manufacturer.id
Ref: contract.id_product > product.id
Ref: score.id_contract - contract.id


