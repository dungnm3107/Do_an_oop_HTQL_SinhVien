package com.example.student_management_sys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseModel {

  private Connection connection;

  public DatabaseModel() {
    connection = ConnectionDatabase.getConnection();
  }

  public ResultSet getLichHoc(
    String maSV,
    String maHK,
    String ngayBatDau,
    String ngayKetThuc
  ) throws SQLException {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      String query =
        "SELECT Ma_HK, lh.Ma_MH, Name_MH, So_Tin, lh.Name_Lop, Thu, Ca, Phong, Ngay_BD, Ngay_KT, " +
        "GV.Ma_GV, Name_CN, TrinhDo, CN.Sdt_CN, CN.Email " +
        "FROM sinhVien SV " +
        "INNER JOIN dangKyMonHoc dk ON dk.Ma_SV = SV.Ma_SV " +
        "INNER JOIN monHoc mh ON mh.Ma_MH = dk.Ma_MH " +
        "INNER JOIN lichHoc lh ON lh.Ma_MH = dk.Ma_MH " +
        "INNER JOIN giangDay gd ON gd.Ma_MH = lh.Ma_MH " +
        "INNER JOIN giaoVien GV ON GV.Ma_GV = gd.Ma_GV " +
        "INNER JOIN caNhan CN ON CN.CCCD = GV.CCCD " +
        "WHERE dk.Ma_SV = ? AND Ma_HK = ? " +
        "AND Ngay_BD <= ? AND ? <= Ngay_KT";

      stmt = connection.prepareStatement(query);
      stmt.setString(1, maSV);
      stmt.setString(2, maHK);
      stmt.setString(3, ngayBatDau);
      stmt.setString(4, ngayKetThuc);

      rs = stmt.executeQuery();

      return rs;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
    }
  }

  public ResultSet getKetQuaHocTap(String maSV) throws SQLException {
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

      displayResultSet(rs);
      return rs;
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
        "ldt.Name_Loai, hdt.Name_He, chuyenNganh.Name_ChuyenNganh, nganhHoc.Name_Nganh, " +
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
          resultSet.getString(11)
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

  }
}
