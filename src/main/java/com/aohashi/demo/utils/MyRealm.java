package com.aohashi.demo.utils;

import com.aohashi.demo.entity.SystemPermission;
import com.aohashi.demo.entity.SystemRole;
import com.aohashi.demo.entity.User;
import com.aohashi.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 根据用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        System.out.println(username);
        User user = userService.findUserByName(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        for (SystemRole role:user.getRoles()){
            for (SystemPermission permission:role.getPermissions()){
                authorizationInfo.addStringPermission(permission.getName());
            }
        }
        return authorizationInfo;
    }

    /**
     * 根据账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        String token = (String)authenticationToken.getCredentials();
        String username = JWTUtil.getUsername(token);
        if (username == null){
            throw new AuthenticationException("Token invalid");
        }
        User user = userService.findUserByName(username);
        System.out.println(user);
        if (user==null){
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JWTUtil.verify(token,username,user.getPassword())){
            throw new AuthenticationException("Password Error!");
        }

        return new SimpleAuthenticationInfo(token,token,"my_realm");
    }
}
