package com.thomas.pice.picse.remote.object;

import android.util.Base64;

/**
 * Created by Thomas on 14/04/2017.
 */

public class Secrets {
    private String ENDPOINT = "https://api.twitter.com/";
    private String CONSUMER_KEY = "YOUR_CONSUMER_KEY";
    private String CONSUMER_SECRET = "YOUR_CONSUMER_SECRET";
    private String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";
    private String GRANT_TYPE = "client_credentials";
    private String ENCODED_BEARER = "Basic " + Base64.encodeToString((CONSUMER_KEY + ":" + CONSUMER_SECRET).getBytes(),Base64.NO_WRAP);
    public String getENDPOINT() { return ENDPOINT; }
    public String getCONTENT_TYPE() {
        return CONTENT_TYPE;
    }
    public String getGRANT_TYPE() {
        return GRANT_TYPE;
    }
    public String getENCODED_BEARER() {
        return ENCODED_BEARER;
    }
}
