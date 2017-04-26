package com.thomas.pice.picse.remote.object;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Thomas on 14/04/2017.
 */

public class Entities {
    @SerializedName("Media")
    private ArrayList<Media> Medias;
    public ArrayList<Media> getMedias() {
        return Medias;
    }
    public void setMedias(ArrayList<Media> Medias) {
        this.Medias = Medias;
    }
}
