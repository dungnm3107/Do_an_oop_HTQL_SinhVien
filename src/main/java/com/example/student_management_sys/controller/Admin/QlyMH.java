package com.example.student_management_sys.controller.Admin;

import com.example.student_management_sys.model.CourseData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class QlyMH {
    @FXML
    private TextField nameMH;
    @FXML
    private TextField maMH;
    @FXML
    private TextField soTC;
    @FXML
    private TextField loaiHP;
public void setCourseData(CourseData courseData){
    nameMH.setText(courseData.getNameMH());
    maMH.setText(courseData.getMaMH());
    soTC.setText(String.valueOf(courseData.getSoTin()));
    loaiHP.setText(courseData.getLoaiHP());
}
}