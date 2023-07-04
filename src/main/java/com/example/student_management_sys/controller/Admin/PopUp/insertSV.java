package com.example.student_management_sys.controller.Admin.PopUp;

import com.example.student_management_sys.model.DB.AdminDatabase;
import com.example.student_management_sys.model.StudentNew;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class insertSV {
    @FXML
    private Button insertSinhVien;
    @FXML
    private TextField tfHoTen1;
    @FXML
    private TextField tfGioiTinh1;
    @FXML
    private TextField tfStatus1;
    @FXML
    private TextField tfAdress1;
    @FXML
    private TextField tfClassQL1;
    @FXML
    private TextField tfBirthDay1;
    @FXML
    private TextField tfSDT1;

    @FXML
    private TextField tfVaoTruong1;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCCCD;
    @FXML
    private TextField tfPass;
    @FXML
    private TextField tfMaSV1;

    @FXML
    private MenuButton menuLDT;
    @FXML
    private MenuButton menuCN;
    private String selectedLDT;
    private String selectedCN;
    @FXML
    public void initialize() {
        // Tạo 3 MenuItem mới
        MenuItem menuItem1 = new MenuItem("Chính quy");
        MenuItem menuItem2 = new MenuItem("Liên thông");
        MenuItem menuItem3 = new MenuItem("Chất lượng cao");

        menuItem1.setUserData("LDT01");
        menuItem2.setUserData("LDT02");
        menuItem3.setUserData("LDT03");
        menuLDT.getItems().addAll(menuItem1, menuItem2, menuItem3);


        menuItem1.setOnAction(e -> {
            selectedLDT = (String) menuItem1.getUserData();
            menuLDT.setText(menuItem1.getText());
        });

        menuItem2.setOnAction(e -> {
            selectedLDT = (String) menuItem2.getUserData();
            menuLDT.setText(menuItem2.getText());
        });

        menuItem3.setOnAction(e -> {
            selectedLDT = (String) menuItem3.getUserData();
            menuLDT.setText(menuItem3.getText());
        });

// Tạo 53 MenuItem mới
        MenuItem menu1 = new MenuItem("Cơ giới hóa xây dựng");
        MenuItem menu2 = new MenuItem("Công nghệ kỹ thuật vật liệu xây dựng");
        MenuItem menu3 = new MenuItem("Công nghệ phần mềm");
        MenuItem menu4 = new MenuItem("Công nghệ thông tin");
        MenuItem menu5 = new MenuItem("Công nghệ vật liệu xây dựng");
        MenuItem menu6 = new MenuItem("Hệ thống kỹ thuật trong công trình");
        MenuItem menu7 = new MenuItem("Khoa học máy tính");
        MenuItem menu8 = new MenuItem("Khoa học máy tính");
        MenuItem menu9 = new MenuItem("Kiến trúc");
        MenuItem menu10 = new MenuItem("Kiến trúc cảnh quan");
        MenuItem menu11 = new MenuItem("Kiến trúc công nghệ");
        MenuItem menu12 = new MenuItem("Kiến trúc công nghệ");
        MenuItem menu13 = new MenuItem("Kiến trúc công nghệ - Liên kết quốc tế");
        MenuItem menu14 = new MenuItem("Kiến trúc dân dụng");
        MenuItem menu15 = new MenuItem("Kiến trúc dân dụng");
        MenuItem menu16 = new MenuItem("Kiến trúc nội thất");
        MenuItem menu17 = new MenuItem("Kiến trúc nội thất");
        MenuItem menu18 = new MenuItem("Kiến trúc, cảnh quan");
        MenuItem menu19 = new MenuItem("Kinh tế");
        MenuItem menu20 = new MenuItem("Kinh tế và quản lý bất động sản");
        MenuItem menu21 = new MenuItem("Kinh tế và quản lý bất động sản");
        MenuItem menu22 = new MenuItem("Kinh tế và quản lý đô thị");
        MenuItem menu23 = new MenuItem("Kinh tế xây dựng");
        MenuItem menu24 = new MenuItem("Kỹ thuật cơ điện");
        MenuItem menu25 = new MenuItem("Kỹ thuật cơ khí");
        MenuItem menu26 = new MenuItem("Kỹ thuật cơ khí");
        MenuItem menu27 = new MenuItem("Kỹ thuật hệ thống");
        MenuItem menu28 = new MenuItem("Kỹ thuật hệ thống và mạng máy tính");
        MenuItem menu29 = new MenuItem("Kỹ thuật máy");
        MenuItem menu30 = new MenuItem("Kỹ thuật môi trường");
        MenuItem menu31 = new MenuItem("Kỹ thuật nhiệt");
        MenuItem menu32 = new MenuItem("Kỹ thuật nhiệt, lạnh");
        MenuItem menu33 = new MenuItem("Kỹ thuật nước - Môi trường nước");
        MenuItem menu34 = new MenuItem("Kỹ thuật ô tô");
        MenuItem menu35 = new MenuItem("Kỹ thuật ô tô");
        MenuItem menu36 = new MenuItem("Kỹ thuật phần mềm");
        MenuItem menu37 = new MenuItem("Kỹ thuật vật liệu");
        MenuItem menu38 = new MenuItem("Kỹ thuật xây dựng");
        MenuItem menu39 = new MenuItem("Kỹ thuật xây dựng");
        MenuItem menu40 = new MenuItem("Kỹ thuật xây dựng công trình giao thông");
        MenuItem menu41 = new MenuItem("Logistics và quản lý chuỗi cung ứng");
        MenuItem menu42 = new MenuItem("Ngành Khoa học máy tính (Mississippi - Hoa Kỳ)");
        MenuItem menu43 = new MenuItem("Ngành Kỹ thuật xây dựng  Mississippi -  Hoa Kỳ)");
        MenuItem menu44 = new MenuItem("Quản lý chuỗi cung ứng");
        MenuItem menu45 = new MenuItem("Quản lý chuỗi cung ứng");
        MenuItem menu46 = new MenuItem("Quản lý hạ tầng, đất đai đô thị");
        MenuItem menu47 = new MenuItem("Quy hoạch - Kiến trúc");
        MenuItem menu48 = new MenuItem("Quy hoạch vùng và đô thị");
        MenuItem menu49 = new MenuItem("Tin học xây dựng");
        MenuItem menu50 = new MenuItem("Xây dựng cầu đường");
        MenuItem menu51 = new MenuItem("Xây dựng cầu đường");
        MenuItem menu52 = new MenuItem("Xây dựng công trình đô thị");
        MenuItem menu53 = new MenuItem("Xây dựng dân dụng và công nghiệp");

// Thiết lập UserData cho từng MenuItem
        menu1.setUserData("KG");
        menu2.setUserData("VL");
        menu3.setUserData("PM");
        menu4.setUserData("IT");
        menu5.setUserData("KSVL");
        menu6.setUserData("HKC");
        menu7.setUserData("CS");
        menu8.setUserData("KSCT");
        menu9.setUserData("KD");
        menu10.setUserData("KDCQC");
        menu11.setUserData("KDEC");
        menu12.setUserData("KDNC");
        menu13.setUserData("XF");
        menu14.setUserData("KDF");
        menu15.setUserData("KDF.");
        menu16.setUserData("KDNTC");
        menu17.setUserData("xe.");
        menu18.setUserData("KDFC");
        menu19.setUserData("KTE");
        menu20.setUserData("KTBDS");
        menu21.setUserData("BDS");
        menu22.setUserData("QD");
        menu23.setUserData("KT");
        menu24.setUserData("MEC");
        menu25.setUserData("ME");
        menu26.setUserData("KM");
        menu27.setUserData("KSGT");
        menu28.setUserData("MHT");
        menu29.setUserData("MN");
        menu30.setUserData("MTC");
        menu31.setUserData("MNE");
        menu32.setUserData("MNEC");
        menu33.setUserData("MNC");
        menu34.setUserData("KOC");
        menu35.setUserData("XE");
        menu36.setUserData("KSDT");
        menu37.setUserData("MSE");
        menu38.setUserData("XDC");
        menu39.setUserData("KTXD");
        menu40.setUserData("HKEC");
        menu41.setUserData("LGT");
        menu42.setUserData("CSQT");
        menu43.setUserData("CEQT");
        menu44.setUserData("CLC");
        menu45.setUserData("CLC.");
        menu46.setUserData("CDQ");
        menu47.setUserData("QHC");
        menu48.setUserData("QH");
        menu49.setUserData("TH");
        menu50.setUserData("CD");
        menu51.setUserData("CD.");
        menu52.setUserData("CDE");
        menu53.setUserData("XD");

// Thiết lập sự kiện cho từng MenuItem
        menu1.setOnAction(e -> {
            selectedCN = (String) menu1.getUserData();
            menuCN.setText(menu1.getText());
        });

        menu2.setOnAction(e -> {
            selectedCN = (String) menu2.getUserData();
            menuCN.setText(menu2.getText());
        });

        menu3.setOnAction(e -> {
            selectedCN = (String) menu3.getUserData();
            menuCN.setText(menu3.getText());
        });

        menu4.setOnAction(e -> {
            selectedCN = (String) menu4.getUserData();
            menuCN.setText(menu4.getText());
        });

        menu5.setOnAction(e -> {
            selectedCN = (String) menu5.getUserData();
            menuCN.setText(menu5.getText());
        });

        menu6.setOnAction(e -> {
            selectedCN = (String) menu6.getUserData();
            menuCN.setText(menu6.getText());
        });

        menu7.setOnAction(e -> {
            selectedCN = (String) menu7.getUserData();
            menuCN.setText(menu7.getText());
        });

        menu8.setOnAction(e -> {
            selectedCN = (String) menu8.getUserData();
            menuCN.setText(menu8.getText());
        });

        menu9.setOnAction(e -> {
            selectedCN = (String) menu9.getUserData();
            menuCN.setText(menu9.getText());
        });

        menu10.setOnAction(e -> {
            selectedCN = (String) menu10.getUserData();
            menuCN.setText(menu10.getText());
        });

        menu11.setOnAction(e -> {
            selectedCN = (String) menu11.getUserData();
            menuCN.setText(menu11.getText());
        });

        menu12.setOnAction(e -> {
            selectedCN = (String) menu12.getUserData();
            menuCN.setText(menu12.getText());
        });

        menu13.setOnAction(e -> {
            selectedCN = (String) menu13.getUserData();
            menuCN.setText(menu13.getText());
        });

        menu14.setOnAction(e -> {
            selectedCN = (String) menu14.getUserData();
            menuCN.setText(menu14.getText());
        });

        menu15.setOnAction(e -> {
            selectedCN = (String) menu15.getUserData();
            menuCN.setText(menu15.getText());
        });

        menu16.setOnAction(e -> {
            selectedCN = (String) menu16.getUserData();
            menuCN.setText(menu16.getText());
        });

        menu17.setOnAction(e -> {
            selectedCN = (String) menu17.getUserData();
            menuCN.setText(menu17.getText());
        });

        menu18.setOnAction(e -> {
            selectedCN = (String) menu18.getUserData();
            menuCN.setText(menu18.getText());
        });

        menu19.setOnAction(e -> {
            selectedCN = (String) menu19.getUserData();
            menuCN.setText(menu19.getText());
        });

        menu20.setOnAction(e -> {
            selectedCN = (String) menu20.getUserData();
            menuCN.setText(menu20.getText());
        });

        menu21.setOnAction(e -> {
            selectedCN = (String) menu21.getUserData();
            menuCN.setText(menu21.getText());
        });

        menu22.setOnAction(e -> {
            selectedCN = (String) menu22.getUserData();
            menuCN.setText(menu22.getText());
        });

        menu23.setOnAction(e -> {
            selectedCN = (String) menu23.getUserData();
            menuCN.setText(menu23.getText());
        });

        menu24.setOnAction(e -> {
            selectedCN = (String) menu24.getUserData();
            menuCN.setText(menu24.getText());
        });

        menu25.setOnAction(e -> {
            selectedCN = (String) menu25.getUserData();
            menuCN.setText(menu25.getText());
        });

        menu26.setOnAction(e -> {
            selectedCN = (String) menu26.getUserData();
            menuCN.setText(menu26.getText());
        });

        menu27.setOnAction(e -> {
            selectedCN = (String) menu27.getUserData();
            menuCN.setText(menu27.getText());
        });

        menu28.setOnAction(e -> {
            selectedCN = (String) menu28.getUserData();
            menuCN.setText(menu28.getText());
        });

        menu29.setOnAction(e -> {
            selectedCN = (String) menu29.getUserData();
            menuCN.setText(menu29.getText());
        });

        menu30.setOnAction(e -> {
            selectedCN = (String) menu30.getUserData();
            menuCN.setText(menu30.getText());
        });

        menu31.setOnAction(e -> {
            selectedCN = (String) menu31.getUserData();
            menuCN.setText(menu31.getText());
        });

        menu32.setOnAction(e -> {
            selectedCN = (String) menu32.getUserData();
            menuCN.setText(menu32.getText());
        });

        menu33.setOnAction(e -> {
            selectedCN = (String) menu33.getUserData();
            menuCN.setText(menu33.getText());
        });

        menu34.setOnAction(e -> {
            selectedCN = (String) menu34.getUserData();
            menuCN.setText(menu34.getText());
        });

        menu35.setOnAction(e -> {
            selectedCN = (String) menu35.getUserData();
            menuCN.setText(menu35.getText());
        });

        menu36.setOnAction(e -> {
            selectedCN = (String) menu36.getUserData();
            menuCN.setText(menu36.getText());
        });

        menu37.setOnAction(e -> {
            selectedCN = (String) menu37.getUserData();
            menuCN.setText(menu37.getText());
        });

        menu38.setOnAction(e -> {
            selectedCN = (String) menu38.getUserData();
            menuCN.setText(menu38.getText());
        });

        menu39.setOnAction(e -> {
            selectedCN = (String) menu39.getUserData();
            menuCN.setText(menu39.getText());
        });

        menu40.setOnAction(e -> {
            selectedCN = (String) menu40.getUserData();
            menuCN.setText(menu40.getText());
        });

        menu41.setOnAction(e -> {
            selectedCN = (String) menu41.getUserData();
            menuCN.setText(menu41.getText());
        });

        menu42.setOnAction(e -> {
            selectedCN = (String) menu42.getUserData();
            menuCN.setText(menu42.getText());
        });

        menu43.setOnAction(e -> {
            selectedCN = (String) menu43.getUserData();
            menuCN.setText(menu43.getText());
        });

        menu44.setOnAction(e -> {
            selectedCN = (String) menu44.getUserData();
            menuCN.setText(menu44.getText());
        });

        menu45.setOnAction(e -> {
            selectedCN = (String) menu45.getUserData();
            menuCN.setText(menu45.getText());
        });

        menu46.setOnAction(e -> {
            selectedCN = (String) menu46.getUserData();
            menuCN.setText(menu46.getText());
        });

        menu47.setOnAction(e -> {
            selectedCN = (String) menu47.getUserData();
            menuCN.setText(menu47.getText());
        });

        menu48.setOnAction(e -> {
            selectedCN = (String) menu48.getUserData();
            menuCN.setText(menu48.getText());
        });

        menu49.setOnAction(e -> {
            selectedCN = (String) menu49.getUserData();
            menuCN.setText(menu49.getText());
        });

        menu50.setOnAction(e -> {
            selectedCN = (String) menu50.getUserData();
            menuCN.setText(menu50.getText());
        });

        menu51.setOnAction(e -> {
            selectedCN = (String) menu51.getUserData();
            menuCN.setText(menu51.getText());
        });

        menu52.setOnAction(e -> {
            selectedCN = (String) menu52.getUserData();
            menuCN.setText(menu52.getText());
        });

        menu53.setOnAction(e -> {
            selectedCN = (String) menu53.getUserData();
            menuCN.setText(menu53.getText());
        });
        menuCN.getItems().addAll(menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu8,menu9,menu10,menu11,menu12,menu13,menu14,menu15,menu16,menu17,menu18,menu19,menu20,menu21,menu22,menu23,menu24,menu25,menu26,menu27,menu28,menu29,menu30,menu31,menu32,menu33,menu34,menu35,menu36,menu37,menu38,menu39,menu40,menu41,menu42,menu43,menu44,menu45,menu46,menu47,menu48,menu49,menu50,menu51,menu52,menu53);
   }

    @FXML
    public void insertSinhVienOnClick() {
        String MSSV1 = tfMaSV1.getText();
        String hoTen1 = tfHoTen1.getText();
        String gioiTinh1 = tfGioiTinh1.getText();
        String ngaySinh1 = tfBirthDay1.getText();
        String queQuan1 = tfAdress1.getText();
        String soDienThoai1 = tfSDT1.getText();
        String email1 = tfEmail.getText();
        String cccd = tfCCCD.getText();
        String lop1 = tfClassQL1.getText();
        String trangThai1 = tfStatus1.getText();
        String ngayVao1 = tfVaoTruong1.getText();
        String Pass = tfPass.getText();
        String maLoai = selectedLDT;
        String maChuyenNganh = selectedCN;


        if (MSSV1.isEmpty() || hoTen1.isEmpty() || gioiTinh1.isEmpty() || ngaySinh1.isEmpty()
                || queQuan1.isEmpty() || soDienThoai1.isEmpty() || email1.isEmpty()
                || cccd.isEmpty() || lop1.isEmpty() || trangThai1.isEmpty()
                || ngayVao1.isEmpty() || Pass.isEmpty() || maLoai.isEmpty() || maChuyenNganh.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin.");
            alert.showAndWait();
            return;
        }
        LocalDate ngaySinhDate = LocalDate.parse(ngaySinh1);
        LocalDate ngayVaoDate = LocalDate.parse(ngayVao1);

        if (ngayVaoDate.isBefore(ngaySinhDate.plusYears(15))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Ngày vào trường không hợp lệ. Vui lòng nhập lại.");
            alert.showAndWait();
            return;
        }


        StudentNew studentNew = new StudentNew(MSSV1, hoTen1, gioiTinh1, ngaySinh1, queQuan1, soDienThoai1, email1, cccd, lop1, maLoai, maChuyenNganh, trangThai1, ngayVao1, Pass);
        AdminDatabase adminDatabase = new AdminDatabase();
        try {
            adminDatabase.insertSinhVien(studentNew);
            // Hiển thị thông báo thành công
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thành công");
            alert.setHeaderText(null);
            alert.setContentText("Thêm sinh viên thành công!");
            alert.showAndWait();
        } catch (AdminDatabase.DuplicateRecordException e) {
            // Hiển thị thông báo lỗi đã tồn tại
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác (nếu có)
            e.printStackTrace();
        }
    }}
