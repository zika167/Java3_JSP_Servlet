<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Include required CSS and JS -->
<script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/dayjs@1/locale/vi.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/modal.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<meta name="context-path" content="${pageContext.request.contextPath}">

<!-- Header Component -->
<header class="header">
    <!-- Unified Top Bar -->
    <div class="unified-top-bar">
        <div class="container">
            <div class="top-content">
                <!-- Logo and Site Title -->
                <div class="logo-section">
                    <img src="${pageContext.request.contextPath}/ASM/assets/images/logo.png" alt="Logo" class="logo-image">
                    <div class="site-title">
                        <h1>ABC News</h1>
                        <span class="site-subtitle">Hệ thống quản lý tin tức</span>
                    </div>
                </div>

                <!-- Date Display -->
                <div id="currentDate" class="date-display"></div>

                <!-- Utility Links -->
                <div class="utility-section">
                    <!-- Search -->
                    <div class="search-container">
                        <input type="text" class="search-input" placeholder="Tìm kiếm tin tức...">
                        <button class="search-toggle">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>

                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <!-- User is logged in -->
                            <span class="user-greeting">
                                <i class="fas fa-user"></i>
                                ${sessionScope.user.fullname}
                            </span>
                            <a href="${pageContext.request.contextPath}/auth/logout" class="utility-link">
                                <i class="fas fa-sign-out-alt"></i>
                                Đăng xuất
                            </a>
                        </c:when>
                        <c:otherwise>
                            <!-- User is not logged in -->
                            <a href="#" class="utility-link" data-auth-action="login">
                                <i class="fas fa-sign-in-alt"></i>
                                Đăng nhập
                            </a>
                            <a href="#" class="utility-link signup-link" data-auth-action="signup">
                                <i class="fas fa-user-plus"></i>
                                Đăng ký
                            </a>
                        </c:otherwise>
                    </c:choose>

                    <!-- Language Selector -->
                    <div class="lang-selector">
                        <a href="?lang=vi" class="lang-link ${sessionScope.lang != 'en' ? 'active' : ''}">VI</a>
                        <span class="lang-divider">|</span>
                        <a href="?lang=en" class="lang-link ${sessionScope.lang == 'en' ? 'active' : ''}">EN</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <nav class="main-nav">
        <div class="container">
            <div class="nav-content">
                <a href="${pageContext.request.contextPath}/reader" class="nav-link">
                    <i class="fas fa-home"></i> Trang chủ
                </a>

                <c:forEach var="category" items="${categories}">
                    <a href="${pageContext.request.contextPath}/category?id=${category.id}" class="nav-link">
                        <i class="fas fa-tag"></i> ${category.name}
                    </a>
                </c:forEach>

                <!-- Role-based navigation link -->
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'A'}">
                        <a href="${pageContext.request.contextPath}/admin" class="nav-link admin-link">
                            <i class="fas fa-cogs"></i> Trang quản trị
                        </a>
                    </c:when>
                    <c:when test="${sessionScope.user.role == 'R'}">
                        <a href="${pageContext.request.contextPath}/reporter" class="nav-link reporter-link">
                            <i class="fas fa-newspaper"></i> Trang phóng viên
                        </a>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </nav>

    <!-- JavaScript for Date Display -->
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            function updateDateTime() {
                dayjs.locale('vi');
                const formattedDate = dayjs().format('dddd, DD/MM/YYYY');
                const dateElement = document.getElementById('currentDate');
                if (dateElement) {
                    dateElement.textContent = formattedDate;
                }
            }
            updateDateTime();
            setInterval(updateDateTime, 60000);

            const searchToggle = document.querySelector('.search-toggle');
            const searchInput = document.querySelector('.search-input');
            const searchContainer = document.querySelector('.search-container');

            searchToggle.addEventListener('click', () => {
                searchContainer.classList.toggle('active');
                if (searchContainer.classList.contains('active')) {
                    searchInput.focus();
                }
            });

            document.addEventListener('click', (e) => {
                if (!searchContainer.contains(e.target)) {
                    searchContainer.classList.remove('active');
                }
            });
        });
    </script>
</header>

<!-- Authentication Modal -->
<div id="authModalOverlay" class="modal-overlay"></div>
<div id="authModalContainer" class="modal-container">
    <div class="modal-header">
        <h2 class="modal-title">Xác thực</h2>
        <button class="modal-close" aria-label="Đóng">&times;</button>
    </div>
    
    <div class="modal-tabs">
        <button class="modal-tab active" data-tab="login">Đăng nhập</button>
        <button class="modal-tab" data-tab="signup">Đăng ký</button>
    </div>
    
    <div class="modal-body">
        <!-- Login Form -->
        <form id="loginForm" class="modal-form active">
            <div class="error-message"></div>
            <div class="success-message"></div>
            
            <div class="form-group">
                <label for="loginId">Tên đăng nhập hoặc Email</label>
                <input type="text" id="loginId" name="id" required autocomplete="username">
            </div>
            
            <div class="form-group">
                <label for="loginPassword">Mật khẩu</label>
                <input type="password" id="loginPassword" name="password" required autocomplete="current-password">
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-modal btn-primary" data-original-text="Đăng nhập">
                    Đăng nhập
                </button>
            </div>
            
            <div class="form-footer">
                Chưa có tài khoản? <a href="#" data-auth-action="signup">Đăng ký ngay</a>
            </div>
        </form>
        
        <!-- Signup Form -->
        <form id="signupForm" class="modal-form">
            <div class="error-message"></div>
            <div class="success-message"></div>
            
            <div class="form-group">
                <label for="signupId">Tên đăng nhập *</label>
                <input type="text" id="signupId" name="id" required autocomplete="username">
            </div>
            
            <div class="form-group">
                <label for="signupFullname">Họ và tên *</label>
                <input type="text" id="signupFullname" name="fullname" required autocomplete="name">
            </div>
            
            <div class="form-group">
                <label for="signupEmail">Email *</label>
                <input type="email" id="signupEmail" name="email" required autocomplete="email">
            </div>
            
            <div class="form-group">
                <label for="signupPassword">Mật khẩu *</label>
                <input type="password" id="signupPassword" name="password" required autocomplete="new-password">
            </div>
            
            <div class="form-group">
                <label for="signupConfirmPassword">Xác nhận mật khẩu *</label>
                <input type="password" id="signupConfirmPassword" name="confirmPassword" required autocomplete="new-password">
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-modal btn-primary" data-original-text="Đăng ký">
                    Đăng ký
                </button>
            </div>
            
            <div class="form-footer">
                Đã có tài khoản? <a href="#" data-auth-action="login">Đăng nhập ngay</a>
            </div>
        </form>
    </div>
</div>

<!-- Auth Modal Script -->
<script src="${pageContext.request.contextPath}/ASM/assets/js/auth-modal.js"></script>

<!-- Favicon and Meta Tags -->
<link rel="apple-touch-icon" sizes="57x57" href="${pageContext.request.contextPath}/ASM/assets/favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="${pageContext.request.contextPath}/ASM/assets/favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/ASM/assets/favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/ASM/assets/favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/ASM/assets/favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/ASM/assets/favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="${pageContext.request.contextPath}/ASM/assets/favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/ASM/assets/favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/ASM/assets/favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192" href="${pageContext.request.contextPath}/ASM/assets/favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/ASM/assets/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="${pageContext.request.contextPath}/ASM/assets/favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/ASM/assets/favicon/favicon-16x16.png">
<link rel="manifest" href="${pageContext.request.contextPath}/ASM/assets/favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/ASM/assets/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
