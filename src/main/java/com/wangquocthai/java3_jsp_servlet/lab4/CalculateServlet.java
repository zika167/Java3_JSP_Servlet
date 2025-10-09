package com.wangquocthai.java3_jsp_servlet.lab4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CalculateServlet", urlPatterns = {"/calculate","/calculate/add", "/calculate/sub"})
public class CalculateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "Nhập số và chọn phép tính");
        req.getRequestDispatcher("/lab4/views/calc.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String aStr = req.getParameter("a");
        String bStr = req.getParameter("b");

        double a, b;
        try {
            a = Double.parseDouble(aStr);
            b = Double.parseDouble(bStr);
        } catch (Exception ex) {
            req.setAttribute("message", "Giá trị nhập không hợp lệ");
            req.getRequestDispatcher("/lab4/views/calc.jsp").forward(req, resp);
            return;
        }

        String servletPath = req.getServletPath();
        double result;
        if ("/calculate/add".equals(servletPath)) {
            result = a + b;
        } else {
            result = a - b;
        }

        req.setAttribute("message", "Kết quả: " + result);
        req.getRequestDispatcher("/lab4/views/calc.jsp").forward(req, resp);
    }
}


