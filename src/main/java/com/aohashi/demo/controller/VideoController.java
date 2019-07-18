package com.aohashi.demo.controller;

import com.aohashi.demo.entity.Video;
import com.aohashi.demo.service.VideoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("/index")
    public Map<String,Object> hello(){
        Map resulr = new HashMap();
        resulr.put("hello",0);
        return resulr;
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Map<String,Object> addVideo(Video video){
        Map result = new HashMap();
        System.out.println(video);
        videoService.addVideo(video);
        result.put("status",200);
        return result;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Map<String,Object> updateVideo(Video video){
        Map result = new HashMap();
        System.out.println(video);
        result.put("video",videoService.updateVideo(video));
        System.out.println(video);
        result.put("status",200);
        return result;
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Map<String,Object> deleteVideo(int vid){
        Map result = new HashMap();
        result.put("video",videoService.deleteVideoById(vid));
        result.put("status",200);
        return result;
    }

    @RequestMapping(value = "/video/{vid}",method = RequestMethod.GET)
    public Map<String,Object> findVideoById(@PathVariable int vid){
        Map result = new HashMap();
        System.out.println(LocalTime.now());
        result.put("video",videoService.findVideoById(vid));
        System.out.println(videoService.findVideoById(vid));
        result.put("status",200);
        return result;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Map<String,Object> listVideo(@RequestParam int page){
        Map result = new HashMap();
        Page<Video> videos = videoService.findVideos(page);
        PageInfo<Video> pageInfo = new PageInfo<>(videos);
        result.put("videos",pageInfo);
        result.put("status",200);
        return result;
    }


}
