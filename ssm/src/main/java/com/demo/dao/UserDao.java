package com.demo.dao;

import com.demo.dao.entity.UserEntity;

public interface UserDao {
        /**
         * 插入用户记录
         *
         * @param userEntity
         * @return
         */
        Integer insert(UserEntity userEntity);

        /**
         * 删除用户记录
         *
         * @param id
         * @return
         */
        Integer delete(Integer id);

        /**
         * 更新用户记录
         *
         * @param userEntity
         * @return
         */
        Integer update(UserEntity userEntity);

        /**
         * 根据用户id查找用户
         *
         * @param id
         * @return
         */
        UserEntity selectById(String id);
}