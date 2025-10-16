<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}" scope="request" />
</c:if>
<fmt:setBundle basename="i18n.home" scope="request" />

<html>
<head>
    <title>contact</title>
</head>
<body>
<h1><fmt:message key="contact.title"/></h1>
</body>
</html>