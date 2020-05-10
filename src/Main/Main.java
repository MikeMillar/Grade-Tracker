package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    public static Dimension size = new Dimension(1024, 800);
    public static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Grade Tracker");
        primaryStage.setScene(new Scene(root, size.width, size.height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
