package com.example.student_management_sys.controller;


import com.example.student_management_sys.model.ConnectionDatabase;
import com.example.student_management_sys.model.DatabaseModel;
import com.example.student_management_sys.model.KetQuaHocTap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class LoginController {

    //Chức năng đăng nhập và đăng xuất
    private String username;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button homeButton;

    private Stage primaryStage;

    @FXML
    private TableView tableView;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void loginButtonClicked() {
        username = usernameTextField.getText();
        try {
            Connection databaseConnection = ConnectionDatabase.getConnection();
            String nameQuery = "SELECT Name_CN FROM caNhan,sinhVien WHERE sinhVien.CCCD = caNhan.CCCD AND sinhVien.Ma_SV='" + username + "'";
            Statement statement = databaseConnection.createStatement();
            ResultSet nameResultSet = statement.executeQuery(nameQuery);
            String nameAccount = "";
            if (nameResultSet.next()) {
                nameAccount = nameResultSet.getString("Name_CN");
            }
            nameResultSet.close();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/home_view.fxml"));
            Parent root = loader.load();
            HomeController homeController = loader.getController();
            homeController.setAccountName(nameAccount);
            homeController.setUsername(username);
            homeController.initBarChart();

            Scene scene = new Scene(root, 1424, 750);


            Stage homeStage = new Stage();
            homeStage.setScene(scene);
            homeStage.setTitle("Hệ thống quản lý sinh viên");

            Stage loginStage = (Stage) homeButton.getScene().getWindow();
            loginStage.close();
            homeStage.show();



        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error access");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}