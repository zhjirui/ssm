package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.dao.entity.UserEntity;
import com.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    public Integer createUser(UserEntity userEntity) {
        return userDao.insert(userEntity);
    }

    public Integer updateUser(UserEntity userEntity) {
        return userDao.update(userEntity);
    }

    public Integer deleteUser(Integer id) {
        return userDao.delete(id);
    }

    public UserEntity getUserById(String id) {
        return userDao.selectById(id);
    }

    public List queryList() {
        Map map = new HashMap();
        List list = userDao.queryForList(map);
        return list;
    }

    public Map queryForObject(String id) {
        Map map = new HashMap();
        HashMap resultMap = (HashMap) userDao.queryForObject(map);
        return resultMap;
    }

}