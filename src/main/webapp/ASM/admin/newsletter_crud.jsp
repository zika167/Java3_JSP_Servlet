<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>ABC News - Quản lý Newsletter</title>
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
            <a href="${pageContext.request.contextPath}/admin" class="nav-link">Người dùng</a>
            <a href="${pageContext.request.contextPath}/admin/newsletter" class="nav-link active">Newsletter</a>
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
            
            <!-- Form thêm/sửa newsletter -->
            <div class="form-section">
                <h2>Quản lý Newsletter</h2>
                <form id="newsletterForm" action="${pageContext.request.contextPath}/admin/newsletter" method="post" class="user-form">
                    <input type="hidden" id="action" name="action" value="CREATE">
                    
                    <div class="form-group">
                        <label for="email">Email: *</label>
                        <input type="email" id="email" name="email" required 
                               value="${editNewsletter != null ? editNewsletter.email : ''}"
                               ${editNewsletter != null ? 'readonly' : ''}>
                    </div>
                    
                    <div class="form-group">
                        <label for="enabled">Trạng thái:</label>
                        <select id="enabled" name="enabled">
                            <option value="1" ${editNewsletter != null && editNewsletter.enabled ? 'selected' : ''}>Kích hoạt</option>
                            <option value="0" ${editNewsletter != null && !editNewsletter.enabled ? 'selected' : ''}>Vô hiệu hóa</option>
                        </select>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">
                            ${editNewsletter != null ? 'Cập nhật' : 'Thêm mới'}
                        </button>
                        <c:if test="${editNewsletter != null}">
                            <button type="button" class="btn btn-secondary" onclick="cancelEdit()">Hủy</button>
                        </c:if>
                    </div>
                </form>
            </div>
            
            <!-- Danh sách newsletter -->
            <div class="table-section">
                <h2>Danh sách người đăng ký nhận tin</h2>
                <div class="table-container">
                    <table class="users-table">
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="newsletter" items="${newsletters}">
                            <tr>
                                <td>${newsletter.email}</td>
                                <td>
                                    <span class="status-badge ${newsletter.enabled ? 'status-active' : 'status-inactive'}">
                                        ${newsletter.enabled ? 'Kích hoạt' : 'Vô hiệu hóa'}
                                    </span>
                                </td>
                                <td class="actions">
                                    <a href="${pageContext.request.contextPath}/admin/newsletter?action=EDIT&email=${newsletter.email}" 
                                       class="btn btn-sm btn-edit">Sửa</a>
                                    <a href="javascript:void(0)" 
                                       onclick="confirmDelete('${newsletter.email}')" 
                                       class="btn btn-sm btn-delete">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty newsletters}">
                            <tr>
                                <td colspan="3" style="text-align: center;">Chưa có người đăng ký nào</td>
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
        <c:if test="${editNewsletter != null}">
            document.getElementById('action').value = 'UPDATE';
        </c:if>
        
        function cancelEdit() {
            window.location.href = '${pageContext.request.contextPath}/admin/newsletter';
        }
        
        function confirmDelete(email) {
            if (confirm('Bạn có chắc chắn muốn xóa email "' + email + '" khỏi danh sách nhận tin?')) {
                window.location.href = '${pageContext.request.contextPath}/admin/newsletter?action=DELETE&email=' + encodeURIComponent(email);
            }
        }
    </script>
</body>
</html>
