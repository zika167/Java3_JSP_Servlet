package com.wangquocthai.java3_jsp_servlet.ASM.controller.auth;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.UserDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.dao.impl.UserDAOImpl;
import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "SignupServlet", urlPatterns = {"/auth/signup"})
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ASM/auth/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");

        com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation v = new com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation();
        v.required("id", id, "Username is required");
        v.required("password", password, "Password is required");
        if (v.hasErrors()) {
            req.setAttribute("errors", v.getErrors());
            req.setAttribute("form", java.util.Map.of("id", id == null ? "" : id, "fullname", fullname == null ? "" : fullname, "email", email == null ? "" : email));
            req.getRequestDispatcher("/ASM/auth/signup.jsp").forward(req, resp);
            return;
        }

        try {
            UserDAO userDAO = new UserDAOImpl();
            User exists = userDAO.findById(id);
            if (exists != null) {
                v = new com.wangquocthai.java3_jsp_servlet.ASM.utils.Validation();
                v.required("id", "", "Username already exists");
                req.setAttribute("errors", v.getErrors());
                req.setAttribute("form", java.util.Map.of("id", id == null ? "" : id));
                req.getRequestDispatcher("/ASM/auth/signup.jsp").forward(req, resp);
                return;
            }
            User user = new User();
            user.setId(id);
            user.setPassword(password);
            user.setFullname(fullname);
            user.setEmail(email);
            user.setRole("R"); // default to Reporter for signup; change as needed
            user.setActive(true);
            user.setBirthday(new Date());

            userDAO.insert(user);
            // auto login
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/reporter");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
