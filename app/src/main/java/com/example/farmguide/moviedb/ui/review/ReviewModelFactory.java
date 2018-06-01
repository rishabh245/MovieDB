package com.example.farmguide.moviedb.ui.review;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.farmguide.moviedb.usecases.GetReviewsUseCase;

public class ReviewModelFactory implements ViewModelProvider.Factory {
    private GetReviewsUseCase getReviewsUseCase;
    private int movieId;

    public ReviewModelFactory(GetReviewsUseCase getReviewsUseCase, int moviewId) {
        this.getReviewsUseCase = getReviewsUseCase;
        this.movieId = moviewId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ReviewViewModel.class)){
            return (T) new ReviewViewModel(getReviewsUseCase, movieId);
        }
        throw new IllegalStateException("MainViewModelClass Exception");
    }
}
