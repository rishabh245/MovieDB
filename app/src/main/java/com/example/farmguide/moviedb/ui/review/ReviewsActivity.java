package com.example.farmguide.moviedb.ui.review;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.farmguide.moviedb.R;
import com.example.farmguide.moviedb.data.api.ApiHelper;
import com.example.farmguide.moviedb.data.db.Review;
import com.example.farmguide.moviedb.usecases.GetReviewsUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ReviewsActivity extends AppCompatActivity {
    @Inject ReviewModelFactory reviewModelFactory;
    ReviewViewModel reviewViewModel;
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        AndroidInjection.inject(this);
        Intent intent = getIntent();
        movieId = intent.getIntExtra("MOVIEID" , 0);
        reviewViewModel = ViewModelProviders.of(this , reviewModelFactory).get(ReviewViewModel.class);

        reviewViewModel.getReviewObservable().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(@Nullable List<Review> reviews) {
                Log.d("tagg" , "hello");
                Log.d("tagg" , reviews.size()+"");
            }
        });
        reviewViewModel.loadData(movieId);
    }
}
