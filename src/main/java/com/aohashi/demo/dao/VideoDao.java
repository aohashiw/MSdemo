package com.aohashi.demo.dao;

import com.aohashi.demo.entity.Video;
import com.github.pagehelper.Page;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoDao {

    int insertVideo(Video video);


    int updateVideoById(Video video);

    int deleteVideoById(int vid);

    Video selectVideoById(int vid);

    Page<Video> selectVideos();

}
