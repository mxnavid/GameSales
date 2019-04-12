package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;

public class Main extends Application {

    private static final File databaseFile = new File("database/gamesales.mv.db");
    private static final File databasePath = new File("database/gamesales.sql");

    private static Main driver;
    private Connection connection;

    private String username = "admin";
    private String password = "password";

    public void init(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("init working");

        if (databaseFile.exists()) {
            connection = Utilities.connect(connection, databaseFile.getAbsolutePath(), username, password);
//            Utilities.fillDatabase(connection);
            System.out.println("exists");
        } else {
//            System.out.println("Else statement is getting executed");
////            Utilities.makeNewDatabase(connection, databasePath.getAbsolutePath(), username, password);
            System.out.println("doesn't exist");
        }

//        Utilities.printCusomterTable(connection);

        //SwingUtilities.invokeLater(() -> {
            //viewManager = new ViewManager();
            //viewManager.setupUserSelect();
        //});
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World, its works");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
        System.out.println("I am test");

        // initialize database connection elements
        init("admin", "password");

        driver = this;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // getters and setters
    public static Main getDriver() { return driver; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
