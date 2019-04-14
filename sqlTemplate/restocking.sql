CREATE TABLE restocking
(
  id    BIGINT PRIMARY KEY,
  vendorID VARCHAR(255) NOT NULL,
  storeID  VARCHAR(255) NOT NULL,
  gameSKU   VARCHAR(255) NOT NULL,
  vendorName  VARCHAR(255) NOT NULL,
  QUANTITY VARCHAR(255) NOT NULL
);


select id from vendor where name = 'Nintendo'