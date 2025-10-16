<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ page pageEncoding="utf-8"%>

<c:if test="${not empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}" scope="session" />
</c:if>
<fmt:setBundle basename="i18n.global" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Lab8</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lab8/assets/style.css" />
    </head>
    <body class="container" style="text-align: center">
        <header>
            <h1>FPT POLYTECHNIC</h1>
        </header>
        <hr>
        <nav>
            <c:url value="/lab8/home" var="path" />
            <a href="${path}/index">
                <fmt:message key="menu.home" />
            </a>
            |
            <a href="${path}/about">
                <fmt:message key="menu.about" />
            </a>
            |
            <a href="${path}/contact">
                <fmt:message key="menu.contact" />
            </a>
            ||
            <a href="?lang=vi">Tiếng Việt</a> - <a href="?lang=en">English</a>
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