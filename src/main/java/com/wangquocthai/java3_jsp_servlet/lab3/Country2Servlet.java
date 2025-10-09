package com.wangquocthai.java3_jsp_servlet.lab3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Lớp Servlet được tạo từ template.
 */
@WebServlet(name = "Country2Servlet", value = "/Country2Servlet")
public class Country2Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // Mã khởi tạo, được chạy một lần khi servlet được tạo.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu HTTP GET.
        List<Country> list = List.of(
                new Country("AM", "America"),
                new Country("PL", "Philadelphia"),
                new Country("TL", "Thailand")
        );
        request.setAttribute("countries", list);
        request.getRequestDispatcher("lab3/views/country2.jsp").forward(request, response);
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