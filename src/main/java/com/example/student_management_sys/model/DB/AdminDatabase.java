package com.example.student_management_sys.model.DB;

import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDatabase extends DatabaseModel {

    private Connection connection;

    public AdminDatabase() {
        connection = ConnectionDatabase.getConnection();
    }
    public ObservableList<Student> timKiem(String queries){
        List<String> list = new ArrayList<>();
        ObservableList <Student> listStudent = FXCollections.observableArrayList();
        String query = "\n" +
                "SELECT *\n" +
                "FROM (\n" +
                "    SELECT CONCAT_WS('/', SV.Ma_SV, caNhan.Name_CN, Gender, TrangThai, caNhan.Que_Quan, Name_Lop,ldt.Name_Loai, caNhan.Ngay_Sinh, caNhan.Sdt_CN, hdt.Name_He, chuyenNganh.Name_ChuyenNganh, nganhHoc.Name_Nganh,NgayVao) AS concatenated_values\n" +
                "    FROM sinhVien SV\n" +
                "    INNER JOIN caNhan ON SV.CCCD = caNhan.CCCD\n" +
                "    INNER JOIN chuyenNganh ON chuyenNganh.Ma_ChuyenNganh = SV.Ma_ChuyenNganh\n" +
                "    INNER JOIN loaiDaoTao ldt ON ldt.Ma_Loai = SV.Ma_Loai\n" +
                "    INNER JOIN heDaoTao hdt ON hdt.Ma_He = ldt.Ma_He\n" +
                "    INNER JOIN nganhHoc ON nganhHoc.Ma_Nganh = chuyenNganh.Ma_Nganh\n" +
                ") AS subquery\n" +
                "WHERE concatenated_values LIKE N'%" + queries + "%'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String thongTin = resultSet.getString("concatenated_values");
                list.add(thongTin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (String s : list) {

            String[] arr = s.split("/");
            Student student = new Student(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7],arr[8], arr[9], arr[10], arr[11], arr[12]);
            listStudent.add(student);
        }
        return listStudent;
    }
    public ObservableList<CourseData> timKiemMonHoc(String queries){
        String query = "\n" +
                "select * from(\n" +
                "SELECT CONCAT_WS(';', \n" +
                "    Ma_MH, Name_Mh, So_Tin, Loai_HP\n" +
                ") AS mh\n" +
                "FROM monHoc\n" +
                "\n" +
                "WHERE Ma_MH NOT IN (\n" +
                "    SELECT Ma_MH FROM Trash_MH\n" +
                ")\n" +
                ") as monhoc\n" +
                "WHERE mh LIKE N'%"+queries+"%'";
        List<String> list = new ArrayList<>();
        ObservableList <CourseData> listCourse = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String thongTin = resultSet.getString("mh");
                list.add(thongTin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (String s : list) {

            String[] arr = s.split(";");
            CourseData courseData = new CourseData(arr[0], arr[1], arr[2], arr[3]);
            listCourse.add(courseData);
        }
        return listCourse;
    }


    public void deleteMonHoc(String maMH){
        String query = "INSERT INTO Trash_MH values (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, maMH);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Không thể xóa môn học này");
        }
    }
    public void updateMonHoc(CourseData CD){
        String query = "UPDATE monHoc SET Name_MH = ?, So_Tin = ?, Loai_HP = ? WHERE Ma_MH = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, CD.getNameMH());
            String soTin = String.valueOf(CD.getSoTin());
            statement.setString(2, soTin);
            statement.setString(3, CD.getLoaiHP());
            statement.setString(4, CD.getMaMH());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Không thể cập nhật môn học này");
        }
    }
    public static void main(String[] args) {
        AdminDatabase adminDatabase = new AdminDatabase();
        ObservableList<CourseData> list = adminDatabase.timKiemMonHoc("Vật Lý");

    }
}
