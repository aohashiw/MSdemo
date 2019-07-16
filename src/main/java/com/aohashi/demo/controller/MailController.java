package com.aohashi.demo.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailSender mailSender;

    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public Map<String,Object> sendMail(String email){
        Map result = new HashMap();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("993626547@qq.com");
        message.setTo(email);
        message.setSubject("测试邮件主题");
        message.setText("测试邮件内容");
        mailSender.send(message);
        result.put("status",200);
        return result;
    }
}
