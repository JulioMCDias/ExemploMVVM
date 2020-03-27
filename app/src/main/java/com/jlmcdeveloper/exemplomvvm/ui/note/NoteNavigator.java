package com.jlmcdeveloper.exemplomvvm.ui.note;


public interface NoteNavigator {

    void handleError(Throwable throwable);

    void handleError( int info);

    void closeActivity();

    String getNoteTitle();

    String getNoteDescription();
}
