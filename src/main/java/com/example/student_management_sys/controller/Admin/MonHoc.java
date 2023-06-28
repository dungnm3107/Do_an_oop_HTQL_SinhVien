package com.example.student_management_sys.controller.Admin;


import com.example.student_management_sys.controller.Admin.PopUp.PhanCongGV;
import com.example.student_management_sys.controller.Admin.PopUp.QlyMH;
import com.example.student_management_sys.controller.Admin.SinhVien;
import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.DB.AdminDatabase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MonHoc extends ControllerAdmin {

    public Button SinhVien;

    @FXML
    TableView table;

    @FXML
    private TextField tfTimKiem;
    @FXML
    private Button btn_timKiem;

private void loadFile(CourseData courseData) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/Admin/Small/QlyMH.fxml"));
        Parent parent = loader.load();
        QlyMH controller = loader.getController();
        controller.setCourseData(courseData);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();


        stage.setScene(scene);

        stage.show();
    } catch (Exception e) {

        System.out.println(e);
    }
}




    public void setMHView(){
        String query = tfTimKiem.getText();
        AdminDatabase Am = new AdminDatabase();
        ObservableList<CourseData> list = Am.timKiemMonHoc(query);
//        String maMH, String nameMH, String soTin, String loaiHP
        table.getColumns().clear();
        TableColumn<CourseData, String> sttColumn = new TableColumn<>("STT");
        sttColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(table.getItems().indexOf(column.getValue()) + 1).asString());
        table.getColumns().add(sttColumn);

        TableColumn<CourseData, String> maMonHocColumn = new TableColumn<>("Mã môn học");
        maMonHocColumn.setCellValueFactory(new PropertyValueFactory<>("maMH"));
        table.getColumns().add(maMonHocColumn);

        TableColumn<CourseData, String> tenMonHocColumn = new TableColumn<>("Tên môn học");
        tenMonHocColumn.setCellValueFactory(new PropertyValueFactory<>("nameMH"));
        table.getColumns().add(tenMonHocColumn);

        TableColumn<CourseData, String> soTinColumn = new TableColumn<>("Số tín chỉ");
        soTinColumn.setCellValueFactory(new PropertyValueFactory<>("soTin"));
        table.getColumns().add(soTinColumn);

        TableColumn<CourseData, String> loaiHocPhanColumn = new TableColumn<>("Loại học phần");
        loaiHocPhanColumn.setCellValueFactory(new PropertyValueFactory<>("loaiHP"));
        table.getColumns().add(loaiHocPhanColumn);
        TableColumn<CourseData, Void> actionColumn = new TableColumn<>("Action");
        table.getColumns().add(actionColumn);
        actionColumn.setCellFactory(column -> new TableCell<CourseData, Void>() {
            private final Button loadFileButton = new Button("Sửa");

            {
                loadFileButton.setOnAction(event -> {
                    CourseData courseData = getTableRow().getItem();
                    if (courseData != null) {
                        // Thực hiện hành động "load file" ở đây
                        loadFile(courseData);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(loadFileButton);
                }
            }
        });

        TableColumn<CourseData, Void> deleteColumn = new TableColumn<>("Delete");
        table.getColumns().add(deleteColumn);
        deleteColumn.setCellFactory(column -> new TableCell<CourseData, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    CourseData courseData = getTableRow().getItem();
                    if (courseData != null) {
                        Am.deleteMonHoc(courseData.getMaMH());
                        setMHView();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

//        add action "Phân công giảng viên"
        TableColumn<CourseData, Void> phanCongColumn = new TableColumn<>("Phân công giảng viên");
        table.getColumns().add(phanCongColumn);
        phanCongColumn.setCellFactory(column -> new TableCell<CourseData, Void>() {
            private final Button phanCongButton = new Button("Phân công giảng viên");

            {
                phanCongButton.setOnAction(event -> {
                    CourseData courseData = getTableRow().getItem();
                    if (courseData != null) {
                        // Thực hiện hành động "load file" ở đây
                        phanCong(courseData);
                    }
                });
            }

            private void phanCong(CourseData courseData) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/Admin/Small/phanCongGV.fxml"));
                    Parent parent = loader.load();
                    PhanCongGV controller = loader.getController();
                    controller.setCourseData(courseData);

                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();

                    stage.setScene(scene);

                    stage.show();
                } catch (Exception e) {
                        System.out.println(e);
                }
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);

                } else {
                    setGraphic(phanCongButton);
                }
            }
        });
        table.setItems(list);
    }

    public void btn_giaoVienClicked() {

    }
}


