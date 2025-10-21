package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.CategoryDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.CategoryDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Category;
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
 * Admin Category CRUD Servlet
 */
@WebServlet(name = "AdminCategoryServlet", urlPatterns = {"/admin/category"})
public class AdminCategoryServlet extends HttpServlet {
    
    private CategoryDAO categoryDAO;
    
    @Override
    public void init() {
        categoryDAO = new CategoryDAOImpl();
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
            List<Category> categories = categoryDAO.findAll();
            request.setAttribute("categories", categories);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi tải danh sách: " + e.getMessage());
        }
        request.getRequestDispatcher("/ASM/admin/category_crud.jsp").forward(request, response);
    }
    
    private void handleEdit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            Category category = categoryDAO.findById(id);
            
            if (category != null) {
                request.setAttribute("editCategory", category);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi tải loại tin: " + e.getMessage());
        }
        
        handleList(request, response);
    }
    
    private void handleCreate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        
        // Validate
        if (id == null || id.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "ID và tên loại tin không được để trống!");
            handleList(request, response);
            return;
        }
        
        try {
            // Check if ID already exists
            Category existing = categoryDAO.findById(id);
            if (existing != null) {
                request.setAttribute("error", "ID loại tin đã tồn tại!");
                handleList(request, response);
                return;
            }
            
            Category category = new Category();
            category.setId(id.trim());
            category.setName(name.trim());
            
            int result = categoryDAO.insert(category);
            boolean success = result > 0;
            
            HttpSession session = request.getSession();
            if (success) {
                session.setAttribute("success", "Thêm loại tin thành công!");
            } else {
                session.setAttribute("error", "Thêm loại tin thất bại!");
            }
        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Lỗi: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/category");
    }
    
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        
        // Validate
        if (id == null || id.trim().isEmpty() || name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "ID và tên loại tin không được để trống!");
            handleList(request, response);
            return;
        }
        
        try {
            Category category = new Category();
            category.setId(id.trim());
            category.setName(name.trim());
            
            int result = categoryDAO.update(category);
            boolean success = result > 0;
            
            HttpSession session = request.getSession();
            if (success) {
                session.setAttribute("success", "Cập nhật loại tin thành công!");
            } else {
                session.setAttribute("error", "Cập nhật loại tin thất bại!");
            }
        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Lỗi: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/category");
    }
    
    private void handleDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String id = request.getParameter("id");
        
        if (id == null || id.trim().isEmpty()) {
            request.setAttribute("error", "ID loại tin không hợp lệ!");
            handleList(request, response);
            return;
        }
        
        try {
            int result = categoryDAO.deleteById(id);
            boolean success = result > 0;
            
            HttpSession session = request.getSession();
            if (success) {
                session.setAttribute("success", "Xóa loại tin thành công!");
            } else {
                session.setAttribute("error", "Xóa loại tin thất bại! Có thể loại tin đang được sử dụng.");
            }
        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Lỗi: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/category");
    }
}
