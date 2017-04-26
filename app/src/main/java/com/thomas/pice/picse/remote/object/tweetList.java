package com.thomas.pice.picse.remote.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Thomas on 14/04/2017.
 */

public class TweetList {

    @SerializedName("statuses")
    private List<Tweet> Tweets;
    public List<Tweet> getTweets() {
        return Tweets;
    }
    public void setTweets(List<Tweet> Tweets) {
        this.Tweets = Tweets;
    }
}