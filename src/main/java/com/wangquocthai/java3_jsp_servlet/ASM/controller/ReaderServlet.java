package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.CategoryDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.CategoryDAOImpl;

/**
 * Servlet cho chức năng Độc giả (Reader)
 */
@WebServlet(name = "ReaderServlet", value = "/reader")
public class ReaderServlet extends HttpServlet {
    
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
        request.getRequestDispatcher("/ASM/reader/news_list.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    /**
     * Tạo dữ liệu mẫu cho danh sách tin tức
     */
    // Mock data removed - data loaded from database via DAO
}
