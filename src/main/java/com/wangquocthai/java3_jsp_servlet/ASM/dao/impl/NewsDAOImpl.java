package com.wangquocthai.java3_jsp_servlet.ASM.dao.impl;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import com.wangquocthai.java3_jsp_servlet.ASM.utils.Jdbc;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {

    @Override
    public int insert(News news) throws Exception {
    String sql = "INSERT INTO JV3_NEWS(ID, TITLE, CONTENT, IMAGE, POSTEDDATE, AUTHOR, VIEWCOUNT, CATEGORYID, HOME) VALUES(?,?,?,?,?,?,?,?,?)";
        Timestamp ts = news.getPostedDate() == null ? null : new Timestamp(news.getPostedDate().getTime());
        return Jdbc.executeUpdate(sql
                , news.getId()
                , news.getTitle()
                , news.getContent()
                , news.getImage()
                , ts
                , news.getAuthor()
                , news.getViewCount()
                , news.getCategoryId()
                , news.getHome()
        );
    }

    @Override
    public int update(News news) throws Exception {
    String sql = "UPDATE JV3_NEWS SET TITLE=?, CONTENT=?, IMAGE=?, POSTEDDATE=?, AUTHOR=?, VIEWCOUNT=?, CATEGORYID=?, HOME=? WHERE ID=?";
        Timestamp ts = news.getPostedDate() == null ? null : new Timestamp(news.getPostedDate().getTime());
        return Jdbc.executeUpdate(
                sql
                , news.getTitle()
                , news.getContent()
                , news.getImage()
                , ts
                , news.getAuthor()
                , news.getViewCount()
                , news.getCategoryId()
                , news.getHome()
                , news.getId()
        );
    }

    @Override
    public int deleteById(String id) throws Exception {
    String sql = "DELETE FROM JV3_NEWS WHERE ID=?";
        return Jdbc.executeUpdate(sql, id);
    }

    @Override
    public List<News> findAll() throws Exception {
    String sql = "SELECT ID, TITLE, CONTENT, IMAGE, POSTEDDATE, AUTHOR, VIEWCOUNT, CATEGORYID, HOME FROM JV3_NEWS";
        ResultSet rs = Jdbc.executeQuery(sql);
        List<News> list = new ArrayList<>();
        while (rs.next()) {
            News n = new News();
            n.setId(rs.getString("id"));
            n.setTitle(rs.getString("title"));
            n.setContent(rs.getString("content"));
            n.setImage(rs.getString("image"));
            Timestamp ts = rs.getTimestamp("posted_date");
            if (ts != null) n.setPostedDate(new Date(ts.getTime()));
            n.setAuthor(rs.getString("author"));
            n.setViewCount(rs.getInt("view_count"));
            n.setCategoryId(rs.getString("category_id"));
            n.setHome(rs.getString("home"));
            list.add(n);
        }
        rs.getStatement().getConnection().close();
        return list;
    }

    @Override
    public News findById(String id) throws Exception {
    String sql = "SELECT ID, TITLE, CONTENT, IMAGE, POSTEDDATE, AUTHOR, VIEWCOUNT, CATEGORYID, HOME FROM JV3_NEWS WHERE ID=?";
        ResultSet rs = Jdbc.executeQuery(sql, id);
        if (rs.next()) {
            News n = new News();
            n.setId(rs.getString("id"));
            n.setTitle(rs.getString("title"));
            n.setContent(rs.getString("content"));
            n.setImage(rs.getString("image"));
            Timestamp ts = rs.getTimestamp("posted_date");
            if (ts != null) n.setPostedDate(new Date(ts.getTime()));
            n.setAuthor(rs.getString("author"));
            n.setViewCount(rs.getInt("view_count"));
            n.setCategoryId(rs.getString("category_id"));
            n.setHome(rs.getString("home"));
            rs.getStatement().getConnection().close();
            return n;
        }
        rs.getStatement().getConnection().close();
        return null;
    }
}
