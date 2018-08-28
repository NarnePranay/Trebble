package com.example.half_bloodprince.trebble.POJO;

import java.io.Serializable;

/**
 * Created by j.girish on 27-08-2018.
 */

public class Comment implements Serializable {
    String date;
    String comment;
    String name;
    String rank;
    String time;

    public Comment(String date, String comment, String name, String rank, String time) {
        this.date = date;
        this.comment = comment;
        this.name = name;
        this.rank = rank;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
