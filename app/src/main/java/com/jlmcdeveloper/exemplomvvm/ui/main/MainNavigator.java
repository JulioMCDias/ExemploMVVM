package com.jlmcdeveloper.exemplomvvm.ui.main;

public interface MainNavigator  {

    void openNote(Long id);

    void handleError(Throwable throwable);

    void handleError( int info);
}
