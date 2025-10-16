<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/style.css" />
</head>
<body>
<div class="auth-container">
    <h2>Đăng nhập</h2>
    <form action="${pageContext.request.contextPath}/auth/login" method="post">
        <label for="id">Username</label>
        <input type="text" id="id" name="id" value="${form.id}" />
        <c:if test="${not empty errors.id}"><div class="error">${errors.id}</div></c:if>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" />
        <c:if test="${not empty errors.password}"><div class="error">${errors.password}</div></c:if>

        <div class="auth-actions">
            <button type="submit">Login</button>
            <a href="${pageContext.request.contextPath}/auth/signup">Sign up</a>
        </div>
    </form>
</div>
</body>
</html>
