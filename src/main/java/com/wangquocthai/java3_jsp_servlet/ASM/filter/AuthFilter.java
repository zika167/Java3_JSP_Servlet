package com.wangquocthai.java3_jsp_servlet.ASM.filter;

import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // --- Bỏ qua các tài nguyên tĩnh và các trang công khai ---
        if (path.startsWith("/ASM/assets/") ||
            path.startsWith("/auth/") ||
            path.startsWith("/reader") ||
            path.startsWith("/news/detail/") ||
            path.equals("/") ||
            path.equals("/index.jsp") ||
            path.startsWith("/category") ||
            path.startsWith("/newsletter")
        ) {
            chain.doFilter(request, response);
            return;
        }

        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // --- Logic xác thực cho các trang yêu cầu đăng nhập ---
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/auth/login");
        } else {
            // Kiểm tra quyền truy cập dựa trên vai trò
            if (path.startsWith("/admin") && !"A".equals(user.getRole())) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            } else if (path.startsWith("/reporter") && !"R".equals(user.getRole())) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            } else {
                chain.doFilter(request, response);
            }
        }
    }
}
