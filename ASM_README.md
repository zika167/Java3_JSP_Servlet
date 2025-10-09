# ABC News - Hệ thống quản lý tin tức

## Tổng quan
Hệ thống quản lý tin tức ABC News được xây dựng theo mô hình MVC với 3 loại người dùng: Độc giả, Phóng viên và Quản trị.

## Cấu trúc dự án

### 1. Model (POJO Classes)
- `Article.java`: Model cho bài viết tin tức
- `User.java`: Model cho người dùng

### 2. Controller (Servlets)
- `ReaderServlet.java`: Xử lý logic cho Độc giả
- `ReporterServlet.java`: Xử lý logic cho Phóng viên  
- `AdminServlet.java`: Xử lý logic cho Quản trị

### 3. View (JSP Pages)
- `ASM/index.jsp`: Trang chủ hệ thống
- `ASM/reader/news_list.jsp`: Danh sách tin tức cho độc giả
- `ASM/reader/news_detail.jsp`: Chi tiết tin tức
- `ASM/reporter/news_crud.jsp`: Quản lý tin tức cho phóng viên
- `ASM/admin/user_crud.jsp`: Quản lý người dùng cho admin

### 4. CSS Files
- `assets/css/reader.css`: CSS cho giao diện độc giả
- `assets/css/reporter.css`: CSS cho giao diện phóng viên
- `assets/css/admin.css`: CSS cho giao diện quản trị

## Chức năng chính

### 👥 Độc giả (Reader)
- Xem danh sách tin tức với thông tin: ID, Tiêu đề, Tóm tắt, Tác giả, Ngày đăng
- Xem chi tiết bài viết
- Sidebar hiển thị: 5 tin hot nhất, 5 tin mới nhất, 5 tin đã xem
- Đăng ký nhận newsletter

### ✍️ Phóng viên (Reporter)
- Form thêm/sửa bài viết với các trường: Tiêu đề, Nội dung, Ảnh minh họa, Danh mục
- Bảng danh sách bài viết: ID, Tiêu đề, Ngày đăng, Trạng thái
- Các trạng thái: Draft, Under Review, Published, Rejected
- Thao tác: Sửa, Xóa bài viết

### ⚙️ Quản trị (Admin)
- Form thêm tài khoản: Tên đăng nhập, Email, Vai trò, Họ và tên
- Bảng danh sách người dùng: ID, Username, Email, Role, Trạng thái
- Các vai trò: Admin, Reporter, Reader
- Thao tác: Khóa/Mở khóa, Xóa tài khoản

## Cách chạy

### 1. Yêu cầu hệ thống
- Java 11+
- Apache Tomcat 10+
- Maven 3.6+

### 2. Cài đặt và chạy
```bash
# Clone project
cd Java3_JSP_Servlet

# Build project
mvn clean compile

# Deploy lên Tomcat
mvn tomcat7:deploy

# Hoặc chạy trực tiếp
mvn tomcat7:run
```

### 3. Truy cập ứng dụng
- Trang chủ: `http://localhost:8080/Java3_JSP_Servlet/ASM/`
- Độc giả: `http://localhost:8080/Java3_JSP_Servlet/reader`
- Phóng viên: `http://localhost:8080/Java3_JSP_Servlet/reporter`
- Quản trị: `http://localhost:8080/Java3_JSP_Servlet/admin`

## Mock Data

### Bài viết mẫu (5 bài)
1. Công nghệ AI đang thay đổi thế giới
2. Kinh tế Việt Nam tăng trưởng mạnh
3. Giáo dục đại học: Xu hướng mới
4. Thể thao: Việt Nam giành huy chương vàng
5. Môi trường: Bảo vệ rừng Amazon

### Người dùng mẫu (5 user)
1. admin01 - Admin
2. reporter01 - Reporter
3. reporter02 - Reporter
4. reader01 - Reader
5. reader02 - Reader (bị khóa)

## Tính năng kỹ thuật

### ✅ Đã hoàn thành
- [x] Cấu trúc MVC hoàn chỉnh
- [x] 3 giao diện JSP cho từng loại người dùng
- [x] CSS hiện đại, responsive
- [x] Mock data để test
- [x] Servlet mapping trong web.xml
- [x] JSTL để hiển thị dữ liệu
- [x] Form validation cơ bản
- [x] Hover effects và animations

### 🔄 Có thể mở rộng
- [ ] Kết nối database thực
- [ ] Authentication & Authorization
- [ ] Upload ảnh thực tế
- [ ] Pagination cho danh sách
- [ ] Search và filter
- [ ] Email notification
- [ ] API REST

## Cấu trúc URL

```
/ASM/                    -> Trang chủ
/reader                  -> Danh sách tin tức (ReaderServlet)
/reporter                -> Quản lý tin tức (ReporterServlet)  
/admin                   -> Quản lý người dùng (AdminServlet)
```

## Ghi chú
- Tất cả dữ liệu hiện tại là mock data
- Chưa có kết nối database
- Chưa có authentication thực tế
- Giao diện đã responsive cho mobile
- Code sạch, có comment đầy đủ

## Liên hệ
Dự án được phát triển bởi: [Tên sinh viên]
Ngày: 2024

