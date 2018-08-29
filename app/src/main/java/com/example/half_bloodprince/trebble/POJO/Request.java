package com.example.half_bloodprince.trebble.POJO;

/**
 * Created by j.girish on 29-08-2018.
 */

public class Request {
    String category;
    String date;
    int id;
    String name;
    long phone_number;
    String question;
    String time;

    public Request(String category, String date, int id, String name, long phone_number, String question, String time) {
        this.category = category;
        this.date = date;
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.question = question;
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
