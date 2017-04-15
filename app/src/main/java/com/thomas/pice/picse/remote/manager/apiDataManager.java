package com.thomas.pice.picse.remote.manager;

import android.content.Context;
import com.thomas.pice.picse.remote.picseRemote;
import com.thomas.pice.picse.remote.retrofitHelper;
import com.thomas.pice.picse.remote.object.*;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Thomas on 14/04/2017.
 *
 * Manage request to oAuth and Twitter API
 */

public class apiDataManager {
    private final Context context;
    private final picseRemote remote;
    private oauth apiOauth;

    // Load remote and secrets info for get the app token from Twitter oAuth APL
    // To get the token, use APP key and secret
    public apiDataManager(Context context) {
        this.context = context;
        remote = retrofitHelper.newPicseRemote();
        secrets secret = new secrets();
        remote.refreshAccessToken(secret.getENCODED_BEARER(), secret.getGRANT_TYPE(), secret.getCONTENT_TYPE())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<oauth>() {
                    @Override
                    public void onCompleted() { }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onNext(oauth value) {
                        apiOauth = value;
                    }
                });
    }

    // Calls Twitter search API.
    // Important: First need to get the token
    public Observable<media> startSearch(String word) {
        // Filters will be used on GET request
        Map<String, String> data = new HashMap<>();
        data.put("q", word);
        data.put("filter", "images");
        data.put("result_type", "mixed");
        data.put("count", "50");

        // Get Twitter search API response, read all the tweets and get all the images from it.
        return remote.searchFor("Bearer " + apiOauth.getAccess_token(), data)
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .concatMap(new Func1<tweetList, Observable<tweet>>() {
                   @Override
                   public Observable<tweet> call(tweetList tweetList) {
                       return Observable.from(tweetList.getTweets());
                   }
               }).map(new Func1<tweet, media>() {
                    @Override
                    public media call(tweet tweet) {
                        try {
                            media itemMedia = tweet.getEntities().getMedias().get(0);
                            if (itemMedia.getMedia_url() != null) {
                                itemMedia.setTweet_text(tweet.getText());
                                itemMedia.setPerson_name(tweet.getUser().getScreen_name());
                                return itemMedia;
                            }
                        } catch (Exception e) { }
                        return null;
                    }
                });
    }
}