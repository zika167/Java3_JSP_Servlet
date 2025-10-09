package com.wangquocthai.java3_jsp_servlet.lab3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet cho bài tập số 4 - Xử lý chuỗi
 */
@WebServlet(name = "NewsServlet", value = "/NewsServlet")
public class NewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tạo Map chứa thông tin bản tin
        Map<String, Object> map = new HashMap<>();
        map.put("title", "Tiêu đề bản tin");
        map.put("content", "Nội dung bản tin thường rất dài và có thể chứa nhiều thông tin quan trọng mà người đọc cần biết. Đây là một ví dụ về nội dung dài để test chức năng cắt chuỗi trong JSTL functions. Nội dung này sẽ được hiển thị với giới hạn 100 ký tự đầu tiên.");
        
        // Truyền Map sang JSP
        request.setAttribute("item", map);
        
        // Forward đến trang JSP
        request.getRequestDispatcher("lab3/views/news.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý POST request tương tự GET
        doGet(request, response);
    }
}

