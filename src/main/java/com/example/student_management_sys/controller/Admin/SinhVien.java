package com.example.student_management_sys.controller.Admin;


import com.example.student_management_sys.controller.Admin.PopUp.updateSV;
import com.example.student_management_sys.model.DB.AdminDatabase;
import com.example.student_management_sys.model.Student;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SinhVien extends ControllerAdmin {

    @FXML
    private TableView tableSV;
    @FXML
    private Button themSV;

    @FXML
    private TextField tfTimKiem;


    @FXML
    private Button btn_timKiem;

    @FXML
    private void insertSVOnClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/Admin/Small/insertSV.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Nhập thông tin sinh viên ");
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void loadFileSV(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/Admin/Small/updateSV1.fxml"));
            Parent parent = loader.load();
            updateSV controller = loader.getController();
            controller.setSinhVien(student);
            controller.setSinhVienController(this);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {

            System.out.println(e);
        }
    }


    public void setTableView() {
        tableSV.getColumns().clear();
        String query = tfTimKiem.getText();
        AdminDatabase dm = new AdminDatabase();
        ObservableList<Student> list = dm.timKiem(query);

        TableColumn<Student, String> sttColumn = new TableColumn<>("STT");
        sttColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableSV.getItems().indexOf(column.getValue()) + 1).asString());
        tableSV.getColumns().add(sttColumn);

        TableColumn<Student, String> maSVColumn = new TableColumn<>("MSSV");
        maSVColumn.setCellValueFactory(new PropertyValueFactory<>("MSSV"));
        tableSV.getColumns().add(maSVColumn);

        TableColumn<Student, String> hoTenColumn = new TableColumn<>("Họ tên");
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        tableSV.getColumns().add(hoTenColumn);

        TableColumn<Student, String> gioiTinhColumn = new TableColumn<>("Giới tính");
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        tableSV.getColumns().add(gioiTinhColumn);

        TableColumn<Student, String> trangThaiColumn = new TableColumn<>("Trạng thái");
        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        tableSV.getColumns().add(trangThaiColumn);

        TableColumn<Student, String> queQuanColumn = new TableColumn<>("Quê quán");
        queQuanColumn.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
        tableSV.getColumns().add(queQuanColumn);

        TableColumn<Student, String> lopColumn = new TableColumn<>("Lớp");
        lopColumn.setCellValueFactory(new PropertyValueFactory<>("lop"));
        tableSV.getColumns().add(lopColumn);

        TableColumn<Student, String> loaiDaoTaoColumn = new TableColumn<>("Loại đào tạo");
        loaiDaoTaoColumn.setCellValueFactory(new PropertyValueFactory<>("loaiDaoTao"));
        tableSV.getColumns().add(loaiDaoTaoColumn);

        TableColumn<Student, String> ngaySinhColumn = new TableColumn<>("Ngày sinh");
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        tableSV.getColumns().add(ngaySinhColumn);

        TableColumn<Student, String> soDienThoaiColumn = new TableColumn<>("Số điện thoại");
        soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        tableSV.getColumns().add(soDienThoaiColumn);

        TableColumn<Student, String> heDaoTaoColumn = new TableColumn<>("Hệ đào tạo");
        heDaoTaoColumn.setCellValueFactory(new PropertyValueFactory<>("heDaoTao"));
        tableSV.getColumns().add(heDaoTaoColumn);

        TableColumn<Student, String> chuyenNganhColumn = new TableColumn<>("Chuyên ngành");
        chuyenNganhColumn.setCellValueFactory(new PropertyValueFactory<>("chuyenNganh"));
        tableSV.getColumns().add(chuyenNganhColumn);

        TableColumn<Student, String> nganhHocColumn = new TableColumn<>("Ngành học");
        nganhHocColumn.setCellValueFactory(new PropertyValueFactory<>("nganhHoc"));
        tableSV.getColumns().add(nganhHocColumn);

        TableColumn<Student, String> ngayVaoColumn = new TableColumn<>("Ngày vào");
        ngayVaoColumn.setCellValueFactory(new PropertyValueFactory<>("ngayVao"));
        tableSV.getColumns().add(ngayVaoColumn);

        TableColumn<Student, Void> actionColumn = new TableColumn<>("Update");
        tableSV.getColumns().add(actionColumn);
        actionColumn.setCellFactory(column -> new TableCell<Student, Void>() {
            private final Button changeButton = new Button("Sửa");

            {
                changeButton.setOnAction(event -> {
                    Student student = getTableRow().getItem();
                    if (student != null) {
                        loadFileSV(student);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(changeButton);
                }
            }
        });

        TableColumn<Student, Void> deleteColumn = new TableColumn<>("Delete");
        tableSV.getColumns().add(deleteColumn);
        deleteColumn.setCellFactory(column -> new TableCell<Student, Void>() {
            private final Button deleteSV = new Button("Delete");

            {
                deleteSV.setOnAction(event -> {
                    Student student = getTableRow().getItem();
                    if (student != null) {
                        try {
                            dm.deleteSV(student.getMSSV());
                            showDeleteSuccessAlert();
                            setTableView();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteSV);
                }
            }

            private void showDeleteSuccessAlert() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Xóa sinh viên thành công.");
                alert.showAndWait();
            }

        });
        tableSV.setItems(list);
    }
}
