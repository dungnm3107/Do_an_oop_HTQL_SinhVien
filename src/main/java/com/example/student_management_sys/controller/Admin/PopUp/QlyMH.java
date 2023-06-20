package com.example.student_management_sys.controller.Admin.PopUp;

import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.DB.AdminDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        maMH.setEditable(false);
        soTC.setText(String.valueOf(courseData.getSoTin()));
        loaiHP.setText(courseData.getLoaiHP());
    }

    public void Sua() {
        String name = nameMH.getText();
        String ma = maMH.getText();
        String soTin = soTC.getText();
        String loai = loaiHP.getText();

        // Kiểm tra trường trống
        if (name.isEmpty() || ma.isEmpty() || soTin.isEmpty() || loai.isEmpty()) {
            // Hiển thị thông báo lỗi
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin.");
            alert.showAndWait();
            return; // Kết thúc hàm
        }

        // Kiểm tra tính hợp lệ của số tín chỉ
        try{
            int soTinChi = Integer.parseInt(soTin);
            if (soTinChi <= 0) {
                // Hiển thị thông báo lỗi
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Số tín chỉ không hợp lệ.");
                alert.showAndWait();
                return; // Kết thúc hàm
            }
        }
        catch (NumberFormatException e) {
            // Hiển thị thông báo lỗi
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Số tín chỉ không hợp lệ.");
            alert.showAndWait();
            return; // Kết thúc hàm
        }

        CourseData courseData = new CourseData(ma, name, soTin, loai);
        AdminDatabase adminDatabase = new AdminDatabase();
        adminDatabase.updateMonHoc(courseData);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Sửa thành công");
        alert.setContentText("Hãy tìm kiếm lại để xem kết quả");
        alert.showAndWait();

        nameMH.getScene().getWindow().hide();
    }

}