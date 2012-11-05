use readerfeeder;

CREATE TABLE if not exists changelog (
  change_number INTEGER NOT NULL,
  start_dt TIMESTAMP NOT NULL,
  complete_dt TIMESTAMP NULL,
  applied_by VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
  constraint pk_changelog primary key (change_number)
);
