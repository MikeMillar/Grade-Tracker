package Main.MainView;

import Main.Models.Datasource;
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Grade Tracker");
        primaryStage.setScene(new Scene(root, size.width, size.height));
        primaryStage.show();
    }
    
    @Override
    public void init() throws Exception {
//        Datasource.getInstance().load();
    }
    
    @Override
    public void stop() throws Exception {
        Datasource.getInstance().save();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
