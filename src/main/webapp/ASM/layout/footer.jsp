<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Footer</title>
</head>
<body>
    <footer class="footer">
        <div class="container">
            <c:choose>
                <c:when test="${sessionScope.lang == 'en'}">
                    <p>&copy; 2024 ABC News. All rights reserved.</p>
                </c:when>
                <c:otherwise>
                    <p>&copy; 2024 ABC News. Tất cả quyền được bảo lưu.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </footer>
</body>
</html>
