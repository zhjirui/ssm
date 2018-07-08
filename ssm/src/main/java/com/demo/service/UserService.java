package com.demo.service;

import com.demo.dao.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    Integer createUser(UserEntity userEntity);

    Integer updateUser(UserEntity userEntity);

    Integer deleteUser(Integer id);

    UserEntity getUserById(String id);

    List queryList();

    Map queryForObject(String id);
}