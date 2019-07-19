package com.aohashi.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;


public class Video implements Serializable {

    private int vid;
    private String title;
    private String introduction;
    private String img;
    private String url;
    private String category;
    private int year;
    private Timestamp addTime;

    public Video() {
    }


    public Video(int vid, String title, String introduction, String img, String url, String category, int year,
                 Timestamp addTime) {
        this.vid = vid;
        this.title = title;
        this.introduction = introduction;
        this.img = img;
        this.url = url;
        this.category = category;
        this.year = year;
        this.addTime = addTime;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }


    @Override
    public String toString() {
        return "Video{" + "vid=" + vid + ", title='" + title + '\'' + ", introduction='" + introduction + '\''
               + ", img='" + img + '\'' + ", url='" + url + '\'' + ", category='" + category + '\'' + ", year=" + year
               + ", addTime=" + addTime + '}';
    }
}
