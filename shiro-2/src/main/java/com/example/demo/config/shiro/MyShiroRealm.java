package com.example.demo.config.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.common.StringUtil;
import com.example.demo.entity.Permission;
import com.example.demo.entity.User;
import com.example.demo.service.PermissionService;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: xie
 * Email: 1487471733@qq.com
 * Date: 2017/9/22
 * Time: 9:56
 * Describe:
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final transient Logger log = LoggerFactory.getLogger(ShiroConfig.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user=(User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Permission> permissions = permissionService.findPermissionListByUserId(user.getId());
        List<String> list = new ArrayList<String>();
        for (Permission item: permissions) {
            if (StringUtil.isNotEmpty(item.getPermCode()))
                list.add(item.getPermCode());
        }
        authorizationInfo.addStringPermissions(list);

        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("MyShiroRealm.doGetAuthenticationInfo()");

        //获取用户的输入的账号.
        String username = (String)authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        User user = userService.selectOne(wrapper);
        if (user == null) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),"MyShiroRealm");

        return simpleAuthenticationInfo;
    }
}
