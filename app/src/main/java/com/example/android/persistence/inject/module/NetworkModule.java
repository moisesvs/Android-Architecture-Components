package com.example.android.persistence.inject.module;

import com.example.android.persistence.inject.component.MainSubComponent;
import com.example.android.persistence.inject.component.ProductListSubComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = {MainSubComponent.class, ProductListSubComponent.class})
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://httpbin.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}