<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>ABC News - Danh sách tin tức</title>
    <link rel="stylesheet" href="/ASM/assets/css/reader.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <!-- Header -->
    <header>
        <jsp:include page="/ASM/layout/header.jsp"/>
    </header>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <h2 style="margin-bottom: 20px">Danh sách tin tức</h2>
            
            <!-- Danh sách bài viết -->
            <div class="articles-list">
                <c:forEach var="news" items="${newsList}">
                    <article class="news-article">
                        <a href="${pageContext.request.contextPath}/news/detail/${news.id}" class="news-link">
                            <div class="news-image">
                                <img src="${pageContext.request.contextPath}/ASM/assets/images/${news.image}" 
                                     alt="<c:out value='${news.title}'/>"
                                     onerror="this.src='https://placehold.co/800x450'">
                            </div>
                            <div class="news-content">
                                <h3 class="news-title"><c:out value="${news.title}"/></h3>
                                <p class="news-summary text-truncate"><c:out value="${news.content}"/></p>
                                <div class="news-meta">
                                    <span class="news-author">
                                        <i class="fas fa-user"></i> <c:out value="${news.author}"/>
                                    </span>
                                    <span class="news-date">
                                        <i class="far fa-clock"></i>
                                        <fmt:formatDate value="${news.postedDate}" pattern="HH:mm dd/MM/yyyy"/>
                                    </span>
                                </div>
                            </div>
                        </a>
                    </article>
                </c:forEach>
            </div>
            
            <!-- Pagination -->
            <div class="pagination">
                <c:if test="${totalPages > 1}">
                    <div class="pagination-controls">
                        <a href="?id=${categoryId}&page=${currentPage - 1}" 
                           class="page-link ${currentPage == 1 ? 'disabled' : ''}"
                           ${currentPage == 1 ? 'aria-disabled="true"' : ''}>
                            <i class="fas fa-chevron-left"> </i>  Trang trước
                        </a>

                        <div class="page-numbers">
                            <c:forEach begin="1" end="${totalPages}" var="pageNum">
                                <a href="?id=${categoryId}&page=${pageNum}" 
                                   class="page-number ${pageNum == currentPage ? 'active' : ''}">
                                    ${pageNum}
                                </a>
                            </c:forEach>
                        </div>

                        <a href="?id=${categoryId}&page=${currentPage + 1}" 
                           class="page-link ${currentPage == totalPages ? 'disabled' : ''}"
                           ${currentPage == totalPages ? 'aria-disabled="true"' : ''}>
                            Trang sau  <i class="fas fa-chevron-right"> </i>
                        </a>
                    </div>
                </c:if>
            </div>
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
            <div class="sidebar-box newsletter-box">
                <h3>Đăng ký nhận bản tin</h3>
                <form action="${pageContext.request.contextPath}/newsletter" method="post">
                    <input type="email" name="email" placeholder="Nhập email của bạn" required>
                    <button type="submit">Đăng ký</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <jsp:include page="/ASM/layout/footer.jsp"/>
    </footer>
</body>
</html>