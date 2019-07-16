package com.aohashi.demo;

import com.aohashi.demo.service.VideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private VideoService videoService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1(){
        System.out.println(videoService.findVideoById(1));

    }

}
