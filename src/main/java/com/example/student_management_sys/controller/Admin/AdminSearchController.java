//package com.example.student_management_sys.controller.Admin;
//
//import com.example.student_management_sys.model.DB.AdminDatabase;
//import com.example.student_management_sys.model.Student;
//import javafx.beans.property.ReadOnlyObjectWrapper;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//public class AdminSearchController extends AdminController {
//    @FXML
//    TableView tableSV;
//
//    @FXML
//    private TextField tfTimKiem;
//    @FXML
//    private Button btn_timKiem;
//
//    public void setTableView(){
//        tableSV.getColumns().clear();
//        String query = tfTimKiem.getText();
//        AdminDatabase dm = new AdminDatabase();
//        ObservableList<Student> list = dm.timKiem(query);
//
//        TableColumn<Student, String> sttColumn = new TableColumn<>("STT");
//        sttColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableSV.getItems().indexOf(column.getValue()) + 1).asString());
//        tableSV.getColumns().add(sttColumn);
//
//
//        TableColumn<Student, String> maSVColumn = new TableColumn<>("Mã SV");
//        maSVColumn.setCellValueFactory(new PropertyValueFactory<>("MSSV"));
//        tableSV.getColumns().add(maSVColumn);
//
//        TableColumn<Student, String> hoTenColumn = new TableColumn<>("Họ tên");
//        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
//        tableSV.getColumns().add(hoTenColumn);
//
//        TableColumn<Student, String> gioiTinhColumn = new TableColumn<>("Giới tính");
//        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
//        tableSV.getColumns().add(gioiTinhColumn);
//
//        TableColumn<Student, String> trangThaiColumn = new TableColumn<>("Trạng thái");
//        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
//        tableSV.getColumns().add(trangThaiColumn);
//
//        TableColumn<Student, String> queQuanColumn = new TableColumn<>("Quê quán");
//        queQuanColumn.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
//        tableSV.getColumns().add(queQuanColumn);
//
//        TableColumn<Student, String> lopColumn = new TableColumn<>("Lớp");
//        lopColumn.setCellValueFactory(new PropertyValueFactory<>("lop"));
//        tableSV.getColumns().add(lopColumn);
//
//        TableColumn<Student, String> loaiDaoTaoColumn = new TableColumn<>("Loại đào tạo");
//        loaiDaoTaoColumn.setCellValueFactory(new PropertyValueFactory<>("loaiDaoTao"));
//        tableSV.getColumns().add(loaiDaoTaoColumn);
//
//        TableColumn<Student, String> ngaySinhColumn = new TableColumn<>("Ngày sinh");
//        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
//        tableSV.getColumns().add(ngaySinhColumn);
//
//        TableColumn<Student, String> soDienThoaiColumn = new TableColumn<>("Số điện thoại");
//        soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
//        tableSV.getColumns().add(soDienThoaiColumn);
//
//        TableColumn<Student, String> heDaoTaoColumn = new TableColumn<>("Hệ đào tạo");
//        heDaoTaoColumn.setCellValueFactory(new PropertyValueFactory<>("heDaoTao"));
//        tableSV.getColumns().add(heDaoTaoColumn);
//
//        TableColumn<Student, String> chuyenNganhColumn = new TableColumn<>("Chuyên ngành");
//        chuyenNganhColumn.setCellValueFactory(new PropertyValueFactory<>("chuyenNganh"));
//        tableSV.getColumns().add(chuyenNganhColumn);
//
//        TableColumn<Student, String> nganhHocColumn = new TableColumn<>("Ngành học");
//        nganhHocColumn.setCellValueFactory(new PropertyValueFactory<>("nganhHoc"));
//        tableSV.getColumns().add(nganhHocColumn);
//
//        TableColumn<Student, String> ngayVaoColumn = new TableColumn<>("Ngày vào");
//        ngayVaoColumn.setCellValueFactory(new PropertyValueFactory<>("ngayVao"));
//        tableSV.getColumns().add(ngayVaoColumn);
//
//        tableSV.setItems(list);
//    }
//}
