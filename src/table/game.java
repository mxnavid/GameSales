package table;

public class game {
    private int sku;
    private int rank;
    private String name;
    private String platform;
    private int year;
    private String genre;
    private String publisher;
    private double price;
    private int sales;

    public game(int sku){
        this.sku = sku;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPlatform(String platform){
        this.platform = platform;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setSales(int sales){
        this.sales = sales;
    }

    public int getRank(){
        return this.rank;
    }

    public String getName(){
        return this.name;
    }

    public String getPlatform(){
        return this.platform;
    }

    public int getYear(){
        return this.year;
    }

    public String getGenre(){
        return this.genre;
    }

    public String getPublisher(){
        return this.publisher;
    }

    public double getPrice(){
        return this.price;
    }

    public int getSales(){
        return this.sales;
    }
}