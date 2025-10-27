<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JAVA 3 FINAL TEST</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        .form-container {
            background-color: #f9f9f9;
            padding: 20px;
            border: 2px solid #ddd;
            border-radius: 5px;
            margin-bottom: 30px;
        }

        .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }

        .form-row label {
            width: 120px;
            font-weight: bold;
            margin-right: 10px;
        }

        .form-row input[type="text"],
        .form-row input[type="password"],
        .form-row input[type="email"] {
            flex: 1;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        .radio-group {
            display: flex;
            gap: 15px;
        }

        .radio-group input[type="radio"] {
            margin-right: 5px;
        }

        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }

        .btn {
            padding: 10px 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #f0f0f0;
            cursor: pointer;
            font-size: 14px;
        }

        .btn:hover {
            background-color: #e0e0e0;
        }

        .btn-create { background-color: #4CAF50; color: white; }
        .btn-update { background-color: #2196F3; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
        .btn-reset { background-color: #ff9800; color: white; }

        .table-container {
            margin-top: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        table th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        table tr:hover {
            background-color: #f5f5f5;
        }

        .edit-link {
            color: #2196F3;
            text-decoration: none;
            font-weight: bold;
        }

        .edit-link:hover {
            text-decoration: underline;
        }

        .message {
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
        }

        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>QUẢN LÝ TÀI KHOẢN KHÁCH HÀNG</h1>
        
        <!-- Hiển thị thông báo -->
        <c:if test="${not empty success}">
            <div class="message success">${success}</div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="message error">${error}</div>
        </c:if>
        
        <!-- Form nhập liệu -->
        <div class="form-container">
            <form method="post" action="${pageContext.request.contextPath}/khachhang">
                <div class="form-row">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" 
                           value="${khachHang.username}" 
                           ${isEdit ? 'readonly' : ''} required>
                </div>
                
                <div class="form-row">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" 
                           value="${khachHang.password}" required>
                </div>
                
                <div class="form-row">
                    <label for="hoten">Họ tên:</label>
                    <input type="text" id="hoten" name="hoten" 
                           value="${khachHang.hoten}" required>
                </div>
                
                <div class="form-row">
                    <label>Giới tính:</label>
                    <div class="radio-group">
                        <label>
                            <input type="radio" name="gioitinh" value="Nam" 
                                   ${khachHang.gioitinh == 'Nam' ? 'checked' : ''}> Nam
                        </label>
                        <label>
                            <input type="radio" name="gioitinh" value="Nữ" 
                                   ${khachHang.gioitinh == 'Nữ' ? 'checked' : ''}> Nữ
                        </label>
                    </div>
                </div>
                
                <div class="form-row">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" 
                           value="${khachHang.email}" required>
                </div>
                
                <div class="button-group">
                    <c:choose>
                        <c:when test="${isEdit}">
                            <button type="submit" name="action" value="update" class="btn btn-update">Update</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" name="action" value="create" class="btn btn-create">Create</button>
                        </c:otherwise>
                    </c:choose>
                    
                    <c:if test="${isEdit}">
                        <button type="submit" name="action" value="delete" class="btn btn-delete" 
                                onclick="return confirm('Bạn có chắc chắn muốn xóa khách hàng này?')">Delete</button>
                    </c:if>
                    
                    <button type="submit" name="action" value="reset" class="btn btn-reset">Reset</button>
                </div>
            </form>
        </div>
        
        <!-- Bảng hiển thị danh sách -->
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Họ và tên</th>
                        <th>Giới tình</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="kh" items="${listKhachHang}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${kh.username}</td>
                            <td>${kh.password}</td>
                            <td>${kh.hoten}</td>
                            <td>${kh.gioitinh}</td>
                            <td>${kh.email}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/khachhang?action=edit&username=${kh.username}" 
                                   class="edit-link">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <c:if test="${empty listKhachHang}">
                        <tr>
                            <td colspan="7" style="text-align: center; font-style: italic;">
                                Chưa có dữ liệu khách hàng
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
