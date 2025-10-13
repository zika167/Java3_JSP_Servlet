package com.wangquocthai.java3_jsp_servlet.lab6;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main_Bai3 {

    public static void main(String[] args) {
        System.out.println("BẮT ĐẦU CHƯƠNG TRÌNH VỚI PREPAREDSTATEMENT\n");

        // 1. Thêm một phòng ban mới
        try {
            System.out.println("--- 1. Thêm phòng ban 'D05'...");
            String insertSql = "INSERT INTO Departments (Id, Name, Description) VALUES (?, ?, ?)";
            int rowsInserted = Jdbc.executeUpdate(insertSql, "D05", "Phòng Pháp lý", "Tư vấn các vấn đề pháp luật.");
            System.out.println(">> Đã thêm thành công: " + rowsInserted + " dòng.");

            // Truy vấn phòng ban theo ID
            System.out.println("\n--- Kiểm tra thông tin phòng ban 'D05'...");
            String selectSql = "SELECT * FROM Departments WHERE Id = ?";
            ResultSet rs = Jdbc.executeQuery(selectSql, "D05");

            System.out.printf("%-5s %-25s %-50s\n", "ID", "TÊN PHÒNG", "MÔ TẢ");
            System.out.println("------------------------------------------------------------------------------");
            if (rs.next()) {
                System.out.printf("%-5s %-25s %-50s\n",
                        rs.getString("Id"),
                        rs.getString("Name"),
                        rs.getString("Description"));
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm: " + e.getMessage());
        }

        // 2. Cập nhật thông tin phòng ban vừa thêm
        try {
            System.out.println("\n--- 2. Cập nhật phòng ban 'D05'...");
            String updateSql = "UPDATE Departments SET Name = ? WHERE Id = ?";
            int rowsUpdated = Jdbc.executeUpdate(updateSql, "Phòng Pháp chế", "D05");
            System.out.println(">> Đã cập nhật thành công: " + rowsUpdated + " dòng.");

            // Truy vấn phòng ban theo ID
            System.out.println("\n--- Kiểm tra thông tin phòng ban 'D05'...");
            String selectSql = "SELECT * FROM Departments WHERE Id = ?";
            ResultSet rs = Jdbc.executeQuery(selectSql, "D05");

            System.out.printf("%-5s %-25s %-50s\n", "ID", "TÊN PHÒNG", "MÔ TẢ");
            System.out.println("------------------------------------------------------------------------------");
            if (rs.next()) {
                System.out.printf("%-5s %-25s %-50s\n",
                        rs.getString("Id"),
                        rs.getString("Name"),
                        rs.getString("Description"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật: " + e.getMessage());
        }

        // 3. Xóa phòng ban vừa thêm
        try {
            System.out.println("\n--- 4. Xóa phòng ban 'D05'...");
            String deleteSql = "DELETE FROM Departments WHERE Id = ?";
            int rowsDeleted = Jdbc.executeUpdate(deleteSql, "D05");
            System.out.println(">> Đã xóa thành công: " + rowsDeleted + " dòng.");

            System.out.println("\n--- Kiểm tra thông tin phòng ban...");
            // In tiêu đề cột
            System.out.printf("%-5s %-25s %-50s\n", "ID", "TÊN PHÒNG", "MÔ TẢ");
            System.out.println("------------------------------------------------------------------------------");
            String finalSql = "SELECT * FROM Departments";
            ResultSet rs = Jdbc.executeQuery(finalSql);
            // Lặp qua kết quả và in ra màn hình
            while (rs.next()) {
                System.out.printf("%-5s %-25s %-50s\n",
                        rs.getString("Id"),
                        rs.getString("Name"),
                        rs.getString("Description"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa: " + e.getMessage());
        }
    }
}
