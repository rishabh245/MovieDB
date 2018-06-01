package com.example.farmguide.moviedb.ui.review;

import android.support.v7.widget.LinearLayoutManager;

import com.example.farmguide.moviedb.usecases.GetReviewsUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class ReviewActivityModule {

    @Provides
    ReviewModelFactory provideReviewModelFactory(GetReviewsUseCase getReviewsUseCase){
        return new ReviewModelFactory(getReviewsUseCase);
    }
    @Provides
    LinearLayoutManager proviedesLinearLayoutManager(ReviewsActivity reviewsActivity){
        return new LinearLayoutManager(reviewsActivity);
    }
}
