package com.example.student_management_sys.model.DB;


import com.example.student_management_sys.model.*;
import com.example.student_management_sys.model.DB.ConnectionDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseModel {

  private Connection connection;

  public DatabaseModel() {
    connection = ConnectionDatabase.getConnection();
  }
  public void updateStudentInformation(String updateQuery,
                                       String hoTen,
                                       String gioiTinh,
                                       String trangThai,
                                       String queQuan,
                                       Date ngaySinh,
                                       String maSV,
                                       String lop,
                                       String chuyenNganh,
                                       String loaiDaoTao,
                                       String sdt,
                                       String ngayVaoTruong) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = ConnectionDatabase.getConnection();

      preparedStatement = connection.prepareStatement(updateQuery);
      preparedStatement.setString(1, hoTen);
      preparedStatement.setString(2, gioiTinh);
      preparedStatement.setString(3, trangThai);
      preparedStatement.setString(4, queQuan);
      preparedStatement.setDate(5, (java.sql.Date) ngaySinh);
      preparedStatement.setString(6, maSV);
      preparedStatement.setString(7, lop);
      preparedStatement.setString(8, chuyenNganh);
      preparedStatement.setString(9, loaiDaoTao);
      preparedStatement.setString(10, sdt);
      preparedStatement.setString(11, ngayVaoTruong);

      preparedStatement.executeUpdate();
    } finally {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
      if (connection != null) {
        connection.close();
      }
    }
  }
  public ObservableList<Subjects> timKiemSubjects(String queries){
    List <String> list = new ArrayList<>();
    ObservableList <Subjects> listSubjects = FXCollections.observableArrayList();
    String query = "\n" +
            "SELECT *\n" +
            "FROM (\n" +
            "    SELECT CONCAT_WS(',',lh.Name_Lop, sv.Ma_SV, cn.Name_CN,mh.Ma_MH, mh.Name_MH, bd.Diem_QT, bd.Diem_Thi, bd.Diem_BS, bd.Diem_KT) AS concatenated_values\n" +
            "    FROM lopHoc lh \n" +
            "    JOIN sinhVien sv ON lh.Name_Lop = sv.Name_Lop\n" +
            "    join caNhan cn on cn.CCCD = sv.CCCD\n" +
            "    JOIN dangKyMonHoc dk ON sv.Ma_SV = dk.Ma_SV JOIN monHoc mh ON dk.Ma_MH = mh.Ma_MH \n" +
            "    JOIN bangDiem bd ON sv.Ma_SV = bd.Ma_SV AND dk.Ma_MH = bd.Ma_MH\n" +
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

      String[] arr = s.split(",");
      Subjects subjects = new Subjects(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8]);
      listSubjects.add(subjects);
    }
    return listSubjects;
  }
  public ObservableList<LichHoc> getLichHoc(
    String maSV,
    String ngayBatDau,
    String ngayKetThuc
  ) throws SQLException {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      String query =
        "SELECT Ma_HK,Name_MH, So_Tin, lh.Name_Lop, Thu, Ca, Phong, " +
        "Name_CN, CN.Sdt_CN, CN.Email " +
        "FROM sinhVien SV " +
        "INNER JOIN dangKyMonHoc dk ON dk.Ma_SV = SV.Ma_SV " +
        "INNER JOIN monHoc mh ON mh.Ma_MH = dk.Ma_MH " +
        "INNER JOIN lichHoc lh ON lh.Ma_MH = dk.Ma_MH " +
        "INNER JOIN giangDay gd ON gd.Ma_MH = lh.Ma_MH " +
        "INNER JOIN giaoVien GV ON GV.Ma_GV = gd.Ma_GV " +
        "INNER JOIN caNhan CN ON CN.CCCD = GV.CCCD " +
        "WHERE dk.Ma_SV = ?  " +
        "AND Ngay_BD <= ? AND ? <= Ngay_KT ORDER BY Thu, Ca;";

      stmt = connection.prepareStatement(query);
      stmt.setString(1, maSV);
      stmt.setString(2, ngayBatDau);
      stmt.setString(3, ngayKetThuc);

      rs = stmt.executeQuery();
      ObservableList<LichHoc> listLichHoc = FXCollections.observableArrayList();
      while (rs.next()) {
        LichHoc lh = new LichHoc(
          rs.getString(1),
          rs.getString(2),
          rs.getString(3),
          rs.getString(4),
          rs.getString(5),
          rs.getString(6),
          rs.getString(7),
            rs.getString(8),
            rs.getString(9),
                rs.getString(10)
        );
        listLichHoc.add(lh);
      }
      return listLichHoc;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
    }
  }

  public List<String> getHocKi(String maSV) throws SQLException {
    List<String> hocKiList = new ArrayList<>();

    String query = "SELECT distinct hocKy.Ma_HK\n" +
            "\tFROM sinhVien\n" +
            "\tINNER JOIN dangKyMonHoc ON sinhVien.Ma_SV = dangKyMonHoc.Ma_SV\n" +
            "\tINNER JOIN hocKy ON dangKyMonHoc.Ma_HK = hocKy.Ma_HK\n" +
            "\tWHERE sinhVien.Ma_SV = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, maSV);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        String hocKi = resultSet.getString("Ma_HK");
        hocKiList.add(hocKi);
      }
    }
    return hocKiList;
  }
  public ObservableList<KetQuaHocTap> getKetQuaHocTap(String maSV, String maHK) throws SQLException {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      String query =
        "SELECT Ma_HK, mh.Ma_MH, Name_MH, So_Tin, Loai_HP, Diem_QT, Diem_BS, Diem_Thi, Diem_KT, HoanThanh " +
        "FROM bangDiem BD " +
        "INNER JOIN sinhVien SV ON SV.Ma_SV = BD.Ma_SV " +
        "INNER JOIN caNhan CN ON CN.CCCD = SV.CCCD " +
        "INNER JOIN monHoc mh ON mh.Ma_MH = BD.Ma_MH " +
        "INNER JOIN dangKyMonHoc ON SV.Ma_SV = dangKyMonHoc.Ma_SV AND mh.Ma_MH = dangKyMonHoc.Ma_MH " +
        "WHERE SV.Ma_SV = ? and Ma_HK = ?";

      stmt = connection.prepareStatement(query);
      stmt.setString(1, maSV);
      stmt.setString(2, maHK);

      rs = stmt.executeQuery();
      ObservableList<KetQuaHocTap> listKetQuaHocTap = FXCollections.observableArrayList();
      while(rs.next()) {
        KetQuaHocTap kqht = new KetQuaHocTap(
          rs.getString(1),
          rs.getString(2),
          rs.getString(3),
          rs.getInt(4),
          rs.getString(5),
          rs.getFloat(6),
          rs.getFloat(7),
          rs.getFloat(8),
          rs.getFloat(9),
          rs.getInt(10)
        );
        listKetQuaHocTap.add(kqht);
      }
      return listKetQuaHocTap;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
    }
  }
  public void deleteSV(String maSV) throws SQLException {
    PreparedStatement stmt = null;
    try {
      String query =
              "DELETE FROM bangDiem " +
                      "WHERE Ma_SV IN ( " +
                      "SELECT Ma_SV " +
                      "FROM sinhVien " +
                      "WHERE Ma_SV = ? " +
                      "); " +
                      "DELETE FROM dangKyMonHoc " +
                      "WHERE Ma_SV IN ( " +
                      "SELECT Ma_SV " +
                      "FROM sinhVien " +
                      "WHERE Ma_SV = ? " +
                      "); " +
                      "DELETE FROM sinhVien " +
                      "WHERE Ma_SV = ?; " +
                      "DELETE FROM caNhan " +
                      "WHERE CCCD = ?; ";
      stmt = connection.prepareStatement(query);
      stmt.setString(1, maSV);
      stmt.setString(2, maSV);
      stmt.setString(3, maSV);
      stmt.setString(4, maSV);
      stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (stmt != null) {
        stmt.close();
      }
    }
  }
  public ObservableList<KetQuaHocTap> getKetQuaHocTapFull(String maSV) throws SQLException {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      String query =
              "SELECT Ma_HK, mh.Ma_MH, Name_MH, So_Tin, Loai_HP, Diem_QT, Diem_BS, Diem_Thi, Diem_KT, HoanThanh " +
                      "FROM bangDiem BD " +
                      "INNER JOIN sinhVien SV ON SV.Ma_SV = BD.Ma_SV " +
                      "INNER JOIN caNhan CN ON CN.CCCD = SV.CCCD " +
                      "INNER JOIN monHoc mh ON mh.Ma_MH = BD.Ma_MH " +
                      "INNER JOIN dangKyMonHoc ON SV.Ma_SV = dangKyMonHoc.Ma_SV AND mh.Ma_MH = dangKyMonHoc.Ma_MH " +
                      "WHERE SV.Ma_SV = ?";

      stmt = connection.prepareStatement(query);
      stmt.setString(1, maSV);

      rs = stmt.executeQuery();
      ObservableList<KetQuaHocTap> listKetQuaHocTap = FXCollections.observableArrayList();
      while(rs.next()) {
        KetQuaHocTap kqht = new KetQuaHocTap(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5),
                rs.getFloat(6),
                rs.getFloat(7),
                rs.getFloat(8),
                rs.getFloat(9),
                rs.getInt(10)
        );
        listKetQuaHocTap.add(kqht);
      }
      return listKetQuaHocTap;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
    }
  }
  public boolean authenticateUser(String username, String password)
    throws SQLException {
    Statement statement = null;
    ResultSet resultSet = null;

    try {
      String query =
        "SELECT Ma_SV, Pass_SV FROM sinhVien WHERE Ma_SV = '" +
        username +
        "' AND Pass_SV = '" +
        password +
        "'";

      statement = connection.createStatement();
      resultSet = statement.executeQuery(query);

      return resultSet.next();
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
      if (statement != null) {
        statement.close();
      }
    }
  }

  public void closeConnection() {}


  public Student getInformation(String maSV) throws SQLException {
    Statement statement = null;
    ResultSet resultSet = null;

    try {
      String query =

              "" +
                      "select SV.Ma_SV, caNhan.Name_CN, Gender, TrangThai, caNhan.Que_Quan, Name_Lop,  " +
                      "ldt.Name_Loai, caNhan.Ngay_Sinh, caNhan.Sdt_CN, hdt.Name_He, chuyenNganh.Name_ChuyenNganh, nganhHoc.Name_Nganh, " +
                      "NgayVao from sinhVien SV " +
                      "inner join caNhan on SV.CCCD = caNhan.CCCD " +
                      "inner join chuyenNganh on chuyenNganh.Ma_ChuyenNganh = SV.Ma_ChuyenNganh " +
                      "inner join loaiDaoTao ldt on ldt.Ma_Loai = SV.Ma_Loai " +
                      "inner join heDaoTao hdt on hdt.Ma_He = ldt.Ma_He " +
                      "inner join nganhHoc on nganhHoc.Ma_Nganh = chuyenNganh.Ma_Nganh\n" +
                      "where SV.Ma_SV = '" +
                      maSV +
                      "'";
      statement = connection.createStatement();
      resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        Student student = new Student(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9),
                resultSet.getString(10),
                resultSet.getString(11),
                resultSet.getString(12),
                resultSet.getString(13)
        );
        return student;
      }
      return null;
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
      if (statement != null) {
        statement.close();
      }
    }
  }
  public String getHoTen(String MSSV){
    Student student = null;
    try {
      student = getInformation(MSSV);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return student.getHoTen();
  }

  public  ObservableList<CourseData> getRegisterForTheCourse(String maSV, String mahk) throws SQLException {
    Statement statement = null;
    ResultSet resultSet = null;

    try {
      String query = "SELECT mh.Ma_MH, mh.Name_MH, lh.Name_Lop, mh.So_Tin, mh.Loai_HP\n" +
              "FROM monHoc mh\n" +
              "JOIN dangKyMonHoc dkmh ON mh.Ma_MH = dkmh.Ma_MH\n" +
              "JOIN sinhVien sv ON sv.Ma_SV = dkmh.Ma_SV\n" +
              "JOIN hocKy hk ON hk.Ma_HK = dkmh.Ma_HK\n" +
              "JOIN lopHoc lh ON sv.Name_Lop = lh.Name_Lop\n" +
              "WHERE sv.Ma_SV = '" + maSV + "' AND hk.Ma_HK = '"+mahk+"';";
      statement = connection.createStatement();
      resultSet = statement.executeQuery(query);
// kiem tra esultSet.next(), = true tức có ít nhất 1 bn ghi có kết quả
      if (resultSet.next()) {
        ObservableList<CourseData> courseDataList = FXCollections.observableArrayList();
        int index = 1;
        do {
          String maMH = resultSet.getString("Ma_MH");
          String nameMH = resultSet.getString("Name_MH");
          String nameLop = resultSet.getString("Name_Lop");
          int soTin = resultSet.getInt("So_Tin");
          String loaiHP = resultSet.getString("Loai_HP");


          CourseData courseData = new CourseData(maMH, nameMH, nameLop, soTin, soTin*325000, loaiHP,false);
          courseDataList.add(courseData);


        } while (resultSet.next());
        return courseDataList;
      } else {
        System.out.println("Not Found");
      }
      return null;
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
      if (statement != null) {
        statement.close();
      }
    }
  }
  public  ObservableList<ReViewData> getReView(String maSV) throws SQLException {
    Statement statement = null;
    ResultSet resultSet = null;

    try {
      String query = "SELECT monHoc.Ma_MH, sinhVien.Name_Lop, monHoc.Name_MH, hocKy.Name_HK, bangDiem.Diem_Thi\n" +
              "FROM bangDiem\n" +
              "JOIN dangKyMonHoc ON bangDiem.Ma_SV = dangKyMonHoc.Ma_SV AND bangDiem.Ma_MH = dangKyMonHoc.Ma_MH\n" +
              "JOIN monHoc ON bangDiem.Ma_MH = monHoc.Ma_MH\n" +
              "JOIN sinhVien ON bangDiem.Ma_SV = sinhVien.Ma_SV\n" +
              "JOIN hocKy ON dangKyMonHoc.Ma_HK = hocKy.Ma_HK\n" +
              "WHERE bangDiem.Diem_Thi < 8\n" +
              "AND sinhVien.Ma_SV = '"+maSV+"';";
      statement = connection.createStatement();
      resultSet = statement.executeQuery(query);
      if (resultSet.next()) {
        ObservableList<ReViewData> reViewDataList = FXCollections.observableArrayList();
        int index = 1;
        do {
          String maMH = resultSet.getString("Ma_MH");
          String nameMH = resultSet.getString("Name_MH");
          String nameLop = resultSet.getString("Name_Lop");
          String nameHK = resultSet.getString("Name_HK");
          double diemThi=resultSet.getDouble("Diem_Thi");

          ReViewData reViewData = new ReViewData(maMH, nameMH,nameLop,nameHK,diemThi,true);
          reViewDataList.add(reViewData);

        } while (resultSet.next());
        return reViewDataList;
      } else {
        System.out.println("Not Found");
      }
      return null;
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
      if (statement != null) {
        statement.close();
      }
    }
  }


  public String getAccountName(String username) throws SQLException {
    Student std = getInformation(username);
    return std.getHoTen();
  }

  public void displayResultSet(ResultSet resultSet) throws SQLException {
    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
    int columnCount = resultSetMetaData.getColumnCount();
    for (int i = 1; i <= columnCount; i++) {
      System.out.print(resultSetMetaData.getColumnName(i) + "\t\t");
    }
    System.out.println();
    while (resultSet.next()) {
      for (int i = 1; i <= columnCount; i++) {
        System.out.print(resultSet.getString(i) + "\t\t");
      }
      System.out.println();
    }
  }



  public static void main(String[] args) throws SQLException {

  }

}
