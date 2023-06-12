package com.example.student_management_sys.controller.SinhVien;

import com.example.student_management_sys.model.DB.DatabaseModel;
import com.example.student_management_sys.model.LichHoc;
import com.example.student_management_sys.model.Student;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import com.example.student_management_sys.model.KetQuaHocTap;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.List;


import static com.example.student_management_sys.controller.SinhVien.ScheduleController.getEndOfWeek;
import static com.example.student_management_sys.controller.SinhVien.ScheduleController.getStartOfWeek;

public class HomeController extends Controller {
    @FXML
    private ImageView imageHome;
    @FXML
    private TableView<LichHoc> lichHoc;
    @FXML
    private TextField tfclass;
    @FXML
    private TextField tfgender;

    @FXML
    private TextField tfmssv;

    @FXML
    private TextField tfname;

    @FXML
    private TextField tfnganh;

    @FXML
    private MenuItem exitButton;
    @FXML
    private MenuButton buttonAccount;
    @FXML
    private MenuButton menuButton;

    @FXML
    private BarChart barChart_KQHT;
    @FXML
    private CategoryAxis xAxis_MH;
    @FXML
    private NumberAxis yAxis_Diem;

    private DatabaseModel databaseModel;

    @FXML
    public void initialize() {
    }

    public void initBarChart() {
        barChart_KQHT.setLegendVisible(false);
        barChart_KQHT.setAnimated(false);
        xAxis_MH.setLabel("Môn học");
        xAxis_MH.setAnimated(false);
        xAxis_MH.setTickLabelsVisible(false);
        yAxis_Diem.setAutoRanging(false);
        yAxis_Diem.setUpperBound(10);

        databaseModel = new DatabaseModel();
        List<KetQuaHocTap> currentUserKQHTList = null;
        try {
            currentUserKQHTList = databaseModel.getKetQuaHocTap(username, "HK01-2023");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        XYChart.Series series = new XYChart.Series();
        for (KetQuaHocTap currentUserKQHT :
                currentUserKQHTList) {
            series.getData().add(new XYChart.Data(currentUserKQHT.getNameMH(), currentUserKQHT.getDiemKT()));
        }
        barChart_KQHT.getData().add(series);
        for (final XYChart.Series<String, Number> series1 : (ObservableList<XYChart.Series<String, Number>>) barChart_KQHT.getData()) {
            for (final XYChart.Data<String, Number> data : series1.getData()) {
                Tooltip tooltip = new Tooltip();
                tooltip.setStyle("-fx-font-size: 40");
                tooltip.setStyle("-fx-font-weight: bold");
                tooltip.setText(data.getXValue() + "\nĐiểm: " +
                        data.getYValue().toString());
                tooltip.setShowDelay(Duration.seconds(0));
                Tooltip.install(data.getNode(), tooltip);
            }
        }
    }
public void turnOffGet(){
    tfname.setEditable(false);
    tfclass.setEditable(false);
    tfgender.setEditable(false);
    tfmssv.setEditable(false);
    tfnganh.setEditable(false);
}
    public void showInforHomeView(String maSV) {
        try {
            DatabaseModel databaseModel = new DatabaseModel();
            Student std = databaseModel.getInformation(maSV);
            turnOffGet();
            tfmssv.setText(std.getMSSV());
            tfname.setText(std.getHoTen());
            tfgender.setText(std.getGioiTinh());
            tfclass.setText(std.getLop());
            tfnganh.setText(std.getChuyenNganh());
            String gender = std.getGioiTinh();
            String imageName = gender.equalsIgnoreCase("Nam") ? "nam.jpg" : "nu.jpg";
            String imagePath = getClass().getResource("/com/example/student_management_sys/view/" + imageName).toExternalForm();
            Image image = new Image(imagePath);
            imageHome.setImage(image);

        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn thông tin sinh viên");
            System.out.println(e.getMessage());
        }
    }
    public void setLichHoc(){
        // String date = java.time.LocalDate.now().toString();
        String date = "2023-5-19";
        ObservableList<LichHoc> list = FXCollections.observableArrayList();
        DatabaseModel databaseModel = new DatabaseModel();
        try {
            String ngayBD = getStartOfWeek(date);
            String ngayKT = getEndOfWeek(date);
            list = databaseModel.getLichHoc(username, ngayBD, ngayKT);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        lichHoc.getColumns().clear();
        TableColumn<LichHoc, Integer> soThuTuColumn = new TableColumn<>("STT");
        soThuTuColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(lichHoc.getItems().indexOf(cellData.getValue()) + 1));
        soThuTuColumn.setId("soThuTuColumn");
        lichHoc.getColumns().add(0, soThuTuColumn);

        TableColumn<LichHoc, String> maHKColumn = new TableColumn<>("Mã HK");
        maHKColumn.setCellValueFactory(new PropertyValueFactory<>("maHK"));
        lichHoc.getColumns().add(maHKColumn);

        TableColumn<LichHoc, String> maMHColumn = new TableColumn<>("Tên môn học");
        maMHColumn.setCellValueFactory(new PropertyValueFactory<>("nameMH"));
        lichHoc.getColumns().add(maMHColumn);

        TableColumn<LichHoc, String> soTinChiColumn = new TableColumn<>("Số tín chỉ");
        soTinChiColumn.setCellValueFactory(new PropertyValueFactory<>("soTin"));
        lichHoc.getColumns().add(soTinChiColumn);

        TableColumn<LichHoc, String> maLopColumn = new TableColumn<>("Lớp");
        maLopColumn.setCellValueFactory(new PropertyValueFactory<>("nameLop"));
        lichHoc.getColumns().add(maLopColumn);

        TableColumn<LichHoc, String> thuColumn = new TableColumn<>("Thứ");
        thuColumn.setCellValueFactory(new PropertyValueFactory<>("thu"));
        lichHoc.getColumns().add(thuColumn);

        TableColumn<LichHoc, String> caColumn = new TableColumn<>("Ca");
        caColumn.setCellValueFactory(new PropertyValueFactory<>("ca"));
        lichHoc.getColumns().add(caColumn);

        TableColumn<LichHoc, String> phongColumn = new TableColumn<>("Phòng");
        phongColumn.setCellValueFactory(new PropertyValueFactory<>("phong"));
        lichHoc.getColumns().add(phongColumn);

        TableColumn<LichHoc, String> tenGVColumn = new TableColumn<>("Tên giảng viên");
        tenGVColumn.setCellValueFactory(new PropertyValueFactory<>("nameCN"));
        lichHoc.getColumns().add(tenGVColumn);

        TableColumn<LichHoc, String> sdtColumn = new TableColumn<>("Số điện thoại");
        sdtColumn.setCellValueFactory(new PropertyValueFactory<>("sdtCN"));
        lichHoc.getColumns().add(sdtColumn);

        TableColumn<LichHoc, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        lichHoc.getColumns().add(emailColumn);


        lichHoc.setItems(list);
    }
}
