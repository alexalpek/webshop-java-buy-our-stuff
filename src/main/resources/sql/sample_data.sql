
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
SELECT pg_catalog.setval('supplier_id_seq', 2, true);

INSERT INTO product_category
VALUES (1, 'Tablet', 'Hardware',
        'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
SELECT pg_catalog.setval('product_category_id_seq', 1, true);

INSERT INTO product
VALUES (1, 'Amazon Fire', 1, 1, 49.99, 'USD',
        'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.');
INSERT INTO product
VALUES (2, 'Lenovo IdeaPad Miix 700', 2, 1, 479.99, 'USD',
        'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.');
INSERT INTO product
VALUES (3, 'Amazon Fire HD 8', 1, 1, 89.99, 'USD',
        'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.');
SELECT pg_catalog.setval('product_id_seq', 3, true);
