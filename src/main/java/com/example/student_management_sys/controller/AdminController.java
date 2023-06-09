package com.example.student_management_sys.controller;

import com.example.student_management_sys.model.DatabaseModel;
import com.example.student_management_sys.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.awt.*;
import java.sql.SQLException;


public class AdminController extends Controller {
    @FXML
    private TextField tfTimKiem;
    @FXML
    private Button btn_timKiem;


    public void timKiemClicked() throws SQLException {
        try {
            String MSSV = tfTimKiem.getText();
            DatabaseModel databaseModel = new DatabaseModel();
            Student std = databaseModel.getInformation(MSSV);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Không tìm thấy sinh viên");
            alert.showAndWait();
        }

    }

}
