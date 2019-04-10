package sample;

import table.Customer;
import table.Game;
import table.Store;
import table.Vendor;
import table.Store;
import table.Restocking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utilities {


    public static Connection connect(Connection connection, String location, String username, String password) {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:file:" + location, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * FillDataBase
     * Fills in all tables with information from a csv file.
     */
    public static void fillDatabase(Connection connection) {
        try {
            createCustomerTable(connection, "src/CSVFiles/Customer.csv");
//            createGameTable(connection, "Game.csv");
//            createGameInstanceTable(connection, "GameInstance.csv");
//            createStoreTable(connection, "Store.csv");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // from csv
    public static void createCustomerTable(Connection connection, String fileName) throws SQLException {
        System.out.println("CreateCustomerTable Called");
        List<Customer> customers = new ArrayList<Customer>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] components = line.split(",");
                customers.add(new Customer(Integer.parseInt(components[0]), components[1], components[2], components[3], components[4], components[5], components[6], components[7], components[8]));
            }
            reader.close();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        for (int counter = 0; counter < customers.size(); counter++) {
            addCustomer(connection, customers.get(counter));
        }
        System.out.println("I reached End");

    }

    public static void addCustomer(Connection connection, Customer customer) {
        System.out.println("Adding Customer Called");
        String command = String.format("INSERT INTO CUSTOMER " +
                        "VALUES(\'%d\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\');",
                customer.getID(), customer.getName(), customer.getUsername(), customer.getPassword(),
                customer.getStreet(), customer.getCity(), customer.getState(), customer.getPhone(), customer.getFrequentShopper());
        try { // execute command
            Statement st = connection.createStatement();
            st.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void printCusomterTable(Connection connection) {
        String command = "SELECT * FROM Customer;";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(command);

            while (rs.next()) {
                System.out.printf("Customer %d: %s %s %s %s %s %s %s %s \n",
                        rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createGameTable(Connection connection, String fileName) throws SQLException {
        System.out.println("Create GameTable called");
        List<Game> games = new ArrayList<Game>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] components = line.split(",");
                games.add(new Game(Integer.parseInt(components[0]), Integer.parseInt(components[1]), components[2], components[3], Integer.parseInt(components[4]), components[5], components[6], Double.parseDouble(components[7])));
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();

        }
    }

    public static void addGames(Connection connection, Game game) {
        System.out.println("Adding Customers Now");
        String command = String.format("INSERT INTO GAME" +
                        "VALUES(\'%d\', \'%d\', \'%s\', \'%s\', \'%d\', \'%s\', \'%s\', \'%f\')",
                game.getSku(), game.getRank(), game.getName(), game.getPlatform(), game.getYear(), game.getGenre(), game.getVendor(),
                game.getPrice());
        try {
            Statement st = connection.createStatement();
            st.execute(command);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStore(Connection connection, Store store) {
        System.out.println("Adding Stores Now");
        String command = String.format("INSERT INTO STORE" +
                        "VALUES(\'%d\',  \'%s\',  \'%s\',  \'%s\',  \'%s\',  \'%s\',  \'%s\');",
                store.getStoreID(), store.getEmail(), store.getPassword(), store.getStreet(), store.getCity(), store.getState(), store.getPhoneNum());
        try {
            Statement st = connection.createStatement();
            st.execute(command);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addVendor(Connection connection, Vendor vendor) {
        System.out.println("Adding Vendors");
        String command = String.format("INSERT INTO VENDOR" +
                        "VALUES(\'%d\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\' )",
                vendor.getID(), vendor.getEmail(), vendor.getPassword(), vendor.getName(), vendor.getStreet(), vendor.getCity(), vendor.getState(), vendor.getPhone());

        try {
            Statement st = connection.createStatement();
            st.execute(command);


        } catch (
                SQLException e) {
            e.printStackTrace();

        }
    }

    
    public static void createStoreTable(Connection connection, String fileName) throws SQLException {

    }

    public static void log(String prefix, String message) {
        String time = String.format("("+ prefix+ ") [%1$tH:%1$tM:%1$tS] ", new Date());
        System.out.println(time + message);
    }

}
