package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.CategoryDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.CategoryDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet cho chức năng Phóng viên (Reporter)
 */
@WebServlet(name = "ReporterServlet", urlPatterns = {"/reporter", "/reporter/dashboard", "/reporter/new_crud"})
public class ReporterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            NewsDAO newsDAO = new NewsDAOImpl();
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            List<News> newsList = newsDAO.findAll();
            List<Category> categories = categoryDAO.findAll();
            request.setAttribute("newsList", newsList);
            request.setAttribute("categories", categories);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        // Forward đến JSP
        request.getRequestDispatcher("/ASM/reporter/news_crud.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Xử lý form thêm/sửa bài viết
        String action = request.getParameter("action");
        
        if ("add".equals(action) || "edit".equals(action)) {
            // Lấy dữ liệu từ form
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String image = request.getParameter("image");
            String category = request.getParameter("category");
            
            // Server-side validation example
            com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation v = new com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation();
            v.required("title", title, "Title is required");
            v.required("category", category, "Category is required");
            if (v.hasErrors()) {
                request.setAttribute("errors", v.getErrors());
                request.setAttribute("form", java.util.Map.of(
                        "title", title == null ? "" : title,
                        "content", content == null ? "" : content,
                        "image", image == null ? "" : image,
                        "category", category == null ? "" : category
                ));
                request.getRequestDispatcher("/ASM/reporter/news_crud.jsp").forward(request, response);
                return;
            }
            
            // Xử lý logic thêm/sửa (mock)
            System.out.println("Processing article: " + title + " - " + category);
            
            // Redirect về trang danh sách
            response.sendRedirect(request.getContextPath() + "/reporter");
            return;
        }
        
        doGet(request, response);
    }
    
    // Mock data removed - data is loaded from database via DAO
}
