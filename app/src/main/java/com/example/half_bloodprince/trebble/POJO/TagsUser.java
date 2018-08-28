package com.example.half_bloodprince.trebble.POJO;

/**
 * Created by j.girish on 28-08-2018.
 */

public class TagsUser {
    int frequency;
    String post_date;
    String search_date;
    double sentiment;

    public TagsUser(int frequency, String post_date, String search_date, double sentiment) {
        this.frequency = frequency;
        this.post_date = post_date;
        this.search_date = search_date;
        this.sentiment = sentiment;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getSearch_date() {
        return search_date;
    }

    public void setSearch_date(String search_date) {
        this.search_date = search_date;
    }

    public double getSentiment() {
        return sentiment;
    }

    public void setSentiment(double sentiment) {
        this.sentiment = sentiment;
    }
}
