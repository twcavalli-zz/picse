package com.thomas.pice.picse.remote.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thomas on 14/04/2017.
 */

public class Tweet {
    @SerializedName("text")
    private String text;
    @SerializedName("Entities")
    private Entities Entities;
    @SerializedName("User")
    private User user;
    @Override
    public String  toString(){
        return text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Entities getEntities() {
        return Entities;
    }
    public void setEntities(Entities Entities) {
        this.Entities = Entities;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}