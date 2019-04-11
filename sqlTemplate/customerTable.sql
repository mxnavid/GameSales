CREATE TABLE CUSTOMER(
  ID     INT PRIMARY KEY,
  name    VARCHAR(255),
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  street    VARCHAR(255),
  city     VARCHAR(255),
  state    VARCHAR(255),
  phone    VARCHAR(255),
  frequentShopper VARCHAR(255) NOT NULL
);

INSERT INTO CUSTOMER
VALUES ('1','Curry Ringsell','cringsell0@dedecms.com','yuHoP855Q','400 Oxford Exchange Blvd','Oxford','AL','(256) 231-2900');

