<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Lab4 - Upload file</title>
        <style>
            body { font-family: Arial, sans-serif; background: #f5f6fa; margin: 0; padding: 40px; display: flex; justify-content: center; }
            .card { background: #fff; padding: 24px 28px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,.08); width: 520px; }
            h2 { margin: 0 0 16px; }
            .msg { margin: 0 0 16px; color: #0d6efd; }
            .row { margin-bottom: 12px; }
            input[type=file] { display: block; }
            .preview { margin-top: 12px; }
            .preview img { max-width: 100%; border-radius: 8px; }
            button { padding: 10px 16px; background: #0d6efd; color: #fff; border: 0; border-radius: 6px; cursor: pointer; }
        </style>
    </head>
    <body>
        <div class="card">
            <h2>Upload file</h2>
            <p class="msg">${message}</p>
            <c:url value="/upload" var="url"/>
            <form action="${url}" method="post" enctype="multipart/form-data">
                <div class="row">
                    <input name="photo" type="file" accept="image/*"/>
                </div>
                <button type="submit">Upload</button>
            </form>
            <c:if test="${not empty imagePath}">
                <div class="row">
                    <div>
                        <strong>Đường dẫn file:</strong>
                        <a href="${imagePath}" target="_blank">${imagePath}</a>
                    </div>
                </div>
                <div class="preview">
                    <img src="${imagePath}" alt="uploaded"/>
                </div>
            </c:if>
            <a href="/lab4" style="padding-top: 20px">Về trang tổng hợp bài tập</a>
        </div>
    </body>
</html>


