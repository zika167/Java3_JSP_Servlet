package com.wangquocthai.java3_jsp_servlet.lab7.servlet;

import com.wangquocthai.java3_jsp_servlet.lab7.dao.DepartmentDAO;
import com.wangquocthai.java3_jsp_servlet.lab7.dao.impl.DepartmentDAOImpl;
import com.wangquocthai.java3_jsp_servlet.lab7.entity.Department;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Lớp Servlet được tạo từ template.
 */
@WebServlet({
    "/department/index"
    ,"/department/edit/*"
    ,"/department/create"
    ,"/department/update"
    ,"/department/delete"
    ,"/department/reset"
})
public class DeparmentServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // Mã khởi tạo, được chạy một lần khi servlet được tạo.
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu HTTP.
        Department form = new Department();
        try {
            BeanUtils.populate(form,request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        DepartmentDAO dao = new DepartmentDAOImpl();
        String path = request.getServletPath();
        List<String> errors = new java.util.ArrayList<>();
        if ("/department/create".equals(path) || "/department/update".equals(path)) {
            if (form.getId() == null || form.getId().trim().isEmpty()) errors.add("Mã phòng ban không được để trống!");
            if (form.getName() == null || form.getName().trim().isEmpty()) errors.add("Tên phòng ban không được để trống!");
        }
        if (path.contains("edit")){
            String id = request.getPathInfo().substring(1);
            form = dao.findById(id);
        } else if (path.contains("create")){
            if (errors.isEmpty()) {
                try {
                    dao.create(form);
                    form = new Department();
                } catch (RuntimeException e) {
                    if (e.getMessage().contains("Duplicate entry")) {
                        errors.add("Mã phòng ban đã tồn tại!");
                    } else {
                        throw e;
                    }
                }
            }
        } else if (path.contains("update")){
            if (errors.isEmpty()) {
                dao.update(form);
            }
        } else if (path.contains("delete")){
            dao.deleteById(form.getId());
            form = new Department();
        } else {
            form = new Department();
        }
        request.setAttribute("item",form);
        List<Department> list = dao.findAll();
        request.setAttribute("list",list);
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/lab7/views/department.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu HTTP POST.
    }

    @Override
    public void destroy() {
        // Mã dọn dẹp, được chạy một lần khi servlet bị hủy.
    }
}