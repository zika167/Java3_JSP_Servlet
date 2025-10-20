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
            <a href="${pageContext.request.contextPath}/index.jsp" class="nav-link">Trang chủ</a>
            <a href="${pageContext.request.contextPath}/reporter" class="nav-link active">Tin tức</a>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="main-container">
        <div class="content">
            <!-- Form thêm/sửa bài viết -->
            <div class="form-section">
                <h2>Thêm/Sửa bài viết</h2>
                <form id="articleForm" action="${pageContext.request.contextPath}/reporter" method="post" class="article-form">
                    <input type="hidden" id="action" name="action" value="create">
                    <input type="hidden" id="id" name="id" value="">

                    <div class="form-group">
                        <label for="title">Tiêu đề:</label>
                        <input type="text" id="title" name="title" required>
                    </div>

                    <div class="form-group">
                        <label for="content">Nội dung:</label>
                        <textarea id="content" name="content" rows="10" required></textarea>
                    </div>

                    <div class="form-group">
                        <label for="image">Ảnh minh họa:</label>
                        <input type="text" id="image" name="image" placeholder="Tên file ảnh (ví dụ: image.jpg)">
                    </div>

                    <div class="form-group-inline">
                        <div class="form-group" style="flex: 1;">
                            <label for="categoryId">Danh mục:</label>
                            <select id="categoryId" name="categoryId" required>
                                <option value="">Chọn danh mục</option>
                                <c:forEach var="cat" items="${categories}">
                                    <option value="${cat.id}">${cat.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" style="flex: 1;">
                            <label for="home">Trạng thái:</label>
                            <select id="home" name="home" required>
                                <option value="N">Trang thường</option>
                                <option value="Y">Trang nhất</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="submit" id="btnCreate" class="btn btn-primary">Tạo mới</button>
                        <button type="submit" id="btnUpdate" class="btn btn-success" disabled>Cập nhật</button>
                        <button type="button" class="btn btn-secondary" onclick="clearForm()">Xóa trắng</button>
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
                            <c:forEach var="news" items="${newsList}">
                                <tr data-id="${news.id}" 
                                    data-title="${news.title}" 
                                    data-content="${news.content}" 
                                    data-image="${news.image}" 
                                    data-categoryid="${news.categoryId}" 
                                    data-home="${news.home}">
                                    <td>${news.id}</td>
                                    <td class="title-cell">${news.title}</td>
                                    <td><fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${news.home == 'Y'}">
                                                <span class="status status-home">Trang nhất</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="status status-normal">Trang thường</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="actions">
                                        <button class="btn btn-sm btn-edit" onclick="editArticle(this)">Sửa</button>
                                        <a href="${pageContext.request.contextPath}/reporter?action=delete&id=${news.id}" class="btn btn-sm btn-delete" onclick="return confirm('Bạn có chắc chắn muốn xóa bài viết này?')">Xóa</a>
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
             <p>Welcome, ${sessionScope.user.fullname}!</p>
        </div>
    </footer>

    <script>
        const form = document.getElementById('articleForm');
        const actionInput = document.getElementById('action');
        const idInput = document.getElementById('id');
        const titleInput = document.getElementById('title');
        const contentInput = document.getElementById('content');
        const imageInput = document.getElementById('image');
        const categoryIdInput = document.getElementById('categoryId');
        const homeInput = document.getElementById('home');
        const btnCreate = document.getElementById('btnCreate');
        const btnUpdate = document.getElementById('btnUpdate');

        function clearForm() {
            form.reset();
            idInput.value = '';
            actionInput.value = 'create';
            btnCreate.disabled = false;
            btnUpdate.disabled = true;
            // Reset form action in case it was changed by editArticle
            form.action = '${pageContext.request.contextPath}/reporter';
        }

        function editArticle(button) {
            const row = button.closest('tr');
            const id = row.dataset.id;
            const title = row.dataset.title;
            const content = row.dataset.content;
            const image = row.dataset.image;
            const categoryId = row.dataset.categoryid;
            const home = row.dataset.home;

            // Đổ dữ liệu lên form
            idInput.value = id;
            titleInput.value = title;
            contentInput.value = content;
            imageInput.value = image;
            categoryIdInput.value = categoryId;
            homeInput.value = home;

            // Cập nhật trạng thái form và nút
            actionInput.value = 'update';
            btnCreate.disabled = true;
            btnUpdate.disabled = false;
            
            // Change form action to point to the update logic
            form.action = '${pageContext.request.contextPath}/reporter';

            // Cuộn lên đầu trang để xem form
            window.scrollTo(0, 0);
        }

        // Đảm bảo form được reset khi tải lại trang
        window.onload = function() {
            clearForm();
        };
    </script>
</body>
</html>
