package com.jlmcdeveloper.exemplomvvm.di.module;

import android.content.Context;

import com.jlmcdeveloper.exemplomvvm.utils.Constants;
import com.jlmcdeveloper.exemplomvvm.data.local.NotesRoomDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalDatabaseModule {

    @Provides
    String provideDatabaseName(){
        return Constants.DATABASE_NAME;
    }

    @Provides
    NotesRoomDatabase provideNotesRoomDatabase(Context context, String databaseName){
        return NotesRoomDatabase.getDatabase(context, databaseName);
    }
}
