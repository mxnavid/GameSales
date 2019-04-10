package table;

public class Sale {
    private int saleID;
    private String customerEmail;
    private int StoreID;
    private int gameSKU;

    public Sale(int saleID, String customerEmail, int storeID, int gameSKU) {
        this.saleID = saleID;
        this.customerEmail = customerEmail;
        StoreID = storeID;
        this.gameSKU = gameSKU;
    }

    public int getSaleID() { return saleID; }

    public String getCustomerEmail() { return customerEmail; }

    public int getStoreID() { return StoreID; }

    public int getGameSKU() { return gameSKU; }
}
