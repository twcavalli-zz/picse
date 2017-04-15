package com.thomas.pice.picse.remote.object;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Thomas on 14/04/2017.
 */

public class entities {
    @SerializedName("media")
    private ArrayList<media> medias;
    public ArrayList<media> getMedias() {
        return medias;
    }
    public void setMedias(ArrayList<media> medias) {
        this.medias = medias;
    }
}
