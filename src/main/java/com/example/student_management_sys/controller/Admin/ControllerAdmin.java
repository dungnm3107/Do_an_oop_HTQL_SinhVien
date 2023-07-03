package com.example.student_management_sys.controller.Admin;

import com.example.student_management_sys.controller.SinhVien.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerAdmin {
    @FXML
    private MenuButton buttonAccount;
    @FXML
    private MenuItem exitButton;
    @FXML
    private Button btn_Diem;
    @FXML
    private Button btn_Student;

    @FXML
    private Button btn_Subjects;

    @FXML
    private Button btn_Teacher;
    public void loginAdmin(String username, String password, String path) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1424, 750);
        Stage homeStage = new Stage();
        homeStage.setScene(scene);
        homeStage.show();
    }
    public FXMLLoader OpenNewFXMLFile(String path, Button btn) throws RuntimeException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(path));
        try {
            Stage currentStage = (Stage) btn.getScene().getWindow();
            Parent root1 = loader1.load();
            ControllerAdmin controllerAdmin = loader1.getController();
            Scene scene = new Scene(root1, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader1;
    }

    @FXML
    public void btn_StudentClicked() {
        FXMLLoader loader =  OpenNewFXMLFile("/com/example/student_management_sys/view/Admin/Student.fxml", btn_Student);
        SinhVien sv = loader.getController();
        sv.setTableView();
    }
    @FXML
    public void btn_DiemClicked() {
        FXMLLoader loader =   OpenNewFXMLFile("/com/example/student_management_sys/view/Admin/Admin_UpDiem.fxml", btn_Diem);
        AdminUpSubjectsController ad = loader.getController();
        ad.setTableView();
    }
    @FXML
    public void btn_SubjectsClicked(){
        FXMLLoader loader =   OpenNewFXMLFile("/com/example/student_management_sys/view/Admin/monhoc.fxml", btn_Subjects);
        MonHoc ad = loader.getController();
        ad.setMHView();
    }
    @FXML
    public void btn_TeacherClicked(){
        FXMLLoader loader =   OpenNewFXMLFile("/com/example/student_management_sys/view/Admin/teacher.fxml", btn_Teacher);
        GiaoVienController giaoVienController = loader.getController();
        giaoVienController.initialize();
    }
    public void exitButtonClicked() {
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
