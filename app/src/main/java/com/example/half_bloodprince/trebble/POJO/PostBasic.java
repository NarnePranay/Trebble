package com.example.half_bloodprince.trebble.POJO;

/**
 * Created by j.girish on 25-08-2018.
 */

public class PostBasic {


     String heading;
     String name;
     String date;
     int views;
     String time;
     String rank;
     int reply_count;

    public PostBasic( String heading,String name, String date, int views,String time,String rank, int reply_count)
    {
        this.date=date;
        this.heading=heading;
        this.name=name;
        this.views=views;
        this.time=time;
        this.rank=rank;
        this.reply_count=reply_count;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }
}
