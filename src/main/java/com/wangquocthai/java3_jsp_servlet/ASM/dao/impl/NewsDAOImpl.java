package com.wangquocthai.java3_jsp_servlet.ASM.dao.impl;

import com.wangquocthai.java3_jsp_servlet.ASM.dao.NewsDAO;
import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import com.wangquocthai.java3_jsp_servlet.ASM.utils.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsDAOImpl implements NewsDAO {

    @Override
    public int insert(News news) throws Exception {
        String sql = "INSERT INTO JV3_NEWS(ID, TITLE, CONTENT, IMAGE, POSTEDDATE, AUTHOR, CATEGORYID, HOME) VALUES(?,?,?,?,?,?,?,?)";
        Timestamp ts = (news.getPostedDate() == null) ? null : new Timestamp(news.getPostedDate().getTime());
        return Jdbc.executeUpdate(sql,
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getImage(),
                ts,
                news.getAuthor(),
                news.getCategoryId(),
                news.getHome()
        );
    }

    @Override
    public int update(News news) throws Exception {
        String sql = "UPDATE JV3_NEWS SET TITLE=?, CONTENT=?, IMAGE=?, CATEGORYID=?, HOME=? WHERE ID=?";
        return Jdbc.executeUpdate(sql,
                news.getTitle(),
                news.getContent(),
                news.getImage(),
                news.getCategoryId(),
                news.getHome(),
                news.getId()
        );
    }

    @Override
    public int deleteById(String id) throws Exception {
        String sql = "DELETE FROM JV3_NEWS WHERE ID=?";
        return Jdbc.executeUpdate(sql, id);
    }

    @Override
    public List<News> findAll() throws Exception {
        String sql = "SELECT * FROM JV3_NEWS ORDER BY POSTEDDATE DESC";
        try (ResultSet rs = Jdbc.executeQuery(sql)) {
            return mapResultSetToNewsList(rs);
        }
    }

    @Override
    public News findById(String id) throws Exception {
        String sql = "SELECT * FROM JV3_NEWS WHERE ID=?";
        try (ResultSet rs = Jdbc.executeQuery(sql, id)) {
            if (rs.next()) {
                return mapRowToNews(rs);
            }
        }
        return null;
    }

    @Override
    public List<News> findByAuthor(String authorId) throws Exception {
        String sql = "SELECT * FROM JV3_NEWS WHERE AUTHOR = ? ORDER BY POSTEDDATE DESC";
        try (ResultSet rs = Jdbc.executeQuery(sql, authorId)) {
            return mapResultSetToNewsList(rs);
        }
    }

    @Override
    public String generateNextId() throws Exception {
        String sql = "SELECT MAX(SUBSTR(ID, 5)) FROM JV3_NEWS WHERE ID LIKE 'NEWS%'";
        int nextNum = 1;
        try (ResultSet rs = Jdbc.executeQuery(sql)) {
            if (rs.next()) {
                String lastNumStr = rs.getString(1);
                if (lastNumStr != null) {
                    nextNum = Integer.parseInt(lastNumStr) + 1;
                }
            }
        }
        return String.format("NEWS%03d", nextNum);
    }

    @Override
    public int countTotalNews() throws Exception {
        String sql = "SELECT COUNT(*) FROM JV3_NEWS";
        try (ResultSet rs = Jdbc.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public List<News> findWithPagination(int pageNumber, int pageSize) throws Exception {
        String sql = "SELECT * FROM JV3_NEWS ORDER BY POSTEDDATE DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        int offset = Math.max(0, (pageNumber - 1) * pageSize);
        try (ResultSet rs = Jdbc.executeQuery(sql, offset, pageSize)) {
            return mapResultSetToNewsList(rs);
        }
    }

    @Override
    public int countTotalNewsByCategory(String categoryId) throws Exception {
        String sql = "SELECT COUNT(*) FROM JV3_NEWS WHERE CATEGORYID = ?";
        try (ResultSet rs = Jdbc.executeQuery(sql, categoryId)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public List<News> findByCategoryWithPagination(String categoryId, int pageNumber, int pageSize) throws Exception {
        String sql = "SELECT * FROM JV3_NEWS WHERE CATEGORYID = ? ORDER BY POSTEDDATE DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        int offset = Math.max(0, (pageNumber - 1) * pageSize);
        try (ResultSet rs = Jdbc.executeQuery(sql, categoryId, offset, pageSize)) {
            return mapResultSetToNewsList(rs);
        }
    }

    private News mapRowToNews(ResultSet rs) throws SQLException {
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
        news.setHome(rs.getString("HOME"));
        return news;
    }

    private List<News> mapResultSetToNewsList(ResultSet rs) throws SQLException {
        List<News> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRowToNews(rs));
        }
        return list;
    }

    @Override
    public List<News> findMostViewed(int limit) throws Exception {
        String sql = "SELECT * FROM JV3_NEWS " +
                "ORDER BY VIEWCOUNT DESC " +
                "FETCH FIRST ? ROWS ONLY";
        ResultSet rs = Jdbc.executeQuery(sql, limit);
        return mapResultSetToNewsList(rs);
    }

    @Override
    public List<News> findNewsByIds(List<String> ids) throws Exception {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        
        // Create placeholders for IN clause
        String placeholders = String.join(",", ids.stream().map(id -> "?").toArray(String[]::new));
        String sql = "SELECT * FROM JV3_NEWS WHERE ID IN (" + placeholders + ")";
        
        ResultSet rs = Jdbc.executeQuery(sql, ids.toArray());
        List<News> allNews = mapResultSetToNewsList(rs);
        
        // Maintain the order of IDs as passed in (LIFO order)
        List<News> orderedNews = new ArrayList<>();
        for (String id : ids) {
            for (News news : allNews) {
                if (news.getId().equals(id)) {
                    orderedNews.add(news);
                    break;
                }
            }
        }
        
        return orderedNews;
    }
}
