package com.wangquocthai.java3_jsp_servlet.lab5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Lớp Servlet được tạo từ template.
 */
@WebServlet(name = "EmailServlet", value = "/send_mail")
public class EmailServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // Mã khởi tạo, được chạy một lần khi servlet được tạo.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/lab5/views/email_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nhận thông tin từ form
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        // Gọi phương thức send từ lớp Mailer
        Mailer.send(from, to, subject, body);

        // In thông báo ra trang web sau khi gửi
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<h2>Đã thực hiện gửi email!</h2>");
        response.getWriter().println("<p>Vui lòng kiểm tra hộp thư của người nhận.</p>");
    }

    @Override
    public void destroy() {
        // Mã dọn dẹp, được chạy một lần khi servlet bị hủy.
    }
}