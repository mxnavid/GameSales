import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.util.Scanner;

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
            System.out.println("1: Admin \n2: Vendor \n3: Owner \n4: Customer Sign-up \n5: Customer Login \n0: Exit\n");
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
                        System.out.println("1: View stats \n/back: to go back");
                        input2 = s.nextLine().toLowerCase();
                        switch(input2){
                            case "1":
                                System.out.println("Vendor Menu/Stats");
                                //SQL statements for vendor stats here
                        }
                    }
                    break;
                case "3":
                    System.out.println("Enter username: ");
                    inputtedUser = s.nextLine();
                    System.out.println("Enter password: ");
                    inputtedPassword = s.nextLine();
                    if(true){
                        String input5 = "";
                        while(!input5.equals("9")){
                            System.out.println("Restock Menu\n");
                            System.out.println("1: Filters \n2: Search game \n3: Add to cart \n4: List cart \n5: Buy cart " +
                                    "\n6: Remove game from cart \n7: Customer info \n8: Vendor info \n9: Back");
                            input5 = s.nextLine();
                            switch(input5) {
                                case "1":
                                    String input51 = "";
                                    while(!input51.equals("6")){
                                        System.out.println("Restock Menu/Filters\n");
                                        System.out.println("1: Genre \n2: Price \n3: System \n4: Rating \n5: Year " +
                                                "\n6: Back");
                                        input51 = s.nextLine();
                                        switch(input51) {
                                            case "1":
                                                System.out.println("Restock Menu/Filters/Genre\n");
                                                System.out.println("Enter a genre: ");
                                                String genre = "";
                                                genre = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                            case "2":
                                                System.out.println("Restock Menu/Filters/Price\n");
                                                System.out.println("Enter a price: ");
                                                String price = "";
                                                price = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                            case "3":
                                                System.out.println("Restock Menu/Filters/System\n");
                                                System.out.println("Enter a system: ");
                                                String system = "";
                                                system = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                            case "4":
                                                System.out.println("Restock Menu/Filters/Rating\n");
                                                System.out.println("Enter a rating: ");
                                                String rating = "";
                                                rating = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                            case "5":
                                                System.out.println("Restock Menu/Filters/Year\n");
                                                System.out.println("Enter a year: ");
                                                String year = "";
                                                year = filterScanner.nextLine();
                                                //SQL statements
                                                break;
                                        }
                                    }
                                    break;
                                case "2":
                                    System.out.println("Restock Menu/SearchGame\n");
                                    System.out.println("Enter a game name or part of a game name: ");
                                    String gameName = "";
                                    gameName = s.nextLine();
                                    //SQL statements
                                    break;
                                case "3":
                                    System.out.println("Restock Menu/AddGame\n");
                                    System.out.println("Add game (SKU): ");
                                    String game = "";
                                    game = s.nextLine();
                                    System.out.println("Add quantity: ");
                                    String quantity = "";
                                    quantity = s.nextLine();
                                    //SQL statements
                                    break;
                                case "4":
                                    System.out.println("Restock Menu/CurrentCart\n");
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
                                    System.out.println("Restock Menu/CustomerInfo\n");
                                    //SQL statements to display customer info
                                    break;
                                case "8":
                                    System.out.println("Restock Menu/VendorInfo\n");
                                    //SQL statements to display the vendor info
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
                    break;
                case "5":
                    System.out.println("Enter username: ");
                    inputtedUser = s.nextLine();
                    String usernameCmd = "select username from customer where username = '" + inputtedUser +"'";
                    if (!Utilities.getStringValue(connection, usernameCmd).equals(inputtedUser)){
                        System.out.println("User not found, type in username again");
                        break;
                    }
                    System.out.println("Enter password: ");
                    inputtedPassword = s.nextLine();
                    String passwordCMD = "select password from customer where password ='"+ inputtedPassword +"'";
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
