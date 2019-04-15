import java.util.Scanner;
import java.sql.Connection;

public class adminPanel {

    public static void adminWorks(Scanner s, Connection connection){


        String input1 = "";
        String pswd = "";
        System.out.println("What is your password?");
        pswd = s.nextLine();
        if (pswd.equals("password")){
            while(!input1.equals("/back")){
                System.out.println("Admin Menu\n");
                System.out.println("Input an SQL statement: (/back to go back)");
                input1 = s.nextLine().toLowerCase();

                if (!input1.equals("/back"))gameUtil.executeSQLCommand(connection, input1);
            }
        }
        else{
            System.out.println("Wrong Admin Password, try again!");
        }
    }
}
