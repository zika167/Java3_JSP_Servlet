package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsletterDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsletterDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Newsletter;
import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Admin Newsletter CRUD Servlet
 */
@WebServlet(name = "AdminNewsletterServlet", urlPatterns = {"/admin/newsletter"})
public class AdminNewsletterServlet extends HttpServlet {
    
    private NewsletterDAO newsletterDAO;
    
    @Override
    public void init() {
        newsletterDAO = new NewsletterDAOImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Check admin authentication
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/reader");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        if (!"A".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/reader");
            return;
        }
        
        try {
            String action = request.getParameter("action");
            
            if ("EDIT".equals(action)) {
                handleEdit(request, response);
            } else if ("DELETE".equals(action)) {
                handleDelete(request, response);
            } else {
                handleList(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi: " + e.getMessage());
            handleList(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Check admin authentication
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/reader");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        if (!"A".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/reader");
            return;
        }
        
        try {
            String action = request.getParameter("action");
            
            if ("CREATE".equals(action)) {
                handleCreate(request, response);
            } else if ("UPDATE".equals(action)) {
                handleUpdate(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi: " + e.getMessage());
            handleList(request, response);
        }
    }
    
    private void handleList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Newsletter> newsletters = newsletterDAO.findAll();
            request.setAttribute("newsletters", newsletters);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi tải danh sách: " + e.getMessage());
        }
        request.getRequestDispatcher("/ASM/admin/newsletter_crud.jsp").forward(request, response);
    }
    
    private void handleEdit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        try {
            Newsletter newsletter = newsletterDAO.findById(email);
            
            if (newsletter != null) {
                request.setAttribute("editNewsletter", newsletter);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi tải newsletter: " + e.getMessage());
        }
        
        handleList(request, response);
    }
    
    private void handleCreate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String enabledStr = request.getParameter("enabled");
        
        // Validate
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email không được để trống!");
            handleList(request, response);
            return;
        }
        
        // Validate email format
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            request.setAttribute("error", "Email không hợp lệ!");
            handleList(request, response);
            return;
        }
        
        try {
            // Check if email already exists
            Newsletter existing = newsletterDAO.findById(email);
            if (existing != null) {
                request.setAttribute("error", "Email đã tồn tại trong danh sách!");
                handleList(request, response);
                return;
            }
            
            Newsletter newsletter = new Newsletter();
            newsletter.setEmail(email.trim());
            newsletter.setEnabled("1".equals(enabledStr));
            
            int result = newsletterDAO.insert(newsletter);
            boolean success = result > 0;
            
            HttpSession session = request.getSession();
            if (success) {
                session.setAttribute("success", "Thêm email thành công!");
            } else {
                session.setAttribute("error", "Thêm email thất bại!");
            }
        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Lỗi: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/newsletter");
    }
    
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String enabledStr = request.getParameter("enabled");
        
        // Validate
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email không được để trống!");
            handleList(request, response);
            return;
        }
        
        try {
            Newsletter newsletter = new Newsletter();
            newsletter.setEmail(email.trim());
            newsletter.setEnabled("1".equals(enabledStr));
            
            int result = newsletterDAO.update(newsletter);
            boolean success = result > 0;
            
            HttpSession session = request.getSession();
            if (success) {
                session.setAttribute("success", "Cập nhật trạng thái thành công!");
            } else {
                session.setAttribute("error", "Cập nhật trạng thái thất bại!");
            }
        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Lỗi: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/newsletter");
    }
    
    private void handleDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email không hợp lệ!");
            handleList(request, response);
            return;
        }
        
        try {
            int result = newsletterDAO.deleteById(email);
            boolean success = result > 0;
            
            HttpSession session = request.getSession();
            if (success) {
                session.setAttribute("success", "Xóa email thành công!");
            } else {
                session.setAttribute("error", "Xóa email thất bại!");
            }
        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Lỗi: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/newsletter");
    }
}
