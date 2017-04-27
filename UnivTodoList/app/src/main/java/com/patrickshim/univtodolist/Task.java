package com.patrickshim.univtodolist;

import java.util.Date;

/**
 * Created by patrickshim on 27/04/2017.
 */

public class Task {

    private String text;
    private Date date;

    public Task() {}

    public Task(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
