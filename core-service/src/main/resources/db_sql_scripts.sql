
SET search_path TO myschema, public;

create table categories
(
    id    bigserial
        constraint categories_pk
            primary key,
    title varchar(255)
);

create table contents
(
    id          bigserial constraint contents_pk  primary key,
    content     varchar(4096),
    moderate    boolean,
    category_id bigserial
        constraint contents_categories_id_fk
            references categories
            on update cascade on delete cascade,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table likes
(
    id         bigserial
        constraint likes_pk
            primary key,
    content_id bigserial
        constraint likes_contents_id_fk
            references contents
);

insert into categories (title) values ('mem');
insert into categories (title) values ('vovochka');
insert into contents (category_id, content, moderate) values (1, 'Лето это 3 зарплаты', true);
insert into contents (category_id, content, moderate) values (2, 'Шутка про Вовочку', true);

