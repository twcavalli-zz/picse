package com.thomas.pice.picse.remote.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thomas on 14/04/2017.
 */

public class user {
    @SerializedName("screen_name")
    private String screen_name;

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }
}
