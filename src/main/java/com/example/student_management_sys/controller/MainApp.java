package com.example.student_management_sys.controller;


import com.example.student_management_sys.model.DatabaseModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.prefs.Preferences;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/student_infor.fxml"));
//            AnchorPane root = loader.load();
//
//            StudentController studentController = loader.getController();
//            String maSV = "010041";
//            studentController.showInferStudent(maSV);
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("Student Information");
//            primaryStage.show();
//            read file schedule

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/schedule.fxml"));
            AnchorPane root = loader.load();
            ScheduleController scheduleController = loader.getController();
            scheduleController.setText();
            String maSV = "012133";
            Preferences preferences = Preferences.userNodeForPackage(MainApp.class);
            preferences.put("MSSV", maSV);
String MSSV = preferences.get("MSSV", maSV);
            scheduleController.setLichHoc(MSSV, ScheduleController.getDate());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Schedule");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading FXML file");
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

