package com.wangquocthai.java3_jsp_servlet.lab5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.io.IOException;
import java.util.Date;

/**
 * Lớp Servlet được tạo từ template.
 */
@WebServlet(name = "BeanServlet", value = "/save")
public class BeanServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu HTTP GET.
        request.setAttribute("message", "Hãy điền thông tin vào form");
        request.getRequestDispatcher("/lab5/views/bean.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Staff bean = new Staff();

        try {
            // Khai bao dinh dang
            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);

            // Doc va chuyen doi tham so vao bean
            BeanUtils.populate(bean, request.getParameterMap());
            System.out.println(bean.getFullname());
            System.out.println(bean.getBirthday());
            System.out.println(bean.getCountry());
            System.out.println(bean.isGender());
            System.out.println(bean.getSalary());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {}
}