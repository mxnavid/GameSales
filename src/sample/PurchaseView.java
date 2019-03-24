package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class PurchaseView extends Application{
    private final int SCENE_WIDTH = 500;
    private final int SCENE_HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane grid = new GridPane();
        grid.setMinSize(SCENE_WIDTH, SCENE_HEIGHT);
        grid.setPadding(new Insets(10, 10, 10,10));
        grid.setHgap(25);
        grid.setVgap(25);
        grid.setAlignment(Pos.CENTER);

        Image cover = new Image(getClass().getResourceAsStream("marioCover.jpg"));
        Image logo = new Image(getClass().getResourceAsStream("marioLogo.jpg"));
        ImageView coverView = new ImageView(cover);
        coverView.setFitHeight(220);
        coverView.setFitWidth(150);
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(150);
        logoView.setFitWidth(150);

        Button homeButton = new Button("Home");
        homeButton.setPrefSize(150, 150);
        Button buyButton = new Button("Buy");
        buyButton.setPrefSize(150, 150);

        TextField userName = new TextField("username");
        TextField password = new TextField("password");
        VBox userInfo = new VBox();
        userInfo.setSpacing(10);
        userInfo.getChildren().add(userName);
        userInfo.getChildren().add(password);

        Text gameName = new Text("Name of game");
        Text publisher = new Text("Name of publisher");
        Text price = new Text("price");
        Text location = new Text("location");
        VBox gameInfo = new VBox();
        gameInfo.setSpacing(10);
        gameInfo.getChildren().add(gameName);
        gameInfo.getChildren().add(publisher);
        gameInfo.getChildren().add(price);
        gameInfo.getChildren().add(location);

        grid.add(logoView, 0, 0);
        grid.add(homeButton, 2, 0);
        grid.add(gameInfo, 1, 0);
        grid.add(coverView, 0, 1);
        grid.add(userInfo, 2, 1);
        grid.add(buyButton, 1, 1);

        Scene purchaseView = new Scene(grid);
        primaryStage.setTitle("Purchase View");
        primaryStage.setScene(purchaseView);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
