import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class storePanel {
    /**
     * Completes all the functionality of store
     * @param s scanner
     * @param connection connection
     */
    public static void storeWorks(Scanner s, Connection connection){
        Scanner filterScanner = new Scanner(System.in);

        System.out.println("Enter your store login email: ");
        String inputtedUser = s.nextLine();
        String usernameCmd = "select Email from store where Email = '" + inputtedUser +"'";  //SQL
        if (!gameUtil.getStringValue(connection, usernameCmd).equals(inputtedUser)){
            System.out.println("Store Account not found, type in the credentials again");
            return;
        }
        System.out.println("Enter password: ");
        String inputtedPassword = s.nextLine();
        String passwordCMD = "select password from store where password ='"+ inputtedPassword +"'";

        if(gameUtil.getStringValue(connection, passwordCMD).equals(inputtedPassword)){
            String input5 = "";
            while(!input5.equals("8")){
                System.out.println("1: Browse Available Games \n2: Restocking \n3: See your Inventory \n4: Checkout a Customer \n5: See Customer Information \n6: Vendor Info \n7: Enroll To Frequent Shopper Program \n8: Back");
                input5 = s.nextLine();
                switch(input5) {
                    case "1":
                        String input51 = "";
                        while(!input51.equals("8")){
                            System.out.println("Restock Menu/Filters\n");
                            System.out.println("1: Search for Specific Game \n2: Genre \n3: Price \n4: Platform \n5: Ranking \n6: Year by Range \n7: Search by SKU" +
                                    "\n8: Back ");
                            input51 = s.nextLine();
                            switch(input51) {
                                case "1":
                                    System.out.println("Searching for a Specific Game by Name");
                                    System.out.println("Enter the Game Name: ");
                                    String gameNameInput = s.nextLine();
                                    String gameName = "select * from game where name = '" + gameNameInput +"'";
                                    gameUtil.executeSQLCommand(connection, gameName);
                                    break;

                                case "2":
                                    System.out.println("Restock Menu/Filters/Genre\n");
                                    System.out.println("Genres available = Action, Shooter, Puzzle, Role-Playing, Misc, Simulation, Racing, Adventure, Strategy, Fighting.");
                                    System.out.println("Enter a genre: ");
                                    List<String> Genres = Arrays.asList("Action", "Shooter", "Puzzle", "Role-PLaying", "Misc", "Simulation", "Racing", "Adventure", "Strategy", "Fighting");

                                    String genreInput = filterScanner.nextLine();
                                    if (!Genres.contains(genreInput)) {
                                        System.out.println("Not valid Genre");
                                        break;
                                    }
                                    gameUtil.filterTable(connection, "Game", "Genre", genreInput);

                                    break;
                                case "3":
                                    System.out.println("Restock Menu/Filters/Price Range\n");
                                    System.out.println("Enter a Price Lower Limit Integers: ");
                                    String priceLow = filterScanner.nextLine();

                                    System.out.println("Enter Price High Limit Integers: ");
                                    String priceHigh = filterScanner.nextLine();
                                    String priceCommand = "select * from game where price between " + priceLow+".00 and "+ priceHigh+".00"; //SQL
                                    gameUtil.executeSQLCommand(connection, priceCommand);
                                    break;
                                case "4":
                                    System.out.println("Restock Menu/Filters/Platform\n");

                                    System.out.println("Systems available = XOne, X360, GBA, DS, PSP, SNES, PS3, PS2, NES, PSV, GC, Wii, PC, PS, SCD, N64, PS4, XB, 2600, 3DS, GEN, WiiU, GB");
                                    System.out.println("Enter a Platform: ");
                                    List<String> Platforms = Arrays.asList("XOne", "X360", "GBA", "DS", "PSP", "SNES", "PS3", "PS2", "NES", "PSV", "GC", "Wii", "PC", "PS", "SCD", "N64", "PS4", "XB", "2600", "3DS", "GEN", "WiiU", "GB");
                                    String platform = filterScanner.nextLine();
                                    if (!Platforms.contains(platform)) {
                                        System.out.println("Not valid Platform");
                                        break;
                                    }
                                    gameUtil.filterTable(connection, "Game", "Platform", platform);
                                    break;
                                case "5":
                                    System.out.println("Restock Menu/Filters/Ranking in Ascending Order\n");
                                    gameUtil.executeSQLCommand(connection, "select * from game order by RANKING+0"); //SQL
                                    //SQL statements
                                    break;
                                case "6":
                                    System.out.println("Restock Menu/Filters/Year\n");
                                    System.out.println("Enter a year: ");
                                    System.out.println("Enter the Starting");
                                    String yearBeg = filterScanner.nextLine();

                                    System.out.println("Enter Ending Year:  ");
                                    String yearEnd = filterScanner.nextLine();
                                    String yearCommand = "select * from game where year between " + yearBeg+" and "+ yearEnd; //SQL
                                    gameUtil.executeSQLCommand(connection, yearCommand);
                                    break;
                                case "7":
                                    System.out.println("Search by SKU");
                                    System.out.println("Enter SKU of the Game: ");
                                    String skuInput = s.nextLine();
                                    String skuCommand = "select * from game where sku = '" + skuInput+"'"; //SQL
                                    gameUtil.executeSQLCommand(connection, skuCommand);
                                    break;

                            }
                        }
                        break;

                    case "2":
                        long restockingID = System.currentTimeMillis();

                        System.out.println("Restocking\n");
                        String forGettingid = "select storeid from store where email= '" + inputtedUser + "'"; //SQL
                        int storeID = Integer.parseInt(gameUtil.getStringValue(connection, forGettingid));

                        System.out.println("Enter SKU Of the Game you want to restock:");
                        int skuInteger = Integer.parseInt(s.nextLine());

                        System.out.println("Please enter the quantity you want to restock: ");
                        int quantityEntered = Integer.parseInt(s.nextLine());


                        String vendorName = gameUtil.getStringValue(connection, "select vendor from game where sku= '"+skuInteger+"'"); //SQL
                        int vendorID = Integer.parseInt(gameUtil.getStringValue(connection, "select id from vendor where name = '"+vendorName + "'"));


                        String restockingCommand = "insert into inventory values('"+ storeID +"','"+skuInteger+ "','" +quantityEntered+"')";
                        gameUtil.executeSQLCommand(connection, restockingCommand);
                        String commandRestock = "insert into restocking values('"+restockingID+"','"+vendorID+"','"+storeID+"','"+skuInteger+"','"+vendorName+"','"+quantityEntered+"')";

                        gameUtil.executeSQLCommand(connection, commandRestock);

                        System.out.println("Restocking Successful");
                        gameUtil.executeSQLCommand(connection, "select * from inventory where sku = '"+skuInteger+"' and storeid = '" + storeID +"'" ); //SQL

                        break;


                    case "3":
                        System.out.println("See your Inventory\n");
                        forGettingid = "select storeid from store where email= '" + inputtedUser + "'"; //SQL
                        storeID = Integer.parseInt(gameUtil.getStringValue(connection, forGettingid));
                        String checkInventory = "select * from inventory where storeid = '"+storeID+"'"; //SQL
                        gameUtil.executeSQLCommand(connection, checkInventory);
                        break;


                    case "4":
                        String customerEmail = "";
                        System.out.println("Checkout a Customer \n");

                        long salesID = System.currentTimeMillis();


                        System.out.println("Whats the customer's email: ");
                        System.out.println("If no email, type none");
                        String cusEmail = s.next();
                        if (cusEmail.equals("none")){
                            customerEmail = "null";
                        }
                        else{
                            customerEmail = cusEmail;
                        }
                        forGettingid = "select storeid from store where email= '" + inputtedUser + "'";
                        storeID = Integer.parseInt(gameUtil.getStringValue(connection, forGettingid));
                        double Price = 0;
                        List<Integer> quantites = new ArrayList<>();
                        List<Integer> subtractedQ = new ArrayList<>();
                        List<Integer> sku = new ArrayList<>();
                        String inputq = s.nextLine();
                        while(!inputq.equals("done")){
                            System.out.println("Enter the SKU Number: ");
                            int enteredSKU = Integer.parseInt(s.nextLine());
                            System.out.println("Enter the Quantity: ");
                            int enteredQuantity = Integer.parseInt(s.nextLine());
                            String addCart = "insert into cart values ('"+salesID +"','" + enteredSKU + "','"+ enteredQuantity +"')";
                            gameUtil.executeSQLCommand(connection, addCart);
                            double tempPrice = Double.parseDouble(gameUtil.getStringValue(connection, "select price from game where sku='"+enteredSKU+"'")); //SQL
                            Price = Price + (tempPrice*enteredQuantity);
                            quantites.add(Integer.parseInt(gameUtil.getStringValue(connection, "select quantity from inventory where sku = '"+enteredSKU+"' and storeid = '" + storeID +"'"))); //SQL
                            subtractedQ.add(enteredQuantity);
                            sku.add(enteredSKU);
                            System.out.println("Press any key to continue or type done if no more game need to be added");
                            inputq = s.nextLine();
                        }
                        if (gameUtil.getStringValue(connection, "select frequentShopper from customer where username='"+customerEmail+"'").equals("TRUE")){ //SQL
                            Price = Price*0.8;
                        }
                        String finalSale = "insert into sale values('"+salesID+"','"+customerEmail +"','"+storeID+"','"+Price+"')";
                        gameUtil.executeSQLCommand(connection, finalSale);

                        for (int counter = 0; counter < quantites.size(); counter++){

                            String updateQCommand = "update inventory set quantity='"+(quantites.get(counter)-subtractedQ.get(counter))+"' where sku= '"+ sku.get(counter)+"' and storeid = '"+ storeID +"'"; //SQL
                            gameUtil.executeSQLCommand(connection, updateQCommand);
                        }

                        System.out.println("Sales Successful, confirmation number: " + salesID);
                        break;




                    case "5":
                        System.out.println("See Customer Information");
                        System.out.println("Please type in Customer Email: ");
                        String cashierPuttingCustEmail = s.nextLine();
                        gameUtil.executeSQLCommand(connection,"select id,name,Username,Street,City,State,Phone,FrequentShopper from customer where username= '"+cashierPuttingCustEmail+"'"); //SQL
                        break;

                    case "6":
                        System.out.println("See Vendor Information");
                        System.out.println("Please type in Vendors Name: ");
                        String vendorNameInput = s.nextLine();
                        gameUtil.executeSQLCommand(connection, "select id,Email,name,street,city,state,phone from vendor where name = '"+vendorNameInput + "'" ); //SQL
                        break;
                    case "7":
                        System.out.println("Enroll Customer To Frequent Shopper Programs");
                        System.out.println("Whats the customer email: ");
                        String emailFreq = s.nextLine();
                        gameUtil.executeSQLCommand(connection, "update customer set frequentShopper = 'TRUE' where username = '"+ emailFreq+"'"); //SQL
                        System.out.println("Enrollment Successful");
                        gameUtil.executeSQLCommand(connection, "select * from customer where username = '"+ emailFreq+"'"); //SQL
                        break;
                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}
