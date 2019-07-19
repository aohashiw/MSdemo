package com.aohashi.demo.service.Impl;

import com.aohashi.demo.dao.UserDao;
import com.aohashi.demo.entity.User;
import com.aohashi.demo.service.UserService;
import com.aohashi.demo.utils.PasswordHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;



    @Override
    public User findUserByID(int id) {
        System.out.println(userDao.selectUserById(id));
        return userDao.selectUserById(id);
    }
    @Override
    public User findUserByName(String username) {
        return userDao.selectUserByName(username);
    }
    @Override
    public User findUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
    }

    @Override
    public int saveUser(User user) {
        passwordHelper.encryptPassword(user);
        return userDao.insertUser(user);
    }

    @Override
    public int resetPassword(User user) {
        passwordHelper.encryptPassword(user);
        return userDao.updatePassword(user);
    }

    @Override
    public int confirmPassword(String password) {
        return 0;
    }

    @Override
    public Page<User> findUsers(int page) {

        PageHelper.startPage(page, 5);
        return userDao.selectUsers();
    }


}
