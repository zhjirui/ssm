package com.demo.service;

import com.demo.domain.Account;

import java.util.List;

public interface AccountService {
    List query();
    Integer insert(Account account) throws Exception;
}