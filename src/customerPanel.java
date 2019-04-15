import java.sql.Connection;
import java.util.Scanner;

public class customerPanel {
    public static void createNewAccount (Scanner s, Connection connection){
        System.out.println("Create New Customer Account");
        System.out.println("Enter new username: ");
        String user = "'" + s.nextLine() + "'";
        System.out.println("Enter new password: ");
        String password = "'"+ s.nextLine() + "'";
        int customerCounter = gameUtil.counter(connection, "SELECT COUNT(*) AS rowCount FROM customer");
        customerCounter = customerCounter+1;
        String customerSignUp = "INSERT INTO CUSTOMER VALUES(" + customerCounter + ", 'null', " + user + ", " + password + ", 'null', 'null','null', 'null', 'false')";
        gameUtil.executeSQLCommand(connection, customerSignUp);
        System.out.println("Successfully registered the customer");
    }



    public static void customerWorks (Scanner s, Connection connection){
        System.out.println("Welcome to Customer Panel");
        System.out.println("Enter username: ");
        String inputtedUser = s.nextLine();
        String usernameCmd = "select username from customer where username = '" + inputtedUser +"'";
        if (!gameUtil.getStringValue(connection, usernameCmd).equals(inputtedUser)){
            System.out.println("User not found, type in username again");
            return;
        }
        System.out.println("Enter password: ");
        String inputtedPassword = s.nextLine();
        String passwordCMD = "select password from customer where password ='"+ inputtedPassword +"'";
        if (gameUtil.getStringValue(connection, passwordCMD).equals(inputtedPassword)){
            String input5 = "";
            while(!input5.equals("5")){
                System.out.println("Customer Menu\n");
                System.out.println("1: Browse Games \n2: View Account Information \n3: Change Account Information \n4: Buy Game \n5: Back");
                input5 = s.nextLine();
                switch(input5) {
                    case "1":
                        System.out.println("Browse Games");











                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}


