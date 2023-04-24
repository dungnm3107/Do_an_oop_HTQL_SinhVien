package com.example.student_management_sys;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;



    @FXML
    private void loginButtonClicked() {

        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Đăng nhập thành công!");
        } else {
            System.out.println("Tên người dùng hoặc mật khẩu không đúng!");
        }
    }

}
