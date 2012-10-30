create database if not exists readerfeeder;
use readerfeeder;
drop table if exists user;
drop table if exists task;
drop table if exists book;

create table user (
    id Integer not null auto_increment,
    name varchar(80) null,
    constraint pk_user primary key (id)
);

create table task (
    id Integer not null,
    name varchar(80) null,
    description varchar(300) null,
    poIntegers Integer null,
    code varchar(10) null,
    constraint pk_task primary key (id)
);

create table book (
    id Integer not null auto_increment,
    title varchar(150) not null,
    author varchar(150) not null,
    description TEXT not null,
    image varchar(300) not null,
    ISBN10 varchar(10) null,
    ISBN13 varchar(14) null,
    constraint pk_book primary key (id)
);