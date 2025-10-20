<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>ABC News - Chi tiết tin tức</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/reader-new.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <!-- Header -->
    <jsp:include page="/ASM/layout/header.jsp"/>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <!-- Breadcrumb -->
            <nav class="breadcrumb">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a> >
                <a href="${pageContext.request.contextPath}/reader">Danh sách tin tức</a> >
                <span>${news.title}</span>
            </nav>

            <!-- Article Detail -->
            <article class="article-detail">
                <h1 class="article-title">${news.title}</h1>
                
                <div class="article-meta">
                    <span class="article-author">Tác giả: ${news.author}</span>
                    <span class="article-date">Ngày đăng: <fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy"/></span>
                    <span class="article-views">Lượt xem: ${news.viewCount}</span>
                </div>

                <img src="${pageContext.request.contextPath}/ASM/assets/images/${news.image}" 
                     alt="${news.title}" 
                     class="news-detail-image" 
                     onerror="this.src='${pageContext.request.contextPath}/ASM/assets/images/placeholder.jpg'">

                <div class="article-content">
                    ${news.content}
                </div>
            </article>

            <!-- Related News -->
            <section class="related-news">
                <h2>Tin cùng loại</h2>
                <div class="related-articles">
                    <c:forEach var="related" items="${relatedNews}">
                        <div class="related-item">
                            <a href="${pageContext.request.contextPath}/news/detail/${related.id}" class="related-link">
                                • ${related.title}
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </div>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- 5 bản tin được xem nhiều -->
            <div class="sidebar-box hot-news">
                <h3>5 bản tin được xem nhiều</h3>
                <div class="sidebar-news-list">
                    <c:forEach var="hot" items="${applicationScope.hotNews}">
                        <div class="sidebar-news-item">
                            <a href="${pageContext.request.contextPath}/news/detail/${hot.id}" class="sidebar-news-link">
                                <div class="sidebar-news-thumb">
                                    <img src="${pageContext.request.contextPath}/ASM/assets/images/${hot.image}"
                                         alt="${hot.title}"
                                         onerror="this.src='https://placehold.co/60x60'">
                                </div>
                                <div class="sidebar-news-content">
                                    <h4 class="sidebar-news-title">${hot.title}</h4>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- 5 bản tin mới nhất -->
            <div class="sidebar-box latest-news">
                <h3>5 bản tin mới nhất</h3>
                <div class="sidebar-news-list">
                    <c:forEach var="latest" items="${applicationScope.latestNews}">
                        <div class="sidebar-news-item">
                            <a href="${pageContext.request.contextPath}/news/detail/${latest.id}" class="sidebar-news-link">
                                <div class="sidebar-news-thumb">
                                    <img src="${pageContext.request.contextPath}/ASM/assets/images/${latest.image}"
                                         alt="${latest.title}"
                                         onerror="this.src='https://placehold.co/60x60'">
                                </div>
                                <div class="sidebar-news-content">
                                    <h4 class="sidebar-news-title">${latest.title}</h4>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Newsletter -->
            <div class="newsletter-box">
                <h3>Đăng ký nhận bản tin</h3>
                <form action="${pageContext.request.contextPath}/newsletter" method="post">
                    <input type="email" name="email" placeholder="Nhập email của bạn" required>
                    <button type="submit">Đăng ký</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Include footer -->
    <jsp:include page="/ASM/layout/footer.jsp"/>
</html>