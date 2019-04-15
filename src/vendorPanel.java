import java.sql.Connection;
import java.util.Scanner;

public class vendorPanel {
    /**
     * Completes all the functionality of vendor
     * @param s scanner
     * @param connection connection
     */
    public static void vendorWorks (Scanner s, Connection connection){
        String input2 = "";
        String inputtedUser = "";
        String inputtedPassword = "";
        while(!input2.equals("/back")){
            System.out.println("Vendor Menu\n");
            System.out.println("Enter username: ");
            inputtedUser = s.nextLine();
            String usernameCmd = "select Email from vendor where Email = '" + inputtedUser +"'"; //SQL
            if (!gameUtil.getStringValue(connection, usernameCmd).equals(inputtedUser)){
                System.out.println("Vendor not found, type in username again");
                break;
            }
            System.out.println("Enter password: ");
            inputtedPassword = s.nextLine();
            String passwordCMD = "select password from vendor where password ='"+ inputtedPassword +"'";
            if (gameUtil.getStringValue(connection, passwordCMD).equals(inputtedPassword)){
                while (!input2.equals("/back") ) {
                    System.out.println("1: View stats \n2: View Information\n3: to go back");
                    input2 = s.nextLine().toLowerCase();
                    switch (input2) {
                        case "1":
                            int vendorID = Integer.parseInt(gameUtil.getStringValue(connection, "select id from vendor where email = '"+inputtedUser+"'"));
                            System.out.println("Vendor Sales Statistics sorted by most recent Vandor Sales to Store");
                            gameUtil.executeSQLCommand(connection, "select * from restocking where vendorID='" + vendorID + "' ORDER BY ID DESC"); //SQL
                            break;
                        case "2":
                            System.out.println("View vendor information");
                            gameUtil.executeSQLCommand(connection, "select * from vendor where email= '"+inputtedUser+"'"); //SQL
                            break;
                        case "3":
                            return;
                    }
                }
            }

        }
    }
}
