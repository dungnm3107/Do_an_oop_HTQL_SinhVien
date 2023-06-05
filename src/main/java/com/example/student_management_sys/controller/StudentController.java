package com.example.student_management_sys.controller;

import com.example.student_management_sys.model.DatabaseModel;
import com.example.student_management_sys.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StudentController{
    @FXML
    private TextField tfMaSV;
    @FXML
    private TextField tfHoTen;
    @FXML
    private TextField tfGioiTinh;
    @FXML
    private TextField tfStatus;
    @FXML
    private TextField tfAdress;
    @FXML
    private TextField tfClassQL;
    @FXML
    private TextField tfLoaiDT;
    @FXML
    private TextField tfBirthDay;
    @FXML
    private TextField tfChuyenNganh;
    @FXML
    private TextField tfSDT;
    @FXML
    private TextField tfVaoTruong;


    @FXML
    private Button btn_dashboard;
    @FXML
    private Button btn_learningOutcome;
    @FXML
    private Button btn_courseEnrolment;
    @FXML
    private Button btn_reExamine;
    @FXML
    private Button btn_schedule;
    public void showInferStudent(String maSV){
        try {
            DatabaseModel databaseModel = new DatabaseModel();
            Student std = databaseModel.getInformation(maSV);
            tfMaSV.setText(std.getMSSV());
            tfHoTen.setText(std.getHoTen());
            tfGioiTinh.setText(std.getGioiTinh());
            tfStatus.setText(std.getTrangThai());
            tfAdress.setText(std.getQueQuan());
            tfClassQL.setText(std.getLop());
            tfLoaiDT.setText(std.getLoaiDaoTao());
            tfBirthDay.setText(std.getNgaySinh());
            tfChuyenNganh.setText(std.getChuyenNganh());
            tfSDT.setText(std.getSoDienThoai());
            tfVaoTruong.setText(std.getNgayVao());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error acces");
            System.out.println(e.getMessage());
        }
    }

    public void exitButtonClicked(ActionEvent actionEvent) {
        HomeController loginController = new HomeController();
        loginController.exitButtonClicked();
    }

    @FXML
    public void btn_learningOutcomeClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/learning_outcomes.fxml"));
        try {
            Stage currentStage = (Stage) btn_learningOutcome.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_scheduleClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/schedule.fxml"));
        try {
            Stage currentStage = (Stage) btn_schedule.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_courseEnrolmentClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/register_for_the_course.fxml"));
        try {
            Stage currentStage = (Stage) btn_courseEnrolment.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_reExamineClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/re-examine.fxml"));
        try {
            Stage currentStage = (Stage) btn_reExamine.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_dashboardClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/home_view.fxml"));
        try {
            Stage currentStage = (Stage) btn_dashboard.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}