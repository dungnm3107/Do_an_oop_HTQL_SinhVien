package com.example.student_management_sys.controller.Admin.Small;
//
//<?xml version="1.0" encoding="UTF-8"?>
//
//        <?import javafx.scene.control.Button?>
//        <?import javafx.scene.control.TableView?>
//        <?import javafx.scene.control.TextField?>
//        <?import javafx.scene.layout.AnchorPane?>
//        <?import javafx.scene.shape.Line?>
//        <?import javafx.scene.text.Font?>
//        <?import javafx.scene.text.Text?>
//
//<AnchorPane maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="338.0" prefWidth="630.0" xmlns="http://javafx.com/javafx/20.0.1" xmlns:fx="http://javafx.com/fxml/1" fx:controller="com.example.student_management_sys.controller.Admin.Small.PhanCongGV">
//<children>
//<TextField fx:id="nameMH" layoutX="153.0" layoutY="80.0" />
//<TextField fx:id="maMH" layoutX="153.0" layoutY="36.0" />
//<Text layoutX="68.0" layoutY="54.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Mã môn học" />
//<TextField fx:id="soTC" layoutX="150.0" layoutY="128.0" />
//<Text layoutX="96.0" layoutY="147.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Số tín" />
//<Text layoutX="66.0" layoutY="98.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Tên môn học" />
//<TextField fx:id="loaiHP" layoutX="153.0" layoutY="175.0" />
//<Text layoutX="63.0" layoutY="193.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Loại học phần" />
//<Button fx:id="PhanCong" layoutX="24.0" layoutY="273.0" mnemonicParsing="false" onAction="#Sua" prefHeight="42.0" prefWidth="303.0" style="-fx-background-color: #3f998d; -fx-background-radius: 6 6 6 6;" text="Phân công" textFill="WHITE">
//<font>
//<Font size="16.0" />
//</font>
//</Button>
//<TextField fx:id="maGV" layoutX="153.0" layoutY="219.0" prefHeight="26.0" prefWidth="113.0" />
//<Text layoutX="84.0" layoutY="237.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Giảng viên" />
//<Line endX="9.5" endY="-14.0" layoutX="334.0" layoutY="14.0" startX="9.5" startY="324.0" />
//<TableView fx:id="table" layoutX="366.0" layoutY="12.0" prefHeight="307.0" prefWidth="252.0" />
//<Button fx:id="Sua1" layoutX="276.0" layoutY="219.0" mnemonicParsing="false" onAction="#Sua" prefHeight="26.0" prefWidth="37.0" style="-fx-background-color: #3f998d; -fx-background-radius: 6 6 6 6;" text="Tìm" textFill="WHITE">
//<font>
//<Font size="11.0" />
//</font>
//</Button>
//</children>
//</AnchorPane>

import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.DB.AdminDatabase;
import com.example.student_management_sys.model.GiaoVien;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PhanCongGV extends QlyMH {
    @FXML
    TextField nameMH;
    @FXML
    TextField maMH;
    @FXML
    TextField soTC;
    @FXML
    TextField loaiHP;
    @FXML
    TextField maGV;
    @FXML
    TableView table;
    public void Sua(){
        AdminDatabase adminDatabase = new AdminDatabase();
        adminDatabase.PhanCongGV(maMH.getText(),maGV.getText());
    }
//    overwrite setCourseData
    public void setCourseData(CourseData courseData){
        nameMH.setText(courseData.getNameMH());
        maMH.setText(courseData.getMaMH());
        maMH.setEditable(false);
        soTC.setText(String.valueOf(courseData.getSoTin()));
        loaiHP.setText(courseData.getLoaiHP());
        AdminDatabase adminDatabase = new AdminDatabase();
        maGV.setText(adminDatabase.getMaGV(courseData.getMaMH()));
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
