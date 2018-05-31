package com.example.farmguide.moviedb.data.model.api;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("3/movie/top_rated?api_key=3b9fe7431be45253421f0875bade8e5a&language=en-US&page=1")
    Single<MoviesResponse> getMovies();
}
