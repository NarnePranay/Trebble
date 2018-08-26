package com.example.half_bloodprince.trebble.POJO;

/**
 * Created by j.girish on 25-08-2018.
 */

public class PostBasic {


     String question;
     String name;
     String date;
     String views;
    public PostBasic( String question,String name, String date, String views)
    {
        this.date=date;
        this.question=question;
        this.name=name;
        this.views=views;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

}
