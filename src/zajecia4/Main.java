package zajecia4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fourth.fxml"));

        //niepotrzebne -- wczytywanie przez fourth.xml
//        root.setStyle("-fx-background-image: url('resources/background.jpg')");
//        root.setStyle(this.getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("Zajęcia 4");
        Scene scene = new Scene(root, 500, 500);
//        scene.getStylesheets().add("resources/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
