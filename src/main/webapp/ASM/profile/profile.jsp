<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>ABC News - Thông tin cá nhân</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/reporter.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .profile-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .profile-section {
            background: white;
            border-radius: 8px;
            padding: 25px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .profile-header {
            text-align: center;
            margin-bottom: 30px;
        }
        .user-avatar {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background: #007bff;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 32px;
            font-weight: bold;
            margin: 0 auto 15px;
        }
        .form-tabs {
            display: flex;
            border-bottom: 2px solid #f0f0f0;
            margin-bottom: 25px;
        }
        .tab-button {
            flex: 1;
            padding: 12px 20px;
            border: none;
            background: none;
            cursor: pointer;
            font-size: 16px;
            color: #666;
            border-bottom: 2px solid transparent;
            transition: all 0.3s;
        }
        .tab-button.active {
            color: #007bff;
            border-bottom-color: #007bff;
        }
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
        .form-row {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
        }
        .form-row .form-group {
            flex: 1;
        }
        .alert {
            padding: 12px 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .alert-success {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .alert-error {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <jsp:include page="/ASM/layout/header.jsp"/>

    <div class="main-container">
        <div class="profile-container">
            <!-- Profile Header -->
            <div class="profile-header">
                <div class="user-avatar">
                    ${user.fullname.substring(0,1).toUpperCase()}
                </div>
                <h2>${user.fullname}</h2>
                <p class="text-muted">
                    <c:choose>
                        <c:when test="${user.role == 'A'}">Quản trị viên</c:when>
                        <c:when test="${user.role == 'R'}">Phóng viên</c:when>
                        <c:otherwise>Người dùng</c:otherwise>
                    </c:choose>
                </p>
            </div>

            <!-- Success/Error Messages -->
            <c:if test="${not empty sessionScope.success}">
                <div class="alert alert-success">
                    ${sessionScope.success}
                </div>
                <c:remove var="success" scope="session"/>
            </c:if>
            <c:if test="${not empty sessionScope.error}">
                <div class="alert alert-error">
                    ${sessionScope.error}
                </div>
                <c:remove var="error" scope="session"/>
            </c:if>

            <!-- Profile Form -->
            <div class="profile-section">
                <div class="form-tabs">
                    <button class="tab-button active" onclick="switchTab('profile')">
                        Thông tin cá nhân
                    </button>
                    <button class="tab-button" onclick="switchTab('password')">
                        Đổi mật khẩu
                    </button>
                </div>

                <!-- Profile Update Tab -->
                <div id="profile-tab" class="tab-content active">
                    <form action="${pageContext.request.contextPath}/profile" method="post">
                        <input type="hidden" name="action" value="updateProfile">
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="id">Tên đăng nhập:</label>
                                <input type="text" id="id" value="${user.id}" disabled class="form-control">
                                <small class="text-muted">Không thể thay đổi tên đăng nhập</small>
                            </div>
                            <div class="form-group">
                                <label for="role">Vai trò:</label>
                                <input type="text" id="role" value="${user.role == 'A' ? 'Quản trị viên' : 'Phóng viên'}" disabled class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="fullname">Họ và tên: *</label>
                            <input type="text" id="fullname" name="fullname" value="${user.fullname}" required class="form-control">
                        </div>

                        <div class="form-group">
                            <label for="email">Email: *</label>
                            <input type="email" id="email" name="email" value="${user.email}" required class="form-control">
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="birthday">Ngày sinh:</label>
                                <input type="date" id="birthday" name="birthday" 
                                       value="<fmt:formatDate value='${user.birthday}' pattern='yyyy-MM-dd'/>" 
                                       class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="gender">Giới tính:</label>
                                <select id="gender" name="gender" class="form-control">
                                    <option value="">Chọn giới tính</option>
                                    <option value="M" ${user.gender == 'M' ? 'selected' : ''}>Nam</option>
                                    <option value="F" ${user.gender == 'F' ? 'selected' : ''}>Nữ</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="mobile">Số điện thoại:</label>
                            <input type="tel" id="mobile" name="mobile" value="${user.mobile}" 
                                   class="form-control" pattern="[0-9]{10,11}" 
                                   placeholder="Nhập số điện thoại (10-11 chữ số)">
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Cập nhật thông tin</button>
                            <a href="${pageContext.request.contextPath}/${user.role == 'A' ? 'admin' : 'reporter'}" class="btn btn-secondary">Quay lại</a>
                        </div>
                    </form>
                </div>

                <!-- Password Change Tab -->
                <div id="password-tab" class="tab-content">
                    <form action="${pageContext.request.contextPath}/profile" method="post">
                        <input type="hidden" name="action" value="changePassword">
                        
                        <div class="form-group">
                            <label for="currentPassword">Mật khẩu hiện tại: *</label>
                            <input type="password" id="currentPassword" name="currentPassword" required class="form-control">
                        </div>

                        <div class="form-group">
                            <label for="newPassword">Mật khẩu mới: *</label>
                            <input type="password" id="newPassword" name="newPassword" required class="form-control" minlength="6">
                            <small class="text-muted">Mật khẩu phải có ít nhất 6 ký tự</small>
                        </div>

                        <div class="form-group">
                            <label for="confirmPassword">Xác nhận mật khẩu mới: *</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" required class="form-control" minlength="6">
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
                            <button type="button" class="btn btn-secondary" onclick="clearPasswordForm()">Xóa trắng</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="/ASM/layout/footer.jsp"/>

    <script>
        function switchTab(tabName) {
            // Hide all tabs
            document.querySelectorAll('.tab-content').forEach(tab => {
                tab.classList.remove('active');
            });
            document.querySelectorAll('.tab-button').forEach(btn => {
                btn.classList.remove('active');
            });

            // Show selected tab
            document.getElementById(tabName + '-tab').classList.add('active');
            event.target.classList.add('active');
        }

        function clearPasswordForm() {
            document.getElementById('currentPassword').value = '';
            document.getElementById('newPassword').value = '';
            document.getElementById('confirmPassword').value = '';
        }

        // Password confirmation validation
        document.getElementById('confirmPassword').addEventListener('input', function() {
            const newPassword = document.getElementById('newPassword').value;
            const confirmPassword = this.value;
            
            if (newPassword !== confirmPassword) {
                this.setCustomValidity('Mật khẩu xác nhận không khớp');
            } else {
                this.setCustomValidity('');
            }
        });
    </script>
</body>
</html>
