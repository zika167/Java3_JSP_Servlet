package com.wangquocthai.java3_jsp_servlet.ASM.model;

/**
 * Model class cho Newsletter (Đăng ký nhận tin) theo schema database
 */
public class Newsletter {
    private String email;       // Email nhận tin
    private boolean enabled;    // Trạng thái (true-còn hiệu lực)
    
    // Constructors
    public Newsletter() {}
    
    public Newsletter(String email, boolean enabled) {
        this.email = email;
        this.enabled = enabled;
    }
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    // Helper methods
    public String getStatusString() {
        return enabled ? "Hoạt động" : "Tạm dừng";
    }
}

