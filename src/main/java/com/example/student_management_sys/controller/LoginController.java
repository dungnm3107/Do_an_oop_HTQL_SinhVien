package com.example.student_management_sys.controller;


import com.example.student_management_sys.model.ConnectionDatabase;

import com.example.student_management_sys.model.DatabaseModel;
import com.example.student_management_sys.model.Student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    //Chức năng đăng nhập và đăng xuất
    private  String username;

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
    @FXML
    private MenuButton menuButton;


    @FXML
    private TableView tableView;

    public void setAccountName(String accountName) {
        buttonAccount.setText(accountName);
    }

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public String getUsername() {
        return username;
    }
    @FXML
    public void loginButtonClicked() {

        try {
            Connection databaseConnection = ConnectionDatabase.getConnection();
            String dataQuery = "SELECT mh.Ma_MH, mh.Name_MH, lh.Name_Lop, mh.So_Tin, mh.Loai_HP\n" +
                    "FROM monHoc mh\n" +
                    "JOIN dangKyMonHoc dkmh ON mh.Ma_MH = dkmh.Ma_MH\n" +
                    "JOIN sinhVien sv ON sv.Ma_SV = dkmh.Ma_SV\n" +
                    "JOIN hocKy hk ON hk.Ma_HK = dkmh.Ma_HK\n" +
                    "JOIN lopHoc lh ON sv.Name_Lop = lh.Name_Lop\n" +
                    "WHERE sv.Ma_SV = '" + username + "' AND hk.Ma_HK = 'HK01-2023';";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(dataQuery);
            tableView.getItems().clear();
            if (resultSet.next()) {


                String nameQuery = "SELECT Name_CN FROM caNhan,sinhVien WHERE sinhVien.CCCD = caNhan.CCCD AND sinhVien.Ma_SV='" + username + "'";
                ResultSet nameResultSet = statement.executeQuery(nameQuery);
                String nameAccount = "";
                if (nameResultSet.next()) {
                    nameAccount = nameResultSet.getString("Name_CN");
                }
                nameResultSet.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/home_view.fxml"));

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

            } else {
                System.out.println("Not Found");
            }
            resultSet.close();
            statement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error access");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        @FXML
        public void exitButtonClicked () {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/login_view.fxml"));
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
