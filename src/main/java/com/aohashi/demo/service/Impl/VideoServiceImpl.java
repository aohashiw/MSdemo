package com.aohashi.demo.service.Impl;

import com.aohashi.demo.dao.VideoDao;
import com.aohashi.demo.entity.Video;
import com.aohashi.demo.service.VideoService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public int addVideo(Video video) {
         return videoDao.insertVideo(video);
    }


    @Override
    public int updateVideo(Video video) {
        return videoDao.updateVideoById(video);
    }

    @Override
    public int deleteVideoById(int vid) {
        return videoDao.deleteVideoById(vid);
    }

    @Override
    public Video findVideoById(int vid) {
        return videoDao.selectVideoById(vid);
    }

    @Override
    public Page<Video> findVideos(int page) {
        PageHelper.startPage(page, 5);
        return videoDao.selectVideos();
    }
}
