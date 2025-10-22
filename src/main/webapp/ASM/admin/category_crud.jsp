<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>ABC News - Quản lý loại tin</title>
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
            <a href="${pageContext.request.contextPath}/admin/category" class="nav-link active">Loại tin</a>
            <a href="${pageContext.request.contextPath}/admin" class="nav-link">Người dùng</a>
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
            
            <!-- Form thêm/sửa loại tin -->
            <div class="form-section">
                <h2>Quản lý loại tin</h2>
                <form id="categoryForm" action="${pageContext.request.contextPath}/admin/category" method="post" class="user-form">
                    <input type="hidden" id="action" name="action" value="CREATE">
                    
                    <div class="form-group">
                        <label for="id">ID loại tin: *</label>
                        <input type="text" id="id" name="id" required 
                               value="${editCategory != null ? editCategory.id : ''}"
                               ${editCategory != null ? 'readonly' : ''}>
                    </div>
                    
                    <div class="form-group">
                        <label for="name">Tên loại tin: *</label>
                        <input type="text" id="name" name="name" required 
                               value="${editCategory != null ? editCategory.name : ''}">
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            ${editCategory != null ? 'Cập nhật' : 'Thêm mới'}
                        </button>
                        <c:if test="${editCategory != null}">
                            <button type="button" class="btn btn-secondary" onclick="cancelEdit()">Hủy</button>
                        </c:if>
                    </div>
                </form>
            </div>
            
            <!-- Danh sách loại tin -->
            <div class="table-section">
                <h2>Danh sách loại tin</h2>
                <div class="table-container">
                    <table class="users-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên loại tin</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="category" items="${categories}">
                            <tr>
                                <td>${category.id}</td>
                                <td>${category.name}</td>
                                <td class="actions">
                                    <a href="${pageContext.request.contextPath}/admin/category?action=EDIT&id=${category.id}" 
                                        class="btn btn-sm btn-edit">
                                        <i class="fas fa-edit"></i>
                                        Sửa
                                    </a>
                                    <a href="javascript:void(0)" 
                                        onclick="confirmDelete('${category.id}', '${category.name}')"
                                        class="btn btn-sm btn-delete">
                                        <i class="fas fa-trash"></i>
                                        Xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty categories}">
                            <tr>
                                <td colspan="3" style="text-align: center;">Chưa có loại tin nào</td>
                            </tr>
                        </c:if>
                    </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Set action to UPDATE if editing
        <c:if test="${editCategory != null}">
            document.getElementById('action').value = 'UPDATE';
        </c:if>
        
        function cancelEdit() {
            window.location.href = '${pageContext.request.contextPath}/admin/category';
        }
        
        function confirmDelete(id, name) {
            if (confirm('Bạn có chắc chắn muốn xóa loại tin "' + name + '"?\n\nLưu ý: Không thể xóa loại tin đang có tin tức.')) {
                window.location.href = '${pageContext.request.contextPath}/admin/category?action=DELETE&id=' + id;
            }
        }
    </script>
</body>
</html>
