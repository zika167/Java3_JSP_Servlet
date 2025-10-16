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
        String sql = "INSERT INTO JV3_NEWSLETTERS(EMAIL, ENABLED) VALUES(?,?)";
        return Jdbc.executeUpdate(sql, newsletter.getEmail(), newsletter.isEnabled() ? "Y" : "N");
    }

    @Override
    public int update(Newsletter newsletter) throws Exception {
        String sql = "UPDATE JV3_NEWSLETTERS SET ENABLED=? WHERE EMAIL=?";
        return Jdbc.executeUpdate(sql, newsletter.isEnabled() ? "Y" : "N", newsletter.getEmail());
    }

    @Override
    public int deleteById(String email) throws Exception {
        String sql = "DELETE FROM JV3_NEWSLETTERS WHERE EMAIL=?";
        return Jdbc.executeUpdate(sql, email);
    }

    @Override
    public List<Newsletter> findAll() throws Exception {
        String sql = "SELECT EMAIL, ENABLED FROM JV3_NEWSLETTERS";
        ResultSet rs = Jdbc.executeQuery(sql);
        List<Newsletter> list = new ArrayList<>();
        while (rs.next()) {
            Newsletter n = new Newsletter();
            n.setEmail(rs.getString("EMAIL"));
            String v = rs.getString("ENABLED");
            n.setEnabled(v != null && (v.equalsIgnoreCase("Y") || v.equals("1")));
            list.add(n);
        }
        rs.getStatement().getConnection().close();
        return list;
    }

    @Override
    public Newsletter findById(String email) throws Exception {
        String sql = "SELECT EMAIL, ENABLED FROM JV3_NEWSLETTERS WHERE EMAIL=?";
        ResultSet rs = Jdbc.executeQuery(sql, email);
        if (rs.next()) {
            Newsletter n = new Newsletter();
            n.setEmail(rs.getString("EMAIL"));
            String v = rs.getString("ENABLED");
            n.setEnabled(v != null && (v.equalsIgnoreCase("Y") || v.equals("1")));
            rs.getStatement().getConnection().close();
            return n;
        }
        rs.getStatement().getConnection().close();
        return null;
    }
}
