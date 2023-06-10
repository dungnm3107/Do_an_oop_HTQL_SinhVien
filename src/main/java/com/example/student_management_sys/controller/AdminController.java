package com.example.student_management_sys.controller;

import com.example.student_management_sys.model.ConnectionDatabase;
import com.example.student_management_sys.model.DatabaseModel;
import com.example.student_management_sys.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
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
            System.out.println(" lỗi truy vấn thông tin sinh viên ở StudentController ");
            System.out.println(e.getMessage());
        }
    }

    public void loginAdmin(String username, String password) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/admin.fxml"));
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
            DatabaseModel databaseModel = new DatabaseModel();
            databaseModel.deleteSV(maSV);
            setTextField("", "", "", "", "", "", "", "", "", "", "");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Xóa thành công");
            alert.showAndWait();
        }
    }

    @FXML
    void clickButtonUpdate(ActionEvent event) {
        String maSV = tfMaSV.getText();
        if (maSV == null || maSV.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng tìm kiếm thông tin sinh viên trước khi cập nhật");
            alert.showAndWait();
        } else {
            try {
                String hoTen = tfHoTen.getText();
                String gioiTinh = tfGioiTinh.getText();
                Date ngaySinh = Date.valueOf(tfBirthDay.getText());
                String queQuan = tfAdress.getText();
                String chuyenNganh = tfChuyenNganh.getText();
                String lop = tfClassQL.getText();
                String loaiDaoTao = tfLoaiDT.getText();
                String sdt = tfSDT.getText();
                String trangThai = tfStatus.getText();
                String ngayVaoTruong = tfVaoTruong.getText();


                String updateQuery = "UPDATE caNhan SET Name_CN = ?, Gender = ?, Que_Quan = ?, Ngay_Sinh = ?, Sdt_CN = ? WHERE CCCD IN (SELECT CCCD FROM sinhVien WHERE Ma_SV = ?)";
                updateQuery += ";";
                updateQuery += "UPDATE sinhVien SET Name_Lop = ?, Ma_ChuyenNganh = (SELECT TOP 1 chuyenNganh.Ma_ChuyenNganh FROM chuyenNganh INNER JOIN nganhHoc ON chuyenNganh.Ma_Nganh = nganhHoc.Ma_Nganh WHERE nganhHoc.Name_Nganh = ?), Ma_Loai = (SELECT Ma_Loai FROM loaiDaoTao WHERE Name_Loai = ?), NgayVao = ?, TrangThai = ? WHERE Ma_SV = ?";

                Connection databaseConnection = ConnectionDatabase.getConnection();
                PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateQuery);
                preparedStatement.setString(1, hoTen);
                preparedStatement.setString(2, gioiTinh);
                preparedStatement.setString(3, queQuan);
                preparedStatement.setDate(4, ngaySinh);
                preparedStatement.setString(5, sdt);
                preparedStatement.setString(6, maSV);
                preparedStatement.setString(7, lop);
                preparedStatement.setString(8, chuyenNganh);
                preparedStatement.setString(9, loaiDaoTao);
                preparedStatement.setString(10, ngayVaoTruong);
                preparedStatement.setString(11, trangThai);
                preparedStatement.setString(12, maSV);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Cập nhật thành công");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Không tìm thấy sinh viên để cập nhật");
                    alert.showAndWait();
                }

                preparedStatement.close();
                databaseConnection.close();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Đã xảy ra lỗi khi cập nhật dữ liệu:\n " + e.getMessage());
                alert.showAndWait();
            }
        }
    }
}

