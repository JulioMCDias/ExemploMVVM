package com.jlmcdeveloper.exemplomvvm.ui.main;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MainProvider {

    @Provides
    NoteAdapter provideNoteAdapter(){
        return new NoteAdapter(new ArrayList<>());
    }

}
