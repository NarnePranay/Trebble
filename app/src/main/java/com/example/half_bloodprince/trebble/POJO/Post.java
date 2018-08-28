package com.example.half_bloodprince.trebble.POJO;

import java.io.Serializable;

/**
 * Created by j.girish on 27-08-2018.
 */

public class Post implements Serializable{
    String body;
    String date;
    String heading;
    String name;
    String rank;
    String time;
    int reply_count;
    String tags;
    int views;
    Comment [] replies;


    public Post(String body, String date, String heading, String name, String rank, String time, int reply_count, String tags, int views, Comment[] replies) {
        this.body = body;
        this.date = date;
        this.heading = heading;
        this.name = name;
        this.rank = rank;
        this.time = time;
        this.reply_count = reply_count;
        this.tags = tags;
        this.views = views;
        this.replies = replies;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Comment[] getReplies() {
        return replies;
    }

    public void setReplies(Comment[] replies) {
        this.replies = replies;
    }
}
