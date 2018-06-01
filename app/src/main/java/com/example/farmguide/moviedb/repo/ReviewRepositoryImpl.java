package com.example.farmguide.moviedb.repo;

import com.example.farmguide.moviedb.data.db.Review;
import com.example.farmguide.moviedb.data.db.ReviewDao;

import java.util.List;

import io.reactivex.Flowable;


public class ReviewRepositoryImpl implements ReviewRepository {
    private ReviewDao reviewDao;


    public ReviewRepositoryImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public Flowable<List<Review>> getReviews(int movieId) {
        return reviewDao.getReviews(movieId);
    }

    @Override
    public void insertAll(List<Review> reviews) {
        reviewDao.insertAll(reviews);

    }
}
