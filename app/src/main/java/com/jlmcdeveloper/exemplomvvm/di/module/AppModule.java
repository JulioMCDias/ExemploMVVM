package com.jlmcdeveloper.exemplomvvm.di.module;

import android.app.Application;
import android.content.Context;

import com.jlmcdeveloper.exemplomvvm.data.AppDataManager;
import com.jlmcdeveloper.exemplomvvm.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
}
