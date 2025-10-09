<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f6fa; margin: 0; padding: 40px; display: flex; justify-content: center; align-items: center; min-height: 100vh; box-sizing: border-box; }
        .card { background: #fff; padding: 32px 36px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,.08); width: 420px; text-align: center; }
        .success-icon { font-size: 48px; color: #198754; }
        h2 { margin: 16px 0 8px; }
        p { color: #555; margin-bottom: 24px; }
        a.button { display: inline-block; text-decoration: none; padding: 10px 16px; background: #0d6efd; color: #fff; border-radius: 6px; cursor: pointer; }
        a.button:hover { background: #0b5ed7; }
    </style>
</head>
<body>
<div class="card">
    <div class="success-icon">✔</div>
    <h2>Thành công!</h2>
    <p>Thông tin của bạn đã được lưu lại thành công. 🥳</p>

    <%-- Đường dẫn quay về trang form ban đầu --%>
    <c:url value="/bean.jsp" var="formUrl"/>
    <a href="${formUrl}" class="button">Thêm nhân viên mới</a>
</div>
</body>
</html>