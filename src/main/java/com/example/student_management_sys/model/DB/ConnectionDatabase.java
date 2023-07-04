package com.example.student_management_sys.model.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    public static Connection getConnection() {
        Connection conn;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");



            String user = "tool";
            String pass = "2003";

            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SinhVienHucer;encrypt=true;trustServerCertificate=true", user, pass);
            if (conn == null) {
                System.out.println("fall connect");
            } else {
                System.out.println("success connect");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }
}