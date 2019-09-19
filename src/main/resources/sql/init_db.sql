ALTER TABLE IF EXISTS ONLY product
    DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY product
    DROP CONSTRAINT IF EXISTS pk_product_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY cart
    DROP CONSTRAINT IF EXISTS pk_account_id CASCADE;
ALTER TABLE IF EXISTS ONLY cart
    DROP CONSTRAINT IF EXISTS pk_line_item_id CASCADE;
ALTER TABLE IF EXISTS ONLY line_item
    DROP CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY shipping_info
    DROP CONSTRAINT IF EXISTS pk_account_id CASCADE;
ALTER TABLE IF EXISTS ONLY order_info
    DROP CONSTRAINT IF EXISTS pk_account_id CASCADE;
ALTER TABLE IF EXISTS ONLY order_info
    DROP CONSTRAINT IF EXISTS pk_cart_id CASCADE;
ALTER TABLE IF EXISTS ONLY order_info
    DROP CONSTRAINT IF EXISTS pk_shipping_info_id CASCADE;

DROP TABLE IF EXISTS supplier CASCADE;
DROP SEQUENCE IF EXISTS supplier_id_seq;
CREATE TABLE supplier
(
    id          SERIAL NOT NULL,
    name        varchar(40),
    description text
--TODO: make name unique (ON CONFLICT UPDATE helps but meh.)
);

DROP TABLE IF EXISTS product CASCADE;
DROP SEQUENCE IF EXISTS product_id_seq;
CREATE TABLE product
(
    id                  SERIAL NOT NULL,
    name                varchar(40),
    supplier_id         int,
    product_category_id int,
    default_price       decimal,
    default_currency    varchar(10),
    description         text
);

DROP TABLE IF EXISTS product_category CASCADE;
DROP SEQUENCE IF EXISTS product_category_id_seq;
CREATE TABLE product_category
(
    id          SERIAL NOT NULL,
    name        varchar(40),
    department  varchar(40),
    description text
);

DROP TABLE IF EXISTS account CASCADE;
DROP SEQUENCE IF EXISTS account_id_seq;
CREATE TABLE account
(
    id       SERIAL NOT NULL,
    name     varchar(40),
    password text,
    UNIQUE (name)
);

DROP TABLE IF EXISTS cart CASCADE;
DROP SEQUENCE IF EXISTS cart_id_seq;
CREATE TABLE cart
(
    id         SERIAL NOT NULL,
    account_id int,
    currency   varchar(10)
);

DROP TABLE IF EXISTS line_item CASCADE;
DROP SEQUENCE IF EXISTS line_item_id_seq;
CREATE TABLE line_item
(
    id         SERIAL NOT NULL,
    product_id int,
    cart_id    int,
    quantity   int
);

DROP TABLE IF EXISTS shipping_info CASCADE;
DROP SEQUENCE IF EXISTS shipping_info_id_seq;
CREATE TABLE shipping_info
(
    id               SERIAL NOT NULL,
    account_id       int,
    name             text,
    email            text,
    phone_number     varchar(25),
    billing_address  text,
    shipping_address text
);

DROP TABLE IF EXISTS order_info CASCADE;
DROP SEQUENCE IF EXISTS order_info_id_seq;
CREATE TABLE order_info
(
    id               SERIAL NOT NULL,
    account_id       int,
    cart_id          int,
    shipping_info_id int
);

ALTER TABLE ONLY supplier
    ADD CONSTRAINT pk_supplier_id PRIMARY KEY (id);
ALTER TABLE ONLY product
    ADD CONSTRAINT pk_product_id PRIMARY KEY (id);
ALTER TABLE ONLY product_category
    ADD CONSTRAINT pk_product_category_id PRIMARY KEY (id);
ALTER TABLE ONLY account
    ADD CONSTRAINT pk_account_id PRIMARY KEY (id);
ALTER TABLE ONLY cart
    ADD CONSTRAINT pk_cart_id PRIMARY KEY (id);
ALTER TABLE ONLY line_item
    ADD CONSTRAINT pk_line_item_id PRIMARY KEY (id);
ALTER TABLE ONLY shipping_info
    ADD CONSTRAINT pk_shipping_info_id PRIMARY KEY (id);
ALTER TABLE ONLY order_info
    ADD CONSTRAINT pk_order_info_id PRIMARY KEY (id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier (id) ON DELETE CASCADE;
ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id) REFERENCES product_category (id) ON DELETE CASCADE;
ALTER TABLE ONLY cart
    ADD CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE;
ALTER TABLE ONLY line_item
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE;
ALTER TABLE ONLY shipping_info
    ADD CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE;
ALTER TABLE ONLY order_info
    ADD CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE;
ALTER TABLE ONLY order_info
    ADD CONSTRAINT fk_cart_id FOREIGN KEY (cart_id) REFERENCES cart (id) ON DELETE CASCADE;
ALTER TABLE ONLY order_info
    ADD CONSTRAINT fk_shipping_info_id FOREIGN KEY (shipping_info_id) REFERENCES shipping_info (id) ON DELETE CASCADE;
