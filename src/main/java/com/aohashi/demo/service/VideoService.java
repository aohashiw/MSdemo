package com.aohashi.demo.service;

import com.aohashi.demo.entity.Video;
import com.github.pagehelper.Page;


public interface VideoService {

     int addVideo(Video video);

     int updateVideo(Video video);

     int deleteVideoById(int vid);

     Video findVideoById(int vid);

     Page<Video> findVideos(int page);


}
