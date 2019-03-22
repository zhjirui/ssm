package com.demo.service;

import com.demo.domain.Account;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AccountService {
    PageInfo queryByPage(Integer pageNo, Integer pageSize);
    List query();
    List queryList();
    Integer insert(Account account) throws Exception;
}