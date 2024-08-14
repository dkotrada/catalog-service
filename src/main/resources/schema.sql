drop table if exists book;
create table book(
    id          bigserial primary key not null,
    isbn        varchar(255) unique not null,
    title       varchar(255) not null,
    author      varchar(255) not null,
    price       float8 not null,
    version     integer not null
);