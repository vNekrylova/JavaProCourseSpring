create table users
(
    id bigserial primary key,
    username varchar(255) unique
);

insert into users (username)
values ('Валерий'),
       ('Алиния'),
       ('Анастасий');