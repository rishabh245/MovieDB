package com.example.farmguide.moviedb.data.model.api;

import java.util.List;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class ApiHelper {

    private ApiService apiService;

    @Inject
    public ApiHelper(ApiService apiService){
        this.apiService = apiService;
    }

    public Single<MoviesResponse> getMovies(){
        return apiService.getMovies();
    }
}
