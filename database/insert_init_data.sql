INSERT INTO client (name, surname, patronymic, phone, address, fax, score, notes) values

 ('Андрей', 'Павлов', 'Александрович', '+7999999999', 'г.Москва ул.Дягелева д.33', '12345678', '87654321', 'Думает, что купить'),
 ('Александр', 'Козлов', 'Андреевич', '+78888888888', 'г.Владивосток ул.Карла Маркса д.17', '32165789', '91436816', 'Выбрал две пары джинс'),
 ('Василий', 'Николенко', 'Александрович', '+77777777777', 'г.Хабаровск ул.Ильюшина д.54 кв.5', '90254916', '90143719', 'Покупал футболку');

INSERT INTO manufacturer (name, address, director, accountant, requisites) values

 ('H&M', 'г.Санкт-Петербург Комендантский проспект д.31к1', 'Исмаилов М.Р', 'Исмаилова С.Р', 'номер карты: 1234567898765432'),
 ('adidas', 'г.Санкт-Петербург ул.Красноармейская д.50к1', 'Мишедаева О.Д', 'Сарычева Е.В', 'номер карты: 3658316583910461');

INSERT INTO product (name, characteristic, priceOne, package, batchDelivery, amount, manufacturer) values

 ('adidas ozweego ', 'Цвет: черный; Размер: 41; Страна: Вьетнам;', 5000, 'Упакован в прочную коробку', 'Поставка от 10 штук', 17, 2),
 ('adidas  crazychaos 2.0 ', 'Цвет: белый; Размер: 41; Страна: США;', 7000, 'Упакован в прочную коробку', 'Поставка от 5 штук', 30, 2),
 ('Джинсы бананы', 'Цвет: черныц; Размер: M; Страна: Италия;', 4000, 'Упакован в бумажный пакет', 'Поставка от 3 штук', 15, 1);

INSERT INTO contract (date_contract, about, product, amount, terms, client, price, isSale) values

 ('January 8, 2022', 'Покупка 10 пар кроссовок', 1, 10, 'Заказ заберут в течение 10 дней', 3, 50000, true),
 ('August 8, 2022', 'Покупка 5 пар джинс', 3, 5, 'Заказ будет оплачен картой', 2, 20000, true);

INSERT INTO score (name, number, contract, date_score, sum, shipment_status, payment_status) values
 ('Счет', '15471', 1, 'January 8, 2022', 20000, false, true),
 ('Счет', '15472', 2, 'August 8, 2022', 50000, true, false);