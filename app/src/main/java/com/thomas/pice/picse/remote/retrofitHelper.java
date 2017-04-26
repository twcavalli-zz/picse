package com.thomas.pice.picse.remote;

import com.thomas.pice.picse.remote.object.Secrets;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Thomas on 14/04/2017.
 */

public class RetrofitHelper {

    public static PicseRemote newPicseRemote() {
        Secrets secret = new Secrets();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(secret.getENDPOINT())
                .build();
        return retrofit.create(PicseRemote.class);
    }
}