package com.wangquocthai.java3_jsp_servlet.ASM.dao;

import com.wangquocthai.java3_jsp_servlet.ASM.model.Newsletter;
import java.util.List;

public interface NewsletterDAO {
    int insert(Newsletter newsletter) throws Exception;
    int update(Newsletter newsletter) throws Exception;
    int deleteById(String email) throws Exception;
    List<Newsletter> findAll() throws Exception;
    Newsletter findById(String email) throws Exception;
}
