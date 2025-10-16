package com.wangquocthai.java3_jsp_servlet.lab8;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet({
    "/lab8/home/index",
    "/lab8/home/about",
    "/lab8/home/contact"
})
public class HomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        Locale locale = "vi".equals(lang) ? Locale.forLanguageTag("vi") : Locale.ENGLISH;
        ResourceBundle globalBundle = ResourceBundle.getBundle("i18n.global", locale);
        ResourceBundle homeBundle = ResourceBundle.getBundle("i18n.home", locale);

        req.setAttribute("menuHome", globalBundle.getString("menu.home"));
        req.setAttribute("menuAbout", globalBundle.getString("menu.about"));
        req.setAttribute("menuContact", globalBundle.getString("menu.contact"));

        String uri = req.getRequestURI();
        if (uri.contains("index")) {
            req.setAttribute("view", "/lab8/views/index.jsp");
            req.setAttribute("pageTitle", homeBundle.getString("index.title"));
        } else if (uri.contains("about")) {
            req.setAttribute("view", "/lab8/views/about.jsp");
            req.setAttribute("pageTitle", homeBundle.getString("about.title"));
        } else if (uri.contains("contact")) {
            req.setAttribute("view", "/lab8/views/contact.jsp");
            req.setAttribute("pageTitle", homeBundle.getString("contact.title"));
        }
        req.getRequestDispatcher("/lab8/views/layout.jsp").forward(req, resp);
    }
}