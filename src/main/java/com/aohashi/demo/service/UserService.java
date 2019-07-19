package com.aohashi.demo.service;

import com.aohashi.demo.entity.User;
import com.github.pagehelper.Page;

public interface UserService {

    int saveUser(User user);

    User findUserByName(String username);

    User findUserByID(int id);

    User findUserByEmail(String email);

    int resetPassword(User user);

    int confirmPassword(String password);

    Page<User> findUsers(int page);


}
