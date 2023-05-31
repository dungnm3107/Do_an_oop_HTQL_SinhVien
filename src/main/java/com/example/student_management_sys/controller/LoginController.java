package com.example.student_management_sys.controller;


import com.example.student_management_sys.model.ConnectionDatabase;
import com.example.student_management_sys.model.CourseData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    private TextField tfclass;

    @FXML
    private TextField tfgender;

    @FXML
    private TextField tfmssv;
    @FXML
    private TextField tfmssv2;

    @FXML
    private TextField tfname;

    @FXML
    private TextField tfnganh;
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

    public void setAccountName(String accountName) {
        buttonAccount.setText(accountName);
    }

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
//    public void reExamine(String MSSV){
//        try{
//            Connection databaseConnection = ConnectionDatabase.getConnection();
//            String queryDKMH = "SELECT mh.Ma_MH, mh.Name_MH, lh.Name_Lop, mh.So_Tin, mh.Loai_HP\n" +
//                    "FROM monHoc mh\n" +
//                    "JOIN dangKyMonHoc dkmh ON mh.Ma_MH = dkmh.Ma_MH\n" +
//                    "JOIN sinhVien sv ON sv.Ma_SV = dkmh.Ma_SV\n" +
//                    "JOIN hocKy hk ON hk.Ma_HK = dkmh.Ma_HK\n" +
//                    "JOIN lopHoc lh ON sv.Name_Lop = lh.Name_Lop\n" +
//                    "WHERE sv.Ma_SV = '" + MSSV + "' AND hk.Ma_HK = '"+strMAHK+"';";
//            Statement statement2 = databaseConnection.createStatement();
//            ResultSet resultSet2 = statement2.executeQuery(queryDKMH);
//
//    }catch (SQLException e) {
//            throw new RuntimeException(e);
//        } ;

    public void registerForTheCourse(String MSSV) {
        MenuItem hk1 = new MenuItem("Học kì 1 năm 2021");
        hk1.setOnAction(event -> {
            selectedMAHK = "HK01-2021";
            strMAHK = selectedMAHK.substring(0, 9);
            tableView.getItems().clear();
            loadCourseData(MSSV);
            menuButton.setText("Học kì 1 năm 2021");
        });
        menuButton.getItems().add(hk1);

        MenuItem hk2= new MenuItem("Học kì 1 năm 2022");
        hk2.setOnAction(event -> {
            selectedMAHK = "HK01-2022";
            strMAHK = selectedMAHK.substring(0, 9);
            tableView.getItems().clear();
            loadCourseData(MSSV);
            menuButton.setText("Học kì 1 năm 2022");
        });
        menuButton.getItems().add(hk2);

        MenuItem hk3= new MenuItem("Học kì 1 năm 2023");
        hk3.setOnAction(event -> {
            selectedMAHK = "HK01-2023";
            strMAHK = selectedMAHK.substring(0, 9);
            tableView.getItems().clear();
            loadCourseData(MSSV);
            menuButton.setText("Học kì 1 năm 2023");
        });
        menuButton.getItems().add(hk3);

        MenuItem hk4= new MenuItem("Học kì 2 năm 2021");
        hk4.setOnAction(event -> {
            selectedMAHK = "HK02-2021";
            strMAHK = selectedMAHK.substring(0, 9);
            tableView.getItems().clear();
            loadCourseData(MSSV);
            menuButton.setText("Học kì 2 năm 2021");
        });
        menuButton.getItems().add(hk4);

        MenuItem hk5= new MenuItem("Học kì 2 năm 2022");
        hk5.setOnAction(event -> {
            selectedMAHK = "HK02-2022";
            strMAHK = selectedMAHK.substring(0, 9);
            tableView.getItems().clear();
            loadCourseData(MSSV);
            menuButton.setText("Học kì 2 năm 2022");
        });
        menuButton.getItems().add(hk5);

        MenuItem hk6= new MenuItem("Học kì 2 năm 2023");
        hk6.setOnAction(event -> {
            selectedMAHK = "HK02-2023";
            strMAHK = selectedMAHK.substring(0, 9);
            tableView.getItems().clear();
            loadCourseData(MSSV);
            menuButton.setText("Học kì 2 năm 2023");
        });
        menuButton.getItems().add(hk6);

        MenuItem hk7= new MenuItem("Học kì 3 năm 2021");
        hk7.setOnAction(event -> {
            selectedMAHK = "HK03-2021";
            strMAHK = selectedMAHK.substring(0, 9);
            tableView.getItems().clear();
            loadCourseData(MSSV);
            menuButton.setText("Học kì 3 năm 2021");
        });
        menuButton.getItems().add(hk7);

        MenuItem hk8= new MenuItem("Học kì 3 năm 2022");
        hk8.setOnAction(event -> {
            selectedMAHK = "HK03-2022";
            strMAHK = selectedMAHK.substring(0, 9);
            tableView.getItems().clear();
            loadCourseData(MSSV);
            menuButton.setText("Học kì 3 năm 2022");
        });
        menuButton.getItems().add(hk8);

        MenuItem hk9= new MenuItem("Học kì 3 năm 2023");
        hk9.setOnAction(event -> {
            selectedMAHK = "HK03-2023";
            strMAHK = selectedMAHK.substring(0, 9);
            tableView.getItems().clear();
            loadCourseData(MSSV);
            menuButton.setText("Học kì 3 năm 2023");
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
            tableView.getItems().clear();
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

                    property.addListener((observable, oldValue, newValue) -> courseData.setTrangThaiDangKi(newValue));
                    return property;
                });
                trangThaiDangKiColumn.setCellFactory(CheckBoxTableCell.forTableColumn(trangThaiDangKiColumn));
                tableView.getColumns().add(trangThaiDangKiColumn);

                tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


//                anchorTable.getChildren().add(tableView);
                tableView.setItems(courseDataList);
                tableView.setStyle("-fx-font-size: 14px;");

            } else {
                System.out.println("Not Found");
            }
            resultSet.close();
            statement.close();
            databaseConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error access");
            System.out.println(e.getMessage());
        }
    }
        public void inforStudent (String maSV){
            try {
                Connection databaseConnection = ConnectionDatabase.getConnection();
                String query = "SELECT sv.Ma_SV, cn.Name_CN, cn.Gender, sv.Name_Lop, nh.Name_Nganh AS Nganh " +
                        "FROM sinhVien AS sv " +
                        "INNER JOIN caNhan AS cn ON sv.CCCD = cn.CCCD " +
                        "INNER JOIN chuyenNganh AS cnn ON sv.Ma_ChuyenNganh = cnn.Ma_ChuyenNganh " +
                        "INNER JOIN nganhHoc AS nh ON cnn.Ma_Nganh = nh.Ma_Nganh " +
                        "WHERE sv.Ma_SV ='" + maSV + "'";
                Statement statement = databaseConnection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    String mssv = resultSet.getString("Ma_SV");
                    String name = resultSet.getString("Name_CN");
                    String gender = resultSet.getString("Gender");
                    String lop = resultSet.getString("Name_Lop");
                    String nganh = resultSet.getString("Nganh");

                    tfmssv.setText(mssv);
                    tfname.setText(name);
                    tfgender.setText(gender);
                    tfclass.setText(lop);
                    tfnganh.setText(nganh);
                    tfmssv2.setText(mssv);
                } else {
                    System.out.println("Not Found");// Xử lý khi không tìm thấy sinh viên với mã sinh viên đã cho
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error access");
                System.out.println(e.getMessage());
            }
        }

        @FXML
        public void loginButtonClicked () {
            try {
                username = usernameTextField.getText();
                String password = passwordPasswordField.getText();

                Connection databaseConnection = ConnectionDatabase.getConnection();

                String query = "SELECT Ma_SV, Pass_SV FROM sinhVien WHERE Ma_SV = '" + username + "' AND Pass_SV = '" + password + "'";

                Statement statement = databaseConnection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {

                    String nameQuery = "SELECT Name_CN FROM caNhan,sinhVien WHERE sinhVien.CCCD = caNhan.CCCD AND sinhVien.Ma_SV='" + username + "'";
                    ResultSet nameResultSet = statement.executeQuery(nameQuery);
                    String nameAccount = "";
                    if (nameResultSet.next()) {
                        nameAccount = nameResultSet.getString("Name_CN");
                    }
                    nameResultSet.close();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/home_view.fxml"));

                    Parent root = loader.load();

                    LoginController homeController = loader.getController();
                    homeController.setAccountName(nameAccount);
                    homeController.inforStudent(username);
                    homeController.registerForTheCourse(username);

                    Scene scene = new Scene(root, 1424, 750);

                    Stage homeStage = new Stage();
                    homeStage.setScene(scene);
                    homeStage.setTitle("Hệ thống quản lý sinh viên");

                    Stage loginStage = (Stage) homeButton.getScene().getWindow();
                    loginStage.close();
                    homeStage.show();


                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi đăng nhập");
                    alert.setHeaderText(null);
                    alert.setContentText("Sai tên đăng nhập hoặc mật khẩu. Vui lòng thử lại!");
                    alert.showAndWait();
                }

                resultSet.close();
                statement.close();
                databaseConnection.close();

            } catch (SQLException | IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Đã xảy ra lỗi. Vui lòng thử lại!");
                alert.showAndWait();
            }
        }

        @FXML
        public void exitButtonClicked () {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/login_view.fxml"));
                Parent root = loader.load();

                Stage loginStage = new Stage();
                loginStage.setTitle("Đăng nhập hệ thống");
                loginStage.setScene(new Scene(root, 600, 400));
                loginStage.show();

                Stage currentStage = (Stage) exitButton.getParentPopup().getOwnerWindow();
                currentStage.close();

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Đã xảy ra lỗi. Vui lòng thử lại!");
                alert.showAndWait();
            }
        }
    }
