package com.thomas.pice.picse.remote.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thomas on 14/04/2017.
 */

public class tweet {
    @SerializedName("text")
    private String text;
    @SerializedName("entities")
    private entities entities;
    @SerializedName("user")
    private  user user;
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
    public com.thomas.pice.picse.remote.object.entities getEntities() {
        return entities;
    }
    public void setEntities(com.thomas.pice.picse.remote.object.entities entities) {
        this.entities = entities;
    }
    public com.thomas.pice.picse.remote.object.user getUser() {
        return user;
    }
    public void setUser(com.thomas.pice.picse.remote.object.user user) {
        this.user = user;
    }
}