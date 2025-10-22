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
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "NewsDetailServlet", urlPatterns = {"/news/detail/*"})
public class NewsDetailServlet extends HttpServlet {
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
            // --- Load Shared Data for Header/Sidebar ---
            List<Category> categories = categoryDAO.findAll();
            request.setAttribute("categories", categories);

            // Hot news is often managed in a listener, but for robustness, we can load it here if needed.
            // Assuming hotNews is already in application scope from a listener.
            // If not, you would load it here:
            // List<News> hotNews = newsDAO.findTop5MostViewed();
            // request.setAttribute("hotNews", hotNews);

            // --- Load Detail-Specific Data ---
            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "News ID is missing.");
                return;
            }
            String newsId = pathInfo.substring(1);

            News news = newsDAO.findById(newsId);

            if (news != null) {
                // Increment view count (basic implementation)
                // A more robust solution would handle this in the DAO or a separate service
                news.setViewCount(news.getViewCount() + 1);
                // We can update the view count in a separate, non-blocking thread or a simpler update call
                // For now, a simple update is fine for this project's scope.
                // newsDAO.update(news); // This might be too broad, let's assume a specific method is better

                // --- Track Recently Viewed News in Session ---
                HttpSession session = request.getSession();
                @SuppressWarnings("unchecked")
                List<String> recentlyViewedIds = (List<String>) session.getAttribute("recentlyViewedIds");
                
                if (recentlyViewedIds == null) {
                    recentlyViewedIds = new ArrayList<>();
                }
                
                // Remove if already exists (to avoid duplicates)
                recentlyViewedIds.remove(newsId);
                
                // Add to the beginning (LIFO - Last In, First Out)
                recentlyViewedIds.add(0, newsId);
                
                // Keep only the last 5 items
                if (recentlyViewedIds.size() > 5) {
                    recentlyViewedIds = recentlyViewedIds.subList(0, 5);
                }
                
                // Save back to session
                session.setAttribute("recentlyViewedIds", recentlyViewedIds);

                // --- Load recently viewed news for sidebar (excluding current news) ---
                List<String> sidebarRecentIds = new ArrayList<>(recentlyViewedIds);
                sidebarRecentIds.remove(newsId); // Remove current news from sidebar
                List<News> recentlyViewedNews = new ArrayList<>();
                if (!sidebarRecentIds.isEmpty()) {
                    recentlyViewedNews = newsDAO.findNewsByIds(sidebarRecentIds);
                }

                request.setAttribute("news", news);
                request.setAttribute("recentlyViewedNews", recentlyViewedNews);

                // Get related news (same category, excluding self)
                List<News> relatedNews = newsDAO.findAll().stream()
                        .filter(n -> n.getCategoryId().equals(news.getCategoryId()) && !n.getId().equals(news.getId()))
                        .limit(5)
                        .collect(Collectors.toList());
                request.setAttribute("relatedNews", relatedNews);

                request.getRequestDispatcher("/ASM/reader/news_detail.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "News article not found.");
            }

        } catch (Exception e) {
            throw new ServletException("Error loading news detail page", e);
        }
    }
}
