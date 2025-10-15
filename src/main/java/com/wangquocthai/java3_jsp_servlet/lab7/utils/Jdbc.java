package com.wangquocthai.java3_jsp_servlet.lab7.utils;

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

    /**
     * Thực thi câu lệnh INSERT, UPDATE, DELETE hoặc các câu lệnh DDL.
     * @param sql là câu lệnh SQL chứa tham số (?)
     * @param values là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
     * @return số hàng bị ảnh hưởng
     */
    public static int executeUpdate(String sql, Object... values) throws SQLException {
        PreparedStatement stmt = Jdbc.createPreStmt(sql,values);
        return stmt.executeUpdate();

    }

    /**
     * Thực thi câu lệnh SELECT.
     * @param sql là câu lệnh SQL chứa tham số (?)
     * @param values là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
     * @return đối tượng ResultSet chứa kết quả truy vấn
     */
    public static ResultSet executeQuery(String sql, Object... values) throws SQLException {
        PreparedStatement stmt = Jdbc.createPreStmt(sql,values);
        return stmt.executeQuery();
    }

    private static PreparedStatement createPreStmt(String sql, Object... values) throws SQLException {
        Connection connection = Jdbc.getConnection();
        PreparedStatement stmt = null;
        if (sql.trim().startsWith("{")) {
            stmt = connection.prepareCall(sql);
        } else {
            stmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < values.length; i++) {
            stmt.setObject(i + 1, values[i]);
        }
        return stmt;

    }
}

