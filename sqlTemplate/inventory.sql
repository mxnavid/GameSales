CREATE TABLE inventory
(
  storeID    VARCHAR(255) NOT NULL,
  SKU VARCHAR(255) NOT NULL,
  quantity  VARCHAR(255) NOT NULL,
);

update inventory set quantity = '319' where sku = '64810'