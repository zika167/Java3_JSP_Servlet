package com.wangquocthai.java3_jsp_servlet.lab7.dao.impl;

import com.wangquocthai.java3_jsp_servlet.lab7.dao.EmployeeDAO;
import com.wangquocthai.java3_jsp_servlet.lab7.entity.Employee;
import com.wangquocthai.java3_jsp_servlet.lab7.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM Employees";
        try {
            List<Employee> entities = new ArrayList<>();
            ResultSet rs = Jdbc.executeQuery(sql);
            while (rs.next()) {
                Employee entity = new Employee();
                entity.setId(rs.getString("Id"));
                entity.setPassword(rs.getString("Password"));
                entity.setFullname(rs.getString("Fullname"));
                entity.setPhoto(rs.getString("Photo"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setBirthday(rs.getDate("Birthday"));
                entity.setSalary(rs.getDouble("Salary"));
                entity.setDepartmentID(rs.getString("DepartmentID"));
                entities.add(entity);
            }
            return entities;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findById(String id) {
        String sql = "SELECT * FROM Employees WHERE Id = ?";
        try {
            ResultSet rs = Jdbc.executeQuery(sql, id);
            if (rs.next()) {
                Employee entity = new Employee();
                entity.setId(rs.getString("Id"));
                entity.setPassword(rs.getString("Password"));
                entity.setFullname(rs.getString("Fullname"));
                entity.setPhoto(rs.getString("Photo"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setBirthday(rs.getDate("Birthday"));
                entity.setSalary(rs.getDouble("Salary"));
                entity.setDepartmentID(rs.getString("DepartmentID"));
                return entity;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Not found");
    }

    @Override
    public void create(Employee entity) {
        String sql = "INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentID) VALUES (?,?,?,?,?,?,?,?)";
        try {
            Object[] values = {
                entity.getId(),
                entity.getPassword(),
                entity.getFullname(),
                entity.getPhoto(),
                entity.isGender(),
                entity.getBirthday(),
                entity.getSalary(),
                entity.getDepartmentID()
            };
            Jdbc.executeUpdate(sql, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee entity) {
        String sql = "UPDATE Employees SET Password=?, Fullname=?, Photo=?, Gender=?, Birthday=?, Salary=?, DepartmentID=? WHERE Id=?";
        try {
            Object[] values = {
                entity.getPassword(),
                entity.getFullname(),
                entity.getPhoto(),
                entity.isGender(),
                entity.getBirthday(),
                entity.getSalary(),
                entity.getDepartmentID(),
                entity.getId()
            };
            Jdbc.executeUpdate(sql, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM Employees WHERE Id=?";
        try {
            Jdbc.executeUpdate(sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}