<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Include required CSS and JS -->
<script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/dayjs@1/locale/vi.js"></script>
<link rel="stylesheet" href="/ASM/assets/css/header.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<!-- Header Component -->
<header class="header">
    <!-- Unified Top Bar -->
    <div class="unified-top-bar">
        <div class="container">
            <div class="top-content">
                <!-- Logo and Site Title -->
                <div class="logo-section">
                    <img src="/ASM/assets/images/logo.png" alt="Logo" class="logo-image">
                    <div class="site-title">
                        <c:choose>
                            <c:when test="${sessionScope.lang == 'en'}">
                                <h1>ABC News</h1>
                                <span class="site-subtitle">News Management System</span>
                            </c:when>
                            <c:otherwise>
                                <h1>ABC News</h1>
                                <span class="site-subtitle">Hệ thống quản lý tin tức</span>
                            </c:otherwise>
                        </c:choose>
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
                            <!-- Người dùng đã đăng nhập -->
                            <span class="user-greeting">
                                <i class="fas fa-user"></i>
                                <c:choose>
                                    <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                                        <span class="role-badge admin">Admin</span>
                                    </c:when>
                                    <c:when test="${sessionScope.user.role eq 'REPORTER'}">
                                        <span class="role-badge reporter">Reporter</span>
                                    </c:when>
                                </c:choose>
                                ${sessionScope.user.fullname}
                            </span>
                            
                            <!-- Menu quản lý dựa trên vai trò -->
                            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                                <a href="${pageContext.request.contextPath}/admin" class="utility-link">
                                    <i class="fas fa-cogs"></i>
                                    Quản trị
                                </a>
                            </c:if>
                            <c:if test="${sessionScope.user.role eq 'REPORTER'}">
                                <a href="${pageContext.request.contextPath}/reporter" class="utility-link">
                                    <i class="fas fa-newspaper"></i>
                                    Quản lý tin
                                </a>
                            </c:if>
                            
                            <a href="${pageContext.request.contextPath}/auth/logout" class="utility-link">
                                <i class="fas fa-sign-out-alt"></i>
                                Đăng xuất
                            </a>
                        </c:when>
                        <c:otherwise>
                            <!-- Menu cho độc giả chưa đăng nhập -->
                            <div class="reader-menu">
                                <a href="${pageContext.request.contextPath}/auth/signup" class="utility-link signup-link">
                                    <i class="fas fa-user-plus"></i>
                                    Đăng ký làm phóng viên
                                </a>
                            </div>
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

    <!-- Main Navigation -->
    <nav class="main-nav">
        <div class="container">
            <div class="nav-content">
                <a href="/ASM/index.jsp" class="nav-link">
                    <i class="fas fa-home"></i> Trang chủ
                </a>
                <a href="#" class="nav-link">
                    <i class="fas fa-robot"></i> Công nghệ
                </a>
                <a href="#" class="nav-link">
                    <i class="fas fa-chart-line"></i> Kinh tế
                </a>
                <a href="#" class="nav-link">
                    <i class="fas fa-graduation-cap"></i> Giáo dục
                </a>
                <a href="#" class="nav-link">
                    <i class="fas fa-trophy"></i> Thể thao
                </a>
                <a href="#" class="nav-link">
                    <i class="fas fa-leaf"></i> Môi trường
                </a>
                <a href="#" class="nav-link">
                    <i class="fas fa-users"></i> Xã hội
                </a>
                <a href="#" class="nav-link">
                    <i class="fas fa-palette"></i> Văn hóa
                </a>
                <a href="#" class="nav-link">
                    <i class="fas fa-gavel"></i> Pháp luật
                </a>
            </div>
        </div>
    </nav>

    <!-- JavaScript for Date Display -->
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            function updateDateTime() {
                // Kích hoạt ngôn ngữ tiếng Việt
                dayjs.locale('vi');

                // Lấy ngày hiện tại và định dạng theo mẫu
                const formattedDate = dayjs().format('dddd, DD/MM/YYYY');

                const dateElement = document.getElementById('currentDate');
                if (dateElement) {
                    dateElement.textContent = formattedDate;
                }
            }

            updateDateTime();
            setInterval(updateDateTime, 60000);
        });

        // Xử lý tìm kiếm
        const searchToggle = document.querySelector('.search-toggle');
        const searchInput = document.querySelector('.search-input');
        const searchContainer = document.querySelector('.search-container');

        searchToggle.addEventListener('click', () => {
            searchContainer.classList.toggle('active');
            if (searchContainer.classList.contains('active')) {
                searchInput.focus();
            }
        });

        // Đóng tìm kiếm khi click bên ngoài
        document.addEventListener('click', (e) => {
            if (!searchContainer.contains(e.target)) {
                searchContainer.classList.remove('active');
            }
        });
    </script>
</header>
