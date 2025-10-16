package com.wangquocthai.java3_jsp_servlet.ASM.dao.impl;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.UserDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import com.wangquocthai.java3_jsp_servlet.ASM.utils.Jdbc;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public int insert(User user) throws Exception {
    String sql = "INSERT INTO JV3_USERS(ID, PASSWORD, FULLNAME, BIRTHDAY, GENDER, MOBILE, EMAIL, ROLE) VALUES(?,?,?,?,?,?,?,?)";
        Timestamp ts = user.getBirthday() == null ? null : new Timestamp(user.getBirthday().getTime());
        return Jdbc.executeUpdate(
                sql
                , user.getId()
                , user.getPassword()
                , user.getFullname()
                , ts
                , user.getGender()
                , user.getMobile()
                , user.getEmail()
                , user.getRole()
        );
    }

    @Override
    public int update(User user) throws Exception {
    String sql = "UPDATE JV3_USERS SET PASSWORD=?, FULLNAME=?, BIRTHDAY=?, GENDER=?, MOBILE=?, EMAIL=?, ROLE=? WHERE ID=?";
        Timestamp ts = user.getBirthday() == null ? null : new Timestamp(user.getBirthday().getTime());
        return Jdbc.executeUpdate(
                sql
                , user.getPassword()
                , user.getFullname()
                , ts
                , user.getGender()
                , user.getMobile()
                , user.getEmail()
                , user.getRole()
                , user.getId()
        );
    }

    @Override
    public int deleteById(String id) throws Exception {
    String sql = "DELETE FROM JV3_USERS WHERE ID=?";
        return Jdbc.executeUpdate(sql, id);
    }

    @Override
    public List<User> findAll() throws Exception {
    String sql = "SELECT ID, PASSWORD, FULLNAME, BIRTHDAY, GENDER, MOBILE, EMAIL, ROLE FROM JV3_USERS";
        ResultSet rs = Jdbc.executeQuery(sql);
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getString("ID"));
            u.setPassword(rs.getString("PASSWORD"));
            u.setFullname(rs.getString("FULLNAME"));
            Timestamp ts = rs.getTimestamp("BIRTHDAY");
            if (ts != null) u.setBirthday(new Date(ts.getTime()));
            u.setGender(rs.getString("GENDER"));
            u.setMobile(rs.getString("MOBILE"));
            u.setEmail(rs.getString("EMAIL"));
            u.setRole(rs.getString("ROLE"));
            // ACTIVE column doesn't exist in DDL; default active to true
            u.setActive(true);
            list.add(u);
        }
        rs.getStatement().getConnection().close();
        return list;
    }

    @Override
    public User findById(String id) throws Exception {
    String sql = "SELECT ID, PASSWORD, FULLNAME, BIRTHDAY, GENDER, MOBILE, EMAIL, ROLE FROM JV3_USERS WHERE ID=?";
        ResultSet rs = Jdbc.executeQuery(sql, id);
        if (rs.next()) {
            User u = new User();
            u.setId(rs.getString("ID"));
            u.setPassword(rs.getString("PASSWORD"));
            u.setFullname(rs.getString("FULLNAME"));
            Timestamp ts = rs.getTimestamp("BIRTHDAY");
            if (ts != null) u.setBirthday(new Date(ts.getTime()));
            u.setGender(rs.getString("GENDER"));
            u.setMobile(rs.getString("MOBILE"));
            u.setEmail(rs.getString("EMAIL"));
            u.setRole(rs.getString("ROLE"));
            u.setActive(true);
            rs.getStatement().getConnection().close();
            return u;
        }
        rs.getStatement().getConnection().close();
        return null;
    }
}
