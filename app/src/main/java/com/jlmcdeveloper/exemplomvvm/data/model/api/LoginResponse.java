package com.jlmcdeveloper.exemplomvvm.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jlmcdeveloper.exemplomvvm.data.model.LoggedInUser;

public class LoginResponse {

    @Expose
    @SerializedName("access_token")
    private String accessToken;

    @Expose
    @SerializedName("user")
    private LoggedInUser user;



    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LoggedInUser getUser() {
        return user;
    }

    public void setUser(LoggedInUser user) {
        this.user = user;
    }
}
