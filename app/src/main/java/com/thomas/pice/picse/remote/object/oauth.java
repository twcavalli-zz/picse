package com.thomas.pice.picse.remote.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thomas on 14/04/2017.
 */

public class Oauth {
    @SerializedName("token_type")
    private String token_type;
    @SerializedName("access_token")
    private String access_token;
    public String getToken_type() {
        return token_type;
    }
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
