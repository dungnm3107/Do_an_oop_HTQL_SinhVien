package com.example.student_management_sys;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button homeButton;
    @FXML
    private MenuButton buttonAccount;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML
    private void loginButtonClicked() {
        try {
            String username = usernameTextField.getText();
            String password = passwordPasswordField.getText();

            Connection databaseConnection = ConnectionDatabase.getConnection();

            String query = "SELECT Login_Name, pass FROM admin_Login WHERE admin_Login.Login_Name = '" + username + "' AND admin_Login.pass = '" + password + "'";

            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("home_view.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root, 1424, 750);

                Stage homeStage = new Stage();
                homeStage.setScene(scene);
                homeStage.setTitle("Home View");

                Stage loginStage = (Stage) homeButton.getScene().getWindow();
                loginStage.close();
                homeStage.show();


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi đăng nhập");
                alert.setHeaderText(null);
                alert.setContentText("Sai tên đăng nhập hoặc mật khẩu. Vui lòng thử lại!");
                alert.showAndWait();
            }

            resultSet.close();
            statement.close();
            databaseConnection.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Đã xảy ra lỗi. Vui lòng thử lại!");
            alert.showAndWait();
        }
    }

}