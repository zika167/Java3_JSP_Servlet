<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Department Management</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/lab7/assets/style.css" />
    </head>
    <body>
        <div class="container">
            <c:url var="path" value="/department" />
            <!-- Show validation errors -->
            <c:if test="${not empty errors}">
                <div style="color: red; margin-bottom: 10px;">
                    <ul>
                        <c:forEach var="err" items="${errors}">
                            <li>${err}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <!-- FORM -->
            <form method="post">
                <label>Id:</label>
                <br>
                <input name="id" value="${item.id}">
                <br>
                <label>Name:</label>
                <br>
                <input name="name" value="${item.name}"><br>
                <label>Description:</label>
                <br>
                <textarea name="description" rows="3">${item.description}</textarea>
                <hr>
                <button formaction="${path}/create">Create</button>
                <button formaction="${path}/update">Update</button>
                <button formaction="${path}/delete">Delete</button>
                <button formaction="${path}/reset">Reset</button>
            </form>
        </div>
        <hr style="margin: 20px 0">
        <!-- TABLE -->
        <table border="1" style="width: 90%">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="d" items="${list}" varStatus="vs">
                <tr>
                    <td>${vs.count}</td>
                    <td>${d.id}</td>
                    <td>${d.name}</td>
                    <td>${d.description}</td>
                    <td><a href="${path}/edit/${d.id}">Edit</a></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/lab7" style="margin: 20px 0; display: block; text-align: center;">Về trang tổng hợp bài tập</a>
    </body>
</html>
