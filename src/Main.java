import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.*;

public class Main extends Application {

    private static final File databaseFile = new File("database/gamesales.mv.db");
    private static final File databasePath = new File("database/gamesales.sql");

    private static Main driver;
    private Connection connection;

    private String username = "admin";
    private String passwords = "password";



    private static String input = "-2";
    private static Scanner s = new Scanner(System.in);
    private static Scanner filterScanner = new Scanner(System.in);
    private static String user;
    private static String password;
    private static String inputtedUser;
    private static String inputtedPassword;


    public void init(String username, String passwords) {
        this.username = username;
        this.passwords = passwords;

        if (databaseFile.exists()) {
            connection = Utilities.connect(connection, databaseFile.getAbsolutePath(), username, passwords);
//            Utilities.fillDatabase(connection);
        } else {
////            Utilities.makeNewDatabase(connection, databasePath.getAbsolutePath(), username, password);
        }

//        Utilities.printCusomterTable(connection);

    }


    @Override
    public void start(Stage primaryStage) throws Exception{

//        System.out.println("I am test");

        // initialize database connection elements
        init("admin", "password");

        driver = this;

        System.out.println("Welcome to GameStop! Login or Create an Account. (-1 to exit)");
        while(!(input.equals("0"))){
            System.out.println("Main Menu\n");
            System.out.println("1: Admin \n2: Vendor \n3: Store  \n4: Customer Sign-up \n5: Customer Login \n 0: Exit\n");
            input = s.nextLine();
            switch(input){
                case "1":
                    String input1 = "";
                    String pswd = "";
                    System.out.println("What is your password?");
                    pswd = s.nextLine();
                    if (pswd.equals("password")){
                        while(!input1.equals("/back")){
                            System.out.println("Admin Menu\n");
                            System.out.println("Input an SQL statement: (/back to go back)");
                            input1 = s.nextLine().toLowerCase();

                            if (!input1.equals("/back"))Utilities.executeSQLCommand(connection, input1);
                        }
                    }
                    else{
                        System.out.println("Wrong Admin Password, try again!");
                    }
                    break;



                case "2":

                    String input2 = "";
                    while(!input2.equals("/back")){
                        System.out.println("Vendor Menu\n");
                        System.out.println("Enter username: ");
                        inputtedUser = s.nextLine();
                        String usernameCmd = "select Email from vendor where Email = '" + inputtedUser +"'";
                        if (!Utilities.getStringValue(connection, usernameCmd).equals(inputtedUser)){
                            System.out.println("Vendor not found, type in username again");
                            break;
                        }
                        System.out.println("Enter password: ");
                        inputtedPassword = s.nextLine();
                        String passwordCMD = "select password from vendor where password ='"+ inputtedPassword +"'";
                        if (Utilities.getStringValue(connection, passwordCMD).equals(inputtedPassword)){
                            System.out.println("1: View stats \n2: View Information\n/back: to go back");
                            while (input2!= "/back") {
                                input2 = s.nextLine().toLowerCase();
                                switch (input2) {
                                    case "1":
                                        int vendorID = Integer.parseInt(Utilities.getStringValue(connection, "select id from vendor where email = '"+inputtedUser+"'"));

                                        System.out.println("Vendor Sales Statistics sorted by most recent Vandor Sales to Store");
                                        Utilities.executeSQLCommand(connection, "select * from restocking where vendorID='" + vendorID + "' ORDER BY ID DESC");
                                        break;
                                    case "2":
                                        System.out.println("View vendor information");
                                        Utilities.executeSQLCommand(connection, "select * from vendor where email= '"+inputtedUser+"'");
                                        break;
                                }
                            }
                        }

                    }
                    break;


                case "3":
                    System.out.println("Enter your store login email: ");
                    inputtedUser = s.nextLine();
                    String usernameCmd = "select Email from store where Email = '" + inputtedUser +"'";
                    if (!Utilities.getStringValue(connection, usernameCmd).equals(inputtedUser)){
                        System.out.println("Store Account not found, type in the credentials again");
                        break;
                    }
                    System.out.println("Enter password: ");
                    inputtedPassword = s.nextLine();
                    String passwordCMD = "select password from store where password ='"+ inputtedPassword +"'";

                    if(Utilities.getStringValue(connection, passwordCMD).equals(inputtedPassword)){
                        String input5 = "";
                        while(!input5.equals("8")){
                            System.out.println("1: Get Game Info for Restocking \n2: Restocking \n3: See your Inventory \n4: Checkout a Customer \n5: See Customer Information \n6: Vendor Info \n7: Enroll To Frequent Shopper Program \n8: Back");
                            input5 = s.nextLine();
                            switch(input5) {
                                case "1":
                                    String input51 = "";
                                    while(!input51.equals("8")){
                                        System.out.println("Restock Menu/Filters\n");
                                        System.out.println("1: Search for Specific Game \n2: Genre \n3: Price \n4: Platform \n5: Ranking \n6: Year Ascending Order \n7: Search by SKU" +
                                                "\n8: Back ");
                                        input51 = s.nextLine();
                                        switch(input51) {
                                            case "1":
                                                System.out.println("Searching for a Specific Game by Name");
                                                System.out.println("Enter the Game Name: ");
                                                String gameNameInput = s.nextLine();
                                                String gameName = "select * from game where name = '" + gameNameInput +"'";
                                                Utilities.executeSQLCommand(connection, gameName);
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
                                                Utilities.filterTable(connection, "Game", "Genre", genreInput);

                                                break;
                                            case "3":
                                                System.out.println("Restock Menu/Filters/Price Range\n");
                                                System.out.println("Enter a Price Lower Limit Integers: ");
                                                String priceLow = filterScanner.nextLine();

                                                System.out.println("Enter Price High Limit Integers: ");
                                                String priceHigh = filterScanner.nextLine();
                                                String priceCommand = "select * from game where price between " + priceLow+".00 and "+ priceHigh+".00";
                                                Utilities.executeSQLCommand(connection, priceCommand);
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
                                                Utilities.filterTable(connection, "Game", "Platform", platform);
                                                break;
                                            case "5":
                                                System.out.println("Restock Menu/Filters/Ranking in Ascending Order\n");
                                                Utilities.executeSQLCommand(connection, "select * from game order by RANKING+0");
                                                //SQL statements
                                                break;
                                            case "6":
                                                System.out.println("Restock Menu/Filters/Year\n");
                                                System.out.println("Enter a year: ");
                                                System.out.println("Enter the Starting");
                                                String yearBeg = filterScanner.nextLine();

                                                System.out.println("Enter Ending Year:  ");
                                                String yearEnd = filterScanner.nextLine();
                                                String yearCommand = "select * from game where year between " + yearBeg+" and "+ yearEnd;
                                                Utilities.executeSQLCommand(connection, yearCommand);
                                                break;
                                            case "7":
                                                System.out.println("Search by SKU");
                                                System.out.println("Enter SKU of the Game: ");
                                                String skuInput = s.nextLine();
                                                String skuCommand = "select * from game where sku = '" + skuInput+"'";
                                                Utilities.executeSQLCommand(connection, skuCommand);
                                                break;

                                        }
                                    }
                                    break;

                                case "2":
                                    long restockingID = System.currentTimeMillis();

                                    System.out.println("Restocking\n");
                                    String forGettingid = "select storeid from store where email= '" + inputtedUser + "'";
                                    int storeID = Integer.parseInt(Utilities.getStringValue(connection, forGettingid));

                                    System.out.println("Enter SKU Of the Game you want to restock:");
                                    int skuInteger = Integer.parseInt(s.nextLine());

                                    System.out.println("Please enter the quantity you want to restock: ");
                                    int quantityEntered = Integer.parseInt(s.nextLine());


                                    String vendorName = Utilities.getStringValue(connection, "select vendor from game where sku= '"+skuInteger+"'");
                                    int vendorID = Integer.parseInt(Utilities.getStringValue(connection, "select id from vendor where name = '"+vendorName + "'"));


                                    String restockingCommand = "insert into inventory values('"+ storeID +"','"+skuInteger+ "','" +quantityEntered+"')";
                                    Utilities.executeSQLCommand(connection, restockingCommand);
                                    String commandRestock = "insert into restocking values('"+restockingID+"','"+vendorID+"','"+storeID+"','"+skuInteger+"','"+vendorName+"','"+quantityEntered+"')";

                                    Utilities.executeSQLCommand(connection, commandRestock);

                                    System.out.println("Restocking Successful");
                                    Utilities.executeSQLCommand(connection, "select * from inventory where sku = '"+skuInteger+"' and storeid = '" + storeID +"'" );

                                    break;


                                case "3":
                                    System.out.println("See your Inventory\n");
                                    forGettingid = "select storeid from store where email= '" + inputtedUser + "'";
                                    storeID = Integer.parseInt(Utilities.getStringValue(connection, forGettingid));
                                    String checkInventory = "select * from inventory where storeid = '"+storeID+"'";
                                    Utilities.executeSQLCommand(connection, checkInventory);
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
                                    storeID = Integer.parseInt(Utilities.getStringValue(connection, forGettingid));
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
                                        Utilities.executeSQLCommand(connection, addCart);
                                        double tempPrice = Double.parseDouble(Utilities.getStringValue(connection, "select price from game where sku='"+enteredSKU+"'"));
                                        Price = Price + (tempPrice*enteredQuantity);
                                        quantites.add(Integer.parseInt(Utilities.getStringValue(connection, "select quantity from inventory where sku = '"+enteredSKU+"' and storeid = '" + storeID +"'")));
                                        subtractedQ.add(enteredQuantity);
                                        sku.add(enteredSKU);
                                        System.out.println("Press any key to continue or type done if no more game need to be added");
                                        inputq = s.nextLine();
                                    }
                                    if (Utilities.getStringValue(connection, "select frequentShopper from customer where username='"+customerEmail+"'").equals("TRUE")){
                                        Price = Price*0.8;
                                    }
                                    String finalSale = "insert into sale values('"+salesID+"','"+customerEmail +"','"+storeID+"','"+Price+"')";
                                    Utilities.executeSQLCommand(connection, finalSale);

                                    for (int counter = 0; counter < quantites.size(); counter++){

                                        String updateQCommand = "update inventory set quantity='"+(quantites.get(counter)-subtractedQ.get(counter))+"' where sku= '"+ sku.get(counter)+"' and storeid = '"+ storeID +"'";
                                        Utilities.executeSQLCommand(connection, updateQCommand);
                                    }

                                    System.out.println("Sales Successful, confirmation number: " + salesID);
                                    break;




                                case "5":
                                    System.out.println("See Customer Information");
                                    System.out.println("Please type in Customer Email: ");
                                    String cashierPuttingCustEmail = s.nextLine();
                                    Utilities.executeSQLCommand(connection,"select * from customer where username= '"+cashierPuttingCustEmail+"'");
                                    break;

                                case "6":
                                    System.out.println("See Vendor Information");
                                    System.out.println("Please type in Vendors Name: ");
                                    String vendorNameInput = s.nextLine();
                                    Utilities.executeSQLCommand(connection, "select * from vendor where name = '"+vendorNameInput + "'" );
                                    break;
                                case "7":
                                    System.out.println("Enroll Customer To Frequent Shopper Programs");
                                    System.out.println("Whats the customer email: ");
                                    String emailFreq = s.nextLine();
                                    Utilities.executeSQLCommand(connection, "update customer set frequentShopper = 'TRUE' where username = '"+ emailFreq+"'");
                                    System.out.println("Enrollment Successful");
                                    Utilities.executeSQLCommand(connection, "select * from customer where username = '"+ emailFreq+"'");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;


                case "4":
                    System.out.println("Enter new username: ");
                    user = "'" + s.nextLine() + "'";
                    System.out.println("Enter new password: ");
                    password = "'"+ s.nextLine() + "'";
                    int customerCounter = Utilities.counter(connection, "SELECT COUNT(*) AS rowCount FROM customer");
                    customerCounter = customerCounter+1;
                    String customerSignUp = "INSERT INTO CUSTOMER VALUES(" + customerCounter + ", 'null', " + user + ", " + password + ", 'null', 'null','null', 'null', 'false')";
                    Utilities.executeSQLCommand(connection, customerSignUp);
                    System.out.println("Successfully registered the customer");
                    break;

                case "5":
                    System.out.println("Enter username: ");
                    inputtedUser = s.nextLine();
                    usernameCmd = "select username from customer where username = '" + inputtedUser +"'";
                    if (!Utilities.getStringValue(connection, usernameCmd).equals(inputtedUser)){
                        System.out.println("User not found, type in username again");
                        break;
                    }
                    System.out.println("Enter password: ");
                    inputtedPassword = s.nextLine();
                    passwordCMD = "select password from customer where password ='"+ inputtedPassword +"'";
                    if (Utilities.getStringValue(connection, passwordCMD).equals(inputtedPassword)){
                        String input5 = "";
                        while(!input5.equals("8")){
                            System.out.println("Customer Menu\n");
                            System.out.println("1: Filters \n2: Search game \n3: Add to cart \n4: List cart \n5: Buy cart " +
                                    "\n6: Remove game from cart \n7: Customer info \n8: Back");
                            input5 = s.nextLine();
                            switch(input5) {
                                case "1":
                                    String input51 = "";
                                    while(!input51.equals("6")){
                                        System.out.println("Customer Menu/Filters\n");
                                        System.out.println("1: Genre \n2: Price \n3: System \n4: Rating \n5: Year " +
                                                "\n6: Back");
                                        input51 = s.nextLine();
                                        switch(input51) {
                                            case "1":
                                                System.out.println("Customer Menu/Filters/Genre\n");
                                                System.out.println("Enter a genre: ");
                                                String genre = "";
                                                genre = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                            case "2":
                                                System.out.println("Customer Menu/Filters/Price\n");
                                                System.out.println("Enter a price: ");
                                                String price = "";
                                                price = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                            case "3":
                                                System.out.println("Customer Menu/Filters/System\n");
                                                System.out.println("Enter a system: ");
                                                String system = "";
                                                system = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                            case "4":
                                                System.out.println("Customer Menu/Filters/Rating\n");
                                                System.out.println("Enter a rating: ");
                                                String rating = "";
                                                rating = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                            case "5":
                                                System.out.println("Customer Menu/Filters/Year\n");
                                                System.out.println("Enter a year: ");
                                                String year = "";
                                                year = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                        }
                                    }
                                    break;
                                case "2":
                                    System.out.println("Customer Menu/SearchGame\n");
                                    System.out.println("Enter a game name or part of a game name: ");
                                    String gameName = "";
                                    gameName = s.nextLine();
                                    //SQL statements
                                    break;
                                case "3":
                                    System.out.println("Customer Menu/AddGame\n");
                                    System.out.println("Add game (SKU): ");
                                    String game = "";
                                    game = s.nextLine();
                                    System.out.println("Add quantity: ");
                                    String quantity = "";
                                    quantity = s.nextLine();
                                    //SQL statements
                                    break;
                                case "4":
                                    System.out.println("Customer Menu/CurrentCart\n");
                                    //SQL statements to display cart
                                    break;
                                case "5":
                                    String venId = "";
                                    System.out.println("Enter vendor ID: ");
                                    venId = s.nextLine();
                                    String venName = "";
                                    System.out.println("Enter vendor name: ");
                                    venName = s.nextLine();
                                    //SQL statements to buy cart
                                    break;
                                case "6":
                                    String remGameName = "";
                                    System.out.println("Enter the name of the game to be removed: ");
                                    remGameName = s.nextLine();
                                    String remGameQuantity = "";
                                    System.out.println("Enter the quantity: ");
                                    remGameQuantity = s.nextLine();
                                    //SQL statements to remove game
                                    break;
                                case "7":
                                    System.out.println("Customer Menu/AccountInfo\n");
                                    //SQL statements to display customer info
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
            }
        }


    }

    public static void main(String[] args) {
        launch(args);
    }

    // getters and setters
    public static Main getDriver() { return driver; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
