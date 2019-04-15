import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class customerPanel {
    /**
     * Creates New Customer Account only email and password
     * @param s scanner
     * @param connection connection
     */
    public static void createNewAccount (Scanner s, Connection connection){
        System.out.println("Create New Customer Account");
        System.out.println("Enter new username: ");
        String user = "'" + s.nextLine() + "'";
        System.out.println("Enter new password: ");
        String password = "'"+ s.nextLine() + "'";
        int customerCounter = gameUtil.counter(connection, "SELECT COUNT(*) AS rowCount FROM customer"); //SQL
        customerCounter = customerCounter+1;
        String customerSignUp = "INSERT INTO CUSTOMER VALUES(" + customerCounter + ", 'null', " + user + ", " + password + ", 'null', 'null','null', 'null', 'false')";
        gameUtil.executeSQLCommand(connection, customerSignUp);
        System.out.println("Successfully registered the customer");
    }


    /**
     * Completes all the functionality of customer
     * @param s scanner
     * @param connection connection
     */
    public static void customerWorks (Scanner s, Connection connection){
        System.out.println("Welcome to Customer Panel");
        System.out.println("Enter username: ");
        String inputtedUser = s.nextLine();
        String usernameCmd = "select username from customer where username = '" + inputtedUser +"'"; //SQL
        if (!gameUtil.getStringValue(connection, usernameCmd).equals(inputtedUser)){
            System.out.println("User not found, type in username again");
            return;
        }
        System.out.println("Enter password: ");
        String inputtedPassword = s.nextLine();
        String passwordCMD = "select password from customer where password ='"+ inputtedPassword +"'"; //SQL
        if (gameUtil.getStringValue(connection, passwordCMD).equals(inputtedPassword)){
            String input5 = "";
            while(!input5.equals("6")){
                System.out.println("Customer Menu\n");
                System.out.println("1: Browse Games \n2: View Account Information \n3: Change Account Information \n4: Buy Game \n5: Purchase History \n6: Back");
                input5 = s.nextLine();
                switch(input5) {
                    case "1":
                        System.out.println("Browse Games");
                        break;
                    case "2":
                        String getAcc  = "select * from customer where username ='"+ inputtedUser+ "'";
                        gameUtil.executeSQLCommand(connection, getAcc);
                        break;
                    case "3":
                        System.out.println("What information you want to change? \n1: name \n2: password \n3: Street \n4: City \n5: State \n6: Phone \n7: Back");
                        String response = s.nextLine();
                        switch(response){
                            case "1":
                                System.out.println("Input New Name: ");
                                String name = "update customer set name= '"+ s.nextLine() +"' where username = '"+ inputtedUser+"'";
                                System.out.println("Change Successful");
                                gameUtil.executeSQLCommand(connection, name);
                                break;
                            case "2":
                                System.out.println("Input New Password: ");
                                String password = "update customer set password= '"+ s.nextLine() +"' where username = '"+ inputtedUser+"'";
                                System.out.println("Change Successful");
                                gameUtil.executeSQLCommand(connection, password);
                                break;
                            case "3":
                                System.out.println("Input New Street: ");
                                String street = "update customer set street= '"+ s.nextLine() +"' where username = '"+ inputtedUser+"'";
                                System.out.println("Change Successful");
                                gameUtil.executeSQLCommand(connection, street);
                                break;
                            case "4":
                                System.out.println("Input New City: ");
                                String city = "update customer set city= '"+ s.nextLine() +"' where username = '"+ inputtedUser+"'";
                                System.out.println("Change Successful");
                                gameUtil.executeSQLCommand(connection, city);
                                break;
                            case "5":
                                System.out.println("Input New State: ");
                                String state = "update customer set state= '"+ s.nextLine() +"' where username = '"+ inputtedUser+"'";
                                System.out.println("Change Successful");
                                gameUtil.executeSQLCommand(connection, state);
                                break;
                            case "6":
                                System.out.println("Input New Phone: ");
                                String phone = "update customer set phone= '"+ s.nextLine() +"' where username = '"+ inputtedUser+"'";
                                System.out.println("Change Successful");
                                gameUtil.executeSQLCommand(connection, phone);
                                break;
                            case "7":
                                return;
                        }
                        break;
                    case "4":
                        String customerEmail = inputtedUser;
                        System.out.println("Buy Games \n");

                        long salesID = System.currentTimeMillis();

                        int storeID = 1111;
                        double Price = 0;
                        List<Integer> quantites = new ArrayList<>();
                        List<Integer> subtractedQ = new ArrayList<>();
                        List<Integer> sku = new ArrayList<>();

                        String inputq = "";
                        while(!inputq.equals("done")){
                            System.out.println("Enter the SKU Number: ");
                            int enteredSKU = Integer.parseInt(s.nextLine());
                            System.out.println("Enter the Quantity: ");
                            int enteredQuantity = Integer.parseInt(s.nextLine());
                            String addCart = "insert into cart values ('"+salesID +"','" + enteredSKU + "','"+ enteredQuantity +"')";
                            gameUtil.executeSQLCommand(connection, addCart);
                            double tempPrice = Double.parseDouble(gameUtil.getStringValue(connection, "select price from game where sku='"+enteredSKU+"'"));
                            Price = Price + (tempPrice*enteredQuantity);
                            quantites.add(Integer.parseInt(gameUtil.getStringValue(connection, "select quantity from inventory where sku = '"+enteredSKU+"' and storeid = '" + storeID +"'")));
                            subtractedQ.add(enteredQuantity);
                            sku.add(enteredSKU);
                            System.out.println("Press any key to continue or type done if no more game need to be added");
                            inputq = s.nextLine();
                        }
                        if (gameUtil.getStringValue(connection, "select frequentShopper from customer where username='"+customerEmail+"'").equals("TRUE")){
                            Price = Price*0.8;
                        }
                        String finalSale = "insert into sale values('"+salesID+"','"+customerEmail +"','"+storeID+"','"+Price+"')";
                        gameUtil.executeSQLCommand(connection, finalSale);

                        for (int counter = 0; counter < quantites.size(); counter++){

                            String updateQCommand = "update inventory set quantity='"+(quantites.get(counter)-subtractedQ.get(counter))+"' where sku= '"+ sku.get(counter)+"' and storeid = '"+ storeID +"'";
                            gameUtil.executeSQLCommand(connection, updateQCommand);
                        }

                        System.out.println("Sales Successful, confirmation number: " + salesID);
                        break;

                    case "5":

                        List<Long> ids = new ArrayList<Long>();
                        List<String> names = new ArrayList<String>();

                        try {

                            String getSaleCommand = "select saleid from sale where custEmail = '" + inputtedUser + "'";

                            Statement st = connection.createStatement();
                            ResultSet rs = st.executeQuery(getSaleCommand);

                            while(rs.next()) {
                                long nextLong = rs.getLong("saleid");
                                ids.add(nextLong);
                                ///System.out.println(nextLong);
                                String nameCMD = "select sku from cart where salesid ='" + nextLong + "'";
                                String skuString = gameUtil.getStringValue(connection, nameCMD);
                                //System.out.println(sku);
                                names.add(gameUtil.getStringValue(connection, "select name from game where sku ='" + skuString +"'"));

                            }
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }

                        for (int counter = 0; counter < ids.size(); counter++) {
                            if (names.get(counter).equals("invalid")){
                                continue;
                            }
                            System.out.println("Game Name: " + names.get(counter));
                            Date date = new Date (ids.get(counter) * 1000);

                            System.out.println("\tPurchased: " + date.toString());
                        }

                        break;
                    case "6":
                        return;



                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}


