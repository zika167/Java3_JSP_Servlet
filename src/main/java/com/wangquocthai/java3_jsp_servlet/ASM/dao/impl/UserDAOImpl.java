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
    String sql = "INSERT INTO JV3_USERS(ID, PASSWORD, FULLNAME, BIRTHDAY, GENDER, MOBILE, EMAIL, ROLE, ACTIVE) VALUES(?,?,?,?,?,?,?,?,?)";
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
                , user.isActive() ? 1 : 0
        );
    }

    @Override
    public int update(User user) throws Exception {
    String sql = "UPDATE JV3_USERS SET PASSWORD=?, FULLNAME=?, BIRTHDAY=?, GENDER=?, MOBILE=?, EMAIL=?, ROLE=?, ACTIVE=? WHERE ID=?";
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
                , user.isActive() ? 1 : 0
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
    String sql = "SELECT ID, PASSWORD, FULLNAME, BIRTHDAY, GENDER, MOBILE, EMAIL, ROLE, ACTIVE FROM JV3_USERS";
        ResultSet rs = Jdbc.executeQuery(sql);
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getString("id"));
            u.setPassword(rs.getString("password"));
            u.setFullname(rs.getString("fullname"));
            Timestamp ts = rs.getTimestamp("birthday");
            if (ts != null) u.setBirthday(new Date(ts.getTime()));
            u.setGender(rs.getString("gender"));
            u.setMobile(rs.getString("mobile"));
            u.setEmail(rs.getString("email"));
            u.setRole(rs.getString("role"));
            u.setActive(rs.getInt("active") == 1);
            list.add(u);
        }
        rs.getStatement().getConnection().close();
        return list;
    }

    @Override
    public User findById(String id) throws Exception {
    String sql = "SELECT ID, PASSWORD, FULLNAME, BIRTHDAY, GENDER, MOBILE, EMAIL, ROLE, ACTIVE FROM JV3_USERS WHERE ID=?";
        ResultSet rs = Jdbc.executeQuery(sql, id);
        if (rs.next()) {
            User u = new User();
            u.setId(rs.getString("id"));
            u.setPassword(rs.getString("password"));
            u.setFullname(rs.getString("fullname"));
            Timestamp ts = rs.getTimestamp("birthday");
            if (ts != null) u.setBirthday(new Date(ts.getTime()));
            u.setGender(rs.getString("gender"));
            u.setMobile(rs.getString("mobile"));
            u.setEmail(rs.getString("email"));
            u.setRole(rs.getString("role"));
            u.setActive(rs.getInt("active") == 1);
            rs.getStatement().getConnection().close();
            return u;
        }
        rs.getStatement().getConnection().close();
        return null;
    }
}
