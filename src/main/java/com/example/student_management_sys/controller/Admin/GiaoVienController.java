package com.example.student_management_sys.controller.Admin;

import com.example.student_management_sys.controller.Admin.PopUp.GiaoVienModifyPopupController;
import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.DB.AdminDatabase;
import com.example.student_management_sys.model.Teacher;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableViewSkin;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.time.LocalDate;

public class GiaoVienController extends ControllerAdmin{
    public TableView tableGV;
    public Button btn_addNewTeacher;
    public Button btn_deleteTeacher;
    public Button btn_updateTeacher;

    public TextField tfTimKiem;
    public Button btn_timKiem;

    private Teacher selectedTeacher;

    public void initialize() {
        AdminDatabase adminDatabase = new AdminDatabase();
        ObservableList<Teacher> teacherObservableList = adminDatabase.getAllActiveTeachers();
        updateTable(teacherObservableList);
    }

    public void updateTable(ObservableList<Teacher> teacherObservableList) {
        setTableView(teacherObservableList);
        setTableEvent();
    }

    private void setTableEvent() {
        tableGV.setRowFactory(tableView -> {
            final TableRow<Teacher> row = new TableRow<>();

            EventHandler<MouseEvent> mouseEventEventHandler = event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    btn_updateTeacher.setVisible(true);
                    btn_deleteTeacher.setVisible(true);

                    selectedTeacher = row.getItem();
                }
            };
            row.setOnMouseClicked(mouseEventEventHandler);
            row.setOnDragDetected(mouseEventEventHandler);
            return row;
        });
    }

    public void setTableView(ObservableList list) {
        tableGV.getItems().clear();
        tableGV.getColumns().clear();

        TableColumn<CourseData, String> maGiaoVienColumn = new TableColumn<>("Mã giáo viên");
        maGiaoVienColumn.setCellValueFactory(new PropertyValueFactory<>("MaGV"));
        tableGV.getColumns().add(maGiaoVienColumn);

        TableColumn<CourseData, String> cccdColumn = new TableColumn<>("CCCD");
        cccdColumn.setCellValueFactory(new PropertyValueFactory<>("CCCD"));
        tableGV.getColumns().add(cccdColumn);

        TableColumn<CourseData, String> hoVaTenColumn = new TableColumn<>("Họ và tên");
        hoVaTenColumn.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        tableGV.getColumns().add(hoVaTenColumn);

        TableColumn<CourseData, String> gioiTinhColumn = new TableColumn<>("Giới tính");
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        tableGV.getColumns().add(gioiTinhColumn);

        TableColumn<CourseData, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableGV.getColumns().add(emailColumn);

        TableColumn<CourseData, String> soDienThoaiColumn = new TableColumn<>("SĐT");
        soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<>("SoDienThoai"));
        tableGV.getColumns().add(soDienThoaiColumn);

        TableColumn<CourseData, String> ngaySinhColumn = new TableColumn<>("Ngày sinh");
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        tableGV.getColumns().add(ngaySinhColumn);

        TableColumn<CourseData, String> queQuanColumn = new TableColumn<>("Quê quán");
        queQuanColumn.setCellValueFactory(new PropertyValueFactory<>("QueQuan"));
        tableGV.getColumns().add(queQuanColumn);

        TableColumn<CourseData, String> trinhDoColumn = new TableColumn<>("Trình độ");
        trinhDoColumn.setCellValueFactory(new PropertyValueFactory<>("TrinhDo"));
        tableGV.getColumns().add(trinhDoColumn);

        tableGV.setItems(list);
        GUIUtils.autoFitTable(tableGV);
    }

    public void btn_addNewTeacherClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/Admin/teacher_modify_popup.fxml"));
            GiaoVienModifyPopupController controller = new GiaoVienModifyPopupController();
            controller.setMode(GiaoVienModifyPopupController.CREATE);
            controller.setParentController(this);
            loader.setController(controller);
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Thêm giáo viên");

            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {

            System.out.println(e);
        }
    }


    public void btn_deleteTeacherClicked(ActionEvent event) {
        Alert alert;
        AdminDatabase adminDatabase = new AdminDatabase();
        if (adminDatabase.isDateInSemester(Date.valueOf(LocalDate.of(2024, 1, 1)))) {
            alert = new Alert(Alert.AlertType.ERROR, "Đang trong thời gian học kỳ, vui lòng thử lại sau khi học kỳ kết thúc.", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc muốn loại bỏ giáo viên này?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                adminDatabase.removeGiaoVien(selectedTeacher);
                updateTable(adminDatabase.getAllActiveTeachers());
                alert.close();
            }
        }


        if (alert.getResult() == ButtonType.YES) {
            //do stuff
        }
    }

    public void btn_updateTeacherClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/Admin/teacher_modify_popup.fxml"));

            GiaoVienModifyPopupController controller = new GiaoVienModifyPopupController();
            controller.setSelectedTeacher(selectedTeacher);
            controller.setMode(GiaoVienModifyPopupController.UPDATE);
            controller.setParentController(this);
            loader.setController(controller);
            Parent parent = loader.load();

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Sửa thông tin giáo viên");

            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public void btn_timKiemClicked() {
        String searchString = tfTimKiem.getText();
        if (searchString == null || searchString.isEmpty()) {
            showNotFoundAlert();
        } else {
            try {
                AdminDatabase adminDatabase = new AdminDatabase();
                ObservableList<Teacher> foundGVList = adminDatabase.timGV(searchString);
                if (foundGVList.size() == 0) {
                    showNotFoundAlert();
                } else {
                    setTableView(foundGVList);
                    setTableEvent();
                }

            } catch (Exception e) {
                e.printStackTrace();
                showNotFoundAlert();
            }
        }
    }

    private void showNotFoundAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText("Không tìm thấy.");
        alert.showAndWait();
    }

    public class GUIUtils {
        private static Method columnToFitMethod;

        static {
            try {
                columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
                columnToFitMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        public static void autoFitTable(TableView tableView) {
            tableView.getItems().addListener(new ListChangeListener<Object>() {
                @Override
                public void onChanged(Change<?> c) {
                    for (Object column : tableView.getColumns()) {
                        try {
                            columnToFitMethod.invoke(tableView.getSkin(), column, -1);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
