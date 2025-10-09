package com.wangquocthai.java3_jsp_servlet.lab5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc cookie từ trình duyệt
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Tìm cookie có tên là "user"
                if (cookie.getName().equals("user")) {
                    try {
                        // Giải mã giá trị cookie bằng Base64
                        String encodedValue = cookie.getValue();
                        byte[] bytes = Base64.getDecoder().decode(encodedValue);
                        String userInfo = new String(bytes);

                        // Tách username và password (được nối bằng dấu "|")
                        String[] parts = userInfo.split("\\|");
                        if (parts.length == 2) {
                            // Set thuộc tính để điền vào form
                            request.setAttribute("username", parts[0]);
                            request.setAttribute("password", parts[1]);
                        }
                    } catch (Exception e) {
                        // Bỏ qua nếu cookie bị lỗi
                    }
                    break;
                }
            }
        }
        // Chuyển tiếp đến trang login.jsp
        request.getRequestDispatcher("/lab5/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember-me");

        // Logic đăng nhập: Kiểm tra tài khoản
        if (username.equalsIgnoreCase("FPT") && password.equals("poly")) {
            // Đăng nhập thành công, lưu username vào session
            request.getSession().setAttribute("username", username);

            // Nếu người dùng tick vào "Remember me"
            if (remember != null) {
                // Tạo chuỗi thông tin user
                String userInfo = username + "|" + password;
                // Mã hóa Base64
                String encodedUserInfo = Base64.getEncoder().encodeToString(userInfo.getBytes());
                // Tạo cookie
                Cookie cookie = new Cookie("user", encodedUserInfo);
                // Thiết lập thời gian sống cho cookie (30 ngày)
                cookie.setMaxAge(30 * 24 * 60 * 60);
                // Thiết lập đường dẫn áp dụng cho toàn bộ ứng dụng
                cookie.setPath("/");
                // Gửi cookie về trình duyệt
                response.addCookie(cookie);
            }
            // Chuyển hướng đến trang dashboard
            response.sendRedirect("/lab5/views/dashboard.jsp");
        } else {
            // Đăng nhập thất bại
            request.setAttribute("message", "Invalid username or password!");
            // Quay lại trang login để hiển thị lỗi
            request.getRequestDispatcher("/lab5/views/login.jsp").forward(request, response);
        }
    }
}