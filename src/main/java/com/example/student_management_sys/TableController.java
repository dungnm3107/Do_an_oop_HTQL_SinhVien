package com.example.student_management_sys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableController {

    @FXML
    public TableView<CourseData> tableView;

    @FXML
    public TabPane tabPane;

    @FXML
    public Tab tableViewTab;

    @FXML
    public void initialize() {
        TableColumn<CourseData, String> maMHColumn = new TableColumn<>("Mã học phần");
        maMHColumn.setCellValueFactory(new PropertyValueFactory<>("maMH"));
        tableView.getColumns().add(maMHColumn);
        TableColumn<CourseData, String> nameMHColumn = new TableColumn<>("Tên môn học");
        nameMHColumn.setCellValueFactory(new PropertyValueFactory<>("nameMH"));
        tableView.getColumns().add(nameMHColumn);
        TableColumn<CourseData, String> nameLopColumn = new TableColumn<>("Lớp");
        nameLopColumn.setCellValueFactory(new PropertyValueFactory<>("nameLop"));
        tableView.getColumns().add(nameLopColumn);
        TableColumn<CourseData, Integer> soTinColumn = new TableColumn<>("Số tín");
        soTinColumn.setCellValueFactory(new PropertyValueFactory<>("soTin"));
        tableView.getColumns().add(soTinColumn);
        TableColumn<CourseData, String> loaiHPColumn = new TableColumn<>("Loại học phần");
        loaiHPColumn.setCellValueFactory(new PropertyValueFactory<>("loaiHP"));
        tableView.getColumns().add(loaiHPColumn);

        // Thực hiện truy vấn và gán dữ liệu vào TableView khi Tab được chọn
        tableViewTab.setOnSelectionChanged(event -> {
            if (tableViewTab.isSelected()) {
                loadTableData();
            }
        });
    }

    private void loadTableData() {
        ObservableList<CourseData> courseList = getCoursesFromDatabase();
        tableView.setItems(courseList);
    }

    public ObservableList<CourseData> getCoursesFromDatabase() {
        String dataQuery = "SELECT mh.Ma_MH, mh.Name_MH, lh.Name_Lop, mh.So_Tin, mh.Loai_HP\n" +
                "FROM monHoc mh\n" +
                "JOIN dangKyMonHoc dkmh ON mh.Ma_MH = dkmh.Ma_MH\n" +
                "JOIN sinhVien sv ON sv.Ma_SV = dkmh.Ma_SV\n" +
                "JOIN hocKy hk ON hk.Ma_HK = dkmh.Ma_HK\n" +
                "JOIN lopHoc lh ON sv.Name_Lop = lh.Name_Lop\n" +
                "WHERE sv.Ma_SV = '010041' AND hk.Ma_HK = 'HK01-2023';";
        ObservableList<CourseData> courseList = FXCollections.observableArrayList();

        try (Connection connection = ConnectionDatabase.getConnection();
             PreparedStatement statement = connection.prepareStatement(dataQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String maMH = resultSet.getString("Ma_MH");
                String nameMH = resultSet.getString("Name_MH");
                String nameLop = resultSet.getString("Name_Lop");
                int soTin = resultSet.getInt("So_Tin");
                String loaiHP = resultSet.getString("Loai_HP");

                CourseData courseData = new CourseData(maMH, nameMH, nameLop, soTin, loaiHP);
                courseList.add(courseData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }
}