package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet xử lý chuyển đổi ngôn ngữ
 */
@WebServlet(name = "LanguageServlet", urlPatterns = {"/lang"})
public class LanguageServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Lấy locale từ parameter
        String locale = request.getParameter("locale");
        
        // Validate locale (chỉ cho phép vi và en)
        if (locale == null || (!locale.equals("vi") && !locale.equals("en"))) {
            locale = "vi"; // Default to Vietnamese
        }
        
        // Lưu locale vào session
        HttpSession session = request.getSession();
        session.setAttribute("lang", locale);
        
        // Redirect về trang trước đó hoặc trang chủ
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.isEmpty()) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect(request.getContextPath() + "/reader");
        }
    }
}
