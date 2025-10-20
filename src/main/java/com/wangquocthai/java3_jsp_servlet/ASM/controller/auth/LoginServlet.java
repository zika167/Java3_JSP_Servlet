package com.wangquocthai.java3_jsp_servlet.ASM.controller.auth;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.UserDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.UserDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/auth/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ASM/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check if this is an AJAX request
        boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation v = new com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation();
        v.required("id", id, "Username is required");
        v.required("password", password, "Password is required");
        
        if (v.hasErrors()) {
            if (isAjax) {
                sendJsonResponse(resp, false, "Vui lòng nhập đầy đủ thông tin", null);
            } else {
                req.setAttribute("errors", v.getErrors());
                req.setAttribute("form", java.util.Map.of("id", id == null ? "" : id));
                req.getRequestDispatcher("/ASM/auth/login.jsp").forward(req, resp);
            }
            return;
        }

        try {
            UserDAO userDAO = new UserDAOImpl();
            User user = userDAO.findById(id);
            
            if (user == null || !user.getPassword().equals(password) || !user.isActive()) {
                if (isAjax) {
                    sendJsonResponse(resp, false, "Tên đăng nhập hoặc mật khẩu không đúng", null);
                } else {
                    v = new com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation();
                    v.required("id", "", "Invalid credentials or inactive account");
                    req.setAttribute("errors", v.getErrors());
                    req.setAttribute("form", java.util.Map.of("id", id == null ? "" : id));
                    req.getRequestDispatcher("/ASM/auth/login.jsp").forward(req, resp);
                }
                return;
            }
            
            // Set session
            req.getSession().setAttribute("user", user);
            
            if (isAjax) {
                // Return JSON response for AJAX
                sendJsonResponse(resp, true, "Đăng nhập thành công", user);
            } else {
                // Traditional redirect based on role
                if ("A".equalsIgnoreCase(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/admin");
                } else if ("R".equalsIgnoreCase(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/reporter");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/reader");
                }
            }
        } catch (Exception e) {
            if (isAjax) {
                sendJsonResponse(resp, false, "Có lỗi xảy ra. Vui lòng thử lại sau.", null);
            } else {
                throw new ServletException(e);
            }
        }
    }
    
    private void sendJsonResponse(HttpServletResponse resp, boolean success, String message, User user) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"success\":").append(success).append(",");
        json.append("\"error\":\"").append(message).append("\"");
        
        if (success && user != null) {
            json.append(",\"user\":{");
            json.append("\"fullname\":\"").append(user.getFullname()).append("\",");
            json.append("\"role\":\"").append(user.getRole()).append("\"");
            json.append("}");
        }
        
        json.append("}");
        resp.getWriter().write(json.toString());
    }
}
