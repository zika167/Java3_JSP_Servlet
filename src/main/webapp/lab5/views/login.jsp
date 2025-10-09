<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f6fa; margin: 0; padding: 40px; display: flex; justify-content: center; align-items: center; min-height: 100vh; }
        .card { background: #fff; padding: 24px 28px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0, 0, 0, .08); width: 380px; }
        h2 { margin: 0 0 20px; text-align: center; }
        .form-row { display: flex; flex-direction: column; margin-bottom: 15px; }
        label { margin-bottom: 6px; color: #555; }
        input[type="text"], input[type="password"] { padding: 10px 12px; border: 1px solid #ccc; border-radius: 6px; font-size: 14px; }
        .remember-row { display: flex; align-items: center; margin-bottom: 20px; }
        .remember-row input { margin-right: 8px; }
        button { padding: 12px 16px; background: #0d6efd; color: #fff; border: 0; border-radius: 6px; cursor: pointer; width: 100%; font-size: 16px; }
        button:hover { background: #0b5ed7; }
        .message { padding: 10px; border-radius: 6px; margin-bottom: 15px; text-align: center; border: 1px solid #f5c6cb; background-color: #f8d7da; color: #721c24; }
    </style>
</head>
<body>
<div class="card">
    <h2>Login</h2>

    <%-- Hiển thị thông báo lỗi nếu có --%>
    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <form action="<c:url value='/login'/>" method="post">
        <div class="form-row">
            <label for="username">Username</label>
            <%-- Điền sẵn username từ cookie (nếu có) --%>
            <input id="username" name="username" type="text" value="${username}">
        </div>
        <div class="form-row">
            <label for="password">Password</label>
            <%-- Điền sẵn password từ cookie (nếu có) --%>
            <input id="password" name="password" type="password" value="${password}">
        </div>
        <div class="remember-row">
            <input id="remember-me" name="remember-me" type="checkbox">
            <label for="remember-me">Remember me?</label>
        </div>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>