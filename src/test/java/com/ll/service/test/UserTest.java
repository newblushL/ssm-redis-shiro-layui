package com.ll.service.test;

import com.ll.entity.UserInfo;
import com.ll.service.BaseTest;
import com.ll.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUserByuserName(){
        UserInfo user = userService.getUserByUserName("admin");
        if(user != null){
            System.out.println(user.getId());
        }
    }
}
