package com.example.student_management_sys.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReExamineController {
    @FXML
    private Button btn_studentInfo;
    @FXML
    private Button btn_dashboard;
    @FXML
    private Button btn_learningOutcome;
    @FXML
    private Button btn_courseEnrolment;
    @FXML
    private Button btn_schedule;

    @FXML
    public void btn_studentInfoClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/student_infor.fxml"));
        try {
            Stage currentStage = (Stage) btn_studentInfo.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
