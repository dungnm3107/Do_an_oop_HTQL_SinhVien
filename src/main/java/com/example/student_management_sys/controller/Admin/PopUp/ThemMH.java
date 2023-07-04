package com.example.student_management_sys.controller.Admin.PopUp;

import com.example.student_management_sys.model.DB.AdminDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ThemMH extends QlyMH {

    @FXML
    private TextField nameMH;
    @FXML
    private TextField maMH;


    @FXML
    private TextField soTC;
    @FXML
    private ComboBox loaiHP;
    @Override
    public void initialize() {
        loaiHP.getItems().addAll("Bắt buộc","Tự chọn");
        loaiHP.getSelectionModel().select("Bắt buộc");
    }
    public void Them(){
        AdminDatabase adminDatabase = new AdminDatabase();
        if (loaiHP.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thêm môn học");
            alert.setHeaderText("Thêm môn học thất bại");
            alert.setContentText("Vui lòng chọn loại học phần");
            alert.showAndWait();
            return;
        }
        adminDatabase.themMH(maMH.getText(), nameMH.getText(), soTC.getText(), loaiHP.getValue().toString());
    }
}
