drop table logger2;

create table if not exists logger2 (
id bigint not null primary key,
fecha timestamp  default current_timestamp,
name varchar,
population varchar,
area varchar
);

create sequence logger2_seq maxvalue 2147483647;

select * from logger