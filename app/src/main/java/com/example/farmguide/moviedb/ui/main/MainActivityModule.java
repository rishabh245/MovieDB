package com.example.farmguide.moviedb.ui.main;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.example.farmguide.moviedb.usecases.GetMoviesUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {


    @Provides
    MainViewModelFactory mainViewModelFactory(GetMoviesUseCase getMoviesUseCase){
        return new MainViewModelFactory(getMoviesUseCase);
    }

    @Provides
    LinearLayoutManager providesLinearLayoutManager(MainActivity mainActivity){
        return new LinearLayoutManager(mainActivity);
    }
}
