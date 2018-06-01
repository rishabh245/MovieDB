package com.example.farmguide.moviedb.repo;

import com.example.farmguide.moviedb.data.db.Review;

import java.util.List;

import io.reactivex.Flowable;

public interface ReviewRepository {

    Flowable<List<Review>> getReviews(int movieId);
    void insertAll(List<Review> reviews);
}
