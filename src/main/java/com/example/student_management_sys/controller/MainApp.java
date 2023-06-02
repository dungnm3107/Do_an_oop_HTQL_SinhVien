package com.example.student_management_sys.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/re-examine.fxml"));
            AnchorPane root = loader.load();

           ReViewController reViewController = loader.getController();
            String mssv="010041";
            reViewController.reViewData(mssv);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading FXML file");
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

