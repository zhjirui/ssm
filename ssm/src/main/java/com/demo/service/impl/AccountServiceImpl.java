package com.demo.service.impl;

import com.demo.domain.Account;
import com.demo.domain.AccountMapper;
import com.demo.domain.CommonMapper;
import com.demo.service.AccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
    private AccountMapper accountMapper;
    @Resource
    private CommonMapper commonMapper;

    public List query() {
        Account account = accountMapper.selectByPrimaryKey(1);
        account.getId();
        List list = new ArrayList();
        Map map = new HashMap();
        map.put(account.getId(),account.getId());
        list.add(map);
        return list;
    }

    public List queryList() {
        List list = commonMapper.queryList();
        return list;
    }

    public Integer insert(Account account) throws Exception{
        Account accountEntity = new Account();
        accountEntity.setId(account.getId());
        accountEntity.setUserid(account.getUserid());
        accountEntity.setNum(account.getNum());
        accountMapper.insert(accountEntity);
        int i = 10/0;
        return 0;
    }

    public PageInfo queryByPage(Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Account> list = accountMapper.getAll();
        //用PageInfo对结果进行包装
        PageInfo page = new PageInfo(list);
        //测试PageInfo全部属性
        System.out.println(page.getPageNum());
        System.out.println(page.getPageSize());
        System.out.println(page.getStartRow());
        System.out.println(page.getEndRow());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.isHasPreviousPage());
        System.out.println(page.isHasNextPage());
        return page;
    }
}