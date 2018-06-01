package com.example.farmguide.moviedb.di;

import com.example.farmguide.moviedb.ui.main.MainActivity;
import com.example.farmguide.moviedb.ui.main.MainActivityModule;
import com.example.farmguide.moviedb.ui.review.ReviewActivityModule;
import com.example.farmguide.moviedb.ui.review.ReviewsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules =ReviewActivityModule.class)
    abstract ReviewsActivity bindReviewActivity();
}
