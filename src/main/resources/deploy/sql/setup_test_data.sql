use readerfeeder;

delete from readings where user_casname="test.twu";
delete from users where casname="test.twu";

delete from books where title like "Lavanya and sanchari QAs";
delete from books where title like "When devs are not coding";

insert into books (id, author,title,description,image,ISBN10,ISBN13) values("1377","J.K. Rowling","Lavanya and sanchari QAs","The books chronicle the adventures of a wizard, Harry Potter, and his friends Ronald Weasley and Hermione Granger, all of whom are students at Hogwarts School of Witchcraft and Wizardry.","http://ecx.images-amazon.com/images/I/51HVlrefdkL._SL500_AA300_.jpg","1234567890","3456789234567");

insert into books (id, author,title,description,image,ISBN10,ISBN13) values("1333","Benjamin Parzybok","When devs are not coding","","","8947321890","");
