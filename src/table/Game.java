package table;

public class Game {
    private int sku;
    private int rank;
    private String name;
    private String platform;
    private int year;
    private String genre;
    private String vendor;
    private double price;

    public Game(int sku, int rank, String name, String platform, int year, String genre, String vendor, double price) {
        this.sku = sku;
        this.rank = rank;
        this.name = name;
        this.platform = platform;
        this.year = year;
        this.genre = genre;
        this.vendor = vendor;
        this.price = price;
    }


    public int getSku() { return sku; }

    public int getRank() { return rank; }

    public String getName() { return name; }

    public String getPlatform() { return platform; }

    public int getYear() { return year; }

    public String getGenre() { return genre; }

    public String getVendor() {return vendor; }

    public double getPrice() { return price; }
}