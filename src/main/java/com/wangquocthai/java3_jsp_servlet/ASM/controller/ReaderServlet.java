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
 * Servlet cho chức năng Độc giả (Reader)
 */
@WebServlet(name = "ReaderServlet", value = "/reader")
public class ReaderServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Mock data - danh sách 5 bài viết mẫu
        List<News> newsList = createMockNews();
        List<Category> categories = createMockCategories();
        
        // Truyền dữ liệu sang JSP
        request.setAttribute("newsList", newsList);
        request.setAttribute("categories", categories);
        
        // Forward đến JSP
        request.getRequestDispatcher("/ASM/reader/news_list.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    /**
     * Tạo dữ liệu mẫu cho danh sách tin tức
     */
    private List<News> createMockNews() {
        List<News> newsList = new ArrayList<>();
        
        newsList.add(new News(
            "NEWS001",
            "Công nghệ AI đang thay đổi thế giới",
            "Trí tuệ nhân tạo (AI) đang trở thành một trong những công nghệ quan trọng nhất của thế kỷ 21, với những ứng dụng rộng rãi từ y tế đến giáo dục, từ kinh doanh đến giải trí...",
            "congngheai.jpg",
            new Date(System.currentTimeMillis() - 86400000), // 1 ngày trước
            "REP001",
            1250,
            "CAT001",
            "T"
        ));
        
        newsList.add(new News(
            "NEWS002",
            "Kinh tế Việt Nam tăng trưởng mạnh",
            "Nền kinh tế Việt Nam tiếp tục duy trì đà tăng trưởng tích cực trong quý đầu năm 2024, với nhiều chỉ số kinh tế vĩ mô khả quan...",
            "kinhtevietnam.png",
            new Date(System.currentTimeMillis() - 172800000), // 2 ngày trước
            "REP002",
            980,
            "CAT002",
            "T"
        ));
        
        newsList.add(new News(
            "NEWS003",
            "Giáo dục đại học: Xu hướng mới",
            "Các trường đại học đang áp dụng những phương pháp giảng dạy mới để phù hợp với thời đại số, tạo ra những cơ hội học tập linh hoạt hơn...",
            "xuhuonggiaoduc.jpg",
            new Date(System.currentTimeMillis() - 259200000), // 3 ngày trước
            "REP001",
            756,
            "CAT003",
            "F"
        ));
        
        newsList.add(new News(
            "NEWS004",
            "Thể thao: Việt Nam giành huy chương vàng",
            "Đội tuyển Việt Nam đã xuất sắc giành huy chương vàng tại giải đấu quốc tế, mang lại niềm tự hào cho người hâm mộ...",
            "thethaovietnam.jpg",
            new Date(System.currentTimeMillis() - 345600000), // 4 ngày trước
            "REP003",
            2100,
            "CAT004",
            "T"
        ));
        
        newsList.add(new News(
            "NEWS005",
            "Môi trường: Bảo vệ rừng Amazon",
            "Các tổ chức quốc tế đang nỗ lực bảo vệ rừng Amazon khỏi nạn phá rừng, nhằm bảo tồn hệ sinh thái quan trọng này...",
            "baoverungamazon.jpg",
            new Date(System.currentTimeMillis() - 432000000), // 5 ngày trước
            "REP002",
            890,
            "CAT005",
            "T"
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
