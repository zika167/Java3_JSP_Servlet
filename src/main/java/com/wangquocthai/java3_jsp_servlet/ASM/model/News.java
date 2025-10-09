package com.wangquocthai.java3_jsp_servlet.ASM.model;

import java.util.Date;

/**
 * Model class cho News (Bản tin) theo schema database
 */
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
    
    // Constructors
    public News() {}
    
    public News(String id, String title, String content, String image, Date postedDate, 
                String author, int viewCount, String categoryId, String home) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.postedDate = postedDate;
        this.author = author;
        this.viewCount = viewCount;
        this.categoryId = categoryId;
        this.home = home;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public Date getPostedDate() {
        return postedDate;
    }
    
    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getViewCount() {
        return viewCount;
    }
    
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    
    public String getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getHome() {
        return home;
    }
    
    public void setHome(String home) {
        this.home = home;
    }

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