package com.wangquocthai.java3_jsp_servlet.lab8;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({
    "/lab8/home/index",
    "/lab8/home/about",
    "/lab8/home/contact"
})
public class HomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("index")) {
            req.setAttribute("view", "/lab8/views/index.jsp");
        } else if (uri.contains("about")) {
            req.setAttribute("view", "/lab8/views/about.jsp");
        } else if (uri.contains("contact")) {
            req.setAttribute("view", "/lab8/views/contact.jsp");
        }
        req.getRequestDispatcher("/lab8/views/layout.jsp").forward(req, resp);
    }
}