package com.jlmcdeveloper.exemplomvvm.ui.main;


import androidx.databinding.ObservableField;

import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;

public class MainItemViewModel {
    public final ObservableField<String> title;
    public final ObservableField<String> description;
    private Long noteId;
    private NoteAdapter.Listener listener;

    public MainItemViewModel(Note note, NoteAdapter.Listener listener) {
        title = new ObservableField<>(note.getTitle());
        description = new ObservableField<>(note.getDescription());
        this.noteId = note.getNoteID();
        this.listener = listener;
    }


    public void onClickNote(){
        listener.onClickNote(noteId);
    }
}
