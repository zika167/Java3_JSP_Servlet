<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Header</title>
</head>
<body>
    <header class="header">
        <div class="container">
            <c:choose>
                <c:when test="${sessionScope.lang == 'en'}">
                    <h1>ABC News - News Management System</h1>
                </c:when>
                <c:otherwise>
                    <h1>ABC News - Hệ thống quản lý tin tức</h1>
                </c:otherwise>
            </c:choose>
            <nav class="nav">
                <a href="index.jsp" class="nav-link">Trang chủ</a>
                <a href="${pageContext.request.contextPath}/reader" class="nav-link">Độc giả</a>
                <a href="${pageContext.request.contextPath}/reporter" class="nav-link">Phóng viên</a>
                <a href="${pageContext.request.contextPath}/admin" class="nav-link">Quản trị</a>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <span class="nav-link">Xin chào, ${sessionScope.user.fullname}</span>
                        <a href="${pageContext.request.contextPath}/auth/logout" class="nav-link">Logout</a>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${not requestScope.hideAuthButtons}">
                            <a href="${pageContext.request.contextPath}/auth/login" class="nav-link">Login</a>
                            <a href="${pageContext.request.contextPath}/auth/signup" class="nav-link">Sign up</a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <!-- Language links -->
                <a href="?lang=vi" class="nav-link">VI</a>
                <a href="?lang=en" class="nav-link">EN</a>
            </nav>
        </div>
    </header>
</body>
</html>
