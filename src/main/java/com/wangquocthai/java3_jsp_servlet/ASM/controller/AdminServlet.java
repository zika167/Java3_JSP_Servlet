package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.CategoryDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.UserDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsletterDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.CategoryDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.UserDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsletterDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Category;
import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Newsletter;
import com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation;
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
    private UserDAO userDAO;
    private NewsletterDAO newsletterDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
        newsletterDAO = new NewsletterDAOImpl();
        categoryDAO = new CategoryDAOImpl(); // <-- THÊM DÒNG NÀY
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            List<User> users = userDAO.findAll();
            List<Newsletter> newsletters = newsletterDAO.findAll();
            List<Category> categories = categoryDAO.findAll();

            request.setAttribute("users", users);
            request.setAttribute("newsletters", newsletters);
            request.setAttribute("categories", categories);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
        // Forward đến JSP
        request.getRequestDispatcher("/ASM/admin/user_crud.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        try {
            switch (action != null ? action : "") {
                case "CREATE":
                    handleCreate(request, response);
                    break;
                case "UPDATE":
                    handleUpdate(request, response);
                    break;
                case "DELETE":
                    handleDelete(request, response);
                    break;
                case "TOGGLE_STATUS":
                    handleToggleStatus(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/admin");
            }
        } catch (Exception e) {
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            doGet(request, response);
        }
    }
    
    private void handleCreate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        
        // Validation
        Validation v = new Validation();
        v.required("id", id, "Tên đăng nhập không được để trống");
        v.required("password", password, "Mật khẩu không được để trống");
        v.required("fullname", fullname, "Họ tên không được để trống");
        v.required("email", email, "Email không được để trống");
        v.required("role", role, "Vai trò không được để trống");
        
        if (v.hasErrors()) {
            request.setAttribute("errors", v.getErrors());
            request.setAttribute("form", java.util.Map.of(
                "id", id == null ? "" : id,
                "fullname", fullname == null ? "" : fullname,
                "email", email == null ? "" : email,
                "role", role == null ? "" : role
            ));
            doGet(request, response);
            return;
        }
        
        try {
            // Check if user exists
            User existingUser = userDAO.findById(id);
            if (existingUser != null) {
                request.setAttribute("error", "Tên đăng nhập đã tồn tại!");
                request.setAttribute("form", java.util.Map.of(
                    "id", id,
                    "fullname", fullname,
                    "email", email,
                    "role", role
                ));
                doGet(request, response);
                return;
            }
            
            // Create new user
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            user.setFullname(fullname);
            user.setEmail(email);
            user.setRole(mapRoleToCode(role));
            user.setActive(true);
            user.setBirthday(new java.util.Date());
            
            userDAO.insert(user);
            request.getSession().setAttribute("success", "Thêm người dùng thành công!");
            response.sendRedirect(request.getContextPath() + "/admin");
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi thêm người dùng: " + e.getMessage());
            doGet(request, response);
        }
    }
    
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        
        try {
            User user = userDAO.findById(id);
            if (user == null) {
                request.setAttribute("error", "Không tìm thấy người dùng!");
                doGet(request, response);
                return;
            }
            
            // Update fields
            user.setFullname(fullname);
            user.setEmail(email);
            user.setRole(mapRoleToCode(role));
            
            // Only update password if provided
            if (password != null && !password.trim().isEmpty()) {
                user.setPassword(password);
            }
            
            userDAO.update(user);
            request.getSession().setAttribute("success", "Cập nhật người dùng thành công!");
            response.sendRedirect(request.getContextPath() + "/admin");
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi cập nhật: " + e.getMessage());
            doGet(request, response);
        }
    }
    
    private void handleDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        
        try {
            userDAO.deleteById(id);
            request.getSession().setAttribute("success", "Xóa người dùng thành công!");
            response.sendRedirect(request.getContextPath() + "/admin");
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi xóa: " + e.getMessage());
            doGet(request, response);
        }
    }
    
    private void handleToggleStatus(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        
        try {
            User user = userDAO.findById(id);
            if (user != null) {
                user.setActive(!user.isActive());
                userDAO.update(user);
                request.getSession().setAttribute("success", 
                    user.isActive() ? "Đã mở khóa người dùng!" : "Đã khóa người dùng!");
            }
            response.sendRedirect(request.getContextPath() + "/admin");
            
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi thay đổi trạng thái: " + e.getMessage());
            doGet(request, response);
        }
    }
    
    private String mapRoleToCode(String roleName) {
        if (roleName == null) return "R";
        switch (roleName.toLowerCase()) {
            case "admin": return "A";
            case "reporter": return "R";
            case "reader": return "R";
            default: return "R";
        }
    }
    
    // Mock data removed - data is loaded from database via DAO
}
