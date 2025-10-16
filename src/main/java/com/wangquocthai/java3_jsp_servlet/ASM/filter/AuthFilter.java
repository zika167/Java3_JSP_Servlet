package com.wangquocthai.java3_jsp_servlet.ASM.filter;

import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/reporter/*"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        User user = (User) req.getSession().getAttribute("user");
        String uri = req.getRequestURI();
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/auth/login");
            return;
        }
        // check role
        if (uri.startsWith(req.getContextPath() + "/admin") && !"A".equalsIgnoreCase(user.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (uri.startsWith(req.getContextPath() + "/reporter") && !"R".equalsIgnoreCase(user.getRole()) && !"A".equalsIgnoreCase(user.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }
}
