package com.example.farmguide.moviedb.ui.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.farmguide.moviedb.usecases.GetMoviesUseCase;



public class MainViewModelFactory implements ViewModelProvider.Factory {

    private GetMoviesUseCase getMoviesUseCase;
    public MainViewModelFactory(GetMoviesUseCase getMoviesUseCase){
        this.getMoviesUseCase = getMoviesUseCase;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(getMoviesUseCase);
        }
        throw new IllegalStateException("MainViewModelClass Exception");
    }
}
