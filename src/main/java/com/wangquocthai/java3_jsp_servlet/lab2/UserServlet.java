package com.wangquocthai.java3_jsp_servlet.lab2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Lớp Servlet được tạo từ template.
 */
@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // Mã khởi tạo, được chạy một lần khi servlet được tạo.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu HTTP GET.
        request.setAttribute("message", "Welcome to FPT Polytechnic!");
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", "Nguyễn Văn Tèo");
        map.put("gender", "Male");
        map.put("country", "Việt Nam");
        request.setAttribute("user", map);
        request.getRequestDispatcher("/lab2/views/page2.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu HTTP POST.
        // Mặc định, bạn có thể gọi doGet để xử lý giống nhau.
        // doGet(request, response);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h1>Hello from " + request.getServletPath() + "</h1>");
        response.getWriter().println("<p>This is a POST request.</p>");
    }

    @Override
    public void destroy() {
        // Mã dọn dẹp, được chạy một lần khi servlet bị hủy.
    }
}