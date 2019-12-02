INSERT INTO User (user_id, username, password, balance, is_logged_in)
VALUES (1, 'Apri', 'apri', 10000.00, false),
       (2, 'Wida', 'wida', 70000.00, true),
       (3, 'Yanti', 'yanti', 5000000.00, true);

INSERT INTO Product (product_id, product_name, stock, price)
VALUES (1, 'Kaos kaki Wonder Woman', 5, 15000.00),
       (2, 'Baju koko Black Panther', 3, 200000.00),
       (3, 'Celana Hulk', 10, 50000);

INSERT INTO Transaction (transaction_id, transaction_date, user_id, username, product_id, product_name, price, quantity, subtotal, tax, total)
VALUES (1, '2019-12-31', 4, 'Widianto', 1, 'Palu Thor', 17500.00, 1, 15000, 1500, 16500);