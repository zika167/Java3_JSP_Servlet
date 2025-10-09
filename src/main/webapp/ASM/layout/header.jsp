<%--
  Created by IntelliJ IDEA.
  User: wangquockhanh
  Date: 2/10/25
  Time: 07:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
</head>
<body>
    <header class="header">
        <div class="container">
            <h1>ABC News - Hệ thống quản lý tin tức</h1>
            <nav class="nav">
                <a href="index.jsp" class="nav-link">Trang chủ</a>
                <a href="${pageContext.request.contextPath}/reader" class="nav-link">Độc giả</a>
                <a href="${pageContext.request.contextPath}/reporter" class="nav-link">Phóng viên</a>
                <a href="${pageContext.request.contextPath}/admin" class="nav-link">Quản trị</a>
            </nav>
        </div>
    </header>
</body>
</html>
