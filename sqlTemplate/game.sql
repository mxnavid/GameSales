CREATE TABLE GAME(
  SKU      INT PRIMARY KEY,
  Ranking  VARCHAR(255) NOT NULL,
  Name     VARCHAR(255) NOT NULL,
  Platform VARCHAR(255) NOT NULL,
  Year     VARCHAR(255) NOT NULL,
  Genre    VARCHAR(255) NOT NULL,
  Vendor   VARCHAR(255) NOT NULL,
  Price    VARCHAR(255) NOT NULL,
);


-- Sorting in ascending order
select * from game order by RANKING+0
