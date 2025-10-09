<%--
  Created by IntelliJ IDEA.
  User: wangquockhanh
  Date: 25/9/25
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<html>
    <head>
        <title>Bài tập 4 - Xử lý chuỗi</title>
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
                text-align: left;
                max-width: 600px;
            }
            h2 {
                margin-bottom: 30px;
                color: #333;
                font-weight: 600;
                text-align: center;
            }
            .news-info {
                background: #f8f9fa;
                padding: 20px;
                border-radius: 8px;
                border-left: 4px solid #28a745;
            }
            ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }
            li {
                padding: 15px 0;
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
            .title {
                color: #dc3545;
                font-weight: 600;
                text-transform: uppercase;
            }
            .content {
                color: #6c757d;
                line-height: 1.5;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Thông tin bản tin</h2>
            <div class="news-info">
                <ul>
                    <li>
                        <span class="label">Title:</span>
                        <span class="value title">${fn:toUpperCase(item.title)}</span>
                    </li>
                    <li>
                        <span class="label">Content:</span>
                        <span class="value content">
                            <c:choose>
                                <c:when test="${fn:length(item.content) > 100}">
                                    ${fn:substring(item.content, 0, 100)}...
                                </c:when>
                                <c:otherwise>${item.content}</c:otherwise>
                            </c:choose>
                        </span>
                    </li>
                </ul>
            </div>
            <a href="/lab3" style="padding-top: 20px">Về trang tổng hợp bài tập</a>
        </div>
    </body>
</html>

