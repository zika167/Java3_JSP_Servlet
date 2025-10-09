package com.wangquocthai.java3_jsp_servlet.lab4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "RegisterServlet", value = "/account/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "Hãy điền thông tin vào form");
        req.getRequestDispatcher("/lab4/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender   = req.getParameter("gender");
        String married  = req.getParameter("married");
        String country  = req.getParameter("country");
        String[] hobbies = req.getParameterValues("hobbies");
        String note     = req.getParameter("note");

        System.out.println("[REGISTER] username=" + username);
        System.out.println("[REGISTER] password=" + password);
        System.out.println("[REGISTER] gender=" + gender);
        System.out.println("[REGISTER] married=" + (married != null));
        System.out.println("[REGISTER] country=" + country);
        System.out.println("[REGISTER] hobbies=" + (hobbies == null ? "[]" : Arrays.toString(hobbies)));
        System.out.println("[REGISTER] note=" + note);

        req.setAttribute("message", "Đã ghi log dữ liệu đăng ký trên console");
        req.getRequestDispatcher("/lab4/views/register.jsp").forward(req, resp);
    }
}


