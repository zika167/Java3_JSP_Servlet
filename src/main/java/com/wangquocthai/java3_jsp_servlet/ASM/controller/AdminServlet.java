package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.UserDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsletterDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.UserDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsletterDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Newsletter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet cho chức năng Quản trị (Admin)
 */
@WebServlet(name ="AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            UserDAO userDAO = new UserDAOImpl();
            NewsletterDAO newsletterDAO = new NewsletterDAOImpl();
            List<User> users = userDAO.findAll();
            List<Newsletter> newsletters = newsletterDAO.findAll();

            request.setAttribute("users", users);
            request.setAttribute("newsletters", newsletters);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
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

            // Server-side validation using Validation helper
            com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation v = new com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation();
            v.required("username", username, "Username is required");
            v.required("email", email, "Email is required");
            if (v.hasErrors()) {
                request.setAttribute("errors", v.getErrors());
                request.setAttribute("form", java.util.Map.of(
                        "username", username == null ? "" : username,
                        "email", email == null ? "" : email,
                        "role", role == null ? "" : role,
                        "fullName", fullName == null ? "" : fullName
                ));
                request.getRequestDispatcher("/ASM/admin/user_crud.jsp").forward(request, response);
                return;
            }

            // Xử lý logic thêm user (mock)
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
    
    // Mock data removed - data is loaded from database via DAO
}
