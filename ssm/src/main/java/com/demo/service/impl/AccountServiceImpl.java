package com.demo.service.impl;

import com.demo.dao.AccountDao;
import com.demo.dao.entity.AccountEntity;
import com.demo.domain.Account;
import com.demo.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * propagation：传播级别
 * isolation：隔离级别
 * timeout：超时时间
 * readOnly：是否只读
 * rollbackFor：发生哪些异常回滚
 * rollbackForClassName：发生异常回滚的类
 * noRollbackFor：哪些异常不回滚
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService{

    @Resource
    private AccountDao accountDao;

    public Integer insert(Account account) throws Exception{
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(account.getId());
        accountEntity.setUserid(account.getUserId());
        accountEntity.setNum(account.getNum());
        accountDao.insert(accountEntity);
        int i = 10/0;
        return 0;
    }

}