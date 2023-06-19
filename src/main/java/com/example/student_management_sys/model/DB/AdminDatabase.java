package com.example.student_management_sys.model.DB;

import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.GiaoVien;
import com.example.student_management_sys.model.Student;
import com.example.student_management_sys.model.Subjects;
import com.example.student_management_sys.model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
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
//
        String query = "\n" +
                "select * from(\n" +
                "SELECT CONCAT_WS(';', \n" +
                "    Ma_MH, Name_Mh, So_Tin, Loai_HP\n" +
                ") AS mh\n" +
                "FROM monHoc\n" +
                "\n" +
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

    public ObservableList<Teacher> getAllActiveTeachers() {
        String query = "\n" +
                "SELECT cn.*, gv.Ma_GV, gv.TrinhDo " +
        "FROM giaoVien gv join caNhan cn on cn.CCCD = gv.CCCD where Ma_GV not in (select Ma_GV from trashGV)";
        ObservableList <Teacher> teacherObservableList = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Teacher currentTeacher = new Teacher();
                currentTeacher.setCCCD(resultSet.getString(1));
                currentTeacher.setMaGV(resultSet.getString(8));
                currentTeacher.setHoTen(resultSet.getString(2));
                currentTeacher.setEmail(resultSet.getString(3));
                currentTeacher.setNgaySinh(resultSet.getString(4));
                currentTeacher.setGioiTinh(resultSet.getString(5));
                currentTeacher.setSoDienThoai(resultSet.getString(6));
                currentTeacher.setQueQuan(resultSet.getString(7));
                currentTeacher.setTrinhDo(resultSet.getString(9));
                teacherObservableList.add(currentTeacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teacherObservableList;
    }

    public Teacher getGiaoVien(String maGV) {
        String query = "\n" +
                "SELECT cn.*, gv.Ma_GV, gv.TrinhDo " +
                "FROM giaoVien gv join caNhan cn on cn.CCCD = gv.CCCD where Ma_GV = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, maGV);
            ResultSet resultSet = statement.executeQuery();
            Teacher currentTeacher = new Teacher();
            while (resultSet.next()) {
                currentTeacher.setCCCD(resultSet.getString(1));
                currentTeacher.setMaGV(resultSet.getString(8));
                currentTeacher.setHoTen(resultSet.getString(2));
                currentTeacher.setEmail(resultSet.getString(3));
                currentTeacher.setNgaySinh(resultSet.getString(4));
                currentTeacher.setGioiTinh(resultSet.getString(5));
                currentTeacher.setSoDienThoai(resultSet.getString(6));
                currentTeacher.setQueQuan(resultSet.getString(7));
                currentTeacher.setTrinhDo(resultSet.getString(9));
            }
            return currentTeacher;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void insertGiaoVien(Teacher teacher) {
        String query = "\n" +
                "INSERT INTO caNhan " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, teacher.getCCCD());
            statement.setString(2, teacher.getHoTen());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getNgaySinh());
            statement.setString(5, teacher.getGioiTinh());
            statement.setString(6, teacher.getSoDienThoai());
            statement.setString(7, teacher.getQueQuan());
            statement.executeUpdate();

            query = "\n" +
                    "INSERT INTO giaoVien " +
                    "VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, teacher.getMaGV());
            statement.setString(2, teacher.getCCCD());
            statement.setString(3, teacher.getTrinhDo());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public boolean isGiaoVienInTrashGV(String maGV) {
        String query = "\n" +
                "SELECT Ma_GV " +
                "FROM giaoVien WHERE Ma_GV = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maGV);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getRow() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void removeGiaoVienFromTrashGV(String maGV) {
        String query = "\n" +
                "DELETE FROM trashGV " +
                "WHERE Ma_GV = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maGV);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateGiaoVien(Teacher teacher) {
        String query = "\n" +
                "UPDATE caNhan " +
                "SET Name_CN = ?, Email = ?, Ngay_Sinh = ?, Gender = ?, Sdt_CN = ?, Que_Quan = ? " +
                "WHERE CCCD = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, teacher.getHoTen());
            statement.setString(2, teacher.getEmail());
            statement.setString(3, teacher.getNgaySinh());
            statement.setString(4, teacher.getGioiTinh());
            statement.setString(5, teacher.getSoDienThoai());
            statement.setString(6, teacher.getQueQuan());
            statement.setString(7, teacher.getCCCD());
            statement.executeUpdate();

            query = "\n" +
                    "UPDATE giaoVien " +
                    "SET Ma_GV = ?, TrinhDo = ? " +
                    "WHERE CCCD = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, teacher.getMaGV());
            statement.setString(2, teacher.getTrinhDo());
            statement.setString(3, teacher.getCCCD());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isDateInSemester(Date date) {
        String query = "\n" +
                "SELECT * " +
                "from hocKy " +
                "WHERE ? BETWEEN Ngay_BD AND Ngay_KT";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet != null && resultSet.getRow() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void removeGiaoVien(Teacher teacher) {
        String query = "\n" +
                "INSERT INTO trashGV " +
                "VALUES (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, teacher.getMaGV());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Teacher> timGV(String queries){
        String query = "SELECT cn.*, gv.Ma_GV, gv.TrinhDo FROM giaoVien gv " +
                "INNER JOIN caNhan cn ON cn.CCCD = gv.CCCD " +
                "where CONCAT_WS(';', gv.CCCD, cn.Name_CN, cn.Sdt_CN, cn.Que_Quan, cn.Ngay_Sinh, gv.Ma_GV, gv.TrinhDo) like N'%" + queries + "%'";
        List<String> list = new ArrayList<>();
        ObservableList <Teacher> teacherObservableList = FXCollections.observableArrayList();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Teacher currentTeacher = new Teacher();
                currentTeacher.setCCCD(resultSet.getString(1));
                currentTeacher.setMaGV(resultSet.getString(8));
                currentTeacher.setHoTen(resultSet.getString(2));
                currentTeacher.setEmail(resultSet.getString(3));
                currentTeacher.setNgaySinh(resultSet.getString(4));
                currentTeacher.setGioiTinh(resultSet.getString(5));
                currentTeacher.setSoDienThoai(resultSet.getString(6));
                currentTeacher.setQueQuan(resultSet.getString(7));
                currentTeacher.setTrinhDo(resultSet.getString(9));
                teacherObservableList.add(currentTeacher);
            }
            return teacherObservableList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public void deleteMonHoc(String maMH){
        String query = "DELETE FROM giangDay WHERE Ma_MH = '" + maMH + "'\n" +
                "DELETE FROM monHoc WHERE Ma_MH = '" + maMH + "'";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sinh viên đã đăng kí MH, Không thể xóa môn học này");
            alert.show();
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

    public String getMaGV(String maMH) {
//        select Ma_GV from giangDay where Ma_MH = '10020109'
        String query = "select Ma_GV from giangDay where Ma_MH = ?";
        String maGV = "";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, maMH);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                maGV = resultSet.getString("Ma_GV");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return maGV;
    }

    public void PhanCongGV(String maMH, String maGV) {
        String query = "MERGE giangDay AS target\n" +
                "USING (VALUES ('"+maMH+"', '"+maGV+"')) AS source (Ma_MH, Ma_GV)\n" +
                "ON (target.Ma_MH = source.Ma_MH AND target.Ma_GV = source.Ma_GV)\n" +
                "WHEN MATCHED THEN\n" +
                "    UPDATE SET target.Ma_MH = source.Ma_MH, target.Ma_GV = source.Ma_GV\n" +
                "WHEN NOT MATCHED THEN\n" +
                "    INSERT (Ma_MH, Ma_GV) VALUES (source.Ma_MH, source.Ma_GV);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//
//select * from (
//SELECT CONCAT_WS(';',
//    Ma_GV, TrinhDo, Name_CN, Sdt_CN
//) AS concatenated_values
//FROM giaoVien
//INNER JOIN caNhan cn ON cn.CCCD = giaoVien.CCCD
//) as sub
//where concatenated_values like N'%Mai%'
    public ObservableList<GiaoVien> timGVMinh(String queries){
        String query = "select * from (\n" +
                "SELECT CONCAT_WS(';', \n" +
                "    Ma_GV, TrinhDo, Name_CN, Sdt_CN\n" +
                ") AS concatenated_values\n" +
                "FROM giaoVien\n" +
                "INNER JOIN caNhan cn ON cn.CCCD = giaoVien.CCCD\n" +
                ") as sub\n" +
                "where concatenated_values like N'%"+queries+"%'";
        List<String> list = new ArrayList<>();
        ObservableList <GiaoVien> listGV = FXCollections.observableArrayList();
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

            String[] arr = s.split(";");
            GiaoVien giaoVien = new GiaoVien(arr[0], arr[1], arr[2], arr[3]);
            listGV.add(giaoVien);
        }
        return listGV;
    }
}
