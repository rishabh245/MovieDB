package com.example.farmguide.moviedb.di;

import com.example.farmguide.moviedb.ui.main.MainActivity;
import com.example.farmguide.moviedb.ui.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();
}
