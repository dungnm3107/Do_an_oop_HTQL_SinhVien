package com.example.student_management_sys.model.DB;

import com.example.student_management_sys.controller.Admin.PopUp.insertSV;
import com.example.student_management_sys.model.CourseData;
import com.example.student_management_sys.model.GiaoVien;
import com.example.student_management_sys.model.Student;
import com.example.student_management_sys.model.StudentNew;
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

    public ObservableList<Student> timKiem(String queries) {
        List<String> list = new ArrayList<>();
        ObservableList<Student> listStudent = FXCollections.observableArrayList();
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
            Student student = new Student(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11], arr[12]);
            listStudent.add(student);
        }
        return listStudent;
    }

    public ObservableList<CourseData> timKiemMonHoc(String queries) {
        String query = "\n" +
                "select * from(\n" +
                "SELECT CONCAT_WS(';', \n" +
                "    Ma_MH, Name_Mh, So_Tin, Loai_HP\n" +
                ") AS mh\n" +
                "FROM monHoc\n" +
                "\n" +
                ") as monhoc\n" +
                "WHERE mh LIKE N'%" + queries + "%'";
        List<String> list = new ArrayList<>();
        ObservableList<CourseData> listCourse = FXCollections.observableArrayList();
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
        ObservableList<Teacher> teacherObservableList = FXCollections.observableArrayList();

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

    public ObservableList<Teacher> timGV(String queries) {
        String query = "SELECT cn.*, gv.Ma_GV, gv.TrinhDo FROM giaoVien gv " +
                "INNER JOIN caNhan cn ON cn.CCCD = gv.CCCD " +
                "WHERE CONCAT_WS(';', gv.CCCD, cn.Name_CN, cn.Sdt_CN, cn.Que_Quan, cn.Ngay_Sinh, gv.Ma_GV, gv.TrinhDo) LIKE N'%" + queries + "%' " +
                "AND gv.Ma_GV NOT IN (SELECT * FROM TrashGV)";
        List<String> list = new ArrayList<>();
        ObservableList<Teacher> teacherObservableList = FXCollections.observableArrayList();
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

    public void deleteMonHoc(String maMH) {
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


    public void updateMonHoc(CourseData CD) {
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
    public class DuplicateRecordException extends Exception {
        public DuplicateRecordException(String message) {
            super(message);
        }
    }

    public void insertSinhVien(StudentNew studentNew) throws DuplicateRecordException {
        String canhanSql = "INSERT INTO caNhan (CCCD, Name_CN, Email, Ngay_Sinh, Gender, Sdt_CN, Que_Quan) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sinhvienSql = "INSERT INTO sinhVien (Ma_SV, CCCD, Pass_SV, Ma_Loai, Ma_ChuyenNganh, Name_Lop, TrangThai, NgayVao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, CONVERT(date, ?, 23))";

        try (Connection databaseConnection = ConnectionDatabase.getConnection();
             PreparedStatement canhanStatement = databaseConnection.prepareStatement(canhanSql);
             PreparedStatement sinhvienStatement = databaseConnection.prepareStatement(sinhvienSql);
        ) {
            // Kiểm tra sự tồn tại của CCCD trong bảng caNhan
            String checkCCCDExistenceSql = "SELECT CCCD FROM caNhan WHERE CCCD = ?";
            try (PreparedStatement checkCCCDExistenceStatement = databaseConnection.prepareStatement(checkCCCDExistenceSql)) {
                checkCCCDExistenceStatement.setString(1, studentNew.getCCCD());
                ResultSet resultSet = checkCCCDExistenceStatement.executeQuery();

                if (resultSet.next()) {
                    throw new DuplicateRecordException("Sinh viên với CCCD đã tồn tại trong cơ sở dữ liệu");
                }
            }

            // Kiểm tra sự tồn tại của MSSV trong bảng sinhVien
            String checkMSSVExistenceSql = "SELECT Ma_SV FROM sinhVien WHERE Ma_SV = ?";
            try (PreparedStatement checkMSSVExistenceStatement = databaseConnection.prepareStatement(checkMSSVExistenceSql)) {
                checkMSSVExistenceStatement.setString(1, studentNew.getMSSV());
                ResultSet resultSet = checkMSSVExistenceStatement.executeQuery();

                if (resultSet.next()) {
                    throw new DuplicateRecordException("Sinh viên với MSSV đã tồn tại trong cơ sở dữ liệu");
                }
            }

            // Thêm dữ liệu vào bảng caNhan
            canhanStatement.setString(1, studentNew.getCCCD());
            canhanStatement.setString(2, studentNew.getHoTen());
            canhanStatement.setString(3, studentNew.getEmail());
            canhanStatement.setString(4, studentNew.getNgaySinh());
            canhanStatement.setString(5, studentNew.getGioiTinh());
            canhanStatement.setString(6, studentNew.getSoDienThoai());
            canhanStatement.setString(7, studentNew.getQueQuan());
            canhanStatement.executeUpdate();

            // Thêm dữ liệu vào bảng sinhVien
            sinhvienStatement.setString(1, studentNew.getMSSV());
            sinhvienStatement.setString(2, studentNew.getCCCD());
            sinhvienStatement.setString(3, studentNew.getPass());
            sinhvienStatement.setString(4, studentNew.getMa_Loai());
            sinhvienStatement.setString(5, studentNew.getMa_Chuyennganh());
            sinhvienStatement.setString(6, studentNew.getLop());
            sinhvienStatement.setString(7, studentNew.getTrangThai());
            sinhvienStatement.setString(8, studentNew.getNgayVao());
            sinhvienStatement.executeUpdate();

            System.out.println("Insert successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateSinhVien(String maSV, Student student) {
        String updateSinhVienQuery = "UPDATE sinhVien " +
                "SET Name_Lop = ?, TrangThai = ?, NgayVao = ? " +
                "WHERE Ma_SV = ?;";
        String updateCaNhanQuery = "UPDATE caNhan " +
                "SET Name_CN = ?, Ngay_Sinh = ?, Gender = ?, Sdt_CN = ?, Que_Quan = ? " +
                "WHERE CCCD IN (SELECT CCCD FROM sinhVien WHERE Ma_SV = ?)";
        String updateHeDaoTaoQuery = "UPDATE heDaoTao " +
                "SET Name_He = ? " +
                "WHERE Ma_He IN (" +
                "    SELECT Ma_Loai " +
                "    FROM sinhVien " +
                "    WHERE Ma_SV = ?" +
                ")";
        String updateLoaiDaoTaoQuery = "UPDATE loaiDaoTao " +
                "SET Name_Loai = ? " +
                "WHERE Ma_Loai IN (" +
                "    SELECT Ma_Loai " +
                "    FROM sinhVien " +
                "    WHERE Ma_SV = ?" +
                ")";
        String updateNganhHocQuery = "UPDATE nganhHoc " +
                "SET Name_Nganh = ? " +
                "WHERE Ma_Nganh IN (" +
                "    SELECT Ma_Nganh " +
                "    FROM chuyenNganh " +
                "    WHERE Ma_ChuyenNganh IN (" +
                "        SELECT Ma_ChuyenNganh " +
                "        FROM sinhVien " +
                "        WHERE Ma_SV = ?" +
                "    )" +
                ")";
        String updateChuyenNganhQuery = "UPDATE chuyenNganh " +
                "SET Name_ChuyenNganh = ? " +
                "WHERE Ma_ChuyenNganh IN (" +
                "    SELECT Ma_ChuyenNganh " +
                "    FROM sinhVien " +
                "    WHERE Ma_SV = ?" +
                ")";

        try (Connection databaseConnection = ConnectionDatabase.getConnection();
             PreparedStatement updateSinhVienStatement = databaseConnection.prepareStatement(updateSinhVienQuery);
             PreparedStatement updateCaNhanStatement = databaseConnection.prepareStatement(updateCaNhanQuery);
             PreparedStatement updateHeDaoTaoStatement = databaseConnection.prepareStatement(updateHeDaoTaoQuery);
             PreparedStatement updateLoaiDaoTaoStatement = databaseConnection.prepareStatement(updateLoaiDaoTaoQuery);
             PreparedStatement updateNganhHocStatement = databaseConnection.prepareStatement(updateNganhHocQuery);
             PreparedStatement updateChuyenNganhStatement = databaseConnection.prepareStatement(updateChuyenNganhQuery)) {

            databaseConnection.setAutoCommit(false);

            updateSinhVienStatement.setString(1, student.getLop());
            updateSinhVienStatement.setString(2, student.getTrangThai());
            updateSinhVienStatement.setString(3, student.getNgayVao());
            updateSinhVienStatement.setString(4, maSV);

            int rowsAffected = updateSinhVienStatement.executeUpdate();

            if (rowsAffected > 0) {
                updateCaNhanStatement.setString(1, student.getHoTen());
                updateCaNhanStatement.setString(2, student.getNgaySinh());
                updateCaNhanStatement.setString(3, student.getGioiTinh());
                updateCaNhanStatement.setString(4, student.getSoDienThoai());
                updateCaNhanStatement.setString(5, student.getQueQuan());
                updateCaNhanStatement.setString(6, maSV);

                updateCaNhanStatement.executeUpdate();

                updateHeDaoTaoStatement.setString(1, student.getHeDaoTao());
                updateHeDaoTaoStatement.setString(2, maSV);

                updateHeDaoTaoStatement.executeUpdate();

                updateLoaiDaoTaoStatement.setString(1, student.getLoaiDaoTao());
                updateLoaiDaoTaoStatement.setString(2, maSV);

                updateLoaiDaoTaoStatement.executeUpdate();

                updateNganhHocStatement.setString(1, student.getNganhHoc());
                updateNganhHocStatement.setString(2, maSV);

                updateNganhHocStatement.executeUpdate();

                updateChuyenNganhStatement.setString(1, student.getChuyenNganh());
                updateChuyenNganhStatement.setString(2, maSV);

                updateChuyenNganhStatement.executeUpdate();

                databaseConnection.commit();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thành công");
                alert.showAndWait();
            } else {
                databaseConnection.rollback();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Không tìm thấy sinh viên để cập nhật");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Đã xảy ra lỗi khi cập nhật dữ liệu");
            alert.showAndWait();
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
        maMH = maMH.replaceAll("\\s", "");
        maGV = maGV.replaceAll("\\s", "");
        String query = "MERGE giangDay AS target\n" +
                "USING (VALUES ('" + maMH + "', '" + maGV + "')) AS source (Ma_MH, Ma_GV)\n" +
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

    public void themMH(String maMH, String tenMH, String soTin, String loaiHP) {
        String query = "INSERT INTO monHoc VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, maMH);
            statement.setString(2, tenMH);
            statement.setString(3, soTin);
            statement.setString(4, loaiHP);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            String errorCode = throwables.getSQLState();
            if (errorCode.equals("23000")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Mã môn học đã tồn tại");
                alert.show();
            }
        }
//        alert Thành công
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Thêm môn học thành công");
        alert.show();
    }

//     public ObservableList<GiaoVien> timGV(String queries){
//         String query = "select * from (\n" +
//                 "SELECT CONCAT_WS(';', \n" +
//                 "    Ma_GV, TrinhDo, Name_CN, Sdt_CN\n" +
//                 ") AS concatenated_values\n" +
//                 "FROM giaoVien\n" +
//                 "INNER JOIN caNhan cn ON cn.CCCD = giaoVien.CCCD\n" +
//                 ") as sub\n" +
//                 "where concatenated_values like N'%" + queries + "%'";
//         List<String> list = new ArrayList<>();
//         ObservableList<GiaoVien> listGV = FXCollections.observableArrayList();
//         try (PreparedStatement statement = connection.prepareStatement(query)) {
//             ResultSet resultSet = statement.executeQuery();
//             while (resultSet.next()) {
//                 String thongTin = resultSet.getString("concatenated_values");
//                 list.add(thongTin);
//             }
//         } catch (SQLException throwables) {
//             throwables.printStackTrace();
//         }
//         for (String s : list) {

//             String[] arr = s.split(";");
//             GiaoVien giaoVien = new GiaoVien(arr[0], arr[1], arr[2], arr[3]);
//             listGV.add(giaoVien);
//         }
//         return listGV;
//     }

    public ObservableList<GiaoVien> timGVMinh(String queries) {

        String query = "select * from (\n" +
                "SELECT CONCAT_WS(';', \n" +
                "    Ma_GV, TrinhDo, Name_CN, Sdt_CN\n" +
                ") AS concatenated_values\n" +
                "FROM giaoVien\n" +
                "INNER JOIN caNhan cn ON cn.CCCD = giaoVien.CCCD\n" +
                ") as sub\n" +
                "where concatenated_values like N'%" + queries + "%'";
        List<String> list = new ArrayList<>();
        ObservableList<GiaoVien> listGV = FXCollections.observableArrayList();
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
