package com.example.farmguide.moviedb.data.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("3/movie/top_rated?api_key=3b9fe7431be45253421f0875bade8e5a&language=en-US&page=1")
    Single<MoviesResponse> getMovies();

    @GET("3/movie/{movieid}/reviews?api_key=3b9fe7431be45253421f0875bade8e5a&language=en-US")
        Single<ReviewResponse> getReviews(@Path("movieid") int movieid);
}
