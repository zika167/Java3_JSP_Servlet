package com.wangquocthai.java3_jsp_servlet.ASM.listener;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.NewsDAOImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Listener to initialize application-wide data
 */
@WebListener
public class NewsContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext context = sce.getServletContext();
            NewsDAO newsDAO = new NewsDAOImpl();
            
            // Get hot news (most viewed)
            context.setAttribute("hotNews", 
                newsDAO.findAll().stream()
                    .sorted((n1, n2) -> Integer.compare(n2.getViewCount(), n1.getViewCount()))
                    .limit(5)
                    .toList()
            );
            
            // Get latest news
            context.setAttribute("latestNews",
                newsDAO.findAll().stream()
                    .sorted((n1, n2) -> n2.getPostedDate().compareTo(n1.getPostedDate()))
                    .limit(5)
                    .toList()
            );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}