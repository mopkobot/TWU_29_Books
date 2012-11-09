create table if not exists readings (
    id Integer not null auto_increment,
    user_casname varchar(30) not null,
    book_id int not null,
    reading_status int not null,
    constraint pk_reading primary key (id),
    foreign key (user_casname) REFERENCES users(casname),
    foreign key (book_id) REFERENCES books(id)
)ENGINE=InnoDB;