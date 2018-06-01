package com.example.farmguide.moviedb.ui.review;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.farmguide.moviedb.R;
import com.example.farmguide.moviedb.data.db.Review;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ReviewsActivity extends AppCompatActivity {
    public static final String KEY_MOVIE_ID = "MOVIEID";
    @Inject
    ReviewModelFactory reviewModelFactory;
    @Inject
    ReviewAdapter reviewAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    private ReviewViewModel reviewViewModel;
    private int movieId;
    private RecyclerView recyclerView;

    public static void start(Context context, int movieId) {
        Intent starter = new Intent(context, ReviewsActivity.class);
        starter.putExtra(KEY_MOVIE_ID, movieId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        AndroidInjection.inject(this);
        recyclerView = findViewById(R.id.recyler_view_reviews);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(reviewAdapter);
        Intent intent = getIntent();
        movieId = intent.getIntExtra(KEY_MOVIE_ID, 0);
        reviewViewModel = ViewModelProviders.of(this, reviewModelFactory).get(ReviewViewModel.class);

        reviewViewModel.getReviewObservable().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(@Nullable List<Review> reviews) {
                reviewAdapter.setReviewList(reviews);
            }
        });
    }
}
