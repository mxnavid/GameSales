package table;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int ID;
    private String name;
    private String username;
    private String password;
    private String Street;
    private String City;
    private String state;
    private String phone;
    private String frequentShopper;

    public Customer(int ID, String name, String username, String password, String street, String city, String state, String phone, String frequentShopper) {
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = password;
        Street = street;
        City = city;
        this.state = state;
        this.phone = phone;
        this.frequentShopper = frequentShopper;
    }

    public int getID() { return ID; }

    public String getName() { return name; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getStreet() { return Street; }

    public String getCity() { return City; }

    public String getState() { return state; }

    public String getPhone() { return phone; }

    public String getFrequentShopper() { return frequentShopper; }
}
