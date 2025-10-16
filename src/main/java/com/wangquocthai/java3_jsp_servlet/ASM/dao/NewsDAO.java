package com.wangquocthai.java3_jsp_servlet.ASM.dao;

import com.wangquocthai.java3_jsp_servlet.ASM.model.News;
import java.util.List;

public interface NewsDAO {
    int insert(News news) throws Exception;
    int update(News news) throws Exception;
    int deleteById(String id) throws Exception;
    List<News> findAll() throws Exception;
    News findById(String id) throws Exception;
}
