package com.example.student_management_sys.controller.Admin.PopUp;


import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.DB.AdminDatabase;
import com.example.student_management_sys.model.GiaoVien;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class PhanCongGV extends QlyMH {
    @FXML
    TextField nameMH;
    @FXML
    TextField maMH;
    @FXML
    TextField soTC;
    @FXML
    ComboBox loaiHP;

    @FXML
    TextField maGV;
    @FXML
    TableView table;
//    init
    public void initialize(){
        maMH.setEditable(false);
        nameMH.setEditable(false);
        soTC.setEditable(false);
        loaiHP.setEditable(false);
    }
    public void Huy(){
        Stage stage = (Stage) nameMH.getScene().getWindow();
        stage.close();
    }
    public void Sua(){
        AdminDatabase adminDatabase = new AdminDatabase();
        adminDatabase.PhanCongGV(maMH.getText(),maGV.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Phân công giảng viên");
        alert.setHeaderText("Phân công giảng viên thành công");
        alert.showAndWait();
    }

    public void setCourseData(CourseData courseData){
        nameMH.setText(courseData.getNameMH());
        maMH.setText(courseData.getMaMH());
        maMH.setEditable(false);
        soTC.setText(String.valueOf(courseData.getSoTin()));
        loaiHP.getItems().addAll("Bắt buộc","Tự chọn");
        loaiHP.getSelectionModel().select(courseData.getLoaiHP());
        AdminDatabase adminDatabase = new AdminDatabase();
        String maGV1 = adminDatabase.getMaGV(courseData.getMaMH());
        maGV1 = maGV1.replaceAll("\\s+","");
        maGV.setText(maGV1);

    }
    public void setTable(){
        AdminDatabase adminDatabase = new AdminDatabase();
        String query = maGV.getText() ;
        ObservableList<GiaoVien> list = adminDatabase.timGVMinh(query);
        table.getColumns().clear();
        TableColumn<GiaoVien, String> sttColumn = new TableColumn<>("STT");
        sttColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(table.getItems().indexOf(column.getValue()) + 1).asString());
        table.getColumns().add(sttColumn);

        TableColumn<GiaoVien, String> maGVColumn = new TableColumn<>("Mã giảng viên");
        maGVColumn.setCellValueFactory(new PropertyValueFactory<>("maGV"));
        table.getColumns().add(maGVColumn);

        TableColumn<GiaoVien, String> tenGVColumn = new TableColumn<>("Tên giảng viên");
        tenGVColumn.setCellValueFactory(new PropertyValueFactory<>("nameCN"));
        table.getColumns().add(tenGVColumn);

        TableColumn<GiaoVien, String> trinhDoColumn = new TableColumn<>("Trình độ");
        trinhDoColumn.setCellValueFactory(new PropertyValueFactory<>("trinhDo"));
        table.getColumns().add(trinhDoColumn);

        TableColumn<GiaoVien, String> sdtColumn = new TableColumn<>("Số điện thoại");
        sdtColumn.setCellValueFactory(new PropertyValueFactory<>("sdtCN"));
        table.getColumns().add(sdtColumn);

        TableColumn<GiaoVien, Void> actionColumn = new TableColumn<>("Action");
        table.getColumns().add(actionColumn);
        actionColumn.setCellFactory(column -> new TableCell<GiaoVien, Void>() {
            private final Button PhanCong = new Button("Phân công");

            {
                PhanCong.setOnAction(event -> {
                    GiaoVien giaoVien = getTableRow().getItem();
                    if (giaoVien != null) {
                        PhanCong();
                    }
                });
            }
            private void PhanCong() {
                GiaoVien giaoVien = getTableRow().getItem();
                maGV.setText(giaoVien.getMaGV());
                Sua();

            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : PhanCong);
            }
        });
        table.setItems(list);

    }

}
