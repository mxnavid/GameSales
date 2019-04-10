package table;

public class Inventory {
    private int storeID;
    private int SKU;
    private int quantity;

    public Inventory(int storeID, int SKU, int quantity) {
        this.storeID = storeID;
        this.SKU = SKU;
        this.quantity = quantity;
    }

    public int getStoreID() { return storeID; }

    public int getSKU() { return SKU; }

    public int getQuantity() { return quantity; }
}
