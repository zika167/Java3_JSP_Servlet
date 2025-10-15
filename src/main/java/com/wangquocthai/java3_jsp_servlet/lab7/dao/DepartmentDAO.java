package com.wangquocthai.java3_jsp_servlet.lab7.dao;

import com.wangquocthai.java3_jsp_servlet.lab7.entity.Department;

import java.util.List;

public interface DepartmentDAO {
    /**Truy vấn tất cả*/
    List<Department> findAll();
    /**Truy vấn theo mã*/
    Department findById(String id);
    /**Thêm mới*/
    void create(Department item);
    /**Cập nhật*/
    void update(Department item);
    /**Xóa theo mã*/
    void deleteById(String id);
}
