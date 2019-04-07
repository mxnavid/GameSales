package table;

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

    public String getStreet(){
        return this.street;
    }

    public String getCity(){
        return this.city;
    }

    public int getNum(){
        return this.num;
    }

    public String getState(){
        return this.state;
    }
}
