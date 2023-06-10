package com.example.student_management_sys.controller.Admin;

import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.DB.AdminDatabase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MonHoc extends AdminController{

    @FXML
    TableView table;

    @FXML
    private TextField tfTimKiem;
    @FXML
    private Button btn_timKiem;
    @FXML

    TextField nameMH = new TextField();
//    load file src/main/resources/com/example/student_management_sys/view/Admin/QlyMH.fxml
private void loadFile(CourseData courseData) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/Admin/QlyMH.fxml"));
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
        table.getColumns().clear();
        String query = tfTimKiem.getText();
        AdminDatabase dm = new AdminDatabase();
        ObservableList<CourseData> list = dm.timKiemMonHoc(query);
//        String maMH, String nameMH, String soTin, String loaiHP

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
            private final Button loadFileButton = new Button("Load File");

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


        table.setItems(list);
    }
}


