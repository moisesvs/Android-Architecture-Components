package com.example.android.persistence.inject.component;

import com.example.android.persistence.AndroidComponentsApp;
import com.example.android.persistence.inject.module.AppModule;
import com.example.android.persistence.inject.module.BuildersModule;
import com.example.android.persistence.inject.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        BuildersModule.class})
public interface AppComponent {
   @Component.Builder
   interface Builder {

      @BindsInstance
      Builder application(AndroidComponentsApp application);
      AppComponent build();
   }

   void inject(AndroidComponentsApp app);
}