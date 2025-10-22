package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.CategoryDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.CategoryDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Category;
import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ReaderServlet", urlPatterns = {"/reader"})
public class ReaderServlet extends HttpServlet {
    private NewsDAO newsDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() {
        newsDAO = new NewsDAOImpl();
        categoryDAO = new CategoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // --- Load data for header (categories) ---
            List<Category> categories = categoryDAO.findAll();
            request.setAttribute("categories", categories);

            // --- Pagination Logic ---
            int page = 1;
            int pageSize = 6; // Number of articles per page
            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                try {
                    page = Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                    page = 1; // Default to page 1 if param is invalid
                }
            }

            // --- Load news for the main content ---
            List<News> newsList = newsDAO.findWithPagination(page, pageSize);
            int totalNews = newsDAO.countTotalNews();
            int totalPages = (int) Math.ceil((double) totalNews / pageSize);

            // --- Load recently viewed news for sidebar ---
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            List<String> recentlyViewedIds = (List<String>) session.getAttribute("recentlyViewedIds");
            List<News> recentlyViewedNews = new ArrayList<>();
            
            if (recentlyViewedIds != null && !recentlyViewedIds.isEmpty()) {
                recentlyViewedNews = newsDAO.findNewsByIds(recentlyViewedIds);
            }

            request.setAttribute("newsList", newsList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("recentlyViewedNews", recentlyViewedNews);

            request.getRequestDispatcher("/ASM/reader/news_list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error in ReaderServlet", e);
        }
    }
}
