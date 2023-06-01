package com.example.student_management_sys.controller;

import com.example.student_management_sys.model.ConnectionDatabase;
import com.example.student_management_sys.model.CourseData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;



public class registerForTheCourseController {
    private String username;

    private String selectedMAHK="";
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
    private String strMAHK;

    @FXML
    private TableView tableView;
    public void registerForTheCourse(String MSSV) {
        MenuItem hk1 = new MenuItem("Học kì 1 năm 2021");
        hk1.setOnAction(event -> {
            selectedMAHK = "HK01-2021";
            strMAHK = selectedMAHK.substring(0, 9);
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk1);

        MenuItem hk2= new MenuItem("Học kì 1 năm 2022");
        hk2.setOnAction(event -> {
            selectedMAHK = "HK01-2022";
            strMAHK = selectedMAHK.substring(0, 9);
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk2);

        MenuItem hk3= new MenuItem("Học kì 1 năm 2023");
        hk3.setOnAction(event -> {
            selectedMAHK = "HK01-2023";
            strMAHK = selectedMAHK.substring(0, 9);
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk3);

        MenuItem hk4= new MenuItem("Học kì 2 năm 2021");
        hk4.setOnAction(event -> {
            selectedMAHK = "HK02-2021";
            strMAHK = selectedMAHK.substring(0, 9);
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk4);

        MenuItem hk5= new MenuItem("Học kì 2 năm 2022");
        hk5.setOnAction(event -> {
            selectedMAHK = "HK02-2022";
            strMAHK = selectedMAHK.substring(0, 9);
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk5);

        MenuItem hk6= new MenuItem("Học kì 2 năm 2023");
        hk6.setOnAction(event -> {
            selectedMAHK = "HK02-2023";
            strMAHK = selectedMAHK.substring(0, 9);
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk6);

        MenuItem hk7= new MenuItem("Học kì 3 năm 2021");
        hk7.setOnAction(event -> {
            selectedMAHK = "HK03-2021";
            strMAHK = selectedMAHK.substring(0, 9);
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk7);

        MenuItem hk8= new MenuItem("Học kì 3 năm 2022");
        hk8.setOnAction(event -> {
            selectedMAHK = "HK03-2022";
            strMAHK = selectedMAHK.substring(0, 9);
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk8);

        MenuItem hk9= new MenuItem("Học kì 3 năm 2023");
        hk9.setOnAction(event -> {
            selectedMAHK = "HK03-2023";
            strMAHK = selectedMAHK.substring(0, 9);
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk9);

    }

    public void loadCourseData(String MSSV) {
        try {
            Connection databaseConnection = ConnectionDatabase.getConnection();
            String dataQuery = "SELECT mh.Ma_MH, mh.Name_MH, lh.Name_Lop, mh.So_Tin, mh.Loai_HP\n" +
                    "FROM monHoc mh\n" +
                    "JOIN dangKyMonHoc dkmh ON mh.Ma_MH = dkmh.Ma_MH\n" +
                    "JOIN sinhVien sv ON sv.Ma_SV = dkmh.Ma_SV\n" +
                    "JOIN hocKy hk ON hk.Ma_HK = dkmh.Ma_HK\n" +
                    "JOIN lopHoc lh ON sv.Name_Lop = lh.Name_Lop\n" +
                    "WHERE sv.Ma_SV = '" + MSSV + "' AND hk.Ma_HK = '"+strMAHK+"';";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(dataQuery);

            if (resultSet.next()) {
                ObservableList<CourseData> courseDataList = FXCollections.observableArrayList();
                int index = 1;
                do {
                    String maMH = resultSet.getString("Ma_MH");
                    String nameMH = resultSet.getString("Name_MH");
                    String nameLop = resultSet.getString("Name_Lop");
                    int soTin = resultSet.getInt("So_Tin");
                    String loaiHP = resultSet.getString("Loai_HP");


                    CourseData courseData = new CourseData(maMH, nameMH, nameLop, soTin, soTin*325000, loaiHP,false);
                    courseDataList.add(courseData);


                } while (resultSet.next());

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
                    // Xử lý sự kiện khi trạng thái đăng kí thay đổi
                    property.addListener((observable, oldValue, newValue) -> courseData.setTrangThaiDangKi(newValue));
                    return property;
                });
                trangThaiDangKiColumn.setCellFactory(CheckBoxTableCell.forTableColumn(trangThaiDangKiColumn));
                tableView.getColumns().add(trangThaiDangKiColumn);

//                anchorTable.getChildren().add(tableView);
                tableView.setItems(courseDataList);
                tableView.setStyle("-fx-font-size: 14px;");
                tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            } else {
                System.out.println("Not Found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error access");
            System.out.println(e.getMessage());
        }
    }

}
