package com.wangquocthai.java3_jsp_servlet.ASM.dao;

import com.wangquocthai.java3_jsp_servlet.ASM.model.User;
import java.util.List;

public interface UserDAO {
    int insert(User user) throws Exception;
    int update(User user) throws Exception;
    int deleteById(String id) throws Exception;
    List<User> findAll() throws Exception;
    User findById(String id) throws Exception;
}
