package model;

import java.util.ArrayList;
import java.util.List;

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

    public void getRank(int rank){
        this.rank = rank;
    }
}