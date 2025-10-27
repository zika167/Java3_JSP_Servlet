package com.wangquocthai.java3_jsp_servlet.final_test.dao;

import com.wangquocthai.java3_jsp_servlet.final_test.model.KhachHang;
import com.wangquocthai.java3_jsp_servlet.final_test.utils.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    /**
     * Thêm khách hàng mới vào database
     * @param khachHang đối tượng KhachHang cần thêm
     * @return true nếu thêm thành công, false nếu thất bại
     */
    public boolean create(KhachHang khachHang) {
        try {
            String sql = "INSERT INTO KHACHHANG (Username, Password, Hoten, Gioitinh, Email) VALUES (?, ?, ?, ?, ?)";
            int result = Jdbc.executeUpdate(sql, 
                khachHang.getUsername(), 
                khachHang.getPassword(), 
                khachHang.getHoten(), 
                khachHang.getGioitinh(), 
                khachHang.getEmail());
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm khách hàng: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lấy danh sách tất cả khách hàng
     * @return List<KhachHang> danh sách khách hàng
     */
    public List<KhachHang> findAll() {
        List<KhachHang> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM KHACHHANG ORDER BY Username";
            ResultSet rs = Jdbc.executeQuery(sql);
            
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setUsername(rs.getString("Username"));
                kh.setPassword(rs.getString("Password"));
                kh.setHoten(rs.getString("Hoten"));
                kh.setGioitinh(rs.getString("Gioitinh"));
                kh.setEmail(rs.getString("Email"));
                list.add(kh);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
        }
        return list;
    }

    /**
     * Tìm khách hàng theo username
     * @param username username cần tìm
     * @return KhachHang nếu tìm thấy, null nếu không tìm thấy
     */
    public KhachHang findByUsername(String username) {
        try {
            String sql = "SELECT * FROM KHACHHANG WHERE Username = ?";
            ResultSet rs = Jdbc.executeQuery(sql, username);
            
            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setUsername(rs.getString("Username"));
                kh.setPassword(rs.getString("Password"));
                kh.setHoten(rs.getString("Hoten"));
                kh.setGioitinh(rs.getString("Gioitinh"));
                kh.setEmail(rs.getString("Email"));
                return kh;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm khách hàng: " + e.getMessage());
        }
        return null;
    }

    /**
     * Cập nhật thông tin khách hàng
     * @param khachHang đối tượng KhachHang với thông tin mới
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean update(KhachHang khachHang) {
        try {
            String sql = "UPDATE KHACHHANG SET Password = ?, Hoten = ?, Gioitinh = ?, Email = ? WHERE Username = ?";
            int result = Jdbc.executeUpdate(sql, 
                khachHang.getPassword(), 
                khachHang.getHoten(), 
                khachHang.getGioitinh(), 
                khachHang.getEmail(),
                khachHang.getUsername());
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật khách hàng: " + e.getMessage());
            return false;
        }
    }

    /**
     * Xóa khách hàng theo username
     * @param username username của khách hàng cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public boolean delete(String username) {
        try {
            String sql = "DELETE FROM KHACHHANG WHERE Username = ?";
            int result = Jdbc.executeUpdate(sql, username);
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa khách hàng: " + e.getMessage());
            return false;
        }
    }

    /**
     * Kiểm tra xem username đã tồn tại chưa
     * @param username username cần kiểm tra
     * @return true nếu đã tồn tại, false nếu chưa tồn tại
     */
    public boolean isUsernameExists(String username) {
        return findByUsername(username) != null;
    }
}
