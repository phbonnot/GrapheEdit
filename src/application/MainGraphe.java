package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
public class MainGraphe extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader();
            URL url = new File("src/gui/GUIView.fxml").
                    toURI().toURL();
            loader.setLocation(url);
            System.out.println(loader.getLocation());
            BorderPane root=loader.load();
            Scene scene = new Scene(root,600,400);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
