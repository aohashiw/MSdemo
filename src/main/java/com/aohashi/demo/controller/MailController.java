package com.aohashi.demo.controller;

import com.aohashi.demo.utils.RandomCodeUtil;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MailController {

    @Value("${web.upload-path}")
    private String path;

    @Autowired
    private MailSender mailSender;

    @PostMapping(value = "/send")
    public Map<String,Object> sendMail(String email){
        Map result = new HashMap();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("993626547@qq.com");
        message.setTo(email);
        message.setSubject("测试邮件主题");
        message.setText("测试邮件内容" + RandomCodeUtil.GetCode(6));
        mailSender.send(message);
        result.put("status",200);
        return result;
    }


    @PostMapping(value = "/upload")
    public String htmlUpload(@RequestParam("file")MultipartFile file) {
        System.out.println(file);
        String suffix = file.getOriginalFilename().substring((file.getOriginalFilename().lastIndexOf(".")));
        String fileName = "hel"+suffix;
        File serverFile = new File(path + fileName);
        try {
            file.transferTo(serverFile);
        }catch (IllegalStateException|IOException e){
           e.printStackTrace();
        }
        return "d";
    }

}
