package com.wangquocthai.java3_jsp_servlet.lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Lớp Servlet được tạo từ template.
 */
@WebServlet(name = "CrudServlet", urlPatterns = {
        "/crud/create",
        "/crud/update",
        "/crud/delete",
        "/crud/edit/2024"
})
public class CrudServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // Mã khởi tạo, được chạy một lần khi servlet được tạo.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String actionMessage = "";
        if (uri.endsWith("/crud/create")) {
            actionMessage = "Create";
        } else if (uri.endsWith("/crud/update")) {
            actionMessage = "Update";
        } else if (uri.endsWith("/crud/delete")) {
            actionMessage = "Delete";
        } else if (uri.endsWith("/crud/edit/2024")) {
            actionMessage = "Edit 2024";
        } else {
            actionMessage = "Unknown";
        }
        request.setAttribute("action", actionMessage + " Page");
        request.getRequestDispatcher("/lab1/views/crud/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu HTTP POST.
        // Mặc định, bạn có thể gọi doGet để xử lý giống nhau.
        String uri = request.getRequestURI();
        String data = request.getParameter("data");
        if (uri.endsWith("/crud/create")) {
            request.setAttribute("action", "Create Page");
            request.setAttribute("message", "Data created successfully: " + data);
        } else if (uri.endsWith("/crud/update")) {
            request.setAttribute("action", "Update Page");
            request.setAttribute("message", "Data updated successfully: " + data);
        } else if (uri.endsWith("/crud/delete")) {
            request.setAttribute("action", "Delete Page");
            request.setAttribute("message", "Data deleted successfully: " + data);
        } else if (uri.endsWith("/crud/edit/2024")) {
            request.setAttribute("action", "Edit 2024 Page");
            request.setAttribute("message", "Data edited for id 2024: " + data);
        }
        request.getRequestDispatcher("/lab1/views/crud/index.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        // Mã dọn dẹp, được chạy một lần khi servlet bị hủy.
    }
}