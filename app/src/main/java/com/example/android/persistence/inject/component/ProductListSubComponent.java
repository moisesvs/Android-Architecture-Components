package com.example.android.persistence.inject.component;


import com.example.android.persistence.ProductListFragment;
import com.example.android.persistence.inject.PerFragment;
import com.example.android.persistence.inject.module.MainActivityModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by BBVA on 19/4/17.
 */
@PerFragment
@Subcomponent(modules = {MainActivityModule.class})
public interface ProductListSubComponent extends AndroidInjector<ProductListFragment> {
    @Subcomponent.Builder
    abstract class BuilderFragment extends AndroidInjector.Builder<ProductListFragment> {
    }
}