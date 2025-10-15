<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Lab8</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lab8/assets/style.css" />
    </head>
    <body style="margin: 20px 0; display: block; text-align: center;">
        <header>
            <h1>FPT POLYTECHNIC</h1>
        </header>
        <hr>
        <nav>
            <c:url value="/lab8/home" var="path" />
            <a href="${path}/index">Home</a> |
            <a href="${path}/about">About Us</a> |
            <a href="${path}/contact">Contact Us</a>
        </nav>
        <hr>
        <main>
            <jsp:include page="${view}" />
        </main>
        <a href="/lab8" style="margin: 20px 0; display: block; text-align: center;">Về trang tổng hợp bài tập</a>
        <footer>
            <hr>
            &copy; 2025 by FPT Polytechnic. All rights reserved.
        </footer>
    </body>
</html>