package com.wangquocthai.java3_jsp_servlet.lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UrlInfoServlet", value = "/url-info/*")
public class UrlInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String servletPath = request.getServletPath();
        String contextPath = request.getContextPath();
        String pathInfo = request.getPathInfo();
        String method = request.getMethod();

        out.println("<html><body>");
        out.println("<h3>Thông tin URL</h3>");
        out.println("<ul>");
        out.println("<li><b>URL</b>: " + url + "</li>");
        out.println("<li><b>URI</b>: " + uri + "</li>");
        out.println("<li><b>QueryString</b>: " + (queryString == null ? "" : queryString) + "</li>");
        out.println("<li><b>ServletPath</b>: " + servletPath + "</li>");
        out.println("<li><b>ContextPath</b>: " + contextPath + "</li>");
        out.println("<li><b>PathInfo</b>: " + (pathInfo == null ? "" : pathInfo) + "</li>");
        out.println("<li><b>Method</b>: " + method + "</li>");
        out.println("</ul>");
        out.println("<a href='" + request.getContextPath() + "/lab1'>Về trang chủ</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


