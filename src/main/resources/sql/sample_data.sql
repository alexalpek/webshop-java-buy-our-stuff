
INSERT INTO cart
VALUES (1, 'USD');
SELECT pg_catalog.setval('cart_id_seq', 1, true);

INSERT INTO account
VALUES (1, 'Barna', '$2a$10$CFsnGLavtXm9C4sieC0YO.X..O5BWkGAQiFKj3hXeTWlxfjr/uFja', 1);
SELECT pg_catalog.setval('account_id_seq', 1, true);

INSERT INTO shipping_info
VALUES (1, 1, 'Matrai Barnabas', 'asd@gmail.com', '0630-123-4567', 'semmi', 'semmi');
SELECT pg_catalog.setval('shipping_info_id_seq', 1, true);


INSERT INTO order_info
VALUES (1, 1, 1, 1);
SELECT pg_catalog.setval('order_info_id_seq', 1, true);

INSERT INTO supplier
VALUES (1, 'Amazon', 'Digital content and services');
INSERT INTO supplier
VALUES (2, 'Lenovo', 'Computers');
INSERT INTO supplier
VALUES (3, 'Dell', 'Computers');
INSERT INTO supplier
VALUES (4, 'Apple',
        'Apple Inc. is an American multinational technology company that designs, develops, and sells consumer electronics, computer software, and online services.');
SELECT pg_catalog.setval('supplier_id_seq', 4, true);

INSERT INTO product_category
VALUES (1, 'Tablet', 'Hardware',
        'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO product_category
VALUES (2, 'Laptop', 'Hardware',
        'A laptop computer, commonly shortened to laptop, or called a notebook computer, is a small, portable personal computer typically having a thin LCD or LED computer screen display.');
INSERT INTO product_category
VALUES (3, 'Smartphone', 'Hardware',
        'Smartphone is a mobile phone that performs many of the functions of a computer, typically having a touchscreen interface, Internet access, and an operating system capable of running downloaded apps.');
SELECT pg_catalog.setval('product_category_id_seq', 3, true);

INSERT INTO product
VALUES (1, 'Amazon Fire', 1, 1, 49.99, 'USD',
        'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.');
INSERT INTO product
VALUES (2, 'Lenovo IdeaPad Miix 700', 2, 1, 479.99, 'USD',
        'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.');
INSERT INTO product
VALUES (3, 'Amazon Fire HD 8', 1, 1, 89.99, 'USD',
        'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.');
INSERT INTO product
VALUES (4, 'Dell ChromeBook 11', 3, 2, 92.86, 'USD',
        '11-inch ChromeBook laptop built to roll with the punches of family life. Featuring the speed, simplicity and security of Chrome in a portable design.');
INSERT INTO product
VALUES (5, 'iPhone 11', 4, 3, 699.00, 'USD', 'iPhone 11 is Apple''s latest lower cost smartphone for 2019.');
SELECT pg_catalog.setval('product_id_seq', 5, true);
