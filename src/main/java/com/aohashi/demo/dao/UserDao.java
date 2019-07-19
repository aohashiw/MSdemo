package com.aohashi.demo.dao;

import com.aohashi.demo.entity.User;
import com.github.pagehelper.Page;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int insertUser(User user);

    User selectUserById(int id);
    User selectUserByName(String username);
    User selectUserByEmail(String email);
    int updatePassword(User user);

    Page<User> selectUsers();

}
