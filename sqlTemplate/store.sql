CREATE TABLE GAME(
                     StoreID      INT PRIMARY KEY,
                     Email  VARCHAR(255) NOT NULL,
                     Password    VARCHAR(255) NOT NULL,
                     Street VARCHAR(255) NOT NULL,
                     City     VARCHAR(255) NOT NULL,
                     State    VARCHAR(255) NOT NULL,
                     PhoneNumber   VARCHAR(255) NOT NULL
  );