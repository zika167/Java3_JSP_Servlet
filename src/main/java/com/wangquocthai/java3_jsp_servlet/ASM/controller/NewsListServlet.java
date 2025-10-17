package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/news/list")
public class NewsListServlet extends HttpServlet {
    private NewsDAO newsDAO;
    private static final int PAGE_SIZE = 5;

    @Override
    public void init() throws ServletException {
        newsDAO = new NewsDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get page number from request parameter, default to 1
            int page = 1;
            String pageStr = req.getParameter("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                try {
                    page = Integer.parseInt(pageStr);
                    if (page < 1) page = 1;
                } catch (NumberFormatException e) {
                    // Invalid page number, use default
                }
            }

            // Get total news count and calculate total pages
            int totalNews = newsDAO.countTotalNews();
            int totalPages = (int) Math.ceil((double) totalNews / PAGE_SIZE);
            
            // Ensure page number doesn't exceed total pages
            if (page > totalPages) page = totalPages;

            // Get paginated list of news
            List<News> newsList = newsDAO.findWithPagination(page, PAGE_SIZE);

            // Set attributes for JSP
            req.setAttribute("newsList", newsList);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);

            req.getRequestDispatcher("/ASM/reader/news_list.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error fetching news list", e);
        }
    }
}