package com.example.student_management_sys.controller;


import com.example.student_management_sys.model.ConnectionDatabase;
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
    private TextField tfclass;

    @FXML
    private TextField tfgender;

    @FXML
    private TextField tfmssv;

    @FXML
    private TextField tfname;

    @FXML
    private TextField tfnganh;
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
    public void inforStudent(String maSV){
        try {
            Connection databaseConnection = ConnectionDatabase.getConnection();
            String query = "SELECT sv.Ma_SV, cn.Name_CN, cn.Gender, sv.Name_Lop, nh.Name_Nganh AS Nganh " +
                    "FROM sinhVien AS sv " +
                    "INNER JOIN caNhan AS cn ON sv.CCCD = cn.CCCD " +
                    "INNER JOIN chuyenNganh AS cnn ON sv.Ma_ChuyenNganh = cnn.Ma_ChuyenNganh " +
                    "INNER JOIN nganhHoc AS nh ON cnn.Ma_Nganh = nh.Ma_Nganh " +
                    "WHERE sv.Ma_SV ='" + maSV +"'";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                String mssv = resultSet.getString("Ma_SV");
                String name = resultSet.getString("Name_CN");
                String gender = resultSet.getString("Gender");
                String lop = resultSet.getString("Name_Lop");
                String nganh = resultSet.getString("Nganh");

                tfmssv.setText(mssv);
                tfname.setText(name);
                tfgender.setText(gender);
                tfclass.setText(lop);
                tfnganh.setText(nganh);
            } else {
                System.out.println("Not Found");// Xử lý khi không tìm thấy sinh viên với mã sinh viên đã cho
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error acces");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void loginButtonClicked() {
        try {
            username = usernameTextField.getText();
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

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/home_view.fxml"));

                Parent root = loader.load();

                LoginController homeController = loader.getController();
                homeController.setAccountName(nameAccount);
                homeController.inforStudent(username);
                Scene scene = new Scene(root, 1424, 750);

                Stage homeStage = new Stage();
                homeStage.setScene(scene);
                homeStage.setTitle("Hệ thống quản lý sinh viên");

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

    @FXML
    public void exitButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login_view.fxml"));
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