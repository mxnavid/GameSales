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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VendorView extends Application{
    private final int SCENE_WIDTH = 500;
    private final int SCENE_HEIGHT = 400;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Image storeLogo = new Image(getClass().getResourceAsStream("gamestop.jpg"));
        ImageView logoView = new ImageView(storeLogo);
        logoView.setFitWidth(SCENE_WIDTH);
        logoView.setFitHeight(150);

        GridPane table = new GridPane();
        table.setMinSize(SCENE_WIDTH, SCENE_HEIGHT);
        table.setPadding(new Insets(10, 10, 10,10));
        table.setHgap(40);
        table.setVgap(30);
        table.setAlignment(Pos.TOP_CENTER);

        Label col1 = new Label("number");
        col1.setAlignment(Pos.CENTER);
        Label col2 = new Label("name");
        col2.setAlignment(Pos.CENTER);
        Label col3 = new Label("publisher");
        col3.setAlignment(Pos.CENTER);
        Label col4 = new Label("price");
        col4.setAlignment(Pos.CENTER);
        Label col5 = new Label("quantity");
        col4.setAlignment(Pos.CENTER);
        table.add(col1, 0, 0);
        table.add(col2, 1, 0);
        table.add(col3, 2, 0);
        table.add(col4, 3, 0);
        table.add(col5, 4, 0);
        int counter = 1;
        for(int j = 0; j < 5; j++){
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

        VBox layout = new VBox();
        layout.setSpacing(5);
        layout.getChildren().add(logoView);
        layout.getChildren().add(table);

        Scene purchaseView = new Scene(layout);
        primaryStage.setTitle("Vendor View");
        primaryStage.setScene(purchaseView);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
