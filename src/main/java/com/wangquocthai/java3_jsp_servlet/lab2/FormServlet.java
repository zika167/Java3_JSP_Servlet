package com.wangquocthai.java3_jsp_servlet.lab2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
// No additional util imports required

/**
 * Lớp Servlet được tạo từ template.
 */
@WebServlet("/form/update")
public class FormServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // Mã khởi tạo, được chạy một lần khi servlet được tạo.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu HTTP GET.
//        Map<String, Object> map = new HashMap<>();
//        map.put("fullname", "Nguyễn Văn Tèo");
//        map.put("gender", true);
//        map.put("country", "VN");
//        request.setAttribute("user", map);
        User bean = new User();
        bean.setFullname("Nguyễn Văn Tèo");
        bean.setGender(true);
        bean.setCountry("VN");
        request.setAttribute("user", bean);
        request.setAttribute("editabled", true);
        request.getRequestDispatcher("/lab2/views/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu HTTP POST.

        // Bind lại dữ liệu người dùng từ form vào bean và forward về form để hiển thị
//        String fullname = request.getParameter("fullname");
//        System.out.println(fullname);
        User bean = new User();
        bean.setFullname(request.getParameter("fullname"));
        String genderParam = request.getParameter("gender");
        bean.setGender("true".equalsIgnoreCase(genderParam));
        bean.setCountry(request.getParameter("country"));
        request.setAttribute("user", bean);
        request.setAttribute("editabled", true);
        request.getRequestDispatcher("/lab2/views/form.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        // Mã dọn dẹp, được chạy một lần khi servlet bị hủy.
    }
}