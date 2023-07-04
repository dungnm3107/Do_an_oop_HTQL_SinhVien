package com.example.student_management_sys.controller.SinhVien;

import com.example.student_management_sys.controller.Admin.ControllerAdmin;
import com.example.student_management_sys.model.DB.ConnectionDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.prefs.Preferences;

public class LoginController {

    public String username;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button homeButton;
    @FXML
    private MenuButton buttonAccount;

    private Stage primaryStage;

    public void setAccountName(String accountName) {
        buttonAccount.setText(accountName);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void loginButtonClicked() throws Exception {
        username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        if ((username.equals("admin")) && (password.equals("admin"))) {
            ControllerAdmin ad = new ControllerAdmin();
            ad.loginAdmin(username, password, "/com/example/student_management_sys/view/Admin/Student.fxml");

            Stage loginStage = (Stage) homeButton.getScene().getWindow();
            loginStage.close();
        } else {
            try {

                Connection databaseConnection = ConnectionDatabase.getConnection();
                String query = "SELECT Ma_SV, Pass_SV FROM sinhVien WHERE Ma_SV = '" + username + "' AND Pass_SV = '" + password + "'";

                Statement statement = databaseConnection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                Preferences preferences = Preferences.userNodeForPackage(Controller.class);
                preferences.put("MSSV", username);
                if (resultSet.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/home_view.fxml"));
                    Parent root = loader.load();
                    HomeController homeController = loader.getController();
                    homeController.setButtonAccount();
                    homeController.showInforHomeView(username);
                    homeController.initBarChart();
                    homeController.setLichHoc();

                    Scene scene = new Scene(root, 1424, 750);
                    Stage homeStage = new Stage();
                    homeStage.setScene(scene);
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
}
