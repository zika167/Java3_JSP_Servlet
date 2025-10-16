<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>ABC News - Chi tiết tin tức</title>
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
            </nav>
        </div>
    </header>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <!-- Breadcrumb -->
            <nav class="breadcrumb">
                <a href="/ASM/index.jsp">Trang chủ</a> >
                <a href="${pageContext.request.contextPath}/reader">Danh sách tin tức</a> >
                <span>Chi tiết tin tức</span>
            </nav>

            <!-- Article Detail -->
            <article class="article-detail">
                <h1 class="article-title">${news.title}</h1>
                
                <div class="article-meta">
                    <span class="article-author">Tác giả: ${news.author}</span>
                    <span class="article-date">Ngày đăng: <fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy"/></span>
                    <span class="article-views">Lượt xem: ${news.viewCount}</span>
                </div>

                <div class="article-image">
                    <img src="${pageContext.request.contextPath}/ASM/assets/images/${news.image}" 
                         alt="${news.title}" onerror="this.src='${pageContext.request.contextPath}/ASM/assets/images/placeholder.jpg'">
                </div>

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