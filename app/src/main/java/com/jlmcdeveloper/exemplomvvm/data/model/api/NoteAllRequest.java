package com.jlmcdeveloper.exemplomvvm.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoteAllRequest {

    @Expose
    @SerializedName("access_token")
    private String accessToken;

    @Expose
    @SerializedName("user_id")
    private Long userId;


    public NoteAllRequest(String accessToken, Long userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
