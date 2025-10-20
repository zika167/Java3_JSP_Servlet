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
import java.util.Date;

@WebServlet(name = "SignupServlet", urlPatterns = {"/auth/signup"})
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ASM/auth/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check if this is an AJAX request
        boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");

        com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation v = new com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation();
        v.required("id", id, "Username is required");
        v.required("password", password, "Password is required");
        v.required("fullname", fullname, "Full name is required");
        v.required("email", email, "Email is required");
        
        if (v.hasErrors()) {
            if (isAjax) {
                sendJsonResponse(resp, false, "Vui lòng nhập đầy đủ thông tin");
            } else {
                req.setAttribute("errors", v.getErrors());
                req.setAttribute("form", java.util.Map.of("id", id == null ? "" : id, "fullname", fullname == null ? "" : fullname, "email", email == null ? "" : email));
                req.getRequestDispatcher("/ASM/auth/signup.jsp").forward(req, resp);
            }
            return;
        }

        try {
            UserDAO userDAO = new UserDAOImpl();
            User exists = userDAO.findById(id);
            
            if (exists != null) {
                if (isAjax) {
                    sendJsonResponse(resp, false, "Tên đăng nhập đã tồn tại");
                } else {
                    v = new com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation();
                    v.required("id", "", "Username already exists");
                    req.setAttribute("errors", v.getErrors());
                    req.setAttribute("form", java.util.Map.of("id", id == null ? "" : id));
                    req.getRequestDispatcher("/ASM/auth/signup.jsp").forward(req, resp);
                }
                return;
            }
            
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            user.setFullname(fullname);
            user.setEmail(email);
            user.setRole("R"); // default to Reader for signup
            user.setActive(true);
            user.setBirthday(new Date());

            userDAO.insert(user);
            
            if (isAjax) {
                // For AJAX, just return success (don't auto-login)
                sendJsonResponse(resp, true, "Đăng ký thành công! Vui lòng đăng nhập.");
            } else {
                // Traditional flow: auto login and redirect
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/reader");
            }
        } catch (Exception e) {
            if (isAjax) {
                sendJsonResponse(resp, false, "Có lỗi xảy ra. Vui lòng thử lại sau.");
            } else {
                throw new ServletException(e);
            }
        }
    }
    
    private void sendJsonResponse(HttpServletResponse resp, boolean success, String message) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"success\":").append(success).append(",");
        json.append("\"error\":\"").append(message).append("\"");
        json.append("}");
        
        resp.getWriter().write(json.toString());
    }
}
