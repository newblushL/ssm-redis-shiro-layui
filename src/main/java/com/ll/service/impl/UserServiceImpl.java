package com.ll.service.impl;

import com.ll.dao.UserDao;
import com.ll.entity.UserInfo;
import com.ll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserInfo getUserById(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    public UserInfo getUserByUserName(String userName) {
        return userDao.selectUserInfoByuserName(userName);
    }
}
