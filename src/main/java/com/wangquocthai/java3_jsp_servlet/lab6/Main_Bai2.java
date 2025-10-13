package com.wangquocthai.java3_jsp_servlet.lab6;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main_Bai2 {

    public static void main(String[] args) {
        System.out.println("BẮT ĐẦU CHƯƠNG TRÌNH KIỂM TRA JDBC\n");

        //==================================================================
        // THAO TÁC VỚI BẢNG DEPARTMENTS
        //==================================================================
        System.out.println("============== THAO TÁC TRÊN BẢNG DEPARTMENTS ==============");

        // 1. Truy vấn tất cả phòng ban
        try {
            System.out.println("\n--- 1. Đang truy vấn tất cả phòng ban...");
            String sql = "SELECT * FROM Departments";
            ResultSet rs = Jdbc.executeQuery(sql);

            // In tiêu đề cột
            System.out.printf("%-5s %-25s %-50s\n", "ID", "TÊN PHÒNG", "MÔ TẢ");
            System.out.println("------------------------------------------------------------------------------");

            // Lặp qua kết quả và in ra màn hình
            while (rs.next()) {
                System.out.printf("%-5s %-25s %-50s\n",
                        rs.getString("Id"),
                        rs.getString("Name"),
                        rs.getString("Description"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn Departments: " + e.getMessage());
        }

        // 2. Thêm mới một phòng ban
        try {
            System.out.println("\n--- 2. Đang thêm phòng ban mới (ID: D04)...");
            String sql = "INSERT INTO Departments (Id, Name, Description) VALUES ('D04', 'Phòng Marketing', 'Chịu trách nhiệm quảng bá sản phẩm.')";
            int rows = Jdbc.executeUpdate(sql);
            System.out.println(">> Đã thêm thành công, số dòng ảnh hưởng: " + rows);

            System.out.println("\n");
            String finalSql = "SELECT * FROM Departments";
            ResultSet rs = Jdbc.executeQuery(finalSql);
            // In tiêu đề cột
            System.out.printf("%-5s %-25s %-50s\n", "ID", "TÊN PHÒNG", "MÔ TẢ");
            System.out.println("------------------------------------------------------------------------------");

            // Lặp qua kết quả và in ra màn hình
            while (rs.next()) {
                System.out.printf("%-5s %-25s %-50s\n",
                        rs.getString("Id"),
                        rs.getString("Name"),
                        rs.getString("Description"));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm Department: " + e.getMessage());
        }

        // 3. Cập nhật phòng ban vừa thêm
        try {
            System.out.println("\n--- 3. Đang cập nhật phòng ban (ID: D04)...");
            String sql = "UPDATE Departments SET Name = 'Phòng Tiếp thị & Truyền thông' WHERE Id = 'D04'";
            int rows = Jdbc.executeUpdate(sql);
            System.out.println(">> Đã cập nhật thành công, số dòng ảnh hưởng: " + rows);

            System.out.println("\n");
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
            System.err.println("Lỗi khi cập nhật Department: " + e.getMessage());
        }

        // 4. Xóa phòng ban vừa thêm
        try {
            System.out.println("\n--- 4. Đang xóa phòng ban (ID: D04)...");
            String sql = "DELETE FROM Departments WHERE Id = 'D04'";
            int rows = Jdbc.executeUpdate(sql);
            System.out.println(">> Đã xóa thành công, số dòng ảnh hưởng: " + rows);
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa Department: " + e.getMessage());
        }


        //==================================================================
        // THAO TÁC VỚI BẢNG EMPLOYEES
        //==================================================================
        System.out.println("\n\n============== THAO TÁC TRÊN BẢNG EMPLOYEES ==============");

        // 1. Truy vấn tất cả nhân viên
        try {
            System.out.println("\n--- 1. Đang truy vấn tất cả nhân viên...");
            String sql = "SELECT * FROM Employees";
            ResultSet rs = Jdbc.executeQuery(sql);

            System.out.printf("%-7s %-25s %-10s %-15s\n", "ID", "HỌ TÊN", "GIỚI TÍNH", "LƯƠNG");
            System.out.println("-----------------------------------------------------------------");

            while (rs.next()) {
                String id = rs.getString("Id");
                String fullname = rs.getString("Fullname");
                boolean gender = rs.getBoolean("Gender");
                double salary = rs.getFloat("Salary");

                System.out.printf("%-7s %-25s %-10s %-15.0f\n", id, fullname, (gender ? "Nam" : "Nữ"), salary);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn Employees: " + e.getMessage());
        }

        // 2. Thêm mới một nhân viên
        try {
            System.out.println("\n--- 2. Đang thêm nhân viên mới (ID: NV004)...");
            // DepartmentID 'D01' phải tồn tại
            String sql = "INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentID) " +
                    "VALUES ('NV004', 'newuser123', 'Phạm Văn Toàn', 'toan.jpg', 1, '1998-07-30', 19000000, 'D01')";
            int rows = Jdbc.executeUpdate(sql);
            System.out.println(">> Đã thêm thành công, số dòng ảnh hưởng: " + rows);
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm Employee: " + e.getMessage());
        }

        // 3. Cập nhật nhân viên vừa thêm
        try {
            System.out.println("\n--- 3. Đang cập nhật nhân viên (ID: NV004)...");
            String sql = "UPDATE Employees SET Salary = 21000000 WHERE Id = 'NV004'";
            int rows = Jdbc.executeUpdate(sql);
            System.out.println(">> Đã cập nhật thành công, số dòng ảnh hưởng: " + rows);
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật Employee: " + e.getMessage());
        }

        // 4. Xóa nhân viên vừa thêm
        try {
            System.out.println("\n--- 4. Đang xóa nhân viên (ID: NV004)...");
            String sql = "DELETE FROM Employees WHERE Id = 'NV004'";
            int rows = Jdbc.executeUpdate(sql);
            System.out.println(">> Đã xóa thành công, số dòng ảnh hưởng: " + rows);
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa Employee: " + e.getMessage());
        }
    }
}