package com.example.student_management_sys.controller.Admin;
import com.example.student_management_sys.model.DB.*;
import com.example.student_management_sys.model.Subjects;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;


public class AdminUpSubjectsController extends ControllerAdmin{
    @FXML
    private TableView<Subjects> tableSV;
    @FXML
    private TextField tfTimKiem;
    public void setTableView() {
        tableSV.getColumns().clear();
        String query = tfTimKiem.getText();
        DatabaseModel dm = new DatabaseModel();
        ObservableList<Subjects> list = dm.timKiemSubjects(query);

        TableColumn<Subjects, String> sttColumn = new TableColumn<>("STT");
        sttColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableSV.getItems().indexOf(column.getValue()) + 1).asString());
        tableSV.getColumns().add(sttColumn);

        TableColumn<Subjects, String> lopMHColumn = new TableColumn<>("Tên lớp");
        lopMHColumn.setCellValueFactory(new PropertyValueFactory<>("LopMH"));
        tableSV.getColumns().add(lopMHColumn);

        TableColumn<Subjects, String> maSVColumn = new TableColumn<>("Mã sinh viên");
        maSVColumn.setCellValueFactory(new PropertyValueFactory<>("MaSV"));
        tableSV.getColumns().add(maSVColumn);

        TableColumn<Subjects, String> tenSVColumn = new TableColumn<>("Tên sinh viên");
        tenSVColumn.setCellValueFactory(new PropertyValueFactory<>("TenSV"));
        tableSV.getColumns().add(tenSVColumn);

        TableColumn<Subjects, String> maMHColumn = new TableColumn<>("Mã môn học ");
        maMHColumn.setCellValueFactory(new PropertyValueFactory<>("MaMH"));
        tableSV.getColumns().add(maMHColumn);

        TableColumn<Subjects, String> tenMHColumn = new TableColumn<>("Tên môn học");
        tenMHColumn.setCellValueFactory(new PropertyValueFactory<>("TenMH"));
        tableSV.getColumns().add(tenMHColumn);

        TableColumn<Subjects, Float> diemQTColumn = new TableColumn<>("Điểm QT");
        diemQTColumn.setCellValueFactory(new PropertyValueFactory<>("Diem_QT"));
        tableSV.getColumns().add(diemQTColumn);

        TableColumn<Subjects, Float> diemThiColumn = new TableColumn<>("Điểm Thi");
        diemThiColumn.setCellValueFactory(new PropertyValueFactory<>("Diem_Thi"));
        tableSV.getColumns().add(diemThiColumn);

        TableColumn<Subjects, Float> diemBSTaoColumn = new TableColumn<>("Điểm BS");
        diemBSTaoColumn.setCellValueFactory(new PropertyValueFactory<>("Diem_BS"));
        tableSV.getColumns().add(diemBSTaoColumn);

        TableColumn<Subjects, Float> diemKTColumn = new TableColumn<>("Điểm KT");
        diemKTColumn.setCellValueFactory(new PropertyValueFactory<>("Diem_KT"));
        tableSV.getColumns().add(diemKTColumn);

        TableColumn<Subjects, Void> editColumn = new TableColumn<>("Sửa điểm");
        Callback<TableColumn<Subjects, Void>, TableCell<Subjects, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Subjects, Void> call(final TableColumn<Subjects, Void> param) {
                final TableCell<Subjects, Void> cell = new TableCell<>() {
                    private final Button editButton = new Button();

                    {
                        editButton.setOnAction(event -> {
                            int rowIndex = getIndex();
                            Subjects selectedSubject = tableSV.getItems().get(rowIndex);
                            String maSV = selectedSubject.getMaSV();
                            String maMH = selectedSubject.getMaMH();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/Admin/UpDiem.fxml"));
                            try {
                                Parent root = loader.load();
                                UpDiemController controller = loader.getController();
                                controller.setMaSV(maSV);
                                controller.setMaMH(maMH);
                                controller.initialize();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.setTitle("Cập nhật điểm");
                                stage.initModality(Modality.WINDOW_MODAL);
                                stage.initOwner(getScene().getWindow());
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/com/example/student_management_sys/view/edit_icon.png")));
                        editIcon.setFitWidth(16);
                        editIcon.setFitHeight(16);
                        editButton.setGraphic(editIcon);
                        setGraphic(editButton);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(editButton);
                        }
                    }
                };
                return cell;
            }
        };
        editColumn.setCellFactory(cellFactory);
        tableSV.getColumns().add(editColumn);

        tableSV.setItems(list);
    }
}
