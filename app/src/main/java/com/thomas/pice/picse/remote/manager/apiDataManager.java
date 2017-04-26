package com.thomas.pice.picse.remote.manager;

import android.content.Context;
import com.thomas.pice.picse.remote.PicseRemote;
import com.thomas.pice.picse.remote.RetrofitHelper;
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

public class ApiDataManager {
    private final Context context;
    private final PicseRemote remote;
    private Oauth apiOauth;

    // Load remote and Secrets info for get the app token from Twitter oAuth APL
    // To get the token, use APP key and secret
    public ApiDataManager(Context context) {
        this.context = context;
        remote = RetrofitHelper.newPicseRemote();
        Secrets secret = new Secrets();
        remote.refreshAccessToken(secret.getENCODED_BEARER(), secret.getGRANT_TYPE(), secret.getCONTENT_TYPE())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Oauth>() {
                    @Override
                    public void onCompleted() { }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onNext(Oauth value) {
                        apiOauth = value;
                    }
                });
    }

    // Calls Twitter Search API.
    // Important: First need to get the token
    public Observable<Media> startSearch(String word) {
        // Filters will be used on GET request
        Map<String, String> data = new HashMap<>();
        data.put("q", word);
        data.put("filter", "images");
        data.put("result_type", "mixed");
        data.put("count", "50");

        // Get Twitter Search API response, read all the tweets and get all the images from it.
        return remote.searchFor("Bearer " + apiOauth.getAccess_token(), data)
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .concatMap(new Func1<TweetList, Observable<Tweet>>() {
                   @Override
                   public Observable<Tweet> call(TweetList TweetList) {
                       return Observable.from(TweetList.getTweets());
                   }
               }).map(new Func1<Tweet, Media>() {
                    @Override
                    public Media call(Tweet Tweet) {
                        try {
                            Media itemMedia = Tweet.getEntities().getMedias().get(0);
                            if (itemMedia.getMedia_url() != null) {
                                itemMedia.setTweet_text(Tweet.getText());
                                itemMedia.setPerson_name(Tweet.getUser().getScreen_name());
                                return itemMedia;
                            }
                        } catch (Exception e) { }
                        return null;
                    }
                });
    }
}