CREATE TABLE IF NOT EXIST Game(
  SKU
  Ranking
  Name
  Platform
  Year
  Genre
  Publisher
  Price
  Sales
)

CREATE TABLE IF NOT EXIST Customer(
  Email
  Password
  ID
  gamesOwned
  Name
  Address
  Phone
)

-- TODO Figure out how to write Derived value for Address

CREATE TABLE IF NOT EXIST Store(
  storeID
  Address
)


CREATE TABLE IF NOT EXIST GameInstance(
  unitedID
  customerUsername
)



