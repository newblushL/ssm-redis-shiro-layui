package com.ll.dao;

import com.ll.entity.UserInfo;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectUserInfoByuserName(String username);
}