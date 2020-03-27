package com.jlmcdeveloper.exemplomvvm.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;

import java.util.List;

public class NoteAllResponse {
    @Expose
    @SerializedName("notes")
    private List<Note> notes;


    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
