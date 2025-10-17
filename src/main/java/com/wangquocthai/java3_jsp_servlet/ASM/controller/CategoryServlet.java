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
    private static final int PAGE_SIZE = 5;

    @Override
    public void init() throws ServletException {
        newsDAO = new NewsDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            List<Category> categories = categoryDAO.findAll();
            req.setAttribute("categories", categories);

            String categoryId = req.getParameter("id");
            if (categoryId == null || categoryId.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/reader");
                return;
            }

            int currentPage = 1;
            if (req.getParameter("page") != null) {
                try {
                    currentPage = Integer.parseInt(req.getParameter("page"));
                    if (currentPage < 1) currentPage = 1;
                } catch (NumberFormatException e) {
                    currentPage = 1;
                }
            }

            int totalNews = newsDAO.countTotalNewsByCategory(categoryId);
            int totalPages = (int) Math.ceil((double) totalNews / PAGE_SIZE);

            List<News> newsList = newsDAO.findByCategoryWithPagination(categoryId, currentPage, PAGE_SIZE);

            req.setAttribute("newsList", newsList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("categoryId", categoryId);

            req.getRequestDispatcher("/ASM/reader/news_list.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}