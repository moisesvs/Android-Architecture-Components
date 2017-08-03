package com.example.android.persistence.inject.module;

import com.example.android.persistence.AndroidComponentsApp;
import com.example.android.persistence.inject.component.MainSubComponent;
import com.example.android.persistence.inject.component.ProductListSubComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = { MainSubComponent.class, ProductListSubComponent.class})
public class AppModule {

    @Provides
    @Singleton
    AndroidComponentsApp providesApplication(AndroidComponentsApp app) {
        return app;
    }

}