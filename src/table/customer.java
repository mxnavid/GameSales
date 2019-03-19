package table;

import java.util.ArrayList;
import java.util.List;

public class customer {
    private String email;
    private String password;
    private String ID;
    private List<String> games = new ArrayList<>();
    private String name;
    private Address address;
    private int phoneNum;

    public customer(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public void setPhone(int num){
        this.phoneNum = num;
    }

    public String getPassword(){
        return this.password;
    }

    public String getID(){
        return this.ID;
    }

    public String getName(){
        return this.name;
    }

    public Address getAddress(){
        return this.address;
    }

    public int getPhone(){
        return this.phoneNum;
    }
}
