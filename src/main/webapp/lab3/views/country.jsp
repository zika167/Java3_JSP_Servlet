<%--
  Created by IntelliJ IDEA.
  User: wangquockhanh
  Date: 25/9/25
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Lab3</title>
        <style>
            body {
                font-family: "Segoe UI", Arial, sans-serif;
                background: #f5f6fa;
                margin: 0;
                padding: 40px;
                display: flex;
                justify-content: center;
            }
            .container {
                background: #ffffff;
                padding: 30px 40px;
                border-radius: 12px;
                box-shadow: 0 4px 10px rgba(0,0,0,0.1);
                text-align: center;
            }
            h3 {
                margin-bottom: 20px;
                color: #333;
                font-weight: 600;
            }
            select {
                padding: 10px 15px;
                border-radius: 6px;
                border: 1px solid #ccc;
                font-size: 16px;
                transition: border 0.2s;
                min-width: 200px;
            }
            select:focus {
                border-color: #4a90e2;
                outline: none;
            }
            button {
                margin-top: 20px;
                padding: 10px 20px;
                background: #4a90e2;
                color: white;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-size: 16px;
            }
            button:hover {
                background: #357ABD;
            }
            select {
                padding: 10px 40px 10px 15px;  /* top right bottom left */
                border-radius: 6px;
                border: 1px solid #ccc;
                font-size: 16px;
                appearance: none;              /* ẩn style mặc định của trình duyệt */
                -webkit-appearance: none;      /* Safari/Chrome */
                -moz-appearance: none;         /* Firefox */
                background: #fff url("data:image/svg+xml;utf8,<svg fill='gray' height='20' width='20' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/></svg>") no-repeat right 10px center;
                background-size: 16px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h3>Chọn quốc gia</h3>
            <form>
                <select name="country">
                    <c:forEach var="ct" items="${countries}">
                        <option value="${ct.id}">${ct.name}</option>
                    </c:forEach>
                </select>
                <br>
                <button type="submit">Xác nhận</button>
            </form>
            <a href="/lab3" style="padding-top: 20px">Về trang tổng hợp bài tập</a>
        </div>
    </body>
</html>
