package com.example.student_management_sys.controller.Admin.PopUp;

import com.example.student_management_sys.controller.Admin.GiaoVienController;
import com.example.student_management_sys.model.DB.AdminDatabase;
import com.example.student_management_sys.model.Teacher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class GiaoVienModifyPopupController {
    public Button btn_OK;
    public Button btn_clear;
    public TextField tf_maGV;
    public TextField tf_CCCD;
    public TextField tf_hoVaTen;
    public TextField tf_gioiTinh;
    public TextField tf_email;
    public TextField tf_SDT;
    public TextField tf_ngaySinh;
    public TextField tf_queQuan;
    public TextField tf_trinhDo;

    private Set<TextField> textFieldSet;

    private Teacher selectedTeacher;

    private GiaoVienController parentController;

    public static final int CREATE = 1;
    public static final int UPDATE = 2;

    private int mode;

    public void initialize() {
        AdminDatabase adminDatabase = new AdminDatabase();
        textFieldSet = new HashSet<>();
        textFieldSet.add(tf_maGV);
        textFieldSet.add(tf_CCCD);
        textFieldSet.add(tf_hoVaTen);
        textFieldSet.add(tf_gioiTinh);
        textFieldSet.add(tf_email);
        textFieldSet.add(tf_SDT);
        textFieldSet.add(tf_ngaySinh);
        textFieldSet.add(tf_queQuan);
        textFieldSet.add(tf_trinhDo);

        switch (mode) {
            case CREATE -> {
                tf_maGV.focusedProperty().addListener(new ChangeListener<Boolean>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                    {
                        System.out.println("fuck");
                        if (newPropertyValue)
                        {
                            tf_CCCD.setDisable(false);
                            tf_hoVaTen.setDisable(false);
                            tf_gioiTinh.setDisable(false);
                            tf_email.setDisable(false);
                            tf_SDT.setDisable(false);
                            tf_ngaySinh.setDisable(false);
                            tf_queQuan.setDisable(false);
                            tf_trinhDo.setDisable(false);
                        }
                        else
                        {
                            if (adminDatabase.isGiaoVienInTrashGV(tf_maGV.getText())) {
                                Teacher existedTeacher = adminDatabase.getGiaoVien(tf_maGV.getText());
                                tf_maGV.setText(existedTeacher.getMaGV());
                                tf_CCCD.setText(existedTeacher.getCCCD());
                                tf_hoVaTen.setText(existedTeacher.getHoTen());
                                tf_gioiTinh.setText(existedTeacher.getGioiTinh());
                                tf_email.setText(existedTeacher.getEmail());
                                tf_SDT.setText(existedTeacher.getSoDienThoai());
                                tf_ngaySinh.setText(existedTeacher.getNgaySinh());
                                tf_queQuan.setText(existedTeacher.getQueQuan());
                                tf_trinhDo.setText(existedTeacher.getTrinhDo());
                                tf_CCCD.setDisable(true);
                                tf_hoVaTen.setDisable(true);
                                tf_gioiTinh.setDisable(true);
                                tf_email.setDisable(true);
                                tf_SDT.setDisable(true);
                                tf_ngaySinh.setDisable(true);
                                tf_queQuan.setDisable(true);
                                tf_trinhDo.setDisable(true);
                            }
                        }
                    }
                });
            }
            case UPDATE -> {
                tf_maGV.setText(selectedTeacher.getMaGV());
                tf_CCCD.setText(selectedTeacher.getCCCD());
                tf_hoVaTen.setText(selectedTeacher.getHoTen());
                tf_gioiTinh.setText(selectedTeacher.getGioiTinh());
                tf_email.setText(selectedTeacher.getEmail());
                tf_SDT.setText(selectedTeacher.getSoDienThoai());
                tf_ngaySinh.setText(selectedTeacher.getNgaySinh());
                tf_queQuan.setText(selectedTeacher.getQueQuan());
                tf_trinhDo.setText(selectedTeacher.getTrinhDo());
                tf_CCCD.setDisable(true);
            }
        }
    }



    public void btn_OKClicked() {
        // check textfield set border red if not filled
        boolean isAllTextFieldFilled = true;
        for (TextField currentTextField :
                textFieldSet) {
            if (currentTextField.getText().trim().isEmpty()) {
                isAllTextFieldFilled = false;
                currentTextField.setStyle("-fx-border-color: #F08080 ; -fx-border-width: 2px ;");
            }
        }

        if (!isAllTextFieldFilled) {
            return;
        }

        // check teacher above 18 years old to teach students
        if (Period.between(LocalDate.parse(tf_ngaySinh.getText().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.now()).getYears() <= 18) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Ngày sinh không hợp lệ!\nGiáo viên chưa đủ tuổi để giảng dạy.");
            alert.showAndWait();
            return;
        }

        AdminDatabase adminDatabase = new AdminDatabase();
        try {
            Teacher modifiedTeacher = new Teacher();
            modifiedTeacher.setCCCD(tf_CCCD.getText().trim());
            modifiedTeacher.setMaGV(tf_maGV.getText().trim());
            modifiedTeacher.setHoTen(tf_hoVaTen.getText().trim());
            modifiedTeacher.setGioiTinh(tf_gioiTinh.getText().trim());
            modifiedTeacher.setEmail(tf_email.getText().trim());
            modifiedTeacher.setNgaySinh(tf_ngaySinh.getText().trim());
            modifiedTeacher.setQueQuan(tf_queQuan.getText().trim());
            modifiedTeacher.setSoDienThoai(tf_SDT.getText().trim());
            modifiedTeacher.setTrinhDo(tf_trinhDo.getText().trim());

            switch (mode) {
                case CREATE -> {
                    if (adminDatabase.isGiaoVienInTrashGV(tf_maGV.getText().trim())) {
                        adminDatabase.removeGiaoVienFromTrashGV(tf_maGV.getText().trim());
                    } else {
                        adminDatabase.insertGiaoVien(modifiedTeacher);
                    }
                }
                case UPDATE -> {
                    adminDatabase.updateGiaoVien(modifiedTeacher);
                }
            }
            parentController.updateTable(adminDatabase.getAllActiveTeachers());
            Stage thisStage = (Stage) btn_OK.getScene().getWindow();
            thisStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btn_clearClicked() {
        tf_CCCD.setDisable(false);
        tf_hoVaTen.setDisable(false);
        tf_gioiTinh.setDisable(false);
        tf_email.setDisable(false);
        tf_SDT.setDisable(false);
        tf_ngaySinh.setDisable(false);
        tf_queQuan.setDisable(false);
        tf_trinhDo.setDisable(false);
        tf_maGV.clear();
        tf_CCCD.clear();
        tf_hoVaTen.clear();
        tf_gioiTinh.clear();
        tf_email.clear();
        tf_SDT.clear();
        tf_ngaySinh.clear();
        tf_queQuan.clear();
        tf_trinhDo.clear();
    }

    public void setSelectedTeacher(Teacher selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setParentController(GiaoVienController parentController) {
        this.parentController = parentController;
    }
}
