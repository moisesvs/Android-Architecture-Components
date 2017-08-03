package com.example.android.persistence.inject.component;


import com.example.android.persistence.MainActivity;
import com.example.android.persistence.inject.PerActivity;
import com.example.android.persistence.inject.module.MainActivityModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by BBVA on 19/4/17.
 */
@PerActivity
@Subcomponent(modules = {MainActivityModule.class})
public interface MainSubComponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }

//    @Subcomponent.Builder
//    abstract class BuilderFragment extends AndroidInjector.Builder<ProductListFragment> {
//    }
}