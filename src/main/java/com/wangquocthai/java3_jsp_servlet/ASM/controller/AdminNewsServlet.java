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

/**
 * Admin News CRUD Servlet - Same functionality as Reporter
 */
@WebServlet(name = "AdminNewsServlet", urlPatterns = {"/admin/news"})
public class AdminNewsServlet extends HttpServlet {
    private NewsDAO newsDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() {
        newsDAO = new NewsDAOImpl();
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
        
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            handleDelete(request, response);
            return;
        }

        loadPage(request, response);
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
        
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            handleCreate(request, response);
        } else if ("update".equals(action)) {
            handleUpdate(request, response);
        }
    }

    private void loadPage(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Category> categories = categoryDAO.findAll();
            request.setAttribute("categories", categories);

            String newsId = request.getParameter("id");
            if (newsId != null && !newsId.isEmpty()) {
                News news = newsDAO.findById(newsId);
                request.setAttribute("editNews", news);
            }

            List<News> newsList = newsDAO.findAll();
            request.setAttribute("newsList", newsList);

            request.getRequestDispatcher("/ASM/admin/news_crud.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error loading page", e);
        }
    }

    private void handleCreate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String image = request.getParameter("image");
            String categoryId = request.getParameter("categoryId");
            boolean home = request.getParameter("home") != null;

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            news.setImage(image);
            news.setCategoryId(categoryId);
            news.setHome(home ? "1" : "0");
            news.setPostedDate(new Date());
            news.setAuthor(user.getId());
            news.setViewCount(0);

            int result = newsDAO.insert(news);

            if (result > 0) {
                session.setAttribute("success", "Thêm tin tức thành công!");
            } else {
                session.setAttribute("error", "Thêm tin tức thất bại!");
            }

            response.sendRedirect(request.getContextPath() + "/admin/news");
        } catch (Exception e) {
            throw new ServletException("Error creating news", e);
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String image = request.getParameter("image");
            String categoryId = request.getParameter("categoryId");
            boolean home = request.getParameter("home") != null;

            News news = newsDAO.findById(id);
            if (news != null) {
                news.setTitle(title);
                news.setContent(content);
                news.setImage(image);
                news.setCategoryId(categoryId);
                news.setHome(home ? "1" : "0");

                int result = newsDAO.update(news);

                HttpSession session = request.getSession();
                if (result > 0) {
                    session.setAttribute("success", "Cập nhật tin tức thành công!");
                } else {
                    session.setAttribute("error", "Cập nhật tin tức thất bại!");
                }
            }

            response.sendRedirect(request.getContextPath() + "/admin/news");
        } catch (Exception e) {
            throw new ServletException("Error updating news", e);
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            int result = newsDAO.deleteById(id);

            HttpSession session = request.getSession();
            if (result > 0) {
                session.setAttribute("success", "Xóa tin tức thành công!");
            } else {
                session.setAttribute("error", "Xóa tin tức thất bại!");
            }

            response.sendRedirect(request.getContextPath() + "/admin/news");
        } catch (Exception e) {
            throw new ServletException("Error deleting news", e);
        }
    }
}
