package com.example.half_bloodprince.trebble.POJO;

/**
 * Created by j.girish on 29-08-2018.
 */

public class sentiment {
    double sentiment;
    String date;

    public sentiment(double sentiment, String date) {
        this.sentiment = sentiment;
        this.date = date;
    }

    public double getSentiment() {
        return sentiment;
    }

    public void setSentiment(double sentiment) {
        this.sentiment = sentiment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
