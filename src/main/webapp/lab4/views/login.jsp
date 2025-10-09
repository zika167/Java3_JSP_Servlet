<%--
  Created by IntelliJ IDEA.
  User: wangquockhanh
  Date: 29/9/25
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Lab4 - Login</title>
        <style>
            body { font-family: Arial, sans-serif; background: #f5f6fa; margin: 0; padding: 40px; display: flex; justify-content: center; }
            .card { background: #fff; padding: 24px 28px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,.08); width: 420px; }
            h2 { margin: 0 0 16px; }
            .msg { margin: 0 0 16px; color: #0d6efd; }
            .form-row { display: flex; flex-direction: column; margin-bottom: 12px; }
            label { margin-bottom: 6px; color: #555; }
            input { padding: 10px 12px; border: 1px solid #ccc; border-radius: 6px; font-size: 14px; }
            button { margin-top: 12px; padding: 10px 16px; background: #0d6efd; color: #fff; border: 0; border-radius: 6px; cursor: pointer; }
            button:hover { background: #0b5ed7; }
        </style>
    </head>
    <body>
        <div class="card">
            <h2>Login</h2>
            <p class="msg">${message}</p>
            <form method="post" action="/account/login">
                <div class="form-row">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="Enter username"/>
                </div>
                <div class="form-row">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter password"/>
                </div>
                <button type="submit">Sign in</button>
            </form>
            <a href="/lab4" style="padding-top: 20px">Về trang tổng hợp bài tập</a>
        </div>
    </body>
</html>
