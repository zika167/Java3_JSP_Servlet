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
        <title>Bài tập 2 - Tạo bảng bằng class</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #f4f6f8;
                margin: 0;
                padding: 20px;
            }

            .container {
                max-width: 800px;
                margin: 40px auto;
                background: #fff;
                padding: 20px 30px;
                border-radius: 10px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.08);
            }

            table {
                width: 100%;
                border-collapse: collapse;
                font-size: 16px;
            }

            thead th {
                background-color: #3498db;
                color: #fff;
                text-align: left;
                padding: 12px;
            }

            tbody td {
                padding: 12px;
                border-bottom: 1px solid #e0e0e0;
            }

            tbody tr:hover {
                background-color: #f1f7ff;
                transition: background 0.2s;
            }

            /* Tùy chọn: làm số thứ tự nhỏ hơn chút */
            td:first-child {
                text-align: center;
                width: 60px;
                color: #555;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Id</th>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ct" items="${countries}" varStatus="vs">
                    <tr>
                        <td>${vs.count}</td>
                        <td>${ct.id}</td>
                        <td>${ct.name}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="/lab3" style="padding-top: 20px">Về trang tổng hợp bài tập</a>s
        </div>
    </body>
</html>
