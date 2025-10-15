package com.wangquocthai.java3_jsp_servlet.lab7.dao.impl;

import com.wangquocthai.java3_jsp_servlet.lab7.dao.DepartmentDAO;
import com.wangquocthai.java3_jsp_servlet.lab7.entity.Department;
import com.wangquocthai.java3_jsp_servlet.lab7.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Override
    public List<Department> findAll() {
        String sql = "SELECT * FROM Departments";
        try {
            List<Department> entities = new ArrayList<>();
            Object[] values = {};
            ResultSet rs = Jdbc.executeQuery(sql,values);
            while (rs.next()) {
                Department entity = new Department();
                entity.setId(rs.getString("Id"));
                entity.setName(rs.getString("Name"));
                entity.setDescription(rs.getString("Description"));
                entities.add(entity);
            }
            return entities;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department findById(String id) {
        String sql = "SELECT * FROM Departments WHERE Id = ?";
        try {
            Object[] values = {id};
            ResultSet rs = Jdbc.executeQuery(sql,values);
            if (rs.next()) {
                Department entity = new Department();
                entity.setId(rs.getString("Id"));
                entity.setName(rs.getString("Name"));
                entity.setDescription(rs.getString("Description"));
                return entity;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Not found");
    }

    @Override
    public void create(Department entity) {
        String sql = "INSERT INTO Departments(Id, Name, Description) VALUES(?,?,?)";
        try {
            Object[] values = {
                    entity.getId(),
                    entity.getName(),
                    entity.getDescription()
            };
            Jdbc.executeUpdate(sql,values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Department entity) {
        String sql = "UPDATE Departments SET Name=?, Description=? WHERE Id=?";
        try {
            Object[] values = {
                    entity.getName(),
                    entity.getDescription(),
                    entity.getId()
            };
            Jdbc.executeUpdate(sql,values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM Departments WHERE Id=?";
        try {
            Object[] values = {id};
            Jdbc.executeUpdate(sql,values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
