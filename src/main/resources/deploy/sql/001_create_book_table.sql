
create table if not exists books (
    id Integer not null auto_increment,
    title varchar(150) not null,
    author varchar(150) not null,
    description TEXT not null,
    image varchar(300) not null,
    ISBN10 varchar(10) null,
    ISBN13 varchar(14) null,
    constraint pk_book primary key (id)
);