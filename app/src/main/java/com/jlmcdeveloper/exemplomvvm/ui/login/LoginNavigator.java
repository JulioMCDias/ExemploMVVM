package com.jlmcdeveloper.exemplomvvm.ui.login;

public interface LoginNavigator {

    String getName();

    String getPassword();

    void openMainActivity();

    void handleError(Throwable throwable);

    void handleError( int info);
}
