package com.example.student_management_sys.controller.Admin;
import com.example.student_management_sys.model.DB.ConnectionDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class UpDiemController {
    @FXML
    private TextField tfDiemQT;
    @FXML
    private TextField tfDiemThi;
    @FXML
    private TextField tfDiemBS;
    @FXML
    private Button btSave;
    @FXML
    private Button btClear;
    private String maSV;
    private String maMH;
    @FXML
    public void initialize() {
        btSave.setOnAction(event -> saveDiem());
        btClear.setOnAction(event -> clearFields());
    }
    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }
    public void setMaMH(String maMH){this.maMH = maMH;};
    private void saveDiem() {
        String diemQTText = tfDiemQT.getText().trim();
        String diemThiText = tfDiemThi.getText().trim();
        String diemBSText = tfDiemBS.getText().trim();

        if (diemQTText.isEmpty() || diemThiText.isEmpty() || diemBSText.isEmpty()) {
            showAlert("Error", "Vui lòng điền đầy đủ thông tin điểm.");
            return;
        }

        float diemQT, diemThi, diemBS;
        try {
            diemQT = Float.parseFloat(diemQTText);
            diemThi = Float.parseFloat(diemThiText);
            diemBS = Float.parseFloat(diemBSText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Điểm không hợp lệ. Vui lòng nhập số.");
            return;
        }

        if (diemQT < 0 || diemQT > 10 || diemThi < 0 || diemThi > 10 || diemBS < 0 || diemBS > 10) {
            showAlert("Error", "Điểm phải nằm trong khoảng từ 0 đến 10.");
            return;
        }
        try {
            Connection connection = ConnectionDatabase.getConnection();
            String query = "UPDATE bangDiem SET Diem_QT = ?, Diem_Thi = ?, Diem_BS = ?, Diem_KT = ? WHERE Ma_SV = ? and Ma_MH = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, diemQT);
            statement.setFloat(2, diemThi);
            statement.setFloat(3, diemBS);
            float temp = (float)(diemQT * 0.3 + diemThi*0.7 + diemBS);
            if(temp > 10){
                temp = 10;
            }
            statement.setFloat(4, temp);
            statement.setString(5, maSV);
            statement.setString(6, maMH);
            statement.executeUpdate();
            connection.close();
            showAlert("Success", "Điểm đã được lưu thành công vào cơ sở dữ liệu.");
            clearFields();
        } catch (SQLException e) {
            showAlert("Error", "Lỗi khi lưu điểm vào cơ sở dữ liệu.");
        }
    }

    private void clearFields() {
        tfDiemQT.clear();
        tfDiemThi.clear();
        tfDiemBS.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
