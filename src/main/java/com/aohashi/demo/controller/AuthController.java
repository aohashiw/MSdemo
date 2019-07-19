package com.aohashi.demo.controller;

import com.aohashi.demo.entity.User;
import com.aohashi.demo.service.UserService;
import com.aohashi.demo.utils.JWTUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/index")
    public Map<String,Object> index( HttpServletRequest request,@RequestParam(value = "list[]") String[] list) {
        Map result = new HashMap();
        try{
            System.out.println(request.getParameterMap());
             System.out.println(list);
        }catch (Exception e){
            // e.printStackTrace();
            result.put("status","500");
        }
        return result;
    }

    @PostMapping(value = "/admin/register")
    public Map<String,Object> register(HttpServletRequest request) {
        Map result = new HashMap();
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setDepartment(request.getParameter("department"));
        user.setJob(request.getParameter("job"));
        System.out.println(user);
        try{
            userService.saveUser(user);
            result.put("status","200");
            System.out.println(user);

        }catch (Exception e){
           // e.printStackTrace();
            result.put("status","500");
        }
        return result;
    }

    @GetMapping(value = "/u/list")

    public Map<String,Object> listUser(@RequestParam int page){
        Map result = new HashMap();
        Page<User> videos = userService.findUsers(page);
        PageInfo<User> pageInfo = new PageInfo<>(videos);
        result.put("users",pageInfo);
        result.put("status",200);
        return result;
    }

    @GetMapping(value = "/u")
    @RequiresAuthentication
    public ResponseBean User(@RequestParam int id){
        Map result = new HashMap();
        User user = userService.findUserByID(id);
        System.out.println(user);
        result.put("user",user);
        return  new ResponseBean(200,"ok",result);
    }

    @PostMapping("/login")
    public Map<String,Object> doLogin(@RequestParam String username,
                          @RequestParam String password) {
        Map result = new HashMap();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);// 使用登录信息创建令牌;
        Subject subject = SecurityUtils.getSubject();        // 获取Subject单例对象
        try {
            //登录
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            System.out.println("password error!");
        } catch (UnknownAccountException uae) {
            System.out.println("username error!");
        }
        User user = userService.findUserByName(username);
        result.put("token", JWTUtil.createToken(user.getUsername(),user.getPassword()));
        // 设置session
        return result;
    }



    @PostMapping("/reset")
    public Object Reset(@RequestParam String username, @RequestParam String password) {

        // 获取Subject单例对象
        User user = userService.findUserByName(username);
        user.setPassword(password);
        userService.resetPassword(user);
        return "SUCCESS";
    }



}
