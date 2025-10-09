package com.wangquocthai.java3_jsp_servlet.lab5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hủy session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Xóa cookie "remember-me"
        Cookie cookie = new Cookie("user", "");
        cookie.setMaxAge(0); // Set thời gian sống bằng 0 để xóa cookie
        cookie.setPath("/");
        response.addCookie(cookie);

        // Chuyển hướng về trang đăng nhập
        response.sendRedirect("/lab5/views/login.jsp");
    }
}