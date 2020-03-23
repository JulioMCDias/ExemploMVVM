package com.jlmcdeveloper.exemplomvvm.di.builder;

import com.jlmcdeveloper.exemplomvvm.ui.main.MainActivity;
import com.jlmcdeveloper.exemplomvvm.ui.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

}
