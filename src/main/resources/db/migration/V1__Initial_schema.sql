create table book(
    id                  bigserial primary key not null,
    created_date        timestamp NOT NULL,
    last_modified_date  timestamp NOT NULL,
    isbn                varchar(255) unique not null,
    title               varchar(255) not null,
    author              varchar(255) not null,
    price               float8 not null,
    version             integer not null
);