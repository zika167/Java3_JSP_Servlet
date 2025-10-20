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
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
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

            String categoryId = request.getParameter("id");
            if (categoryId == null || categoryId.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/reader");
                return;
            }

            // --- Pagination Logic ---
            int page = 1;
            int pageSize = 6; // Match the page size in ReaderServlet
            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                try {
                    page = Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }

            // --- Load news for the main content ---
            List<News> newsList = newsDAO.findByCategoryWithPagination(categoryId, page, pageSize);
            int totalNews = newsDAO.countTotalNewsByCategory(categoryId);
            int totalPages = (int) Math.ceil((double) totalNews / pageSize);

            request.setAttribute("newsList", newsList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("selectedCategoryId", categoryId); // To highlight active category

            // Forward to the same view as ReaderServlet for consistency
            request.getRequestDispatcher("/ASM/reader/news_list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error in CategoryServlet", e);
        }
    }
}
