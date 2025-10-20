<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}" scope="request" />
</c:if>
<fmt:setBundle basename="i18n.footer" scope="request" />

<footer class="footer">
    <div class="container">
        <p><fmt:message key="footer.copyright"/></p>
    </div>
</footer>
