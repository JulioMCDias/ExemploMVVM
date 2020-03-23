package com.jlmcdeveloper.exemplomvvm.di.component;


import android.app.Application;

import com.jlmcdeveloper.exemplomvvm.AndroidApplication;
import com.jlmcdeveloper.exemplomvvm.di.builder.ActivityBuilder;
import com.jlmcdeveloper.exemplomvvm.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface ApplicationComponent {

    void inject(AndroidApplication androidApplication);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

}
