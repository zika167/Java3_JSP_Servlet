package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.CategoryDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.CategoryDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet cho chức năng xem chi tiết tin tức
 */
@WebServlet(name = "NewsDetailServlet", urlPatterns = {"/news/detail/*"})
public class NewsDetailServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Extract news ID from URL path
            String pathInfo = request.getPathInfo();
            String newsId = pathInfo.substring(1); // Remove leading slash
            
            // Get news detail
            NewsDAO newsDAO = new NewsDAOImpl();
            News news = newsDAO.findById(newsId);
            
            if (news != null) {
                // Increment view count
                news.setViewCount(news.getViewCount() + 1);
                newsDAO.update(news);
                
                // Set attributes for JSP
                request.setAttribute("news", news);
                
                // Get related news (same category)
                request.setAttribute("relatedNews", newsDAO.findAll().stream()
                    .filter(n -> n.getCategoryId().equals(news.getCategoryId()) 
                            && !n.getId().equals(news.getId()))
                    .limit(5)
                    .toList());
                
                // Forward to detail view
                request.getRequestDispatcher("/ASM/reader/news_detail.jsp")
                    .forward(request, response);
            } else {
                // News not found
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}