package com.demo.service;

import com.demo.dao.entity.UserEntity;

public interface UserService {
    Integer createUser(UserEntity userEntity);

    Integer updateUser(UserEntity userEntity);

    Integer deleteUser(Integer id);

    UserEntity getUserById(String id);
}