<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>ABC News - Hệ thống quản lý tin tức</title>
    <link rel="stylesheet" href="assets/css/reader.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <!-- Header -->
    <% request.setAttribute("hideAuthButtons", true); %>
    <jsp:include page="/ASM/layout/header.jsp"/>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <h2>Chào mừng đến với ABC News</h2>
            <p>Hệ thống quản lý tin tức với 3 loại người dùng:</p>
            
            <div class="user-types">
                <div class="user-type-card">
                    <h3>👥 Độc giả (Reader)</h3>
                    <p>Xem danh sách tin tức, đọc chi tiết bài viết</p>
                    <a href="${pageContext.request.contextPath}/reader" class="btn-detail">Truy cập</a>
                </div>
                
                <div class="user-type-card">
                    <h3>✍️ Phóng viên (Reporter)</h3>
                    <p>Quản lý bài viết, thêm/sửa/xóa tin tức</p>
                    <a href="${pageContext.request.contextPath}/reporter" class="btn-detail">Truy cập</a>
                </div>
                
                <div class="user-type-card">
                    <h3>⚙️ Quản trị (Admin)</h3>
                    <p>Quản lý người dùng, phân quyền hệ thống</p>
                    <a href="${pageContext.request.contextPath}/admin" class="btn-detail">Truy cập</a>
                </div>
            </div>

            <div class="features">
                <h3>Tính năng chính:</h3>
                <ul>
                    <li>✅ Giao diện hiện đại, responsive</li>
                    <li>✅ Quản lý tin tức theo mô hình MVC</li>
                    <li>✅ Phân quyền người dùng rõ ràng</li>
                    <li>✅ Mock data để test giao diện</li>
                    <li>✅ CSS tách biệt cho từng module</li>
                </ul>
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
                </div>            <!-- Newsletter -->
            <div class="newsletter-box">
                <h3>Đăng ký nhận bản tin</h3>
                <form action="#" method="post">
                    <input type="email" name="email" placeholder="Nhập email của bạn" required>
                    <button type="submit">Đăng ký</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="/ASM/layout/footer.jsp"/>

    <style>
        .user-types {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 1.5rem;
            margin: 2rem 0;
        }

        .user-type-card {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: center;
            transition: transform 0.3s ease;
        }

        .user-type-card:hover {
            transform: translateY(-5px);
        }

        .user-type-card h3 {
            color: #1e3c72;
            margin-bottom: 1rem;
            font-size: 1.3rem;
        }

        .user-type-card p {
            color: #666;
            margin-bottom: 1.5rem;
        }

        .features {
            background: #f8f9fa;
            padding: 1.5rem;
            border-radius: 10px;
            margin-top: 2rem;
        }

        .features h3 {
            color: #1e3c72;
            margin-bottom: 1rem;
        }

        .features ul {
            list-style: none;
            padding-left: 0;
        }

        .features li {
            padding: 0.5rem 0;
            color: #333;
        }
    </style>
</body>
</html>
