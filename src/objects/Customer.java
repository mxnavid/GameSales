package objects;

public class Customer {

    private int id;
    private String name;
    private String username;
    private String password;
    private String street;
    private String city;
    private String state;
    private String phone;

    public Customer(int id, String name, String username, String password, String street, String city, String state, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.street = street;
        this.city = city;
        this.state = state;
        this.phone = phone;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getPhone() { return phone; }
}
