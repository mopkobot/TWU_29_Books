create table if not exists users (
    id Integer not null auto_increment,
    casname varchar(30) not null,
    name varchar(150) not null,
    constraint pk_user primary key (id)
)ENGINE=InnoDB;
