import java.io.File;
import java.sql.Connection;
import java.util.Scanner;
import java.util.*;

public class Main {

    private static final File databaseFile = new File("database/gamesales.mv.db");
    private static final File databasePath = new File("database/gamesales.sql");

    private static Connection connection;

    private static String input = "-2";
    private static Scanner s = new Scanner(System.in);
    private static String user;
    private static String password;
    private static String inputtedUser;
    private static String inputtedPassword;


    public static void init() {
        connection = gameUtil.connect(connection, databaseFile.getAbsolutePath(), "admin", "password");
        //gameUtil.printCusomterTable(connection);

    }

    public static void main(String[] args) {

//        System.out.println("I am test");

        // initialize database connection elements
        init();

        System.out.println("Welcome to GameStop! Login or Create an Account. (-1 to exit)");
        while(!(input.equals("0"))){
            System.out.println("Main Menu\n");
            System.out.println("1: Admin Panel \n2: Vendor Panel \n3: Store Panel \n4: Customer Sign-up \n5: Customer Panel \n0: Exit");
            System.out.println("Enter an option: ");
            input = s.nextLine();
            switch(input){
                case "1":
                    adminPanel.adminWorks(s,connection);
                    break;

                case "2":
                    vendorPanel.vendorWorks(s, connection);
                    break;

                case "3":
                    storePanel.storeWorks(s, connection);
                    break;


                case "4":
                   customerPanel.createNewAccount(s, connection);
                    break;

                case "5":
                    customerPanel.customerWorks(s, connection);
                    break;
                case "0":
                    return;

            }
        }


    }
}
