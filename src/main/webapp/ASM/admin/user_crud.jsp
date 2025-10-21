<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>ABC News - Quản lý người dùng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/admin-enhanced.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="context-path" content="${pageContext.request.contextPath}">
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
            <a href="${pageContext.request.contextPath}/reader" class="nav-link">Trang chủ</a>
            <a href="${pageContext.request.contextPath}/admin/news" class="nav-link">Tin tức</a>
            <a href="${pageContext.request.contextPath}/admin/category" class="nav-link">Loại tin</a>
            <a href="${pageContext.request.contextPath}/admin" class="nav-link active">Người dùng</a>
            <a href="${pageContext.request.contextPath}/admin/newsletter" class="nav-link">Newsletter</a>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <!-- Success/Error Messages -->
            <c:if test="${not empty sessionScope.success}">
                <div class="alert alert-success">${sessionScope.success}</div>
                <c:remove var="success" scope="session"/>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-error">${error}</div>
            </c:if>
            
            <!-- Form thêm/sửa tài khoản -->
            <div class="form-section">
                <h2>Quản lý tài khoản</h2>
                <form id="userForm" action="${pageContext.request.contextPath}/admin" method="post" class="user-form">
                    <input type="hidden" id="action" name="action" value="CREATE">
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="id">Tên đăng nhập: *</label>
                            <input type="text" id="id" name="id" required value="${form.id}">
                        </div>
                        
                        <div class="form-group">
                            <label for="password">Mật khẩu: *</label>
                            <input type="password" id="password" name="password" required placeholder="Nhập mật khẩu">
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="fullname">Họ và tên: *</label>
                            <input type="text" id="fullname" name="fullname" required value="${form.fullname}">
                        </div>
                        
                        <div class="form-group">
                            <label for="email">Email: *</label>
                            <input type="email" id="email" name="email" required value="${form.email}">
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="role">Vai trò: *</label>
                            <select id="role" name="role" required>
                                <option value="">Chọn vai trò</option>
                                <option value="Admin">Admin</option>
                                <option value="Reporter">Reporter</option>
                                <option value="Reader">Reader</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" id="createBtn" class="btn btn-primary">Tạo mới</button>
                        <button type="button" id="updateBtn" class="btn btn-success disabled" disabled>Cập nhật</button>
                        <button type="button" id="resetBtn" class="btn btn-secondary">Làm mới</button>
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
                                <tr data-user-id="${user.id}" 
                                    data-fullname="${user.fullname}" 
                                    data-email="${user.email}" 
                                    data-role="${user.getRoleString()}">
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
                                        <button class="btn btn-sm btn-edit" data-id="${user.id}">
                                            <i class="fas fa-edit"></i> Sửa
                                        </button>
                                        <button class="btn btn-sm btn-toggle-status" 
                                                data-id="${user.id}" 
                                                data-active="${user.active}">
                                            <i class="fas fa-${user.active ? 'lock' : 'unlock'}"></i>
                                            ${user.active ? 'Khóa' : 'Mở'}
                                        </button>
                                        <button class="btn btn-sm btn-delete" data-id="${user.id}">
                                            <i class="fas fa-trash"></i> Xóa
                                        </button>
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

    <!-- Admin CRUD JavaScript -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="${pageContext.request.contextPath}/ASM/assets/js/admin-crud.js"></script>
</body>
</html>