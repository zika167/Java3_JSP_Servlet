package com.wangquocthai.java3_jsp_servlet.lab1;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = {"/hello-servlet","/trangchu","/index"})
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Wang Quoc Thai sieu dep trai";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<a href='" + request.getContextPath() + "/lab1'>Về trang chủ</a>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}