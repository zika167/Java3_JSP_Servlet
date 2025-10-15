package com.wangquocthai.java3_jsp_servlet.lab7.servlet;

import com.wangquocthai.java3_jsp_servlet.lab7.dao.EmployeeDAO;
import com.wangquocthai.java3_jsp_servlet.lab7.dao.impl.EmployeeDAOImpl;
import com.wangquocthai.java3_jsp_servlet.lab7.dao.DepartmentDAO;
import com.wangquocthai.java3_jsp_servlet.lab7.dao.impl.DepartmentDAOImpl;
import com.wangquocthai.java3_jsp_servlet.lab7.entity.Employee;
import com.wangquocthai.java3_jsp_servlet.lab7.entity.Department;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@WebServlet({
    "/employee/index",
    "/employee/edit/*",
    "/employee/create",
    "/employee/update",
    "/employee/delete",
    "/employee/reset"
})
@MultipartConfig
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee form = new Employee();

        DateTimeConverter dtc = new DateConverter(new Date());
        dtc.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dtc, Date.class);

        try {
            BeanUtils.populate(form, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        EmployeeDAO dao = new EmployeeDAOImpl();
        DepartmentDAO deptDao = new DepartmentDAOImpl();
        String path = request.getServletPath();
        List<String> errors = new java.util.ArrayList<>();
        // Validation
        if ("/employee/create".equals(path) || "/employee/update".equals(path)) {
            if (form.getId() == null || form.getId().trim().isEmpty()) errors.add("Mã nhân viên không được để trống!");
            if (form.getPassword() == null || form.getPassword().trim().isEmpty()) errors.add("Mật khẩu không được để trống!");
            if (form.getFullname() == null || form.getFullname().trim().isEmpty()) errors.add("Họ tên không được để trống!");
            if (form.getSalary() == null) errors.add("Lương không được để trống!");
            if (form.getDepartmentID() == null || form.getDepartmentID().trim().isEmpty()) errors.add("Phòng ban không được để trống!");
        }
        if (path.contains("edit")) {
            String id = request.getPathInfo().substring(1);
            form = dao.findById(id);
        } else if (path.contains("create")) {
            if (errors.isEmpty()) {
                Part photoPart = request.getPart("photoFile");
                if (photoPart != null && photoPart.getSize() > 0) {
                    String fileName = photoPart.getSubmittedFileName();
                    String uploadDir = request.getServletContext().getRealPath("/uploads");
                    File dir = new File(uploadDir);
                    if (!dir.exists()) dir.mkdirs();
                    photoPart.write(uploadDir + File.separator + fileName);
                    form.setPhoto(fileName);
                }
                try {
                    dao.create(form);
                    form = new Employee();
                } catch (RuntimeException e) {
                    if (e.getMessage().contains("Duplicate entry")) {
                        errors.add("Mã phòng ban đã tồn tại!");
                    } else {
                        throw e;
                    }
                }
            }
        } else if (path.contains("update")) {
            if (errors.isEmpty()) {
                Employee old = dao.findById(form.getId());
                Part photoPart = request.getPart("photoFile");
                if (photoPart != null && photoPart.getSize() > 0) {
                    String fileName = photoPart.getSubmittedFileName();
                    String uploadDir = request.getServletContext().getRealPath("/uploads");
                    File dir = new File(uploadDir);
                    if (!dir.exists()) dir.mkdirs();
                    photoPart.write(uploadDir + File.separator + fileName);
                    form.setPhoto(fileName);
                } else {
                    form.setPhoto(old.getPhoto()); // keep old photo if not uploaded
                }
                dao.update(form);
            }
        } else if (path.contains("delete")) {
            dao.deleteById(form.getId());
            form = new Employee();
        } else {
            form = new Employee();
        }
        request.setAttribute("item", form);
        List<Employee> list = dao.findAll();
        request.setAttribute("list", list);
        List<Department> departments = deptDao.findAll();
        request.setAttribute("departments", departments);
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/lab7/views/employee.jsp").forward(request, response);
    }
}