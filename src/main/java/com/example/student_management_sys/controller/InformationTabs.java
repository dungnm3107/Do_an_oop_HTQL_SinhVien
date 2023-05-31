package com.example.student_management_sys.controller;
import com.example.student_management_sys.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
public class InformationTabs {
    @FXML
    private TextField TrangThai_TT;

    @FXML
    private TextField NgaySinh_TT;

    @FXML
    private TextField Lop_TT;

    @FXML
    private TextField LoaiDT_TT;

    @FXML
    private TextField QueQuan_TT;

    @FXML
    private TextField NgayVao_TT;

    @FXML
    private TextField SDT_TT;

    @FXML
    private TextField ChuyenNganh_TT;

    @FXML
    private TextField MSSV_TT;

    @FXML
    private TextField HoTen_TT;

    @FXML
    private TextField GioiTinh_TT;

    public void initialize() {
        DatabaseModel databaseModel = new DatabaseModel();
        try {
            Student student = databaseModel.getInformation("010041");
            MSSV_TT.setText(student.getMSSV());
            HoTen_TT.setText(student.getHoTen());
            GioiTinh_TT.setText(student.getGioiTinh());
            TrangThai_TT.setText(student.getTrangThai());
            QueQuan_TT.setText(student.getQueQuan());
            Lop_TT.setText(student.getLop());
            LoaiDT_TT.setText(student.getLoaiDaoTao());
            ChuyenNganh_TT.setText(student.getChuyenNganh());
            NgayVao_TT.setText(student.getNgayVao());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        InformationTabs informationTabs = new InformationTabs();
        informationTabs.initialize();
    }
}

