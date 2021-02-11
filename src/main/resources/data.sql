DELETE FROM BASE_CURRENCY;
DELETE FROM exchange_rates;

INSERT INTO BASE_CURRENCY(ID,currency_name)
value(1,'PHP');
INSERT INTO BASE_CURRENCY(ID,currency_name)
value(2,'USD');
INSERT INTO BASE_CURRENCY(ID,currency_name)
value(3,'EUR');