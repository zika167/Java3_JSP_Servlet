<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Lab4 - Bài 2</title>
        <style>
            body { font-family: Arial, sans-serif; background: #f5f6fa; margin: 0; padding: 40px; display: flex; justify-content: center; }
            .card { background: #fff; padding: 24px 28px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,.08); width: 420px; }
            h2 { margin: 0 0 16px; }
            .msg { margin: 0 0 16px; color: #0d6efd; }
            .row { margin-bottom: 10px; }
            input { padding: 10px 12px; border: 1px solid #ccc; border-radius: 6px; font-size: 14px; width: 100%; }
            .actions { display: flex; gap: 10px; }
            button { flex: 1; padding: 10px 16px; background: #0d6efd; color: #fff; border: 0; border-radius: 6px; cursor: pointer; }
            button.secondary { background: #6c757d; }
        </style>
    </head>
    <body>
        <div class="card">
            <h2>Tính toán</h2>
            <p class="msg">${message}</p>
            <c:url value="/calculate" var="cal"/>
            <form method="post">
                <div class="row"><input name="a" placeholder="Nhập số a" value="${param.a}"/></div>
                <div class="row"><input name="b" placeholder="Nhập số b" value="${param.b}"/></div>
                <div class="actions">
                    <button formaction="${cal}/add"                     >+</button>
                    <button formaction="${cal}/sub" class="secondary"   >-</button>
                </div>
            </form>
            <a href="/lab4" style="padding-top: 20px">Về trang tổng hợp bài tập</a>
        </div>
    </body>
</html>


