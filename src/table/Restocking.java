package table;

public class Restocking {
    private int ID;
    private int vendorID;
    private int storeID;
    private int gameSKU;
    private String vendorName;
    private int quantity;

    public Restocking(int ID, int vendorID, int storeID, int gameSKU, String vendorName, int quantity) {
        this.ID = ID;
        this.vendorID = vendorID;
        this.storeID = storeID;
        this.gameSKU = gameSKU;
        this.vendorName = vendorName;
        this.quantity = quantity;
    }

    public int getID() { return ID; }

    public int getVendorID() { return vendorID; }

    public int getStoreID() { return storeID; }

    public int getGameSKU() { return gameSKU; }

    public String getVendorName() { return vendorName; }

    public int getQuantity() { return quantity; }
}
