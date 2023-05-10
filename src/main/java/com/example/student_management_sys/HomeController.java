package com.example.student_management_sys;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController implements Initializable {
    private Stage stage;

    public HomeController(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home_view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Trang chủ");
            stage.setScene(scene);

            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.setMaxWidth(600);
            stage.setMaxHeight(400);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

