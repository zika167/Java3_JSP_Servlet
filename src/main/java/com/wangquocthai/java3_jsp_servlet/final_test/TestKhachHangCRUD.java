package com.wangquocthai.java3_jsp_servlet.final_test;

import com.wangquocthai.java3_jsp_servlet.final_test.dao.KhachHangDAO;
import com.wangquocthai.java3_jsp_servlet.final_test.model.KhachHang;

import java.util.List;

public class TestKhachHangCRUD {
    
    public static void main(String[] args) {
        System.out.println("=== KIỂM TRA CHỨC NĂNG CRUD KHÁCH HÀNG ===\n");
        
        KhachHangDAO dao = new KhachHangDAO();
        
        // Test 1: Hiển thị danh sách khách hàng hiện tại
        testFindAll(dao);
        
        // Test 2: Thêm khách hàng mới
        testCreate(dao);
        
        // Test 3: Tìm khách hàng theo username
        testFindByUsername(dao);
        
        // Test 4: Cập nhật thông tin khách hàng
        testUpdate(dao);
        
        // Test 5: Xóa khách hàng
        testDelete(dao);
        
        // Test 6: Hiển thị danh sách cuối cùng
        testFindAll(dao);
        
        System.out.println("\n=== HOÀN THÀNH KIỂM TRA ===");
    }
    
    /**
     * Test hiển thị danh sách khách hàng
     */
    private static void testFindAll(KhachHangDAO dao) {
        System.out.println("--- TEST: Hiển thị danh sách khách hàng ---");
        
        List<KhachHang> list = dao.findAll();
        
        if (list.isEmpty()) {
            System.out.println("Danh sách trống!");
        } else {
            System.out.printf("%-15s %-15s %-25s %-10s %-30s%n", 
                "Username", "Password", "Họ tên", "Giới tính", "Email");
            System.out.println("-".repeat(95));
            
            for (KhachHang kh : list) {
                System.out.printf("%-15s %-15s %-25s %-10s %-30s%n",
                    kh.getUsername(),
                    kh.getPassword(),
                    kh.getHoten(),
                    kh.getGioitinh(),
                    kh.getEmail());
            }
        }
        System.out.println();
    }
    
    /**
     * Test thêm khách hàng mới
     */
    private static void testCreate(KhachHangDAO dao) {
        System.out.println("--- TEST: Thêm khách hàng mới ---");
        
        KhachHang newKH = new KhachHang(
            "testuser", 
            "password123", 
            "Nguyễn Văn Test", 
            "Nam", 
            "testuser@example.com"
        );
        
        boolean result = dao.create(newKH);
        
        if (result) {
            System.out.println("✅ Thêm khách hàng thành công!");
        } else {
            System.out.println("❌ Thêm khách hàng thất bại!");
        }
        System.out.println();
    }
    
    /**
     * Test tìm khách hàng theo username
     */
    private static void testFindByUsername(KhachHangDAO dao) {
        System.out.println("--- TEST: Tìm khách hàng theo username ---");
        
        String username = "testuser";
        KhachHang kh = dao.findByUsername(username);
        
        if (kh != null) {
            System.out.println("✅ Tìm thấy khách hàng:");
            System.out.println("   Username: " + kh.getUsername());
            System.out.println("   Họ tên: " + kh.getHoten());
            System.out.println("   Email: " + kh.getEmail());
        } else {
            System.out.println("❌ Không tìm thấy khách hàng với username: " + username);
        }
        System.out.println();
    }
    
    /**
     * Test cập nhật thông tin khách hàng
     */
    private static void testUpdate(KhachHangDAO dao) {
        System.out.println("--- TEST: Cập nhật thông tin khách hàng ---");
        
        KhachHang updateKH = new KhachHang(
            "testuser", 
            "newpassword456", 
            "Nguyễn Văn Test - Updated", 
            "Nam", 
            "testuser.updated@example.com"
        );
        
        boolean result = dao.update(updateKH);
        
        if (result) {
            System.out.println("✅ Cập nhật khách hàng thành công!");
        } else {
            System.out.println("❌ Cập nhật khách hàng thất bại!");
        }
        System.out.println();
    }
    
    /**
     * Test xóa khách hàng
     */
    private static void testDelete(KhachHangDAO dao) {
        System.out.println("--- TEST: Xóa khách hàng ---");
        
        String username = "testuser";
        boolean result = dao.delete(username);
        
        if (result) {
            System.out.println("✅ Xóa khách hàng thành công!");
        } else {
            System.out.println("❌ Xóa khách hàng thất bại!");
        }
        System.out.println();
    }
}
