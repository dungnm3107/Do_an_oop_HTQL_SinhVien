package com.example.student_management_sys.controller;

import com.example.student_management_sys.model.ConnectionDatabase;
import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.DatabaseModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;




public class RegisterCourseController extends Controller {

    private String selectedMAHK = "";
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button homeButton;
    @FXML
    private MenuItem exitButton;
    @FXML
    private MenuButton buttonAccount;
    @FXML
    private MenuButton menuButton;

    @FXML
    private TableView tableView;

    public void registerCourse(String MSSV) {
        DatabaseModel dt = new DatabaseModel();
        MenuItem hk1 = new MenuItem("Học kì 1 năm 2021");
        hk1.setOnAction(event -> {
            selectedMAHK = "HK01-2021";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk1);

        MenuItem hk2 = new MenuItem("Học kì 1 năm 2022");
        hk2.setOnAction(event -> {
            selectedMAHK = "HK01-2022";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk2);

        MenuItem hk3 = new MenuItem("Học kì 1 năm 2023");
        hk3.setOnAction(event -> {
            selectedMAHK = "HK01-2023";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk3);

        MenuItem hk4 = new MenuItem("Học kì 2 năm 2021");
        hk4.setOnAction(event -> {
            selectedMAHK = "HK02-2021";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk4);

        MenuItem hk5 = new MenuItem("Học kì 2 năm 2022");
        hk5.setOnAction(event -> {
            selectedMAHK = "HK02-2022";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk5);

        MenuItem hk6 = new MenuItem("Học kì 2 năm 2023");
        hk6.setOnAction(event -> {
            selectedMAHK = "HK02-2023";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk6);

        MenuItem hk7 = new MenuItem("Học kì 3 năm 2021");
        hk7.setOnAction(event -> {
            selectedMAHK = "HK03-2021";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk7);

        MenuItem hk8 = new MenuItem("Học kì 3 năm 2022");
        hk8.setOnAction(event -> {
            selectedMAHK = "HK03-2022";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk8);

        MenuItem hk9 = new MenuItem("Học kì 3 năm 2023");
        hk9.setOnAction(event -> {
            selectedMAHK = "HK03-2023";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk9);

    }

    public void loadCourseData(String MSSV) {
        try {

            DatabaseModel databaseModel = new DatabaseModel();
            ObservableList<CourseData> courseDataList = databaseModel.getRegisterForTheCourse(MSSV, selectedMAHK.substring(0, 9));

            tableView.getColumns().clear();

            TableColumn<CourseData, Integer> soThuTuColumn = new TableColumn<>("STT");
            soThuTuColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(cellData.getValue()) + 1));
            soThuTuColumn.setId("soThuTuColumn");
            tableView.getColumns().add(0, soThuTuColumn);

            TableColumn<CourseData, String> maMHColumn = new TableColumn<>("Mã môn học");
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

            TableColumn<CourseData, Double> hocPhiColumn = new TableColumn<>("Học phí");
            hocPhiColumn.setCellValueFactory(new PropertyValueFactory<>("hocPhi"));
            tableView.getColumns().add(hocPhiColumn);

            TableColumn<CourseData, String> loaiHPColumn = new TableColumn<>("Loại học phần");
            loaiHPColumn.setCellValueFactory(new PropertyValueFactory<>("loaiHP"));
            tableView.getColumns().add(loaiHPColumn);

            TableColumn<CourseData, Boolean> trangThaiDangKiColumn = new TableColumn<>("Trạng thái đăng kí");
            trangThaiDangKiColumn.setCellValueFactory(cellData -> {
                CourseData courseData = cellData.getValue();
                BooleanProperty property = new SimpleBooleanProperty(courseData.isTrangThaiDangKi());
                property.addListener((observable, oldValue, newValue) -> courseData.setTrangThaiDangKi(newValue));
                return property;
            });
            trangThaiDangKiColumn.setCellFactory(CheckBoxTableCell.forTableColumn(trangThaiDangKiColumn));
            tableView.getColumns().add(trangThaiDangKiColumn);
            tableView.setItems(courseDataList);
            tableView.setStyle("-fx-font-size: 14px;");
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error access");
            System.out.println(e.getMessage());
        }
    }

}