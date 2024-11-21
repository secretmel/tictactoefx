package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("tictactoe"), 640, 480); // Ensure the FXML file exists in resources/com/example/
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/" + fxml + ".fxml"));
        return fxmlLoader.load(); // Ensure to load and return as Parent
    }

    public static void main(String[] args) {
        launch();
    }
}
