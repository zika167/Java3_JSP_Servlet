<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/style.css" />
</head>
<body>
<div class="auth-container">
    <h2>Đăng ký</h2>
    <form action="${pageContext.request.contextPath}/auth/signup" method="post">
        <label for="id">Username</label>
        <input type="text" id="id" name="id" value="${form.id}" />
        <c:if test="${not empty errors.id}"><div class="error">${errors.id}</div></c:if>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" />
        <c:if test="${not empty errors.password}"><div class="error">${errors.password}</div></c:if>

        <label for="fullname">Full name</label>
        <input type="text" id="fullname" name="fullname" value="${form.fullname}" />
        <c:if test="${not empty errors.fullname}"><div class="error">${errors.fullname}</div></c:if>

        <div class="auth-actions">
            <button type="submit">Sign up</button>
            <a href="${pageContext.request.contextPath}/auth/login">Login</a>
        </div>
    </form>
</div>
</body>
</html>
