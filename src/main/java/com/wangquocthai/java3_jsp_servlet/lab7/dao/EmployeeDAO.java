package com.wangquocthai.java3_jsp_servlet.lab7.dao;

import com.wangquocthai.java3_jsp_servlet.lab7.entity.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(String id);
    void create(Employee item);
    void update(Employee item);
    void deleteById(String id);
}