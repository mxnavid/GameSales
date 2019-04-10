package table;

public class Store {
    private int storeID;
    private String email;
    private String Password;
    private String Street;
    private String City;
    private String State;
    private int phoneNum;

    public Store(int storeID, String email, String password, String street, String city, String state, int phoneNum) {
        this.storeID = storeID;
        this.email = email;
        Password = password;
        Street = street;
        City = city;
        State = state;
        this.phoneNum = phoneNum;
    }

    public int getStoreID() { return storeID; }

    public String getEmail() { return email; }

    public String getPassword() { return Password; }

    public String getStreet() { return Street; }

    public String getCity() { return City; }

    public String getState() { return State; }

    public int getPhoneNum() { return phoneNum; }
}
