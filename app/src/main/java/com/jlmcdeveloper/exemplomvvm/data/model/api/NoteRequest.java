package com.jlmcdeveloper.exemplomvvm.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;

public class NoteRequest {
    @Expose
    @SerializedName("access_token")
    private String accessToken;

    @Expose
    @SerializedName("note")
    private Note note;

    public NoteRequest(String accessToken, Note note) {
        this.accessToken = accessToken;
        this.note = note;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
