package com.thomas.pice.picse.remote.object;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Thomas on 14/04/2017.
 */

public class media implements Parcelable{
    @SerializedName("media_url")
    private String media_url;
    private String tweet_text;
    private String person_name;
    public String getMedia_url() {
        return media_url;
    }
    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }
    public String getTweet_text() {
        return tweet_text;
    }
    public void setTweet_text(String tweet_text) {
        this.tweet_text = tweet_text;
    }
    public String getPerson_name() {
        return person_name;
    }
    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    private media(Parcel in) {
        media_url = in.readString();
        tweet_text = in.readString();
        person_name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(media_url);
        dest.writeString(tweet_text);
        dest.writeString(person_name);
    }

    public static final Parcelable.Creator<media> CREATOR = new Parcelable.Creator<media>() {
        public media createFromParcel(Parcel in) {
            return new media(in);
        }

        public media[] newArray(int size) {
            return new media[size];
        }
    };
}
