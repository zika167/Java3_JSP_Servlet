# 📘 HƯỚNG DẪN SỬ DỤNG - ABC NEWS UPGRADE

## 🚀 Tổng quan

Dự án ABC News đã được nâng cấp toàn diện với 4 tính năng chính:
1. **Điều hướng tự động** cho khách chưa đăng nhập
2. **Modal đăng nhập/đăng ký** với AJAX
3. **Thiết kế CSS hiện đại** theo phong cách Yeah1
4. **Admin CRUD hoàn chỉnh** với JavaScript

---

## 📂 Cấu trúc Files Mới

```
src/main/
├── java/.../ASM/controller/
│   ├── HomeServlet.java (✏️ đã sửa)
│   ├── AdminServlet.java (✏️ đã sửa)
│   └── auth/
│       ├── LoginServlet.java (✏️ đã sửa)
│       └── SignupServlet.java (✏️ đã sửa)
│
└── webapp/ASM/
    ├── layout/
    │   └── header.jsp (✏️ đã sửa - có modal)
    ├── admin/
    │   └── user_crud.jsp (✏️ đã sửa - CRUD UI)
    ├── assets/
    │   ├── css/
    │   │   ├── modal.css (🆕)
    │   │   ├── reader-new.css (🆕)
    │   │   └── admin-enhanced.css (🆕)
    │   └── js/
    │       ├── auth-modal.js (🆕)
    │       └── admin-crud.js (🆕)
    └── index.jsp (✏️ đã sửa - CSS path)
```

---

## 🎯 PHẦN 1: Điều hướng tự động

### Cách hoạt động:
- Truy cập `http://localhost:8080/` → Tự động redirect đến `/reader`
- Không cần đăng nhập để xem tin tức

### Code đã thay đổi:
**HomeServlet.java:**
```java
@WebServlet(name = "HomeServlet", urlPatterns = {"/", "/index.jsp"})
public class HomeServlet extends HttpServlet {
    protected void doGet(...) {
        // Luôn redirect đến reader
        response.sendRedirect(request.getContextPath() + "/reader");
    }
}
```

### Test:
1. Mở browser: `http://localhost:8080/`
2. Kiểm tra: Tự động chuyển đến trang đọc báo
3. Không cần login để xem tin

---

## 🎯 PHẦN 2: Modal đăng nhập/đăng ký

### Tính năng:
- ✅ Click "Đăng nhập" → Hiện popup modal
- ✅ Click "Đăng ký" → Hiện popup modal
- ✅ Tab switching giữa Login/Signup
- ✅ AJAX submission (không reload trang)
- ✅ Hiển thị lỗi trong modal
- ✅ Auto-reload sau login thành công

### Cách sử dụng:

**1. Đăng nhập:**
```
1. Click "Đăng nhập" ở header
2. Modal hiện ra
3. Nhập username/password
4. Click "Đăng nhập"
5. Nếu thành công → Tự động reload, header cập nhật
6. Nếu thất bại → Hiện lỗi trong modal
```

**2. Đăng ký:**
```
1. Click "Đăng ký" ở header (hoặc tab trong modal)
2. Nhập: username, fullname, email, password, confirm password
3. Click "Đăng ký"
4. Thành công → Chuyển sang tab Login
5. Thất bại → Hiện lỗi
```

### API Endpoints:

**Login:**
```http
POST /auth/login
Headers: X-Requested-With: XMLHttpRequest
Body: id=username&password=pass

Response (Success):
{
  "success": true,
  "error": "Đăng nhập thành công",
  "user": {
    "fullname": "Nguyen Van A",
    "role": "R"
  }
}

Response (Failure):
{
  "success": false,
  "error": "Tên đăng nhập hoặc mật khẩu không đúng"
}
```

**Signup:**
```http
POST /auth/signup
Headers: X-Requested-With: XMLHttpRequest
Body: id=user&fullname=Name&email=email@test.com&password=pass

Response (Success):
{
  "success": true,
  "error": "Đăng ký thành công! Vui lòng đăng nhập."
}
```

### Files liên quan:
- `modal.css` - Styling cho modal
- `auth-modal.js` - Logic xử lý modal và AJAX
- `header.jsp` - HTML structure của modal
- `LoginServlet.java` - Backend xử lý login
- `SignupServlet.java` - Backend xử lý signup

---

## 🎯 PHẦN 3: CSS Redesign (Yeah1 Style)

### Đặc điểm thiết kế:

**Layout:**
- Grid 2 columns: Content (70%) + Sidebar (30%)
- Sticky sidebar
- Card-based design với border-radius 12px

**Typography:**
- Font: Inter, system fonts
- Heading: 700 weight, line-height 1.3
- Body: 400 weight, line-height 1.7
- Responsive font sizes

**Colors:**
- Primary: Navy Blue (#1e3c72)
- Accent: Yellow (#ffc107)
- Background: Light Gray (#f5f5f5)
- Text: Dark (#1a1a1a)

**Components:**
- News cards với hover effects (scale, shadow)
- Sidebar với thumbnail images (80x80px)
- Newsletter box với gradient background
- Pagination với rounded buttons

### Áp dụng CSS mới:

**Option 1: Test trước (khuyến nghị)**
```jsp
<!-- Trong news_list.jsp -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/reader-new.css">
```

**Option 2: Replace hoàn toàn**
```bash
cd src/main/webapp/ASM/assets/css/
mv reader.css reader-old.css
mv reader-new.css reader.css
```

### Responsive Breakpoints:
- Desktop: > 1024px
- Tablet: 768px - 1024px
- Mobile: < 768px
- Small Mobile: < 480px

---

## 🎯 PHẦN 4: Admin CRUD

### Tính năng:

**CREATE (Tạo mới):**
- Form ở trên cùng
- Nút "Tạo mới" enabled, "Cập nhật" disabled
- Validation: username, password, fullname, email, role
- Submit → Kiểm tra duplicate → Insert DB

**UPDATE (Cập nhật):**
- Click nút "Sửa" trên row
- Data load vào form
- Username field → readonly
- Password → optional (để trống = không đổi)
- Nút "Tạo mới" disabled, "Cập nhật" enabled
- Submit → Update DB

**DELETE (Xóa):**
- Click nút "Xóa"
- Confirm dialog
- Submit → Delete từ DB

**TOGGLE STATUS (Khóa/Mở):**
- Click nút "Khóa" hoặc "Mở"
- Confirm dialog
- Submit → Toggle active field

### Workflow chi tiết:

#### 1. Tạo người dùng mới:
```
1. Truy cập: /admin
2. Điền form:
   - Tên đăng nhập: user123
   - Mật khẩu: pass123
   - Họ tên: Nguyen Van A
   - Email: user@test.com
   - Vai trò: Reader
3. Click "Tạo mới"
4. JavaScript validate
5. Submit với action=CREATE
6. AdminServlet:
   - Check duplicate username
   - Insert vào DB
   - Redirect với success message
7. Trang reload, hiện alert "Thêm người dùng thành công!"
```

#### 2. Sửa người dùng:
```
1. Tìm user trong bảng
2. Click nút "Sửa" (icon bút)
3. Form auto-fill với data
4. Tên đăng nhập → readonly (không đổi được)
5. Mật khẩu → để trống (không đổi) hoặc nhập mới
6. Sửa thông tin khác
7. Click "Cập nhật"
8. Submit với action=UPDATE
9. AdminServlet update DB
10. Redirect với success message
```

#### 3. Xóa người dùng:
```
1. Click nút "Xóa" (icon thùng rác)
2. Confirm: "Bạn có chắc chắn muốn xóa...?"
3. OK → Submit với action=DELETE
4. AdminServlet xóa khỏi DB
5. Redirect với success message
```

#### 4. Khóa/Mở người dùng:
```
1. Click nút "Khóa" hoặc "Mở" (icon ổ khóa)
2. Confirm: "Bạn có chắc chắn muốn khóa/mở...?"
3. OK → Submit với action=TOGGLE_STATUS
4. AdminServlet toggle active field
5. Redirect với success message
```

### JavaScript API:

**AdminCRUD Class:**
```javascript
class AdminCRUD {
  // Methods:
  handleCreate()           // Xử lý tạo mới
  handleUpdate()           // Xử lý cập nhật
  validateForm(isUpdate)   // Validate form
  loadUserToForm(userId)   // Load data vào form
  confirmDelete(userId)    // Confirm và xóa
  confirmToggleStatus()    // Confirm và toggle
  switchToUpdateMode()     // Chuyển sang chế độ update
  resetForm()              // Reset form
}
```

### Form States:

**CREATE Mode (mặc định):**
- ID field: editable
- Password: required
- Nút "Tạo mới": enabled
- Nút "Cập nhật": disabled

**UPDATE Mode:**
- ID field: readonly
- Password: optional
- Nút "Tạo mới": disabled
- Nút "Cập nhật": enabled

---

## 🧪 TESTING

### 1. Test Modal Authentication:

**Test Login:**
```
✓ Click "Đăng nhập" → Modal hiện
✓ Nhập sai thông tin → Hiện lỗi trong modal
✓ Nhập đúng → Reload, header cập nhật
✓ ESC key → Đóng modal
✓ Click overlay → Đóng modal
```

**Test Signup:**
```
✓ Click "Đăng ký" → Modal hiện
✓ Password không khớp → Hiện lỗi
✓ Username đã tồn tại → Hiện lỗi
✓ Đăng ký thành công → Chuyển tab Login
```

### 2. Test Admin CRUD:

**Test CREATE:**
```
✓ Nhập thiếu field → Hiện lỗi validation
✓ Username trùng → Hiện lỗi "đã tồn tại"
✓ Tạo thành công → Hiện alert success
✓ User mới xuất hiện trong bảng
```

**Test UPDATE:**
```
✓ Click "Sửa" → Form fill data
✓ ID field → readonly
✓ Sửa thông tin → Click "Cập nhật"
✓ Update thành công → Hiện alert
✓ Data trong bảng cập nhật
```

**Test DELETE:**
```
✓ Click "Xóa" → Hiện confirm
✓ Cancel → Không xóa
✓ OK → Xóa thành công
✓ User biến mất khỏi bảng
```

**Test TOGGLE:**
```
✓ Click "Khóa" → Confirm → Khóa
✓ Status đổi thành "Khóa"
✓ Click "Mở" → Confirm → Mở
✓ Status đổi thành "Hoạt động"
```

### 3. Test Responsive:

**Desktop (> 1024px):**
```
✓ Layout 2 columns
✓ Sidebar sticky
✓ Full navigation
```

**Tablet (768-1024px):**
```
✓ Layout 1 column
✓ Sidebar dưới content
✓ Grid sidebar items
```

**Mobile (< 768px):**
```
✓ Single column
✓ Smaller fonts
✓ Touch-friendly buttons
✓ Modal full width
```

---

## 🐛 TROUBLESHOOTING

### Vấn đề 1: CSS không load

**Triệu chứng:** Trang hiển thị không có style

**Giải pháp:**
```jsp
<!-- Kiểm tra đường dẫn -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/ASM/assets/css/reader.css">

<!-- Không dùng hard-coded path -->
<link rel="stylesheet" href="/ASM/assets/css/reader.css"> ❌
```

### Vấn đề 2: Modal không hiện

**Triệu chứng:** Click "Đăng nhập" không có gì xảy ra

**Kiểm tra:**
1. Console có lỗi JavaScript không?
2. File `auth-modal.js` đã load chưa?
3. Meta tag `context-path` có trong header không?

**Giải pháp:**
```jsp
<!-- Trong header.jsp -->
<meta name="context-path" content="${pageContext.request.contextPath}">
<script src="${pageContext.request.contextPath}/ASM/assets/js/auth-modal.js"></script>
```

### Vấn đề 3: AJAX không hoạt động

**Triệu chứng:** Submit form reload trang thay vì AJAX

**Kiểm tra:**
1. Servlet có check header `X-Requested-With` không?
2. JavaScript có gửi header đúng không?

**Giải pháp:**
```java
// Trong Servlet
boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
```

```javascript
// Trong JavaScript
fetch(url, {
    headers: {
        'X-Requested-With': 'XMLHttpRequest'
    }
})
```

### Vấn đề 4: Admin CRUD không hoạt động

**Triệu chứng:** Click nút không có phản ứng

**Kiểm tra:**
1. File `admin-crud.js` đã load chưa?
2. Console có lỗi không?
3. Data attributes có đúng không?

**Giải pháp:**
```jsp
<!-- Kiểm tra data attributes -->
<tr data-user-id="${user.id}" 
    data-fullname="${user.fullname}" 
    data-email="${user.email}" 
    data-role="${user.getRoleString()}">
```

---

## 📝 NOTES

### Best Practices:

1. **Luôn dùng `${pageContext.request.contextPath}`** cho paths
2. **Validate cả client-side và server-side**
3. **Sử dụng AJAX cho better UX**
4. **Responsive design là bắt buộc**
5. **Confirm trước khi xóa data**

### Security:

1. **Password không được hiển thị** trong form update
2. **Validate input** để tránh SQL injection
3. **Check permissions** trong servlet
4. **HTTPS** cho production

### Performance:

1. **CSS được minify** cho production
2. **JavaScript được cache**
3. **Images được optimize**
4. **Lazy loading** cho danh sách dài

---

## 🎓 KẾT LUẬN

Dự án ABC News đã được nâng cấp toàn diện với:
- ✅ 4/4 tính năng hoàn thành
- ✅ ~2000+ dòng code mới
- ✅ Modern UI/UX
- ✅ AJAX integration
- ✅ Full CRUD operations
- ✅ Responsive design

**Sẵn sàng để deploy và test!** 🚀

---

**Tác giả:** Cascade AI Assistant  
**Ngày:** 2025-10-20  
**Version:** 1.0.0
