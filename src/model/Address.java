package model;

public class Address {
    private String street;
    private String city;
    private String state;
    private int num;

    public void setStreet(String street){
        this.street = street;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setNum(int num){
        this.num = num;
    }

    public void setState(String state){
        this.state = state;
    }

    public String setStreet(){
        return this.street;
    }

    public String setCity(){
        return this.city;
    }

    public int setNum(){
        return this.num;
    }

    public String setState(){
        return this.state;
    }
}
