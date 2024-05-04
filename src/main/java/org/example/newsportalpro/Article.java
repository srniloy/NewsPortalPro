package org.example.newsportalpro;

import java.io.Serializable;

class Article implements Serializable {
    String title;
    String desc;
    String imgPath;
    String newsType;
    String writerName;
    String publishedDate;
    Article (String title, String desc, String imgPath, String newsType, String writerName, String publishedDate){
        this.title = title;
        this.desc = desc;
        this.imgPath = imgPath;
        this.newsType = newsType;
        this.writerName = writerName;
        this.publishedDate = publishedDate;
    }
    void print(){
        System.out.println(title);
        System.out.println(desc);
        System.out.println(imgPath);
        System.out.println(newsType);
        System.out.println(writerName);
        System.out.println(publishedDate);
    }
}