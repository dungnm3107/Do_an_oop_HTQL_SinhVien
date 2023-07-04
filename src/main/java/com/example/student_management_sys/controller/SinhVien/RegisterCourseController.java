package com.example.student_management_sys.controller.SinhVien;

import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.DB.DatabaseModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.text.DecimalFormat;


public class RegisterCourseController extends Controller {

    private String selectedMAHK = "";
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

        MenuItem hk2 = new MenuItem("Học kì 2 năm 2021");
        hk2.setOnAction(event -> {
            selectedMAHK = "HK02-2021";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk2);

        MenuItem hk3 = new MenuItem("Học kì 3 năm 2021");
        hk3.setOnAction(event -> {
            selectedMAHK = "HK03-2021";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk3);


        MenuItem hk4 = new MenuItem("Học kì 1 năm 2022");
        hk4.setOnAction(event -> {
            selectedMAHK = "HK01-2022";
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

        MenuItem hk6 = new MenuItem("Học kì 3 năm 2022");
        hk6.setOnAction(event -> {
            selectedMAHK = "HK03-2022";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk6);

        MenuItem hk7 = new MenuItem("Học kì 1 năm 2023");
        hk7.setOnAction(event -> {
            selectedMAHK = "HK01-2023";
            try {
                dt.getRegisterForTheCourse(username, selectedMAHK.substring(0, 9));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadCourseData(MSSV);
        });
        menuButton.getItems().add(hk7);


        MenuItem hk8 = new MenuItem("Học kì 2 năm 2023");
        hk8.setOnAction(event -> {
            selectedMAHK = "HK02-2023";
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

            TableColumn<CourseData, Integer> soThuTuColumn = new TableColumn<>(" STT ");
            soThuTuColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(cellData.getValue()) + 1));
            soThuTuColumn.setId("soThuTuColumn");
            soThuTuColumn.setPrefWidth(50);

            soThuTuColumn.setCellFactory(column -> {
                return new TableCell<CourseData, Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setAlignment(Pos.CENTER); // Căn giữa nếu cell trống
                        } else {
                            setText(String.valueOf(item));
                            setAlignment(Pos.CENTER); // Căn giữa nội dung của cell
                        }
                    }
                };
            });

            tableView.getColumns().add(0, soThuTuColumn);


            TableColumn<CourseData, String> maMHColumn = new TableColumn<>("  Mã môn học   ");
            maMHColumn.setCellValueFactory(new PropertyValueFactory<>("maMH"));
            maMHColumn.setPrefWidth(150);
            maMHColumn.setCellFactory(column -> {
                return new TableCell<CourseData, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setAlignment(Pos.CENTER); // Căn giữa nếu cell trống
                        } else {
                            setText(item);
                            setAlignment(Pos.CENTER); // Căn giữa nội dung của cell
                        }
                    }
                };
            });
            tableView.getColumns().add(maMHColumn);

            TableColumn<CourseData, String> nameMHColumn = new TableColumn<>(" Tên môn học ");
            nameMHColumn.setCellValueFactory(new PropertyValueFactory<>("nameMH"));
            nameMHColumn.setPrefWidth(400);
            nameMHColumn.setCellFactory(column -> {
                return new TableCell<CourseData, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setAlignment(Pos.CENTER); // Căn giữa nếu cell trống
                        } else {
                            setText(item);
                            setAlignment(Pos.CENTER); // Căn giữa nội dung của cell
                        }
                    }
                };
            });
            tableView.getColumns().add(nameMHColumn);

            TableColumn<CourseData, String> nameLopColumn = new TableColumn<>(" Lớp ");
            nameLopColumn.setCellValueFactory(new PropertyValueFactory<>("nameLop"));
            nameLopColumn.setPrefWidth(100);
            nameLopColumn.setCellFactory(column -> {
                return new TableCell<CourseData, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setAlignment(Pos.CENTER); // Căn giữa nếu cell trống
                        } else {
                            setText(item);
                            setAlignment(Pos.CENTER); // Căn giữa nội dung của cell
                        }
                    }
                };
            });
            tableView.getColumns().add(nameLopColumn);

            TableColumn<CourseData, Integer> soTinColumn = new TableColumn<>("Số tín");
            soTinColumn.setCellValueFactory(new PropertyValueFactory<>("soTin"));
            soTinColumn.setPrefWidth(100);
            soTinColumn.setCellFactory(column -> {
                return new TableCell<CourseData, Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setAlignment(Pos.CENTER);
                        } else {
                            setText(String.valueOf(item));
                            setAlignment(Pos.CENTER);
                        }
                    }
                };
            });
            tableView.getColumns().add(soTinColumn);


            TableColumn<CourseData, Double> hocPhiColumn = new TableColumn<>(" Học phí ");
            hocPhiColumn.setCellValueFactory(new PropertyValueFactory<>("hocPhi"));
            hocPhiColumn.setPrefWidth(200);
            hocPhiColumn.setCellFactory(column -> {
                return new TableCell<CourseData, Double>() {
                    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setAlignment(Pos.CENTER); // can giua cell trong
                        } else {
                            setText(decimalFormat.format(item));
                            setAlignment(Pos.CENTER); // can giua noi dung
                        }
                    }
                };
            });
            tableView.getColumns().add(hocPhiColumn);

            TableColumn<CourseData, String> loaiHPColumn = new TableColumn<>("  Loại học phần  ");
            loaiHPColumn.setCellValueFactory(new PropertyValueFactory<>("loaiHP"));
            loaiHPColumn.setPrefWidth(200);
            loaiHPColumn.setCellFactory(column -> {
                return new TableCell<CourseData, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setAlignment(Pos.CENTER);
                        } else {
                            setText(item);
                            setAlignment(Pos.CENTER);
                        }
                    }
                };
            });

            tableView.getColumns().add(loaiHPColumn);

            TableColumn<CourseData, Boolean> trangThaiDangKiColumn = new TableColumn<>("    Trạng thái đăng kí     ");
            trangThaiDangKiColumn.setCellValueFactory(cellData -> {
                CourseData courseData = cellData.getValue();
                BooleanProperty property = new SimpleBooleanProperty(courseData.isTrangThaiDangKi());
                property.addListener((observable, oldValue, newValue) -> courseData.setTrangThaiDangKi(newValue));
                return property;
            });
            trangThaiDangKiColumn.setCellFactory(CheckBoxTableCell.forTableColumn(trangThaiDangKiColumn));
            trangThaiDangKiColumn.setPrefWidth(200);
            tableView.getColumns().add(trangThaiDangKiColumn);
            tableView.setItems(courseDataList);
            tableView.setStyle("-fx-font-size: 14px;");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error access");
            System.out.println(e.getMessage());
        }
    }

}