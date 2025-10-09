<%--
  Created by IntelliJ IDEA.
  User: wangquockhanh
  Date: 25/9/25
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
    <head>
        <title>Bài tập 3 - Định dạng thời gian và số</title>
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
                /*text-align: center;*/
                max-width: 500px;
            }
            h2 {
                margin-bottom: 30px;
                color: #333;
                font-weight: 600;
            }
            .product-info {
                background: #f8f9fa;
                padding: 20px;
                border-radius: 8px;
                border-left: 4px solid #4a90e2;
            }
            ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }
            li {
                padding: 10px 0;
                font-size: 16px;
                border-bottom: 1px solid #e9ecef;
            }
            li:last-child {
                border-bottom: none;
            }
            .label {
                font-weight: 600;
                color: #495057;
                display: inline-block;
                width: 80px;
                text-align: left;
            }
            .value {
                color: #212529;
            }
            .price {
                color: #28a745;
                font-weight: 600;
            }
            .date {
                color: #6c757d;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Thông tin sản phẩm</h2>
            <div class="product-info">
                <ul>
                    <li>
                        <span class="label">Name:</span>
                        <span class="value">${item.name}</span>
                    </li>
                    <li>
                        <span class="label">Price:</span>
                        <span class="value price">
                            <fmt:formatNumber value="${item.price}" pattern="#,###.00"/>
                        </span>
                    </li>
                    <li>
                        <span class="label">Date:</span>
                        <span class="value date">
                            <fmt:formatDate value="${item.date}" pattern="MMM dd, yyyy"/>
                        </span>
                    </li>
                </ul>
            </div>
            <a href="/lab3" style="padding-top: 20px">Về trang tổng hợp bài tập</a>
        </div>
    </body>
</html>

