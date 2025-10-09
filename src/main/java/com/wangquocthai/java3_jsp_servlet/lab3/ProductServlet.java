package com.wangquocthai.java3_jsp_servlet.lab3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet cho bài tập số 3 - Định dạng thời gian và số
 */
@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tạo Map chứa thông tin sản phẩm
        Map<String, Object> map = new HashMap<>();
        map.put("name", "iPhone 2024");
        map.put("price", 12345.678);
        map.put("date", new Date());
        
        // Truyền Map sang JSP
        request.setAttribute("item", map);
        
        // Forward đến trang JSP
        request.getRequestDispatcher("lab3/views/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý POST request tương tự GET
        doGet(request, response);
    }
}

