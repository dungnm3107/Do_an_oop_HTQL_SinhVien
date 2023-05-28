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
    private MenuItem exitButton;
    @FXML
    private MenuButton buttonAccount;
    public void setAccountName(String accountName) {
        buttonAccount.setText(accountName);
    }
    private Stage primaryStage;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public TableController tableController;
    public void setTableController(TableController tableController) {
        this.tableController = tableController;
    }



    @FXML
    public void loginButtonClicked() {
        try {
            String username = usernameTextField.getText();
            String password = passwordPasswordField.getText();

            Connection databaseConnection = ConnectionDatabase.getConnection();

            String query = "SELECT Ma_SV, Pass_SV FROM sinhVien WHERE Ma_SV = '" + username + "' AND Pass_SV = '" + password + "'";

            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {

                String nameQuery = "SELECT Name_CN FROM caNhan,sinhVien WHERE sinhVien.CCCD = caNhan.CCCD AND sinhVien.Ma_SV='" + username + "'";
                ResultSet nameResultSet = statement.executeQuery(nameQuery);
                String nameAccount = "";
                if (nameResultSet.next()) {
                    nameAccount = nameResultSet.getString("Name_CN");
                }
                nameResultSet.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("home_view.fxml"));
                Parent root = loader.load();

                LoginController homeController = loader.getController();
                homeController.setAccountName(nameAccount);

                Scene scene = new Scene(root, 1424, 750);

                Stage homeStage = new Stage();
                homeStage.setScene(scene);
                homeStage.setTitle("Hệ thống quản lý sinh viên");

                Stage loginStage = (Stage) homeButton.getScene().getWindow();
                loginStage.close();
                homeStage.show();

                LoginController loginController = loader.getController();
                loginController.setTableController(tableController);

                loginController.tableController.getCoursesFromDatabase();
                loginController.tableController.initialize();

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

    @FXML
    public void exitButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Đăng nhập hệ thống");
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();

            Stage currentStage = (Stage) exitButton.getParentPopup().getOwnerWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Đã xảy ra lỗi. Vui lòng thử lại!");
            alert.showAndWait();
        }
    }

}