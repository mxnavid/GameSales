package table;

public class Vendor {
    private int ID;
    private String email;
    private String password;
    private String name;
    private String street;
    private String city;
    private String state;
    private String phone;

    public Vendor(int ID, String email, String password, String name, String street, String city, String state, String phone) {
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.phone = phone;
    }

    public int getID() { return ID; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getName() { return name; }

    public String getStreet() { return street; }

    public String getCity() { return city; }

    public String getState() { return state; }

    public String getPhone() { return phone; }
}
