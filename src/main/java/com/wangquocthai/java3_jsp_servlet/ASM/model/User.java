package com.wangquocthai.java3_jsp_servlet.ASM.model;

import java.util.Date;

/**
 * Model class cho User (Người dùng) theo schema database
 */
public class User {
    private String id;          // Mã đăng nhập
    private String password;    // Mật khẩu
    private String fullname;    // Họ và tên
    private Date birthday;      // Ngày sinh
    private String gender;     // Giới tính (true: nam, false: nữ)
    private String mobile;      // Điện thoại
    private String email;       // Email
    private String role;       // Vai trò (true: quản trị, false: phóng viên)
    private boolean active;     // Trạng thái tài khoản
    
    // Constructors
    public User() {}
    
    public User(String id, String password, String fullname, Date birthday, String gender,
                String mobile, String email, String role, boolean active) {
        this.id = id;
        this.password = password;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.role = role;
        this.active = active;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFullname() {
        return fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {return active;}

    public void setActive(boolean active) {this.active = active;}
    
    // Helper methods
    public String getRoleString() {
        if( getRole().equalsIgnoreCase("A") ){
            setRole("Admin");
        }
        if( getRole().equalsIgnoreCase("R") ){
            setRole("Reporter");
        }
        return role;
    }
    
    public String getGenderString() {
        if( getGender().equalsIgnoreCase("M") ){
            setGender("Male");
        }
        if( getGender().equalsIgnoreCase("F") ){
            setGender("Female");
        }
        return gender;
    }
}
