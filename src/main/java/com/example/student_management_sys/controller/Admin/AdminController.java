package com.example.student_management_sys.controller.Admin;

import com.example.student_management_sys.controller.SinhVien.Controller;
import com.example.student_management_sys.model.*;
import com.example.student_management_sys.model.DB.AdminDatabase;
import com.example.student_management_sys.model.DB.ConnectionDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.sql.*;


public class AdminController extends Controller {
    @FXML
    private javafx.scene.control.Button homeButton;

    @FXML
    private MenuItem exitButton;

    @FXML
    private TextField tfTimKiem;
    @FXML
    private Button btn_timKiem;
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
    private TextField tfHDT;
    @FXML
    private TextField tfNganhHoc;
    @FXML
    private TextField tfChuyenNganh;
    @FXML
    private TextField tfSDT;
    @FXML
    private TextField tfVaoTruong;
    @FXML
    private Button buttonDelete;
    @FXML
    private ImageView imageSinhVien;
    @FXML
    private Button btn_Update;



    public void showInforStudent(String maSV) {

        AdminDatabase databaseModel = new AdminDatabase();
        Student std = databaseModel.timKiem(maSV).get(0);
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
    }

    public void loginAdmin(String username, String password, String path) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1424, 750);
        Stage homeStage = new Stage();
        homeStage.setScene(scene);
        homeStage.show();
    }
    @FXML
    public void timKiemClicked() throws Exception {
        String MSSV = tfTimKiem.getText();
        if (MSSV == null || MSSV.isEmpty()) {
            tfMaSV.setText(null);
            tfHoTen.setText(null);
            tfGioiTinh.setText(null);
            tfStatus.setText(null);
            tfAdress.setText(null);
            tfClassQL.setText(null);
            tfLoaiDT.setText(null);
            tfBirthDay.setText(null);
            tfChuyenNganh.setText(null);
            tfSDT.setText(null);
            tfVaoTruong.setText(null);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Không tìm thấy sinh viên");
            alert.showAndWait();
        } else {
            try {
                showInforStudent(MSSV);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Không tìm thấy sinh viên");
                alert.showAndWait();
            }
        }
    }


    public void setTextField(String maSV, String hoTen, String gioiTinh, String status, String adress, String classQL, String loaiDT, String birthDay, String chuyenNganh, String SDT, String vaoTruong) {
        tfMaSV.setText(maSV);
        tfHoTen.setText(hoTen);
        tfGioiTinh.setText(gioiTinh);
        tfStatus.setText(status);
        tfAdress.setText(adress);
        tfClassQL.setText(classQL);
        tfLoaiDT.setText(loaiDT);
        tfBirthDay.setText(birthDay);
        tfChuyenNganh.setText(chuyenNganh);
        tfSDT.setText(SDT);
        tfVaoTruong.setText(vaoTruong);
    }

    @FXML
    public void deleteClick() throws SQLException {
        String maSV = tfMaSV.getText();
        if (maSV == null || maSV.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng tìm kiếm sinh viên trước khi xóa");
            alert.showAndWait();
        } else {
            AdminDatabase databaseModel = new AdminDatabase();
            databaseModel.deleteSV(maSV);
            setTextField("", "", "", "", "", "", "", "", "", "", "");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Xóa thành công");
            alert.showAndWait();
        }
    }
}
