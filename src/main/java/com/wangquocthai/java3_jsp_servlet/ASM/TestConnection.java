package com.wangquocthai.java3_jsp_servlet.ASM;

import com.wangquocthai.java3_jsp_servlet.ASM.utils.Jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TestConnection {

    public static void main(String[] args) {
        System.out.println("--- Bắt đầu kiểm tra kết nối đến Oracle DB ---");

        try {
            // Sử dụng câu lệnh SQL đơn giản nhất để kiểm tra kết nối với Oracle
            String testSql = "SELECT SYSDATE FROM DUAL";

            // Gọi phương thức executeQuery từ lớp Jdbc của bạn
            ResultSet rs = Jdbc.executeQuery(testSql);

            // Nếu câu lệnh chạy thành công và trả về kết quả, kết nối đã OK
            if (rs.next()) {
                Date databaseTime = rs.getTimestamp(1); // Lấy kết quả từ cột đầu tiên
                System.out.println("✅ Kết nối thành công!");
                System.out.println("   -> Giờ hiện tại của server Oracle là: " + databaseTime);
            } else {
                // Trường hợp này rất hiếm khi xảy ra với câu lệnh trên
                System.out.println("❌ Kết nối được nhưng không nhận được dữ liệu.");
            }

        } catch (SQLException e) {
            // Nếu có lỗi xảy ra (sai URL, username, password, không tìm thấy driver,...)
            System.err.println("❌ Lỗi! Kết nối đến Oracle thất bại.");
            System.err.println("   -> Vui lòng kiểm tra lại thông tin trong file Jdbc.java");
            System.err.println("   -> và đảm bảo thư viện ojdbc đã được thêm vào dự án.");

            // In ra chi tiết lỗi để dễ dàng sửa chữa
            e.printStackTrace();
        }

        System.out.println("\n--- Kết thúc kiểm tra ---");
    }
}