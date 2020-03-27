package com.jlmcdeveloper.exemplomvvm.di.builder;

import com.jlmcdeveloper.exemplomvvm.ui.main.MainActivity;
import com.jlmcdeveloper.exemplomvvm.ui.login.LoginActivity;
import com.jlmcdeveloper.exemplomvvm.ui.main.MainProvider;
import com.jlmcdeveloper.exemplomvvm.ui.note.NoteActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = MainProvider.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract NoteActivity bindNoteActivity();
}
