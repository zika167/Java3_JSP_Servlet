package com.wangquocthai.java3_jsp_servlet.ASM.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class cho News (Bản tin) theo schema database
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {
    private String id;          // Mã bản tin
    private String title;       // Tiêu đề
    private String content;     // Nội dung
    private String image;       // Hình ảnh/video
    private Date postedDate;    // Ngày đăng
    private String author;      // Tác giả (mã phóng viên)
    private int viewCount;      // Số lượt xem
    private String categoryId;  // Mã loại tin
    private String home;       // Trang nhất (true sẽ xuất hiện trên trang chủ)
    
    
    public String getHomeString() {
        if( getHome().equalsIgnoreCase("T") ){
            setHome("Trang nhất");
        }
        if( getHome().equalsIgnoreCase("F") ){
            setHome("Bình thường");
        }
        return home;
    }
}