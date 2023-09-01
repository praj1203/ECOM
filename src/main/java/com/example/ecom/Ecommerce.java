package com.example.ecom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application {
     UserInterFace UI = new UserInterFace();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(UI.createContent());
        stage.setTitle("ECOM");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}