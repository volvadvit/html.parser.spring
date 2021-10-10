create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );

create table words_count (
     id bigint not null,
     word varchar(255) not null,
     count int not null,
     url_id bigint,
     primary key (id)
);

create table url (
     id bigint not null,
     url varchar(255) not null,
     primary key (id)
);

alter table words_count add constraint words_count_fk foreign key (url_id) references url (id);