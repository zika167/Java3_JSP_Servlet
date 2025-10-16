package com.wangquocthai.java3_jsp_servlet.ASM.dao;

import com.wangquocthai.java3_jsp_servlet.ASM.model.Category;
import java.util.List;

public interface CategoryDAO {
    int insert(Category category) throws Exception;
    int update(Category category) throws Exception;
    int deleteById(String id) throws Exception;
    List<Category> findAll() throws Exception;
    Category findById(String id) throws Exception;
}
