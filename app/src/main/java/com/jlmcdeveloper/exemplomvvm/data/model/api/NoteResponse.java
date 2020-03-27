package com.jlmcdeveloper.exemplomvvm.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;

public class NoteResponse {

    @Expose
    @SerializedName("note")
    private Note note;


    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
