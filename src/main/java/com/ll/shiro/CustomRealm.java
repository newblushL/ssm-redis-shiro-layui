package com.ll.shiro;

import com.ll.entity.UserInfo;
import com.ll.service.UserService;
import com.ll.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户名
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // 通过用户名获取用户对象
        UserInfo userInfo = userService.getUserByUserName(userName);
        if (userInfo == null) {
            throw new UnknownAccountException("账号不存在");
        }
        if(!password.equals(userInfo.getPassword())){
            throw new IncorrectCredentialsException("密码不正确");
        }
        password = new Md5Hash(password).toString();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo, password, this.getName());
        return info;
    }
}
