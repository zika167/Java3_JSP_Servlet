package com.wangquocthai.java3_jsp_servlet.final_test.model;

public class KhachHang {
    private String username;
    private String password;
    private String hoten;
    private String gioitinh;
    private String email;

    // Constructor mặc định
    public KhachHang() {
    }

    // Constructor đầy đủ tham số
    public KhachHang(String username, String password, String hoten, String gioitinh, String email) {
        this.username = username;
        this.password = password;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.email = email;
    }

    // Getter và Setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString method để debug
    @Override
    public String toString() {
        return "KhachHang{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hoten='" + hoten + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
