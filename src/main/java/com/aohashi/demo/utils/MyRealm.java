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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class MyRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = JWTUtil.getUsername(principals.toString());
        User user = userService.findUserByName(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (SystemRole role:user.getRoles()){
            for (SystemPermission permission:role.getPermissions()){
                authorizationInfo.addStringPermission(permission.getName());
            }
            authorizationInfo.addRole(role.getRole());
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
        if (user==null){
            throw new AuthenticationException("User didn't existed!");
        }
        if (!JWTUtil.verify(token,username,user.getPassword())){
            throw new AuthenticationException("Password Error!");
        }
        /// 获取当前线程的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("user",user);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(token,token,"my_realm");
        return authenticationInfo;

    }


}
