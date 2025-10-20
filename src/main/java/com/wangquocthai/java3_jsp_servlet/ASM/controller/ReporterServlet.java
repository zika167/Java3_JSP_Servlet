package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.CategoryDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.CategoryDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Category;
import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ReporterServlet", urlPatterns = {"/reporter"})
public class ReporterServlet extends HttpServlet {
    private NewsDAO newsDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() {
        newsDAO = new NewsDAOImpl();
        categoryDAO = new CategoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            handleDelete(request, response);
            return;
        }

        loadPage(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            handleCreate(request, response);
        } else if ("update".equals(action)) {
            handleUpdate(request, response);
        }
    }

    private void loadPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");

            if (currentUser == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            List<News> newsList = newsDAO.findByAuthor(currentUser.getId());
            List<Category> categories = categoryDAO.findAll();

            request.setAttribute("newsList", newsList);
            request.setAttribute("categories", categories);

            request.getRequestDispatcher("/ASM/reporter/news_crud.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error loading page", e);
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                newsDAO.deleteById(id);
            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/reporter");
    }

    private void handleCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("user");

            if (currentUser == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            News news = new News();
            news.setId(newsDAO.generateNextId()); // Auto-generate ID
            news.setTitle(request.getParameter("title"));
            news.setContent(request.getParameter("content"));
            news.setImage(request.getParameter("image"));
            news.setCategoryId(request.getParameter("categoryId"));
            news.setHome(request.getParameter("home"));
            news.setAuthor(currentUser.getId()); // Auto-set author
            news.setPostedDate(new Date()); // Auto-set posted date

            newsDAO.insert(news);

        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/reporter");
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String id = request.getParameter("id");
            News news = newsDAO.findById(id);

            if (news != null) {
                news.setTitle(request.getParameter("title"));
                news.setContent(request.getParameter("content"));
                news.setImage(request.getParameter("image"));
                news.setCategoryId(request.getParameter("categoryId"));
                news.setHome(request.getParameter("home"));
                // Author and PostedDate are not changed on update

                newsDAO.update(news);
            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/reporter");
    }
}
