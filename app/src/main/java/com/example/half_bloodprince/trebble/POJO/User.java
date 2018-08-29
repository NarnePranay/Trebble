package com.example.half_bloodprince.trebble.POJO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by j.girish on 29-08-2018.
 */

public class User {
   String name;
   int post_count;
   String post_date;
   String posts;
   String rank;
   Double sentiment;
   ArrayList<sentiment> arrylist;
   HashMap<String,TagsUser> hm;

   public  User()
   {}
    public User(String name, int post_count, String post_date, String posts, String rank, Double sentiment, HashMap<String, TagsUser> hm,ArrayList<sentiment> arrylist) {
        this.name = name;
        this.post_count = post_count;
        this.post_date = post_date;
        this.posts = posts;
        this.rank = rank;
        this.sentiment = sentiment;
        this.hm = hm;
        this.arrylist=arrylist;
    }

    public ArrayList<com.example.half_bloodprince.trebble.POJO.sentiment> getArrylist() {
        return arrylist;
    }

    public void setArrylist(ArrayList<com.example.half_bloodprince.trebble.POJO.sentiment> arrylist) {
        this.arrylist = arrylist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Double getSentiment() {
        return sentiment;
    }

    public void setSentiment(Double sentiment) {
        this.sentiment = sentiment;
    }

    public HashMap<String, TagsUser> getHm() {
        return hm;
    }

    public void setHm(HashMap<String, TagsUser> hm) {
        this.hm = hm;
    }
}
