package com.thomas.pice.picse.remote.object;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 14/04/2017.
 */

public class tweetList  {

    @SerializedName("statuses")
    private List<tweet> tweets;
    public List<tweet> getTweets() {
        return tweets;
    }
    public void setTweets(List<tweet> tweets) {
        this.tweets = tweets;
    }
}