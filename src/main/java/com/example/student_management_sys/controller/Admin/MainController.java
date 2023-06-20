package com.example.student_management_sys.controller.Admin;

import com.example.student_management_sys.controller.SinhVien.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController extends Controller{

@FXML
    private Button SinhVien;
    @FXML
    public void btnSV(){
        FXMLLoader loader =   OpenNewFXMLFile("/com/example/student_management_sys/view/Admin/Student.fxml", SinhVien );
    }

}
