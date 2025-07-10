create table products
(
    id bigserial primary key,
    account_number varchar(20) unique,
    balance numeric(15, 2) default 0.00,
    product_type varchar(20),
    user_id bigint references users(id)
);

insert into products (account_number, balance, product_type, user_id)
values ('1234560000000001', 15000.50, 'счет', 1),
       ('1234560000000002', 3400.00, 'карта', 1),
       ('1234560000000003', 123.45, 'карта', 2),
       ('1234560000000004', 999.99, 'карта', 2);