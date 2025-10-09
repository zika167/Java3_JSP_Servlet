<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>ABC News - Chi tiết tin tức</title>
    <link rel="stylesheet" href="../assets/css/reader.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <!-- Header -->
    <header class="header">
        <div class="container">
            <h1>ABC News</h1>
            <nav class="nav">
                <a href="../index.jsp" class="nav-link">Trang chủ</a>
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
                <a href="index.jsp">Trang chủ</a> >
                <a href="${pageContext.request.contextPath}/reader">Danh sách tin tức</a> >
                <span>Chi tiết tin tức</span>
            </nav>

            <!-- Article Detail -->
            <article class="article-detail">
                <h1 class="article-title">Công nghệ AI đang thay đổi thế giới</h1>
                
                <div class="article-meta">
                    <span class="article-author">Tác giả: Nguyễn Văn A</span>
                    <span class="article-date">Ngày đăng: 15/12/2024</span>
                    <span class="article-category">Danh mục: Công nghệ</span>
                </div>

                <div class="article-image">
                    <img src="../assets/images/ai-news.jpg" alt="Công nghệ AI">
                </div>

                <div class="article-content">
                    <p>Trí tuệ nhân tạo (AI) đang trở thành một trong những công nghệ quan trọng nhất của thế kỷ 21, với những ứng dụng rộng rãi từ y tế đến giáo dục, từ kinh doanh đến giải trí.</p>
                    
                    <p>Trong lĩnh vực y tế, AI đang giúp các bác sĩ chẩn đoán bệnh chính xác hơn thông qua việc phân tích hình ảnh y tế và dữ liệu bệnh nhân. Các thuật toán machine learning có thể phát hiện các dấu hiệu bệnh lý mà mắt thường khó nhận biết.</p>
                    
                    <p>Trong giáo dục, AI đang cá nhân hóa việc học tập, tạo ra các chương trình học phù hợp với từng học sinh. Các chatbot thông minh có thể trả lời câu hỏi của học sinh 24/7, giúp cải thiện trải nghiệm học tập.</p>
                    
                    <p>Trong kinh doanh, AI đang tối ưu hóa quy trình sản xuất, dự đoán xu hướng thị trường, và cải thiện dịch vụ khách hàng. Các hệ thống AI có thể phân tích dữ liệu lớn để đưa ra những quyết định kinh doanh thông minh.</p>
                    
                    <p>Tuy nhiên, sự phát triển của AI cũng đặt ra những thách thức về đạo đức và quyền riêng tư. Chúng ta cần cân nhắc cẩn thận về cách sử dụng AI một cách có trách nhiệm và công bằng.</p>
                    
                    <p>Nhìn chung, AI đang mở ra những cơ hội to lớn cho nhân loại, nhưng cũng đòi hỏi chúng ta phải thích ứng và học hỏi liên tục để tận dụng tối đa tiềm năng của công nghệ này.</p>
                </div>
            </article>

            <!-- Related News -->
            <section class="related-news">
                <h2>Tin cùng loại</h2>
                <div class="related-articles">
                    <div class="related-item">
                        <a href="#" class="related-link">• Tiêu đề bản tin cùng loại 1</a>
                    </div>
                    <div class="related-item">
                        <a href="#" class="related-link">• Tiêu đề bản tin cùng loại 2</a>
                    </div>
                    <div class="related-item">
                        <a href="#" class="related-link">• Tiêu đề bản tin cùng loại 3</a>
                    </div>
                </div>
            </section>
        </div>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- 5 bản tin được xem nhiều -->
            <div class="sidebar-box hot-news">
                <h3>5 bản tin được xem nhiều</h3>
                <ul>
                    <li><a href="#">Công nghệ AI đang thay đổi thế giới</a></li>
                    <li><a href="#">Kinh tế Việt Nam tăng trưởng mạnh</a></li>
                    <li><a href="#">Giáo dục đại học: Xu hướng mới</a></li>
                    <li><a href="#">Thể thao: Việt Nam giành huy chương vàng</a></li>
                    <li><a href="#">Môi trường: Bảo vệ rừng Amazon</a></li>
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
    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 ABC News. Tất cả quyền được bảo lưu.</p>
        </div>
    </footer>
</body>
</html>