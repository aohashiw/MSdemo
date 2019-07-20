package com.aohashi.demo.controller;

import com.aohashi.demo.entity.User;
import com.aohashi.demo.exception.UnauthorizedException;
import com.aohashi.demo.service.UserService;
import com.aohashi.demo.utils.JWTUtil;
import com.aohashi.demo.utils.PasswordHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.spi.oa.OADefault;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/index")
    public Map<String, Object> index(HttpServletRequest request, @RequestParam(value = "list[]") String[] list) {
        Map result = new HashMap();
        try {
            System.out.println(request.getParameterMap());
            System.out.println(list);
        } catch (Exception e) {
            // e.printStackTrace();
            result.put("status", "500");
        }
        return result;
    }

    @PostMapping("/login")
    public ResponseBean doLogin(@RequestParam String username,
                                @RequestParam String password) {
        User user = userService.findUserByName(username);
        if (PasswordHelper.comparePassword(user, password)) {
            JWTUtil.createToken(username, user.getPassword());
            return new ResponseBean(200, "Login success", JWTUtil.createToken(username, user.getPassword()));
        } else {
            throw new UnauthorizedException();
        }

    }

    @PostMapping("/reset")
    public Object Reset(@RequestParam String username, @RequestParam String password) {

        // 获取Subject单例对象
        User user = userService.findUserByName(username);
        user.setPassword(password);
        userService.resetPassword(user);
        return "SUCCESS";
    }

    @PostMapping(value = "/admin/register")
    @RequiresAuthentication
    public Map<String, Object> register(HttpServletRequest request) {
        Map result = new HashMap();
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setDepartment(request.getParameter("department"));
        user.setJob(request.getParameter("job"));
        try {
            userService.saveUser(user);
            result.put("status", "200");
            System.out.println(user);

        } catch (Exception e) {
            // e.printStackTrace();
            result.put("status", "500");
        }
        return result;
    }

    @GetMapping(value = "/u/list")
    @RequiresAuthentication
    public ResponseBean listUser(@RequestParam int page) {
        Map result = new HashMap();
        Page<User> videos = userService.findUsers(page);
        PageInfo<User> pageInfo = new PageInfo<>(videos);

        result.put("users", pageInfo);
        result.put("status", 200);
        return new ResponseBean(200, "ok", result);
    }

    @GetMapping(value = "/u")
    @RequiresAuthentication
    public ResponseBean User(@RequestParam int id, HttpServletRequest request) {
        Map<String, Object> data = new HashMap();
        User user = userService.findUserByID(id);
        data.put("user", user);
        System.out.println(request.getAttribute("user"));
        return new ResponseBean(200, "ok", data);
    }


    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }


}
