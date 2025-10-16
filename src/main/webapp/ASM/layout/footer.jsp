<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
