package com.wangquocthai.java3_jsp_servlet.lab6;

import java.sql.*;

public class Jdbc {
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String dburl = "jdbc:mysql://localhost:3306/DB_Java3_Lab6";
    static String username = "wangquockhanh";
    static String password = "matkhau123";
    static {
        try { // nạp driver
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**Mở kết nối*/
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }
//    /**Thao tác dữ liệu*/
//    public static int executeUpdate(String sql) throws SQLException {
//        Connection connection = getConnection();
//        Statement statement = connection.createStatement();
//        return statement.executeUpdate(sql);
//    }
//    /**Truy vấn dữ liệu*/
//    public static ResultSet executeQuery(String sql) throws SQLException {
//        Connection connection = getConnection();
//        Statement statement = connection.createStatement();
//        return statement.executeQuery(sql);
//    }
    /**
     * Thực thi câu lệnh INSERT, UPDATE, DELETE hoặc các câu lệnh DDL.
     * @param sql là câu lệnh SQL chứa tham số (?)
     * @param args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
     * @return số hàng bị ảnh hưởng
     */
    public static int executeUpdate(String sql, Object... args) throws SQLException {
        try (Connection connection = getConnection();
             CallableStatement stmt = connection.prepareCall(sql)) {

            // Gán giá trị cho các tham số '?'
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }

            return stmt.executeUpdate();
        }
    }

    /**
     * Thực thi câu lệnh SELECT.
     * @param sql là câu lệnh SQL chứa tham số (?)
     * @param args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
     * @return đối tượng ResultSet chứa kết quả truy vấn
     */
    public static ResultSet executeQuery(String sql, Object... args) throws SQLException {
        Connection connection = getConnection(); // Giữ kết nối mở để ResultSet hoạt động
        CallableStatement stmt = connection.prepareCall(sql);

        // Gán giá trị cho các tham số '?'
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }

        return stmt.executeQuery();
    }
}

