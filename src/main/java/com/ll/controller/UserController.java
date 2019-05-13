package com.ll.controller;

import com.ll.entity.UserInfo;
import com.ll.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findUserById(@RequestParam("id") String id) {
        UserInfo userInfo = userService.getUserById(Integer.parseInt(id));
        log.info(userInfo.toString());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user",userInfo);
        //System.out.println(userInfo.getUsername()+userInfo.getPassword()+userInfo.getAge()+userInfo.getId());
        return map;
    }
}
