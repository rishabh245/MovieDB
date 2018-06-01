package com.example.farmguide.moviedb.usecases;

import android.util.Log;

import com.example.farmguide.moviedb.data.api.ApiHelper;
import com.example.farmguide.moviedb.data.api.ApiService;
import com.example.farmguide.moviedb.data.api.MoviesResponse;
import com.example.farmguide.moviedb.data.api.ReviewApi;
import com.example.farmguide.moviedb.data.api.ReviewResponse;
import com.example.farmguide.moviedb.data.db.Review;
import com.example.farmguide.moviedb.data.db.ReviewDao;
import com.example.farmguide.moviedb.repo.ReviewRepository;
import com.example.farmguide.moviedb.repo.ReviewRepositoryImpl;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class GetReviewsUseCase {

    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiHelper apiHelper;
    private ReviewRepository reviewRepository;

    @Inject
    public GetReviewsUseCase(ApiHelper apiHelper, ReviewRepository reviewRepository) {
        this.apiHelper = apiHelper;
        this.reviewRepository = reviewRepository;
    }

    public Flowable<List<Review>> getReviews(int movieId){
        fetchReviewFromNetwork(movieId);
        return reviewRepository.getReviews(movieId).subscribeOn(Schedulers.io());
    }

    private void fetchReviewFromNetwork(int movieId) {
        disposable.add(apiHelper.getReviews(movieId).subscribeOn(Schedulers.io())
        .map(new Function<ReviewResponse, List<Review>>() {
            @Override
            public List<Review> apply(ReviewResponse reviewResponse) throws Exception {
                return ReviewMapper_Api_To_Db.map(reviewResponse);
            }
        }).subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviews) throws Exception {
                        reviewRepository.insertAll(reviews);
                    }
                }));
    }

    static class ReviewMapper_Api_To_Db{

        public static List<Review> map(ReviewResponse reviewResponse){
            List<ReviewApi> reviewsApi = reviewResponse.getReviews();
            List<Review> reviews= new ArrayList<>();
            for(ReviewApi reviewApi : reviewsApi){
                Review review = new Review(reviewApi.getId() , reviewResponse.getId(), reviewApi.getAuthor(),
                        reviewApi.getContent());
                reviews.add(review);
            }
            return reviews;
        }
    }


}
