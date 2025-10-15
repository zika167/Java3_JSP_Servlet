<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Employee Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lab7/assets/style.css" />
</head>
<body>
    <div class="container">
        <c:url var="path" value="/employee" />
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
        <form method="post" enctype="multipart/form-data">
            <label>Id:</label><br>
            <input name="id" value="${item.id}"><br>
            <label>Password:</label><br>
            <input name="password" value="${item.password}"><br>
            <label>Fullname:</label><br>
            <input name="fullname" value="${item.fullname}"><br>
            <label>Photo:</label><br>
            <input type="file" name="photoFile"><br>
            <c:if test="${not empty item.photo}">
                <img src="${pageContext.request.contextPath}/uploads/${item.photo}" width="80" />
            </c:if><br>
            <label>Gender:</label><br>
            <select name="gender">
                <option value="true" ${item.gender ? 'selected' : ''}>Nam</option>
                <option value="false" ${!item.gender ? 'selected' : ''}>Nữ</option>
            </select><br>
            <label>Birthday:</label><br>
            <input type="date" name="birthday" value="${item.birthday}"><br>
            <label>Salary:</label><br>
            <input name="salary" value="${item.salary}"><br>
            <label>Department:</label><br>
            <select name="departmentID">
                <c:forEach var="d" items="${departments}">
                    <option value="${d.id}" ${d.id == item.departmentID ? 'selected' : ''}>${d.name}</option>
                </c:forEach>
            </select><br>
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
                <th>Fullname</th>
                <th>Photo</th>
                <th>Gender</th>
                <th>Birthday</th>
                <th>Salary</th>
                <th>Department</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${list}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${e.id}</td>
                <td>${e.fullname}</td>
                <td><c:if test="${not empty e.photo}"><img src="${pageContext.request.contextPath}/uploads/${e.photo}" width="50" /></c:if></td>
                <td>${e.gender ? 'Nam' : 'Nữ'}</td>
                <td>${e.birthday}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty e.salary}">
                            <fmt:formatNumber value="${e.salary}" type="currency" currencySymbol="" groupingUsed="true" />đ
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${e.departmentID}</td>
                <td><a href="${path}/edit/${e.id}">Edit</a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/lab7" style="margin: 20px 0; display: block; text-align: center;">Về trang tổng hợp bài tập</a>
</body>
</html>
