<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
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
            </nav>
        </div>
    </header>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <h2>Danh sách tin tức</h2>
            
            <!-- Danh sách bài viết -->
            <div class="articles-list">
                <c:forEach var="newsList" items="${newsList}">
                    <div class="article-item">
                        <div class="article-image">
                            <img src="/ASM/assets/images/${newsList.image}" alt="${newsList.title}">
                        </div>
                        <div class="article-content">
                            <h3 class="article-title">${newsList.title}</h3>
                            <p class="article-summary">${newsList.content}</p>
                            <div class="article-meta">
                                <span class="article-author">Tác giả: ${newsList.author}</span>
                                <span class="article-date">
                                    Ngày đăng: <fmt:formatDate value="${newsList.postedDate}" pattern="dd/MM/yyyy"/>
                                </span>
                            </div>
                            <a href="news_detail.jsp?id=${newsList.id}" class="btn-detail">Xem chi tiết</a>
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
                    <li><a href="ASM/reader/news_detail.jsp">Công nghệ AI đang thay đổi thế giới</a></li>
                    <li><a href="reader/news_detail.jsp">Kinh tế Việt Nam tăng trưởng mạnh</a></li>
                    <li><a href="reader/news_detail.jsp">Giáo dục đại học: Xu hướng mới</a></li>
                    <li><a href="reader/news_detail.jsp">Thể thao: Việt Nam giành huy chương vàng</a></li>
                    <li><a href="reader/news_detail.jsp">Môi trường: Bảo vệ rừng Amazon</a></li>
                </ul>
            </div>

            <!-- 5 bản tin mới nhất -->
            <div class="sidebar-box latest-news">
                <h3>5 bản tin mới nhất</h3>
                <ul>
                    <li><a href="#">Tin tức mới nhất 1</a></li>
                    <li><a href="#">Tin tức mới nhất 2</a></li>
                    <li><a href="#">Tin tức mới nhất 3</a></li>
                    <li><a href="#">Tin tức mới nhất 4</a></li>
                    <li><a href="#">Tin tức mới nhất 5</a></li>
                </ul>
            </div>

            <!-- 5 bản tin đã xem -->
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