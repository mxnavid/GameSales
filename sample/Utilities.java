package sample;

import objects.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public static void makeNewDatabase(Connection connection, String path, String username, String password) {
        System.out.println("I am getting executed");
        connect(connection, path, username, password);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder component = new StringBuilder(); String line;
            System.out.println(component);

            while ((line = reader.readLine()) != null) {
                // skip comments
                if (line.startsWith("--")) continue;
                component.append(line);

                // found component, execute
                if (line.endsWith(";")) {

                    Statement statement = connection.createStatement();
                    System.out.println(component);
                    statement.execute(component.toString());
                    component = new StringBuilder();
                }
            }
            reader.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
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
                customers.add(new Customer(Integer.parseInt(components[0]), components[1], components[2], components[3], components[4], components[5], components[6], components[7]));
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
                "VALUES(\'%d\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\');",
                customer.getId(), customer.getName(), customer.getUsername(), customer.getPassword(),
                customer.getStreet(), customer.getCity(), customer.getCity(), customer.getState(), customer.getPhone());
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
                System.out.printf("Customer %d: %s %s %s %s %s %s %s\n",
                        rs.getInt(1),
                        rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),
                        rs.getString(8));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createGameTable(Connection connection, String fileName) throws SQLException {

    }

    public static void createGameInstanceTable(Connection connection, String fileName) throws SQLException {

    }

    public static void createStoreTable(Connection connection, String fileName) throws SQLException {

    }

    public static void log(String prefix, String message) {
        String time = String.format("("+ prefix+ ") [%1$tH:%1$tM:%1$tS] ", new Date());
        System.out.println(time + message);
    }

}
