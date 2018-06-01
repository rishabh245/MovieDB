package com.example.farmguide.moviedb.ui.review;

import android.support.v7.widget.LinearLayoutManager;

import com.example.farmguide.moviedb.usecases.GetReviewsUseCase;

import dagger.Module;
import dagger.Provides;

import static com.example.farmguide.moviedb.ui.review.ReviewsActivity.KEY_MOVIE_ID;

@Module
public class ReviewActivityModule {

    @Provides
    ReviewModelFactory provideReviewModelFactory(GetReviewsUseCase getReviewsUseCase, ReviewsActivity reviewsActivity) {
        int movieId = reviewsActivity.getIntent().getIntExtra(KEY_MOVIE_ID, 0);
        return new ReviewModelFactory(getReviewsUseCase, movieId);
    }
    @Provides
    LinearLayoutManager providesLinearLayoutManager(ReviewsActivity reviewsActivity){
        return new LinearLayoutManager(reviewsActivity);
    }
}
