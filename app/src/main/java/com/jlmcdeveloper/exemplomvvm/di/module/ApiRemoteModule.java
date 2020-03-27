package com.jlmcdeveloper.exemplomvvm.di.module;

import android.content.Context;

import com.jlmcdeveloper.exemplomvvm.data.remote.ApiEndPoint;
import com.jlmcdeveloper.exemplomvvm.data.remote.ApiRestServer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiRemoteModule {

    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    @Provides
    @Singleton
    ApiRestServer provideApiRestServer(Retrofit retrofit){
        return retrofit.create(ApiRestServer.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiEndPoint.url)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache){
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }


    @Provides
    @Singleton
    Cache provideCache(Context context){
        return new Cache(context.getCacheDir(), CACHE_SIZE);
    }

}
