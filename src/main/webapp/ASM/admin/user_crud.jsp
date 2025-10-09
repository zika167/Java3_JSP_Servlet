<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>ABC News - Quản lý người dùng</title>
    <link rel="stylesheet" href="/ASM/assets/css/admin.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <!-- Header -->
    <header class="header">
        <div class="container">
            <h1>CÔNG CỤ QUẢN TRỊ TIN TỨC</h1>
        </div>
    </header>

    <!-- Navigation -->
    <nav class="nav-menu">
        <div class="container">
            <a href="/ASM/index.jsp" class="nav-link">Trang chủ</a>
            <a href="#" class="nav-link">Tin tức</a>
            <a href="#" class="nav-link">Loại tin</a>
            <a href="#" class="nav-link active">Người dùng</a>
            <a href="#" class="nav-link">Newsletter</a>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <!-- Form thêm tài khoản -->
            <div class="form-section">
                <h2>Thêm tài khoản mới</h2>
                <form action="../admin" method="post" class="user-form">
                    <input type="hidden" name="action" value="add">
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="username">Tên đăng nhập:</label>
                            <input type="text" id="username" name="username" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="fullName">Họ và tên:</label>
                            <input type="text" id="fullName" name="fullName" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="role">Vai trò:</label>
                            <select id="role" name="role" required>
                                <option value="">Chọn vai trò</option>
                                <option value="Admin">Admin</option>
                                <option value="Reporter">Reporter</option>
                                <option value="Reader">Reader</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Thêm tài khoản</button>
                        <button type="button" class="btn btn-secondary" onclick="clearForm()">Làm mới</button>
                    </div>
                </form>
            </div>

            <!-- Bảng danh sách người dùng -->
            <div class="table-section">
                <h2>Danh sách người dùng</h2>
                <div class="table-container">
                    <table class="users-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Username</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Trạng thái</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td class="username-cell">${user.fullname}</td>
                                    <td class="email-cell">${user.email}</td>
                                    <td>
                                        <span class="role role-${user.role}">
                                            ${user.getRoleString()}
                                        </span>
                                    </td>
                                    <td>
                                        <span class="status ${user.active ? 'status-active' : 'status-inactive'}">
                                            ${user.active ? 'Hoạt động' : 'Khóa'}
                                        </span>
                                    </td>
                                    <td class="actions">
                                        <c:choose>
                                            <c:when test="${user.active}">
                                                <button class="btn btn-sm btn-lock" onclick="lockUser(${user.id})">Khóa</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-sm btn-unlock" onclick="unlockUser(${user.id})">Mở khóa</button>
                                            </c:otherwise>
                                        </c:choose>
                                        <button class="btn btn-sm btn-delete" onclick="deleteUser(${user.id})">Xóa</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="/ASM/layout/footer.jsp"/>

    <script>
        function clearForm() {
            document.querySelector('.user-form').reset();
        }

        function lockUser(id) {
            if (confirm('Bạn có chắc chắn muốn khóa tài khoản này?')) {
                // Mock function - trong thực tế sẽ gửi request khóa
                alert('Đã khóa tài khoản ID: ' + id);
            }
        }

        function unlockUser(id) {
            if (confirm('Bạn có chắc chắn muốn mở khóa tài khoản này?')) {
                // Mock function - trong thực tế sẽ gửi request mở khóa
                alert('Đã mở khóa tài khoản ID: ' + id);
            }
        }

        function deleteUser(id) {
            if (confirm('Bạn có chắc chắn muốn xóa tài khoản này? Hành động này không thể hoàn tác!')) {
                // Mock function - trong thực tế sẽ gửi request xóa
                alert('Đã xóa tài khoản ID: ' + id);
            }
        }
    </script>
</body>
</html>