package com.example.student_management_sys;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    @FXML
    private void loginButtonClicked() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Đăng nhập thành công!");
            setPrimaryStage(primaryStage);

            try {
                // Load home_view.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home_view.fxml"));
                Parent root = loader.load();
                HomeController controller = loader.getController();


                // close login
                Stage loginStage = (Stage) usernameTextField.getScene().getWindow();
                loginStage.close();

            } catch (IOException e) {
                System.out.println("Lỗi khi load file fxml: " + e.getMessage());
            }

        } else {
            System.out.println("Tên người dùng hoặc mật khẩu không đúng!");
        }
    }
}
