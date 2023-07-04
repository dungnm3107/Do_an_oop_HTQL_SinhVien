package com.example.student_management_sys.controller.Admin.PopUp;

import com.example.student_management_sys.controller.Admin.SinhVien;
import com.example.student_management_sys.model.DB.AdminDatabase;
import com.example.student_management_sys.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class updateSV {
    @FXML
    private Button updateSinhVien;
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

    private SinhVien sinhvienController;


    public void setSinhVienController(SinhVien sinhVien) {
        this.sinhvienController = sinhVien;
    }

    public void setSinhVien(Student student) {
        tfMaSV.setText(student.getMSSV());
        tfHoTen.setText(student.getHoTen());
        tfGioiTinh.setText(student.getGioiTinh());
        tfStatus.setText(student.getTrangThai());
        tfAdress.setText(student.getQueQuan());
        tfClassQL.setText(student.getLop());
        tfLoaiDT.setText(student.getLoaiDaoTao());
        tfBirthDay.setText(student.getNgaySinh());
        tfHDT.setText(student.getHeDaoTao());
        tfNganhHoc.setText(student.getNganhHoc());
        tfChuyenNganh.setText(student.getChuyenNganh());
        tfSDT.setText(student.getSoDienThoai());
        tfVaoTruong.setText(student.getNgayVao());
    }
    @FXML
    public void upDateSV() {
        String MSSV = tfMaSV.getText();
        String hoTen = tfHoTen.getText();
        String gioiTinh = tfGioiTinh.getText();
        String trangThai = tfStatus.getText();
        String queQuan = tfAdress.getText();
        String lop = tfClassQL.getText();
        String loaiDaoTao = tfLoaiDT.getText();
        String ngaySinh = tfBirthDay.getText();
        String soDienThoai = tfSDT.getText();
        String heDaoTao = tfHDT.getText();
        String chuyenNganh = tfChuyenNganh.getText();
        String nganhHoc = tfNganhHoc.getText();
        String ngayVao = tfVaoTruong.getText();

        LocalDate ngaySinhDate = LocalDate.parse(ngaySinh);
        LocalDate ngayVaoDate = LocalDate.parse(ngayVao);

        if (ngayVaoDate.isBefore(ngaySinhDate.plusYears(15))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Ngày vào trường không hợp lệ. Vui lòng nhập lại.");
            alert.showAndWait();
            return;
        }

        if (MSSV.isEmpty() || hoTen.isEmpty() || gioiTinh.isEmpty() || trangThai.isEmpty()
                || queQuan.isEmpty() || lop.isEmpty() || loaiDaoTao.isEmpty() || ngaySinh.isEmpty()
                || soDienThoai.isEmpty() || heDaoTao.isEmpty() || chuyenNganh.isEmpty()
                || nganhHoc.isEmpty() || ngayVao.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin.");
            alert.showAndWait();
            return;
        }
        Student student = new Student(MSSV, hoTen, gioiTinh, trangThai, queQuan, lop, loaiDaoTao, ngaySinh, soDienThoai, heDaoTao, chuyenNganh, nganhHoc, ngayVao);
        AdminDatabase adminDatabase = new AdminDatabase();
        adminDatabase.updateSinhVien(MSSV, student);
        if (sinhvienController != null) {
            sinhvienController.setTableView();
        }
        ((Stage) tfMaSV.getScene().getWindow()).close();
    }
}
