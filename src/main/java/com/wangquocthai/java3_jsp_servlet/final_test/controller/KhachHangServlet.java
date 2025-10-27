package com.wangquocthai.java3_jsp_servlet.final_test.controller;

import com.wangquocthai.java3_jsp_servlet.final_test.dao.KhachHangDAO;
import com.wangquocthai.java3_jsp_servlet.final_test.model.KhachHang;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/khachhang")
public class KhachHangServlet extends HttpServlet {
    
    private KhachHangDAO khachHangDAO;
    
    @Override
    public void init() throws ServletException {
        khachHangDAO = new KhachHangDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null || action.equals("list")) {
            listKhachHang(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else {
            listKhachHang(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set encoding để xử lý tiếng Việt
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        
        if (action == null) {
            listKhachHang(request, response);
            return;
        }
        
        switch (action) {
            case "create":
                createKhachHang(request, response);
                break;
            case "update":
                updateKhachHang(request, response);
                break;
            case "delete":
                deleteKhachHang(request, response);
                break;
            case "reset":
                resetForm(request, response);
                break;
            default:
                listKhachHang(request, response);
                break;
        }
    }
    
    /**
     * Hiển thị danh sách khách hàng
     */
    private void listKhachHang(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<KhachHang> listKhachHang = khachHangDAO.findAll();
        request.setAttribute("listKhachHang", listKhachHang);
        request.setAttribute("khachHang", new KhachHang()); // Đối tượng rỗng cho form
        request.getRequestDispatcher("/final_test/khachhang.jsp").forward(request, response);
    }
    
    /**
     * Hiển thị form chỉnh sửa
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        KhachHang khachHang = khachHangDAO.findByUsername(username);
        
        List<KhachHang> listKhachHang = khachHangDAO.findAll();
        request.setAttribute("listKhachHang", listKhachHang);
        request.setAttribute("khachHang", khachHang);
        request.setAttribute("isEdit", true);
        
        request.getRequestDispatcher("/final_test/khachhang.jsp").forward(request, response);
    }
    
    /**
     * Tạo khách hàng mới
     */
    private void createKhachHang(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hoten = request.getParameter("hoten");
        String gioitinh = request.getParameter("gioitinh");
        String email = request.getParameter("email");
        
        // Validate dữ liệu
        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("error", "Username không được để trống!");
            listKhachHang(request, response);
            return;
        }
        
        // Kiểm tra username đã tồn tại chưa
        if (khachHangDAO.isUsernameExists(username)) {
            request.setAttribute("error", "Username đã tồn tại!");
            listKhachHang(request, response);
            return;
        }
        
        KhachHang khachHang = new KhachHang(username, password, hoten, gioitinh, email);
        
        if (khachHangDAO.create(khachHang)) {
            request.setAttribute("success", "Thêm khách hàng thành công!");
        } else {
            request.setAttribute("error", "Thêm khách hàng thất bại!");
        }
        
        listKhachHang(request, response);
    }
    
    /**
     * Cập nhật khách hàng
     */
    private void updateKhachHang(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hoten = request.getParameter("hoten");
        String gioitinh = request.getParameter("gioitinh");
        String email = request.getParameter("email");
        
        KhachHang khachHang = new KhachHang(username, password, hoten, gioitinh, email);
        
        if (khachHangDAO.update(khachHang)) {
            request.setAttribute("success", "Cập nhật khách hàng thành công!");
        } else {
            request.setAttribute("error", "Cập nhật khách hàng thất bại!");
        }
        
        listKhachHang(request, response);
    }
    
    /**
     * Xóa khách hàng
     */
    private void deleteKhachHang(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        
        if (khachHangDAO.delete(username)) {
            request.setAttribute("success", "Xóa khách hàng thành công!");
        } else {
            request.setAttribute("error", "Xóa khách hàng thất bại!");
        }
        
        listKhachHang(request, response);
    }
    
    /**
     * Reset form
     */
    private void resetForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Chỉ cần redirect về trang chính để reset form
        response.sendRedirect(request.getContextPath() + "/khachhang");
    }
}
