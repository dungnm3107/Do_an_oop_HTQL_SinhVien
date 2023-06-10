package com.example.student_management_sys.controller.SinhVien;

import com.example.student_management_sys.model.DB.DatabaseModel;
import com.example.student_management_sys.model.KetQuaHocTap;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



public class KQHTController extends Controller {

    String maSV = username;
    @FXML
    TableView<KetQuaHocTap> ketQuaHocTap;
    @FXML
    TableColumn<KetQuaHocTap, String> maHK;
    @FXML
    TableColumn<KetQuaHocTap, String> maMH;
    @FXML
    TableColumn<KetQuaHocTap, String> tenMH;
    @FXML
    TableColumn<KetQuaHocTap, String> soTC;
    @FXML
    TableColumn<KetQuaHocTap, String> diemQT;
    @FXML
    TableColumn<KetQuaHocTap, String> diemKT;
    @FXML
    TableColumn<KetQuaHocTap, String> diemThi;
    @FXML
    TableColumn<KetQuaHocTap, String> diemBS;
    @FXML
    private MenuButton mbHocKi;
    @FXML
    private TextField tfDiemTBHK;

    @FXML
    private TextField tfDiemTBTL;

    @FXML
    private TextField tfHocLucHK;

    @FXML
    private TextField tfHocLucTL;

    @FXML
    private TextField tfSoTinDat;

    @FXML
    private TextField tfSoTinDk;

    @FXML
    private TextField tfSoTinNo;
    @FXML
    private Button openPdfButton;
    @FXML
    private MenuButton buttonAccount;




    public void showKetQuaHocTayByHocKi(String maSV) throws SQLException {
        DatabaseModel dm = new DatabaseModel();
        List<String> hocKiList = dm.getHocKi(maSV);
        mbHocKi.getItems().clear();
        for (String hocKi : hocKiList) {
            MenuItem hocKiItem = new MenuItem(hocKi);
            hocKiItem.setOnAction((ActionEvent event) -> {
                try {
                    showKetQuaHocTap(maSV, hocKi);
                    showDiemVaHocLucHK(maSV, hocKi);
                    showTongKet(maSV);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            mbHocKi.getItems().add(hocKiItem);
        }
    }
    public void showKetQuaHocTap(String MSSV, String maHK) throws SQLException {
        DatabaseModel dm = new DatabaseModel();
        try {
            ObservableList<KetQuaHocTap> listKQHT = dm.getKetQuaHocTap(maSV, maHK);
            ketQuaHocTap.getColumns().clear();
            TableColumn<KetQuaHocTap, Integer> soThuTuColumn = new TableColumn<>("STT");
            soThuTuColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(ketQuaHocTap.getItems().indexOf(column.getValue())+1));
            ketQuaHocTap.getColumns().add(soThuTuColumn);

            TableColumn<KetQuaHocTap, String> maHKColumn = new TableColumn<>("Mã học kỳ");
            maHKColumn.setCellValueFactory(new PropertyValueFactory<>("maHK"));
            ketQuaHocTap.getColumns().add(maHKColumn);

            TableColumn<KetQuaHocTap, String> maMHColumn = new TableColumn<>("Mã môn học");
            maMHColumn.setCellValueFactory(new PropertyValueFactory<>("maMH"));
            ketQuaHocTap.getColumns().add(maMHColumn);

            TableColumn<KetQuaHocTap, String> tenMHColumn = new TableColumn<>("Tên môn học");
            tenMHColumn.setCellValueFactory(new PropertyValueFactory<>("nameMH"));
            ketQuaHocTap.getColumns().add(tenMHColumn);

            TableColumn<KetQuaHocTap, String> soTCColumn = new TableColumn<>("Số tín chỉ");
            soTCColumn.setCellValueFactory(new PropertyValueFactory<>("soTin"));
            ketQuaHocTap.getColumns().add(soTCColumn);

            TableColumn<KetQuaHocTap, String> diemQTColumn = new TableColumn<>("Điểm quá trình");
            diemQTColumn.setCellValueFactory(new PropertyValueFactory<>("diemQT"));
            ketQuaHocTap.getColumns().add(diemQTColumn);

            TableColumn<KetQuaHocTap, String> diemBSColumn = new TableColumn<>("Điểm bổ sung");
            diemBSColumn.setCellValueFactory(new PropertyValueFactory<>("diemBS"));
            ketQuaHocTap.getColumns().add(diemBSColumn);


            TableColumn<KetQuaHocTap, String> diemThiColumn = new TableColumn<>("Điểm thi");
            diemThiColumn.setCellValueFactory(new PropertyValueFactory<>("diemThi"));
            ketQuaHocTap.getColumns().add(diemThiColumn);


            TableColumn<KetQuaHocTap, String> diemKTColumn = new TableColumn<>("Điểm kết thúc");
            diemKTColumn.setCellValueFactory(new PropertyValueFactory<>("diemKT"));
            ketQuaHocTap.getColumns().add(diemKTColumn);
            ketQuaHocTap.setStyle("-fx-font-size: 12px;");
            ketQuaHocTap.setItems(listKQHT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public float tinhTrungBinhTungHocKy(String maSV, String maHK) throws SQLException {
        DatabaseModel dm = new DatabaseModel();
        ObservableList<KetQuaHocTap> listKQHT = dm.getKetQuaHocTap(maSV, maHK);
        float tongDiem = 0;
        int soTin = 0 ;
        int soMonHoc = listKQHT.size();
        for (KetQuaHocTap kqht : listKQHT) {
            tongDiem += kqht.getDiemKT()*kqht.getSoTin();
            soTin += kqht.getSoTin();
        }
        return tongDiem / soTin;
    }
    public float tinhTrungBinhTichLuy(String maSV) throws SQLException {
        DatabaseModel dm = new DatabaseModel();
        float tongTrungBinh = 0.0f;
        try {
            List<String> hocKiList = dm.getHocKi(maSV);
            for(String hocKi : hocKiList){
                tongTrungBinh += tinhTrungBinhTungHocKy(maSV, hocKi);
            }
            return tongTrungBinh/hocKiList.size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void showDiemVaHocLucHK(String maSV, String maHK) throws SQLException {
        float diemTrungBinh = tinhTrungBinhTungHocKy(maSV, maHK);
        tfDiemTBHK.setText(String.format("%.2f", diemTrungBinh));
        String hocLuc;
        if (diemTrungBinh < 5) {
            hocLuc = "Yếu";
        } else if (diemTrungBinh >= 5 && diemTrungBinh <= 6.5) {
            hocLuc = "Trung bình";
        } else if (diemTrungBinh > 6.5 && diemTrungBinh < 7.5) {
            hocLuc = "Khá";
        } else if (diemTrungBinh >= 7.5 && diemTrungBinh < 8.5) {
            hocLuc = "Giỏi";
        } else {
            hocLuc = "Xuất sắc";
        }
        tfHocLucHK.setText(hocLuc);
    }
    public void showTongKet(String maSV) throws SQLException {
        DatabaseModel dm = new DatabaseModel();
        ObservableList<KetQuaHocTap> listKQHT = dm.getKetQuaHocTapFull(maSV);
        int tongSoTinDK = 0;
        int tongSoTinNo = 0;
        int tongSoTinDat = 0;
        for (KetQuaHocTap kqht : listKQHT) {
            int soTin = kqht.getSoTin();
            int hoanThanh = kqht.isHoanThanh();
            float diemKT = kqht.getDiemKT();
            tongSoTinDK += soTin;
            if (hoanThanh == 0) {
                tongSoTinDat += soTin;
            } else {
                tongSoTinNo += soTin;
            }
        }
        float diemTrungBinhTichLuy = tinhTrungBinhTichLuy(maSV);
        String hocLuc;
        if (diemTrungBinhTichLuy < 5) {
            hocLuc = "Yếu";
        } else if (diemTrungBinhTichLuy >= 5 && diemTrungBinhTichLuy <= 6.5) {
            hocLuc = "Trung bình";
        } else if (diemTrungBinhTichLuy > 6.5 && diemTrungBinhTichLuy < 7.5) {
            hocLuc = "Khá";
        } else if (diemTrungBinhTichLuy >= 7.5 && diemTrungBinhTichLuy < 8.5) {
            hocLuc = "Giỏi";
        } else {
            hocLuc = "Xuất sắc";
        }
        tfHocLucTL.setText(hocLuc);
        tfSoTinDk.setText(String.valueOf(tongSoTinDK));
        tfSoTinNo.setText(String.valueOf(tongSoTinNo));
        tfSoTinDat.setText(String.valueOf(tongSoTinDat));
        tfDiemTBTL.setText(String.format("%.2f", diemTrungBinhTichLuy));
    }

    @FXML
     public void openPdfButtonClicked() {
        String pdfFilePath = getClass().getResource("/com/example/student_management_sys/view/HocBong.pdf").getPath();

        File pdfFile = new File(pdfFilePath);
        if (pdfFile.exists()) {
            openPDF(pdfFile);
        } else {
            System.out.println("File not found: " + pdfFilePath);
        }
    }
    private void openPDF(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}