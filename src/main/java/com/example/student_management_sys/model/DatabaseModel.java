package com.example.student_management_sys.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseModel {

  private Connection connection;

  public DatabaseModel() {
    connection = ConnectionDatabase.getConnection();
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

    String query = "SELECT * FROM dbo.allHocKySinhVien(?)";
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
  public Student getregisterForTheCourse(String maSV, String strMAHK) throws SQLException {
    Statement statement = null;
    ResultSet resultSet = null;

    try {
      String query = "SELECT mh.Ma_MH, mh.Name_MH, lh.Name_Lop, mh.So_Tin, mh.Loai_HP\n" +
              "FROM monHoc mh\n" +
              "JOIN dangKyMonHoc dkmh ON mh.Ma_MH = dkmh.Ma_MH\n" +
              "JOIN sinhVien sv ON sv.Ma_SV = dkmh.Ma_SV\n" +
              "JOIN hocKy hk ON hk.Ma_HK = dkmh.Ma_HK\n" +
              "JOIN lopHoc lh ON sv.Name_Lop = lh.Name_Lop\n" +
              "WHERE sv.Ma_SV = '" + maSV + "' AND hk.Ma_HK = '"+strMAHK+"';";
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
    DatabaseModel databaseModel = new DatabaseModel();
    Student std = databaseModel.getInformation("010041");
    std.DisplayStudent();
    ObservableList<LichHoc> lh = databaseModel.getLichHoc(
      "016951",
      "2023-04-05",
      "2023-04-12"
    );
    for (LichHoc lichHoc : lh) {
      lichHoc.displayLichHoc();
    }
    databaseModel.closeConnection();
  }
}
