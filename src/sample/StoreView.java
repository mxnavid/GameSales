package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StoreView extends Application{
    private final int SCENE_WIDTH = 500;
    private final int SCENE_HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) throws Exception{
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 2;\n" +
                "-fx-border-width: 1;\n" +
                "-fx-border-style: solid;\n";

        VBox layout = new VBox();
        layout.setMaxSize(SCENE_WIDTH, SCENE_HEIGHT);
        layout.setSpacing(10);

        VBox key = new VBox();
        key.setSpacing(10);
        HBox horizKey = new HBox();
        horizKey.setSpacing(162);
        Button games = new Button("Games");
        Button stores = new Button("Stores");
        Button customers = new Button("Customers");
        horizKey.getChildren().add(games);
        horizKey.getChildren().add(stores);
        horizKey.getChildren().add(customers);
        key.getChildren().add(horizKey);

        GridPane table = new GridPane();

        table.setPadding(new Insets(10, 10, 10,10));
        table.setHgap(20);
        table.setVgap(18);
        table.setAlignment(Pos.CENTER);
        Label col1 = new Label("number");
        col1.setAlignment(Pos.CENTER);
        Label col2 = new Label("name");
        col2.setAlignment(Pos.CENTER);
        Label col3 = new Label("publisher");
        col3.setAlignment(Pos.CENTER);
        Label col4 = new Label("price");
        col4.setAlignment(Pos.CENTER);
        table.add(col1, 0, 0);
        table.add(col2, 1, 0);
        table.add(col3, 2, 0);
        table.add(col4, 3, 0);
        int counter = 1;
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 5; i++){
                if(j == 1) {
                    table.add(new Button("Test: " + counter), j, i + 1);
                    counter++;
                } else {
                    table.add(new Label("Test: " + counter), j, i + 1);
                    counter++;
                }
            }
        }

        layout.getChildren().add(key);
        layout.getChildren().add(table);

        Scene purchaseView = new Scene(layout);
        primaryStage.setTitle("Store View");
        primaryStage.setScene(purchaseView);
        primaryStage.show();
    }

    public void resizeTable(){

    }

    public void gamesStoreTable(){

    }

    public void storeStoretabel(){

    }

    public void customerStoreTable(){

    }

    public static void main(String[] args) {
        launch(args);
    }
}
