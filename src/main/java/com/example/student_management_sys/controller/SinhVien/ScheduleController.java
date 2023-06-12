package com.example.student_management_sys.controller.SinhVien;

import com.example.student_management_sys.model.*;
import com.example.student_management_sys.model.DB.DatabaseModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import javafx.collections.FXCollections;





public class ScheduleController  extends Controller {

    String MSSV= username;
    @FXML
    private TextField ngayLH;
    @FXML
    private Button tuanTruoc;
    @FXML
    private Button tuanSau;
    @FXML
    private TableView<LichHoc> tableLichHoc;
    @FXML
    private TableColumn<LichHoc, String> tenMH;
    @FXML
    private TableColumn<LichHoc, String> thu;
    @FXML
    private TableColumn<LichHoc, String> ca;
    @FXML
    private TableColumn<LichHoc, String> phong;
    @FXML
    private TableColumn<LichHoc, String> tenGV;
    @FXML
    private TableColumn<LichHoc, String> sdt;
    @FXML
    private TableColumn<LichHoc, String> email;
    @FXML
    private TableColumn<LichHoc, String> lop;
    @FXML
    private MenuButton buttonAccount;

    private void ScheduleController(){
    }
    public void setText(){
        String date = java.time.LocalDate.now().toString();
        ngayLH.setText(date);
    }
    public String getDate(){
        String date = java.time.LocalDate.now().toString();
        return date;
    }
    public static String getStartOfWeek(String date) {
        System.out.println(date);
        String [] dateSplit = date.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        LocalDate localDate = LocalDate.of(year,month,day);
        LocalDate startOfWeek = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return startOfWeek.toString();
    }

    public static String getEndOfWeek(String date) {
        String[] dateSplit = date.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        LocalDate localDate = LocalDate.of(year, month, day);
        LocalDate endOfWeek = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return endOfWeek.toString();
    }
    public void tuanTruocClicked(){
        String date = ngayLH.getText();
        String[] dateSplit = date.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        if(day-7<=0){
            if(month-1<=0){
                year--;
                month=12;
                day=31-(7-day);
            }
            else{
                month--;
                day=31-(7-day);
            }
        }
        else{
            day-=7;
        }
        String newDate = year+"-"+month+"-"+day;
        ngayLH.setText(newDate);
        setLichHoc(newDate);
    }
    public <T> ObservableList<T> convertArrayListToObservableList(ArrayList<T> arrayList) {
        ObservableList<T> observableList = FXCollections.observableArrayList(arrayList);
        return observableList;
    }
public void tuanSauClicked(){
        String date = ngayLH.getText();
        String[] dateSplit = date.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        if(day+7>31){
            if(month+1>12){
                year++;
                month=1;
                day=day+7-31;
            }
            else{
                month++;
                day=day+7-31;
            }
        }
        else{
            day+=7;
        }
        String newDate = year+"-"+month+"-"+day;
        ngayLH.setText(newDate);
        setLichHoc(newDate);
    }
    public void setLichHoc(String date){
        ObservableList<LichHoc> list =FXCollections.observableArrayList();
        DatabaseModel databaseModel = new DatabaseModel();
        try {
            String ngayBD = getStartOfWeek(date);
            String ngayKT = getEndOfWeek(date);
            list = databaseModel.getLichHoc(MSSV, ngayBD, ngayKT);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tableLichHoc.getColumns().clear();

        TableColumn<LichHoc, Integer> soThuTuColumn = new TableColumn<>("STT");
        soThuTuColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(tableLichHoc.getItems().indexOf(cellData.getValue()) + 1));
        soThuTuColumn.setId("soThuTuColumn");
        tableLichHoc.getColumns().add(0, soThuTuColumn);

        TableColumn<LichHoc, String> maHKColumn = new TableColumn<>("Mã học kì");
        maHKColumn.setCellValueFactory(new PropertyValueFactory<>("maHK"));
        tableLichHoc.getColumns().add(maHKColumn);

        TableColumn<LichHoc, String> maMHColumn = new TableColumn<>("Tên môn học");
        maMHColumn.setCellValueFactory(new PropertyValueFactory<>("nameMH"));
        tableLichHoc.getColumns().add(maMHColumn);

        TableColumn<LichHoc, String> soTinChiColumn = new TableColumn<>("Số tín chỉ");
        soTinChiColumn.setCellValueFactory(new PropertyValueFactory<>("soTin"));
        tableLichHoc.getColumns().add(soTinChiColumn);

        TableColumn<LichHoc, String> maLopColumn = new TableColumn<>("Lớp");
        maLopColumn.setCellValueFactory(new PropertyValueFactory<>("nameLop"));
        tableLichHoc.getColumns().add(maLopColumn);

        TableColumn<LichHoc, String> thuColumn = new TableColumn<>("Thứ");
        thuColumn.setCellValueFactory(new PropertyValueFactory<>("thu"));
        tableLichHoc.getColumns().add(thuColumn);

        TableColumn<LichHoc, String> caColumn = new TableColumn<>("Ca");
        caColumn.setCellValueFactory(new PropertyValueFactory<>("ca"));
        tableLichHoc.getColumns().add(caColumn);

        TableColumn<LichHoc, String> phongColumn = new TableColumn<>("Phòng");
        phongColumn.setCellValueFactory(new PropertyValueFactory<>("phong"));
        tableLichHoc.getColumns().add(phongColumn);

        TableColumn<LichHoc, String> tenGVColumn = new TableColumn<>("Tên giảng viên");
        tenGVColumn.setCellValueFactory(new PropertyValueFactory<>("nameCN"));
        tableLichHoc.getColumns().add(tenGVColumn);

        TableColumn<LichHoc, String> sdtColumn = new TableColumn<>("Số điện thoại");
        sdtColumn.setCellValueFactory(new PropertyValueFactory<>("sdtCN"));
        tableLichHoc.getColumns().add(sdtColumn);

        TableColumn<LichHoc, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableLichHoc.getColumns().add(emailColumn);
        tableLichHoc.setStyle("-fx-font-size: 14px;");

        tableLichHoc.setItems(list);

    }
}
