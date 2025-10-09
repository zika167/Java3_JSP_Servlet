<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Kiểm tra xem người dùng đã đăng nhập hay chưa (thông qua session)
    if (session.getAttribute("username") == null) {
        // Nếu chưa, chuyển hướng về trang login
        response.sendRedirect("login");
        return; // Dừng thực thi trang này
    }
%>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f6fa; margin: 0; padding: 40px; display: flex; justify-content: center; align-items: center; min-height: 100vh; }
        .card { background: #fff; padding: 32px 36px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,.08); width: 420px; text-align: center; }
        h2 { margin: 16px 0 8px; }
        p { color: #555; margin-bottom: 24px; }
        a.button { display: inline-block; text-decoration: none; padding: 10px 16px; background: #dc3545; color: #fff; border-radius: 6px; cursor: pointer; }
        a.button:hover { background: #c82333; }
    </style>
</head>
<body>
<div class="card">
    <h2>Welcome, ${username}!</h2>
    <p>You have successfully logged in. ✅</p>
    <a href="${pageContext.request.contextPath}/logout" class="button">Logout</a>
</div>
</body>
</html>