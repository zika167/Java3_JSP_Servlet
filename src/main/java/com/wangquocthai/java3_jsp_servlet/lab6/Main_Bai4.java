package com.wangquocthai.java3_jsp_servlet.lab6;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main_Bai4 {
    public static void main(String[] args) {
        System.out.println("BẮT ĐẦU CHƯƠNG TRÌNH VỚI CALLABLESTATEMENT\n");

        // 1. Gọi procedure spSelectAll để lấy danh sách
        try {
            System.out.println("--- 1. Gọi procedure: spSelectAll() ---");
            String sql = "{CALL spSelectAll()}";
            ResultSet rs = Jdbc.executeQuery(sql);
            System.out.printf("%-5s %-25s\n", "ID", "TÊN PHÒNG");
            System.out.println("---------------------------------");
            while (rs.next()) {
                System.out.printf("%-5s %-25s\n", rs.getString("Id"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi spSelectAll: " + e.getMessage());
        }

        // 2. Gọi procedure spInsert để thêm mới
        try {
            System.out.println("\n--- 2. Gọi procedure: spInsert(?, ?, ?) ---");
            String sql = "{CALL spInsert(?, ?, ?)}";
            int rows = Jdbc.executeUpdate(sql, "D07", "Phòng Đào tạo", "Tổ chức các khóa huấn luyện.");
            System.out.println(">> Đã thêm thành công, số dòng ảnh hưởng: " + (rows >= 0 ? 1 : 0));
        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi spInsert: " + e.getMessage());
        }

        // 3. Gọi procedure spUpdate để cập nhật
        try {
            System.out.println("\n--- 3. Gọi procedure: spUpdate(?, ?, ?) ---");
            String sql = "{CALL spUpdate(?, ?, ?)}";
            int rows = Jdbc.executeUpdate(sql, "D07", "Phòng Huấn luyện", "Cập nhật mô tả.");
            System.out.println(">> Đã cập nhật thành công, số dòng ảnh hưởng: " + rows);
        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi spUpdate: " + e.getMessage());
        }

        // 4. Gọi procedure spSelectById để xem chi tiết
        try {
            System.out.println("\n--- 4. Gọi procedure: spSelectById(?) ---");
            String sql = "{CALL spSelectById(?)}";
            ResultSet rs = Jdbc.executeQuery(sql, "D07");
            if (rs.next()) {
                System.out.println(">> Tìm thấy phòng ban: " + rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi spSelectById: " + e.getMessage());
        }

        // 5. Gọi procedure spDeleteById để xóa
        try {
            System.out.println("\n--- 5. Gọi procedure: spDeleteById(?) ---");
            String sql = "{CALL spDeleteById(?)}";
            int rows = Jdbc.executeUpdate(sql, "D07");
            System.out.println(">> Đã xóa thành công, số dòng ảnh hưởng: " + rows);
        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi spDeleteById: " + e.getMessage());
        }
    }
}
