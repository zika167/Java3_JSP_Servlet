<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>ABC News - Quản lý tin tức</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/reporter.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <!-- Header -->
    <header class="header">
        <div class="container">
            <h1>CÔNG CỤ QUẢN TRỊ TIN TỨC</h1>
        </div>
    </header>

    <!-- Navigation -->
    <nav class="nav-menu">
        <div class="container">
            <a href="/ASM/index.jsp" class="nav-link">Trang chủ</a>
            <a href="#" class="nav-link active">Tin tức</a>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <!-- Form thêm/sửa bài viết -->
            <div class="form-section">
                <h2>Thêm/Sửa bài viết</h2>
                <form action="../reporter" method="post" class="article-form">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="id" value="">
                    
                    <div class="form-group">
                        <label for="title">Tiêu đề:</label>
                        <input type="text" id="title" name="title" required value="${form.title}">
                        <c:if test="${not empty errors.title}">
                            <div class="error">${errors.title}</div>
                        </c:if>
                    </div>
                    
                    <div class="form-group">
                        <label for="content">Nội dung:</label>
                        <textarea id="content" name="content" rows="10" required>${form.content}</textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="image">Ảnh minh họa:</label>
                        <input type="text" id="image" name="image" placeholder="Tên file ảnh" value="${form.image}">
                    </div>
                    
                    <div class="form-group">
                        <label for="category">Danh mục:</label>
                        <select id="category" name="category" required>
                            <option value="">Chọn danh mục</option>
                            <option value="Công nghệ" <c:if test="${form.category == 'Công nghệ'}">selected</c:if>>Công nghệ</option>
                            <option value="Kinh tế" <c:if test="${form.category == 'Kinh tế'}">selected</c:if>>Kinh tế</option>
                            <option value="Giáo dục" <c:if test="${form.category == 'Giáo dục'}">selected</c:if>>Giáo dục</option>
                            <option value="Thể thao" <c:if test="${form.category == 'Thể thao'}">selected</c:if>>Thể thao</option>
                            <option value="Môi trường" <c:if test="${form.category == 'Môi trường'}">selected</c:if>>Môi trường</option>
                            <option value="Xã hội" <c:if test="${form.category == 'Xã hội'}">selected</c:if>>Xã hội</option>
                            <option value="Ẩm thực" <c:if test="${form.category == 'Ẩm thực'}">selected</c:if>>Ẩm thực</option>
                            <option value="Giao thông" <c:if test="${form.category == 'Giao thông'}">selected</c:if>>Giao thông</option>
                            <option value="Văn hóa" <c:if test="${form.category == 'Văn hóa'}">selected</c:if>>Văn hóa</option>
                            <option value="Kinh doanh" <c:if test="${form.category == 'Kinh doanh'}">selected</c:if>>Kinh doanh</option>
                        </select>
                        <c:if test="${not empty errors.category}">
                            <div class="error">${errors.category}</div>
                        </c:if>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Lưu bài viết</button>
                        <button type="button" class="btn btn-secondary" onclick="clearForm()">Làm mới</button>
                    </div>
                </form>
            </div>

            <!-- Bảng danh sách bài viết -->
            <div class="table-section">
                <h2>Danh sách bài viết của tôi</h2>
                <div class="table-container">
                    <table class="articles-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tiêu đề</th>
                                <th>Ngày đăng</th>
                                <th>Trạng thái</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="newsList" items="${newsList}">
                                <c:choose>
                                    <c:when test="${newsList.home eq 'T'}">
                                        <c:set var="homeClass" value="status-home"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="homeClass" value="status-normal"/>
                                    </c:otherwise>
                                </c:choose>
                                <tr>
                                    <td>${newsList.id}</td>
                                    <td class="title-cell">${newsList.title}</td>
                                    <td><fmt:formatDate value="${newsList.postedDate}" pattern="dd/MM/yyyy"/></td>
                                    <td>
                                        <span class="status ${homeClass}">
                                            ${newsList.getHomeString()}
                                        </span>
                                    </td>
                                    <td class="actions">
                                        <button class="btn btn-sm btn-edit" onclick="editArticle('${newsList.id}')">Sửa</button>
                                        <button class="btn btn-sm btn-delete" onclick="deleteArticle('${newsList.id}')">Xóa</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <p>Welcome Phóng viên A</p>
        </div>
    </footer>

    <script>
        function clearForm() {
            document.querySelector('.article-form').reset();
            document.querySelector('input[name="id"]').value = '';
            document.querySelector('input[name="action"]').value = 'add';
        }

        function editArticle(id) {
            // Mock function - trong thực tế sẽ load dữ liệu từ server
            alert('Chức năng sửa bài viết ID: ' + id);
        }

        function deleteArticle(id) {
            if (confirm('Bạn có chắc chắn muốn xóa bài viết này?')) {
                // Mock function - trong thực tế sẽ gửi request xóa
                alert('Đã xóa bài viết ID: ' + id);
            }
        }
    </script>
</body>
</html>