package com.example.android.persistence.inject.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.android.persistence.MainActivity;
import com.example.android.persistence.ProductListFragment;
import com.example.android.persistence.inject.component.MainSubComponent;
import com.example.android.persistence.inject.component.ProductListSubComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by BBVA on 19/4/17.
 */
@Module
public abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindLoginActivityInjectorFactory(MainSubComponent.Builder builder);

    @Binds
    @IntoMap
    @dagger.android.support.FragmentKey(ProductListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindProductFragmentInjectorFactory(ProductListSubComponent.BuilderFragment builder);
}