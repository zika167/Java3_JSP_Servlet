package com.wangquocthai.java3_jsp_servlet.ASM.model;

/**
 * Model class cho Category (Loại tin) theo schema database
 */
public class Category {
    private String id;      // Mã loại tin
    private String name;    // Tên loại tin
    
    // Constructors
    public Category() {}
    
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}

