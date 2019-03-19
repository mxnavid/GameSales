package table;

public class store {
    private int storeID;
    private Address address;
    private int phoneNum;
    private String userName;

    public store(int storeID){
        this.storeID = storeID;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNum(int num){
        this.phoneNum = num;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public int getStoreID(){
        return this.storeID;
    }

    public Address getAddress() {
        return this.address;
    }

    public int getPhoneNum(){
        return this.phoneNum;
    }

    public String getUserName(){
        return this.userName;
    }
}
