package com.wangquocthai.java3_jsp_servlet.lab2;

/**
 * JavaBean đại diện cho thông tin người dùng sử dụng trong Lab 2.
 */

public class User {

    private String fullname;
    private boolean gender;
    private String country;

    public User() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}


