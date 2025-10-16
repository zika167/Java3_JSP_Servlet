package com.wangquocthai.java3_jsp_servlet.ASM.dao.impl;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsletterDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.model.Newsletter;
import com.wangquocthai.java3_jsp_servlet.ASM.utils.Jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NewsletterDAOImpl implements NewsletterDAO {

    @Override
    public int insert(Newsletter newsletter) throws Exception {
        String sql = "INSERT INTO Newsletter(email, enabled) VALUES(?,?)";
        return Jdbc.executeUpdate(sql, newsletter.getEmail(), newsletter.isEnabled() ? 1 : 0);
    }

    @Override
    public int update(Newsletter newsletter) throws Exception {
        String sql = "UPDATE Newsletter SET enabled=? WHERE email=?";
        return Jdbc.executeUpdate(sql, newsletter.isEnabled() ? 1 : 0, newsletter.getEmail());
    }

    @Override
    public int deleteById(String email) throws Exception {
        String sql = "DELETE FROM Newsletter WHERE email=?";
        return Jdbc.executeUpdate(sql, email);
    }

    @Override
    public List<Newsletter> findAll() throws Exception {
        String sql = "SELECT email, enabled FROM Newsletter";
        ResultSet rs = Jdbc.executeQuery(sql);
        List<Newsletter> list = new ArrayList<>();
        while (rs.next()) {
            Newsletter n = new Newsletter();
            n.setEmail(rs.getString("email"));
            n.setEnabled(rs.getInt("enabled") == 1);
            list.add(n);
        }
        rs.getStatement().getConnection().close();
        return list;
    }

    @Override
    public Newsletter findById(String email) throws Exception {
        String sql = "SELECT email, enabled FROM Newsletter WHERE email=?";
        ResultSet rs = Jdbc.executeQuery(sql, email);
        if (rs.next()) {
            Newsletter n = new Newsletter();
            n.setEmail(rs.getString("email"));
            n.setEnabled(rs.getInt("enabled") == 1);
            rs.getStatement().getConnection().close();
            return n;
        }
        rs.getStatement().getConnection().close();
        return null;
    }
}
