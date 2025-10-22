package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.UserDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.UserDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Profile management servlet for reporters and admins
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Check authentication
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/reader");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        // Only reporters and admins can access profile
        if (!"R".equals(user.getRole()) && !"A".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/reader");
            return;
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("/ASM/profile/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Check authentication
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/reader");
            return;
        }
        
        User currentUser = (User) session.getAttribute("user");
        String action = request.getParameter("action");

        if ("updateProfile".equals(action)) {
            handleUpdateProfile(request, response, currentUser);
        } else if ("changePassword".equals(action)) {
            handleChangePassword(request, response, currentUser);
        }
    }

    private void handleUpdateProfile(HttpServletRequest request, HttpServletResponse response, User currentUser) 
            throws ServletException, IOException {
        try {
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String birthday = request.getParameter("birthday");
            String gender = request.getParameter("gender");
            String mobile = request.getParameter("mobile");

            // Update user info
            currentUser.setFullname(fullname);
            currentUser.setEmail(email);
            
            // Parse birthday string to Date
            if (birthday != null && !birthday.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date birthdayDate = sdf.parse(birthday);
                    currentUser.setBirthday(birthdayDate);
                } catch (Exception e) {
                    // Keep existing birthday if parsing fails
                }
            }
            
            currentUser.setGender(gender);
            currentUser.setMobile(mobile);

            int result = userDAO.update(currentUser);
            
            HttpSession session = request.getSession();
            if (result > 0) {
                // Update session with new info
                session.setAttribute("user", currentUser);
                session.setAttribute("success", "Cập nhật thông tin thành công!");
            } else {
                session.setAttribute("error", "Cập nhật thông tin thất bại!");
            }

            response.sendRedirect(request.getContextPath() + "/profile");
        } catch (Exception e) {
            throw new ServletException("Error updating profile", e);
        }
    }

    private void handleChangePassword(HttpServletRequest request, HttpServletResponse response, User currentUser) 
            throws ServletException, IOException {
        try {
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            HttpSession session = request.getSession();

            // Validate current password
            if (!currentUser.getPassword().equals(currentPassword)) {
                session.setAttribute("error", "Mật khẩu hiện tại không đúng!");
                response.sendRedirect(request.getContextPath() + "/profile");
                return;
            }

            // Validate new password confirmation
            if (!newPassword.equals(confirmPassword)) {
                session.setAttribute("error", "Mật khẩu mới và xác nhận không khớp!");
                response.sendRedirect(request.getContextPath() + "/profile");
                return;
            }

            // Update password
            currentUser.setPassword(newPassword);
            int result = userDAO.update(currentUser);

            if (result > 0) {
                // Update session with new password
                session.setAttribute("user", currentUser);
                session.setAttribute("success", "Đổi mật khẩu thành công!");
            } else {
                session.setAttribute("error", "Đổi mật khẩu thất bại!");
            }

            response.sendRedirect(request.getContextPath() + "/profile");
        } catch (Exception e) {
            throw new ServletException("Error changing password", e);
        }
    }
}
