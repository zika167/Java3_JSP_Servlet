package com.wangquocthai.java3_jsp_servlet.ASM.dao.impl;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.CategoryDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Category;
import com.wangquocthai.java3_jsp_servlet.ASM.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public int insert(Category category) throws Exception {
        String sql = "INSERT INTO JV3_CATEGORIES(ID, NAME) VALUES(?,?)";
        return Jdbc.executeUpdate(sql, category.getId(), category.getName());
    }

    @Override
    public int update(Category category) throws Exception {
        String sql = "UPDATE JV3_CATEGORIES SET NAME=? WHERE ID=?";
        return Jdbc.executeUpdate(sql, category.getName(), category.getId());
    }

    @Override
    public int deleteById(String id) throws Exception {
        String sql = "DELETE FROM JV3_CATEGORIES WHERE ID=?";
        return Jdbc.executeUpdate(sql, id);
    }

    @Override
    public List<Category> findAll() throws Exception {
        String sql = "SELECT ID, NAME FROM JV3_CATEGORIES";
        ResultSet rs = Jdbc.executeQuery(sql);
        List<Category> list = new ArrayList<>();
        while (rs.next()) {
            Category c = new Category();
            c.setId(rs.getString("id"));
            c.setName(rs.getString("name"));
            list.add(c);
        }
        rs.getStatement().getConnection().close();
        return list;
    }

    @Override
    public Category findById(String id) throws Exception {
        String sql = "SELECT ID, NAME FROM JV3_CATEGORIES WHERE ID=?";
        ResultSet rs = Jdbc.executeQuery(sql, id);
        if (rs.next()) {
            Category c = new Category();
            c.setId(rs.getString("id"));
            c.setName(rs.getString("name"));
            rs.getStatement().getConnection().close();
            return c;
        }
        rs.getStatement().getConnection().close();
        return null;
    }
}
