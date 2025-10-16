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
    public List<News> findWithPagination(int pageNumber, int pageSize) throws Exception {
        // Oracle-specific pagination using ROWNUM
        String sql = "SELECT * FROM (SELECT a.*, ROWNUM rnum FROM (SELECT * FROM JV3_NEWS ORDER BY POSTEDDATE DESC) a WHERE ROWNUM <= ?) WHERE rnum > ?";
        int maxRows = pageNumber * pageSize;
        int minRows = (pageNumber - 1) * pageSize;
        
        List<News> list = new ArrayList<>();
        ResultSet rs = Jdbc.executeQuery(sql, maxRows, minRows);
        while (rs.next()) {
            News news = new News();
            news.setId(rs.getString("ID"));
            news.setTitle(rs.getString("TITLE"));
            news.setContent(rs.getString("CONTENT"));
            news.setImage(rs.getString("IMAGE"));
            
            Timestamp ts = rs.getTimestamp("POSTEDDATE");
            news.setPostedDate(ts != null ? new Date(ts.getTime()) : null);
            
            news.setAuthor(rs.getString("AUTHOR"));
            news.setViewCount(rs.getInt("VIEWCOUNT"));
            news.setCategoryId(rs.getString("CATEGORYID"));
            news.setHome(String.valueOf(rs.getInt("HOME")));
            list.add(news);
        }
        return list;
    }

    @Override
    public int countTotalNews() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM JV3_NEWS";
        ResultSet rs = Jdbc.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
    }

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
            n.setId(rs.getString("ID"));
            n.setTitle(rs.getString("TITLE"));
            n.setContent(rs.getString("CONTENT"));
            n.setImage(rs.getString("IMAGE"));
            Timestamp ts = rs.getTimestamp("POSTEDDATE");
            if (ts != null) n.setPostedDate(new Date(ts.getTime()));
            n.setAuthor(rs.getString("AUTHOR"));
            n.setViewCount(rs.getInt("VIEWCOUNT"));
            n.setCategoryId(rs.getString("CATEGORYID"));
            n.setHome(rs.getString("HOME"));
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
            n.setId(rs.getString("ID"));
            n.setTitle(rs.getString("TITLE"));
            n.setContent(rs.getString("CONTENT"));
            n.setImage(rs.getString("IMAGE"));
            Timestamp ts = rs.getTimestamp("POSTEDDATE");
            if (ts != null) n.setPostedDate(new Date(ts.getTime()));
            n.setAuthor(rs.getString("AUTHOR"));
            n.setViewCount(rs.getInt("VIEWCOUNT"));
            n.setCategoryId(rs.getString("CATEGORYID"));
            n.setHome(rs.getString("HOME"));
            rs.getStatement().getConnection().close();
            return n;
        }
        rs.getStatement().getConnection().close();
        return null;
    }
}
