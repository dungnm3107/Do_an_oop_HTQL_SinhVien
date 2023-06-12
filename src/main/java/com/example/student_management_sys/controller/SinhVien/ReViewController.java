package com.example.student_management_sys.controller.SinhVien;

import com.example.student_management_sys.model.DB.DatabaseModel;
import com.example.student_management_sys.model.ReViewData;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Random;

public class ReViewController extends Controller {
    @FXML
    private MenuButton buttonAccount;

    @FXML
    private TableView tableReView;
    public void reViewData(String MSSV) {
        try {
            DatabaseModel databaseModel = new DatabaseModel();
            ObservableList<ReViewData>  reViewDataList = databaseModel.getReView(MSSV);

            tableReView.getColumns().clear();

            TableColumn<ReViewData, Integer> soThuTuColumn = new TableColumn<>("STT");
            soThuTuColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(tableReView.getItems().indexOf(cellData.getValue()) + 1));
            soThuTuColumn.setId("soThuTuColumn");
            soThuTuColumn.setPrefWidth(50);
            tableReView.getColumns().add(0, soThuTuColumn);

            TableColumn<ReViewData, String> maMHColumn = new TableColumn<>("Mã môn học");
            maMHColumn.setCellValueFactory(new PropertyValueFactory<>("maMH"));
            tableReView.getColumns().add(maMHColumn);


            TableColumn<ReViewData, String> nameMHColumn = new TableColumn<>("Tên môn học");
            nameMHColumn.setCellValueFactory(new PropertyValueFactory<>("nameMH"));
            tableReView.getColumns().add(nameMHColumn);

            TableColumn<ReViewData, String> nameLopColumn = new TableColumn<>("Lớp học phần");
            nameLopColumn.setCellValueFactory(new PropertyValueFactory<>("nameLop"));
            tableReView.getColumns().add(nameLopColumn);

            TableColumn<ReViewData, String> nameHKColumn = new TableColumn<>("Tên học kì");
            nameHKColumn.setCellValueFactory(new PropertyValueFactory<>("nameHK"));
            tableReView.getColumns().add(nameHKColumn);

            TableColumn<ReViewData, Double> diemThiColumn = new TableColumn<>("Điểm thi");
            diemThiColumn.setCellValueFactory(new PropertyValueFactory<>("diemThi"));
            tableReView.getColumns().add(diemThiColumn);

            TableColumn<ReViewData, Integer> lePhiColumn = new TableColumn<>("Lệ phí");
            lePhiColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(50000).asObject());
            tableReView.getColumns().add(lePhiColumn);

            TableColumn<ReViewData, Boolean> trangThaiNop = new TableColumn<>("Đã nộp");
            trangThaiNop.setCellValueFactory(cellData -> {
                ReViewData reViewData = cellData.getValue();
                BooleanProperty property = new SimpleBooleanProperty(reViewData.isTrangThaiNop());
                property.addListener((observable, oldValue, newValue) -> reViewData.setTrangThaiNop(newValue));
                return property;
            });
            trangThaiNop.setCellFactory(CheckBoxTableCell.forTableColumn(trangThaiNop));
            tableReView.getColumns().add(trangThaiNop);

            TableColumn<ReViewData, String> diemPhucKhaoColumn = new TableColumn<>("Điểm phúc khảo");
            diemPhucKhaoColumn.setCellValueFactory(cellData -> {
                ReViewData reViewData = cellData.getValue();
                double diemThi = reViewData.getDiemThi();
                double randomValue = new Random().nextDouble() * 1.2;
                double diemPhucKhao = diemThi + randomValue;
                String formattedDiemPhucKhao = String.format("%.1f", diemPhucKhao);
                return new SimpleStringProperty(formattedDiemPhucKhao);
            });
            tableReView.getColumns().add(diemPhucKhaoColumn);



            TableColumn<ReViewData, String> trangThaiColumn = new TableColumn<>("Trạng thái");
            trangThaiColumn.setCellValueFactory(cellData -> new SimpleStringProperty("Phúc khảo thành công"));
            trangThaiColumn.setPrefWidth(150);
            tableReView.getColumns().add(trangThaiColumn);


            tableReView.setItems(reViewDataList);
            tableReView.setStyle("-fx-font-size: 14px;");
            tableReView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error access");
            System.out.println(e.getMessage());
        }
    }
}
