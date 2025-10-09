package com.wangquocthai.java3_jsp_servlet.ASM.controller;

import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet cho chức năng Phóng viên (Reporter)
 */
@WebServlet(name = "ReporterServlet", urlPatterns = {"/reporter", "/reporter/dashboard", "/reporter/new_crud"})
public class ReporterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Mock data - danh sách 5 bài viết do phóng viên đăng
        List<News> newsList = createMockReporterNews();
        List<Category> categories = createMockCategories();
        
        // Truyền dữ liệu sang JSP
        request.setAttribute("newsList", newsList);
        request.setAttribute("categories", categories);
        
        // Forward đến JSP
        request.getRequestDispatcher("/ASM/reporter/news_crud.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Xử lý form thêm/sửa bài viết
        String action = request.getParameter("action");
        
        if ("add".equals(action) || "edit".equals(action)) {
            // Lấy dữ liệu từ form
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String image = request.getParameter("image");
            String category = request.getParameter("category");
            
            // Xử lý logic thêm/sửa (mock)
            // Trong thực tế sẽ lưu vào database
            System.out.println("Processing article: " + title + " - " + category);
            
            // Redirect về trang danh sách
            response.sendRedirect(request.getContextPath() + "/reporter");
            return;
        }
        
        doGet(request, response);
    }
    
    /**
     * Tạo dữ liệu mẫu cho danh sách tin tức của phóng viên
     */
    private List<News> createMockReporterNews() {
        List<News> newsList = new ArrayList<>();
        
        newsList.add(new News(
            "NEWS006",
            "Phóng sự về cuộc sống nông thôn",
            "Một cái nhìn sâu sắc về cuộc sống của người dân nông thôn Việt Nam trong thời đại hiện đại...",
            "rural-life.jpg",
            new Date(System.currentTimeMillis() - 86400000), // 1 ngày trước
            "REP001",
            450,
            "CAT006",
            "F"
        ));
        
        newsList.add(new News(
            "NEWS007",
            "Khám phá ẩm thực đường phố",
            "Hành trình khám phá những món ăn đường phố độc đáo tại Hà Nội, từ phở bò đến bún chả...",
            "street-food.jpg",
            new Date(System.currentTimeMillis() - 172800000), // 2 ngày trước
            "REP001",
            680,
            "CAT007",
            "T"
        ));
        
        newsList.add(new News(
            "NEWS008",
            "Giao thông công cộng: Thách thức và cơ hội",
            "Phân tích về tình hình giao thông công cộng tại các thành phố lớn và những giải pháp cải thiện...",
            "public-transport.jpg",
            new Date(System.currentTimeMillis() - 259200000), // 3 ngày trước
            "REP001",
            320,
            "CAT006",
            "F"
        ));
        
        newsList.add(new News(
            "NEWS009",
            "Nghệ thuật truyền thống trong thời hiện đại",
            "Tìm hiểu về sự phát triển của nghệ thuật truyền thống Việt Nam trong bối cảnh toàn cầu hóa...",
            "traditional-art.jpg",
            new Date(System.currentTimeMillis() - 345600000), // 4 ngày trước
            "REP001",
            890,
            "CAT007",
            "T"
        ));
        
        newsList.add(new News(
            "NEWS010",
            "Startup Việt Nam: Hành trình khởi nghiệp",
            "Câu chuyện về những startup thành công tại Việt Nam và bài học kinh nghiệm từ các doanh nhân trẻ...",
            "startup-news.jpg",
            new Date(System.currentTimeMillis() - 432000000), // 5 ngày trước
            "REP001",
            560,
            "CAT002",
            "F"
        ));
        
        return newsList;
    }
    
    /**
     * Tạo dữ liệu mẫu cho danh sách loại tin
     */
    private List<Category> createMockCategories() {
        List<Category> categories = new ArrayList<>();
        
        categories.add(new Category("CAT001", "Công nghệ"));
        categories.add(new Category("CAT002", "Kinh tế"));
        categories.add(new Category("CAT003", "Giáo dục"));
        categories.add(new Category("CAT004", "Thể thao"));
        categories.add(new Category("CAT005", "Môi trường"));
        categories.add(new Category("CAT006", "Xã hội"));
        categories.add(new Category("CAT007", "Văn hóa"));
        categories.add(new Category("CAT008", "Pháp luật"));
        
        return categories;
    }
}
