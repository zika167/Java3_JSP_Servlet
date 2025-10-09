<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f6fa; margin: 0; padding: 40px; display: flex; justify-content: center; align-items: center; min-height: 100vh; box-sizing: border-box; }
        .card { background: #fff; padding: 32px 36px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,.08); width: 420px; text-align: center; }
        .error-icon { font-size: 48px; color: #dc3545; }
        h2 { margin: 16px 0 8px; }
        p { color: #555; margin-bottom: 24px; }
        a.button { display: inline-block; text-decoration: none; padding: 10px 16px; background: #6c757d; color: #fff; border-radius: 6px; cursor: pointer; }
        a.button:hover { background: #5c636a; }
        .error-details { font-family: monospace; font-size: 12px; color: #777; margin-top: 20px; text-align: left; background: #f1f1f1; padding: 10px; border-radius: 4px; display: none; /* Ẩn đi khi deploy thực tế */ }
    </style>
</head>
<body>
<div class="card">
    <div class="error-icon">!</div>
    <h2>Oops! Có lỗi đã xảy ra</h2>
    <p>Đã có lỗi không mong muốn trong quá trình xử lý yêu cầu của bạn. Vui lòng thử lại sau. 😥</p>

    <c:url value="/" var="homeUrl"/>
    <a href="${homeUrl}" class="button">Quay về trang chủ</a>

    <%-- PHẦN NÀY CHỈ DÀNH CHO LẬP TRÌNH VIÊN ĐỂ DEBUG --%>
    <%-- Bạn nên xóa hoặc ẩn đi khi deploy sản phẩm --%>
    <div class="error-details">
        <strong>Error Details (for developers):</strong><br>
        <%= exception.getMessage() %>
    </div>
</div>
</body>
</html>