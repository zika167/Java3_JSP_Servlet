<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>ABC News - Danh sách tin tức</title>
    <link rel="stylesheet" href="/ASM/assets/css/reader.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <!-- Header -->
    <header class="header">
        <div class="container">
            <h1>ABC News</h1>
            <nav class="nav">
                <a href="/ASM/index.jsp" class="nav-link">Trang chủ</a>
                <a href="#" class="nav-link">Văn hóa</a>
                <a href="#" class="nav-link">Pháp luật</a>
                <a href="#" class="nav-link">Thể thao</a>
                <a href="#" class="nav-link">Công nghệ</a>
                <a href="#" class="nav-link">Kinh tế</a>
                <!-- Auth links for news_list context -->
                <a href="${pageContext.request.contextPath}/auth/login" class="nav-link">Login</a>
                <a href="${pageContext.request.contextPath}/auth/signup" class="nav-link">Sign up</a>
                <!-- Language links -->
                <a href="?lang=vi" class="nav-link">VI</a>
                <a href="?lang=en" class="nav-link">EN</a>
            </nav>
        </div>
    </header>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <h2>Danh sách tin tức</h2>
            
            <!-- Danh sách bài viết -->
            <div class="articles-list">
                <c:forEach var="news" items="${newsList}">
                    <div class="article-item">
                        <div class="article-image">
                            <img src="${pageContext.request.contextPath}/ASM/assets/images/${news.image}" alt="${news.title}">
                        </div>
                        <div class="article-content">
                            <h3 class="article-title">${news.title}</h3>
                            <p class="article-summary">${news.content}</p>
                            <div class="article-meta">
                                <span class="article-author">Tác giả: ${news.author}</span>
                                <span class="article-date">
                                    Ngày đăng: <fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy"/>
                                </span>
                            </div>
                            <a href="${pageContext.request.contextPath}/news/detail/${news.id}" class="btn-detail">Xem chi tiết</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- 5 bản tin được xem nhiều -->
                <div class="sidebar-box hot-news">
                    <h3>5 bản tin được xem nhiều</h3>
                    <ul>
                        <c:forEach var="hot" items="${applicationScope.hotNews}">
                            <li>
                                <a href="${pageContext.request.contextPath}/news/detail/${hot.id}">
                                    ${hot.title}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <!-- 5 bản tin mới nhất -->
                <div class="sidebar-box latest-news">
                    <h3>5 bản tin mới nhất</h3>
                    <ul>
                        <c:forEach var="latest" items="${applicationScope.latestNews}">
                            <li>
                                <a href="${pageContext.request.contextPath}/news/detail/${latest.id}">
                                    ${latest.title}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>            <!-- 5 bản tin đã xem -->
            <div class="sidebar-box viewed-news">
                <h3>5 bản tin bạn đã xem</h3>
                <ul>
                    <li><a href="#">Tin tức đã xem 1</a></li>
                    <li><a href="#">Tin tức đã xem 2</a></li>
                    <li><a href="#">Tin tức đã xem 3</a></li>
                    <li><a href="#">Tin tức đã xem 4</a></li>
                    <li><a href="#">Tin tức đã xem 5</a></li>
                </ul>
            </div>

            <!-- Newsletter -->
            <div class="newsletter-box">
                <h3>Đăng ký nhận bản tin</h3>
                <form action="../newsletter" method="post">
                    <input type="email" name="email" placeholder="Nhập email của bạn" required>
                    <button type="submit">Đăng ký</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="/ASM/layout/footer.jsp"/>
</body>
</html>