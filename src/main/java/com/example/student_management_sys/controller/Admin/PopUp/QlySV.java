package com.example.student_management_sys.controller.Admin.PopUp;

import com.example.student_management_sys.controller.Admin.SinhVien;
import com.example.student_management_sys.model.DB.AdminDatabase;
import com.example.student_management_sys.model.Student;
import com.example.student_management_sys.model.StudentNew;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class QlySV {
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

    @FXML
    private Button insertSinhVien;

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCCCD;
    @FXML
    private TextField tfPass;
    @FXML
    private TextField tfMaSV1;
    @FXML
    private TextField tfHoTen1;
    @FXML
    private TextField tfGioiTinh1;
    @FXML
    private TextField tfStatus1;
    @FXML
    private TextField tfAdress1;
    @FXML
    private TextField tfClassQL1;
    @FXML
    private TextField tfBirthDay1;
    @FXML
    private TextField tfSDT1;
    @FXML
    private ComboBox<String> cpHDT;
    @FXML
    private TextField tfVaoTruong1;

    @FXML
    public void initialize() {
        cpHDT.getItems().addAll("Đại học", "Thạc sĩ", "Tiến sĩ");
        cpHDT.setValue("Đại học");
    }

    @FXML
    public void insertSinhVienOnClick() {
        String MSSV1 = tfMaSV1.getText();
        String hoTen1 = tfHoTen1.getText();
        String gioiTinh1 = tfGioiTinh1.getText();
        String ngaySinh1 = tfBirthDay1.getText();
        String queQuan1 = tfAdress1.getText();
        String soDienThoai1 = tfSDT1.getText();
        String email1 = tfEmail.getText();
        String cccd = tfCCCD.getText();
        String lop1 = tfClassQL1.getText();
        String trangThai1 = tfStatus1.getText();
        String ngayVao1 = tfVaoTruong1.getText();
        String Pass = tfPass.getText();
        if (MSSV1.isEmpty() || hoTen1.isEmpty() || gioiTinh1.isEmpty() || ngaySinh1.isEmpty()
                || queQuan1.isEmpty() || soDienThoai1.isEmpty() || email1.isEmpty()
                || cccd.isEmpty() || lop1.isEmpty() || trangThai1.isEmpty()
                || ngayVao1.isEmpty() || Pass.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin.");
            alert.showAndWait();
            return;
        }

        StudentNew studentNew = new StudentNew(MSSV1, hoTen1, gioiTinh1, ngaySinh1, queQuan1, soDienThoai1, email1, cccd, lop1, trangThai1, ngayVao1, Pass);
        AdminDatabase adminDatabase = new AdminDatabase();
        adminDatabase.insertSinhVien(studentNew);
    }




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

//    public void setSinhVienNew(StudentNew student) {
//        tfMaSV1.setText(student.getMSSV());
//        tfHoTen1.setText(student.getHoTen());
//        tfGioiTinh1.setText(student.getGioiTinh());
//        tfBirthDay1.setText(student.getNgaySinh());
//        tfAdress1.setText(student.getQueQuan());
//        tfSDT1.setText(student.getSoDienThoai());
//        tfEmail.setText(student.getEmail());
//        tfCCCD.setText(student.getCCCD());
//        tfClassQL1.setText(student.getLop());
//        tfStatus1.setText(student.getTrangThai());
//        tfVaoTruong1.setText(student.getNgayVao());
//        tfPass.setText(student.getPass());
//    }


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
