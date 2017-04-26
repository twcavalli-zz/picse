package com.thomas.pice.picse.remote;
import com.thomas.pice.picse.remote.object.Oauth;
import com.thomas.pice.picse.remote.object.TweetList;

import java.util.Map;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Thomas on 14/04/2017.
 */

public interface PicseRemote {

    //Method to grt token
    @FormUrlEncoded
    @POST("oauth2/token")
    Observable<Oauth> refreshAccessToken(@Header("Authorization") String auth,
                                         @Field("grant_type") String grantType,
                                         @Header("Content-Type") String contentType);
    //Method to get searches
    @GET("1.1/Search/tweets.json")
    Observable<TweetList> searchFor(@Header("Authorization") String auth, @QueryMap Map<String, String> options);
}
