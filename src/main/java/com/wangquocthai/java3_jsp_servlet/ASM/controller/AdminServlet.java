package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Newsletter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet cho chức năng Quản trị (Admin)
 */
@WebServlet(name ="AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Mock data - danh sách 5 user mẫu
        List<User> users = createMockUsers();
        List<Newsletter> newsletters = createMockNewsletters();
        
        // Truyền dữ liệu sang JSP
        request.setAttribute("users", users);
        request.setAttribute("newsletters", newsletters);
        
        // Forward đến JSP
        request.getRequestDispatcher("/ASM/admin/user_crud.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Xử lý form thêm user
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            // Lấy dữ liệu từ form
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            String fullName = request.getParameter("fullName");
            
            // Xử lý logic thêm user (mock)
            // Trong thực tế sẽ lưu vào database
            System.out.println("Adding user: " + username + " - " + role);
            
            // Redirect về trang danh sách
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }
        
        // Xử lý các action khác (khóa/xóa)
        String userId = request.getParameter("userId");
        if ("lock".equals(action) || "delete".equals(action)) {
            // Xử lý logic khóa/xóa user (mock)
            // Trong thực tế sẽ cập nhật database
            System.out.println("Action: " + action + " on user: " + userId);
            
            // Redirect về trang danh sách
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }
        
        doGet(request, response);
    }
    
    /**
     * Tạo dữ liệu mẫu cho danh sách user
     */
    private List<User> createMockUsers() {
        List<User> users = new ArrayList<>();
        
        users.add(new User(
            "ADMIN001",
            "admin123",
            "Nguyễn Văn Admin",
            new Date(1990, 5, 15),
            "M",  // Nam
            "0901234567",
            "admin01@abcnews.com",
            "A",   // Admin
                true
        ));
        
        users.add(new User(
            "REP001",
            "reporter123",
            "Trần Thị Phóng viên",
            new Date(1985, 8, 20),
            "F", // Nữ
            "0901234568",
            "reporter01@abcnews.com",
            "R",  // Reporter
                true
        ));
        
        users.add(new User(
            "REP002",
            "reporter456",
            "Lê Văn Biên tập",
            new Date(1988, 3, 10),
            "M",  // Nam
            "0901234569",
            "reporter02@abcnews.com",
            "R",  // Reporter
                true
        ));
        
        users.add(new User(
            "REP003",
            "reporter789",
            "Phạm Thị Cộng tác viên",
            new Date(1992, 11, 25),
            "F", // Nữ
            "0901234570",
            "reporter03@abcnews.com",
            "R",  // Reporter
                true
        ));
        
        users.add(new User(
            "REP004",
            "reporter000",
            "Hoàng Văn Thực tập",
            new Date(1995, 7, 5),
            "M",  // Nam
            "0901234571",
            "reporter04@abcnews.com",
            "R",  // Reporter
                true
        ));
        
        return users;
    }
    
    /**
     * Tạo dữ liệu mẫu cho danh sách newsletter
     */
    private List<Newsletter> createMockNewsletters() {
        List<Newsletter> newsletters = new ArrayList<>();
        
        newsletters.add(new Newsletter("user1@gmail.com", true));
        newsletters.add(new Newsletter("user2@yahoo.com", true));
        newsletters.add(new Newsletter("user3@hotmail.com", false));
        newsletters.add(new Newsletter("user4@gmail.com", true));
        newsletters.add(new Newsletter("user5@outlook.com", true));
        
        return newsletters;
    }
}
