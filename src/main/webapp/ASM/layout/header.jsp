<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.lang}">
    <fmt:setLocale value="${sessionScope.lang}" scope="request" />
</c:if>
<fmt:setBundle basename="i18n.header" scope="request" />

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
                        <h1><fmt:message key="header.sitename"/></h1>
                        <span class="site-subtitle"><fmt:message key="header.subtitle"/></span>
                    </div>
                </div>

                <!-- Date Display -->
                <div id="currentDate" class="date-display"></div>

                <!-- Utility Links -->
                <div class="utility-section">
                    <!-- Search -->
                    <div class="search-container">
                        <input type="text" class="search-input" placeholder="<fmt:message key='header.search.placeholder'/>">
                        <button class="search-toggle" aria-label="Search">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>

                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <!-- User is logged in -->
                            <div class="user-greeting">
                                <c:if test="${sessionScope.user.role == 'A'}">
                                    <span class="role-badge admin">ADMIN</span>
                                </c:if>
                                <c:if test="${sessionScope.user.role == 'R'}">
                                    <span class="role-badge reporter">REPORTER</span>
                                </c:if>
                                <i class="fas fa-user"></i>
                                <span><fmt:message key="header.welcome"/>, ${sessionScope.user.fullname}</span>
                            </div>
                            <a href="${pageContext.request.contextPath}/auth/logout" class="utility-link">
                                <i class="fas fa-sign-out-alt"></i> <fmt:message key="header.logout"/>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <!-- User is not logged in -->
                            <a href="#" class="utility-link" data-auth-action="login">
                                <i class="fas fa-sign-in-alt"></i> <fmt:message key="header.login"/>
                            </a>
                            <a href="#" class="utility-link signup-link" data-auth-action="signup">
                                <i class="fas fa-user-plus"></i> <fmt:message key="header.signup"/>
                            </a>
                        </c:otherwise>
                    </c:choose>

                    <!-- Language Selector -->
                    <div class="lang-selector">
                        <a href="${pageContext.request.contextPath}/lang?locale=vi" class="lang-link ${empty sessionScope.lang or sessionScope.lang == 'vi' ? 'active' : ''}">VI</a>
                        <span class="lang-divider">|</span>
                        <a href="${pageContext.request.contextPath}/lang?locale=en" class="lang-link ${sessionScope.lang == 'en' ? 'active' : ''}">EN</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <nav class="main-nav">
        <div class="container">
            <div class="nav-content">
                <a href="${pageContext.request.contextPath}/reader" class="nav-link">
                    <fmt:message key="header.nav.home"/>
                </a>

                <c:forEach var="category" items="${categories}">
                    <a href="${pageContext.request.contextPath}/category?id=${category.id}" class="nav-link">
                        ${category.name}
                    </a>
                </c:forEach>

                <!-- Role-based navigation link -->
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'A'}">
                        <a href="${pageContext.request.contextPath}/admin" class="nav-link admin-link">
                            <fmt:message key="header.nav.admin"/>
                        </a>
                    </c:when>
                    <c:when test="${sessionScope.user.role == 'R'}">
                        <a href="${pageContext.request.contextPath}/reporter" class="nav-link reporter-link">
                            <fmt:message key="header.nav.reporter"/>
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
        <h2 class="modal-title"><fmt:message key="modal.title"/></h2>
        <button class="modal-close" aria-label="Close">&times;</button>
    </div>
    
    <div class="modal-tabs">
        <button class="modal-tab active" data-tab="login"><fmt:message key="modal.login.tab"/></button>
        <button class="modal-tab" data-tab="signup"><fmt:message key="modal.signup.tab"/></button>
    </div>
    
    <div class="modal-body">
        <!-- Login Form -->
        <form id="loginForm" class="modal-form active">
            <div class="error-message"></div>
            <div class="success-message"></div>
            
            <div class="form-group">
                <label for="loginId"><fmt:message key="modal.login.username"/></label>
                <input type="text" id="loginId" name="id" required autocomplete="username">
            </div>
            
            <div class="form-group">
                <label for="loginPassword"><fmt:message key="modal.login.password"/></label>
                <input type="password" id="loginPassword" name="password" required autocomplete="current-password">
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-modal btn-primary">
                    <fmt:message key="modal.login.button"/>
                </button>
            </div>
            
            <div class="form-footer">
                <fmt:message key="modal.login.footer"/> <a href="#" data-auth-action="signup"><fmt:message key="modal.login.signup"/></a>
            </div>
        </form>
        
        <!-- Signup Form -->
        <form id="signupForm" class="modal-form">
            <div class="error-message"></div>
            <div class="success-message"></div>
            
            <div class="form-group">
                <label for="signupId"><fmt:message key="modal.signup.username"/></label>
                <input type="text" id="signupId" name="id" required autocomplete="username">
            </div>
            
            <div class="form-group">
                <label for="signupFullname"><fmt:message key="modal.signup.fullname"/></label>
                <input type="text" id="signupFullname" name="fullname" required autocomplete="name">
            </div>
            
            <div class="form-group">
                <label for="signupEmail"><fmt:message key="modal.signup.email"/></label>
                <input type="email" id="signupEmail" name="email" required autocomplete="email">
            </div>
            
            <div class="form-group">
                <label for="signupPassword"><fmt:message key="modal.signup.password"/></label>
                <input type="password" id="signupPassword" name="password" required autocomplete="new-password">
            </div>
            
            <div class="form-group">
                <label for="signupConfirmPassword"><fmt:message key="modal.signup.confirm"/></label>
                <input type="password" id="signupConfirmPassword" name="confirmPassword" required autocomplete="new-password">
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-modal btn-primary">
                    <fmt:message key="modal.signup.button"/>
                </button>
            </div>
            
            <div class="form-footer">
                <fmt:message key="modal.signup.footer"/> <a href="#" data-auth-action="login"><fmt:message key="modal.signup.login"/></a>
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
