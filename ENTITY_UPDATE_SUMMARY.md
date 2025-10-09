# Cập nhật Entity Models theo Schema Database

## ✅ Đã hoàn thành cập nhật

### 1. **News.java** (thay thế Article.java)
Theo schema **NEWS** entity:
```java
- String id          // Mã bản tin
- String title       // Tiêu đề  
- String content     // Nội dung
- String image       // Hình ảnh/video
- Date postedDate    // Ngày đăng
- String author      // Tác giả (mã phóng viên)
- int viewCount      // Số lượt xem
- String categoryId  // Mã loại tin
- boolean home       // Trang nhất (true sẽ xuất hiện trên trang chủ)
```

### 2. **User.java** (cập nhật theo schema)
Theo schema **USERS** entity:
```java
- String id          // Mã đăng nhập
- String password    // Mật khẩu
- String fullname    // Họ và tên
- Date birthday      // Ngày sinh
- boolean gender     // Giới tính (true: nam, false: nữ)
- String mobile      // Điện thoại
- String email       // Email
- boolean role       // Vai trò (true: quản trị, false: phóng viên)
```

### 3. **Category.java** (mới)
Theo schema **CATEGORIES** entity:
```java
- String id          // Mã loại tin
- String name        // Tên loại tin
```

### 4. **Newsletter.java** (mới)
Theo schema **NEWSLETTERS** entity:
```java
- String email       // Email nhận tin
- boolean enabled    // Trạng thái (true-còn hiệu lực)
```

## 🔄 Cập nhật các Servlet

### **ReaderServlet.java**
- ✅ Import `News` và `Category` thay vì `Article`
- ✅ Tạo mock data cho `List<News>` và `List<Category>`
- ✅ Cập nhật attribute names: `newsList`, `categories`
- ✅ Mock data với ID string format: "NEWS001", "CAT001", etc.

### **ReporterServlet.java**
- ✅ Import `News` và `Category` thay vì `Article`
- ✅ Tạo mock data cho `List<News>` và `List<Category>`
- ✅ Cập nhật attribute names: `myNews`, `categories`
- ✅ Mock data với ID string format: "NEWS006-NEWS010", "CAT001-CAT008"

### **AdminServlet.java**
- ✅ Import `User` và `Newsletter`
- ✅ Cập nhật User constructor với đầy đủ fields
- ✅ Tạo mock data cho `List<Newsletter>`
- ✅ Mock data với ID string format: "ADMIN001", "REP001-REP004"

## 📊 Mock Data mới

### **News (5 tin tức cho Reader)**
- NEWS001: Công nghệ AI (1250 lượt xem, trang nhất)
- NEWS002: Kinh tế Việt Nam (980 lượt xem, trang nhất)
- NEWS003: Giáo dục đại học (756 lượt xem)
- NEWS004: Thể thao (2100 lượt xem, trang nhất)
- NEWS005: Môi trường (890 lượt xem)

### **News (5 tin tức cho Reporter)**
- NEWS006: Phóng sự nông thôn (450 lượt xem)
- NEWS007: Ẩm thực đường phố (680 lượt xem, trang nhất)
- NEWS008: Giao thông công cộng (320 lượt xem)
- NEWS009: Nghệ thuật truyền thống (890 lượt xem, trang nhất)
- NEWS010: Startup Việt Nam (560 lượt xem)

### **Users (5 người dùng)**
- ADMIN001: Nguyễn Văn Admin (Admin, Nam, 1990)
- REP001: Trần Thị Phóng viên (Reporter, Nữ, 1985)
- REP002: Lê Văn Biên tập (Reporter, Nam, 1988)
- REP003: Phạm Thị Cộng tác viên (Reporter, Nữ, 1992)
- REP004: Hoàng Văn Thực tập (Reporter, Nam, 1995)

### **Categories (8 loại tin)**
- CAT001: Công nghệ
- CAT002: Kinh tế
- CAT003: Giáo dục
- CAT004: Thể thao
- CAT005: Môi trường
- CAT006: Xã hội
- CAT007: Văn hóa
- CAT008: Pháp luật

### **Newsletters (5 email đăng ký)**
- user1@gmail.com (Hoạt động)
- user2@yahoo.com (Hoạt động)
- user3@hotmail.com (Tạm dừng)
- user4@gmail.com (Hoạt động)
- user5@outlook.com (Hoạt động)

## 🎯 Lợi ích của việc cập nhật

### ✅ **Phù hợp với Database Schema**
- Tất cả fields đều match với schema trong hình
- Data types chính xác (String, Date, boolean, int)
- Naming convention nhất quán

### ✅ **Dữ liệu thực tế hơn**
- ID dạng string có ý nghĩa (NEWS001, REP001, CAT001)
- ViewCount thực tế cho tin tức
- Birthday và Gender cho user
- Home flag để phân biệt tin trang nhất

### ✅ **Mở rộng dễ dàng**
- Có thể thêm Category management
- Có thể thêm Newsletter management
- Dễ dàng kết nối database thực

## 📝 Lưu ý

### **Cần cập nhật JSP**
- Thay đổi từ `articles` thành `newsList`
- Thay đổi từ `myArticles` thành `myNews`
- Cập nhật field names: `date` → `postedDate`, `summary` → `content`
- Thêm hiển thị `viewCount`, `home` status

### **Helper Methods**
- `User.getRoleString()`: "Admin" hoặc "Reporter"
- `User.getGenderString()`: "Nam" hoặc "Nữ"
- `Newsletter.getStatusString()`: "Hoạt động" hoặc "Tạm dừng"

## 🚀 Sẵn sàng cho Database Integration
Các entity models đã được thiết kế theo đúng schema database, sẵn sàng để:
- Tạo database tables
- Implement DAO pattern
- Kết nối JDBC/JPA
- Thực hiện CRUD operations

