package com.example.farmguide.moviedb.repo;

import com.example.farmguide.moviedb.data.db.Movie;

import java.util.List;

import io.reactivex.Flowable;

public interface MoviesRepository {
    Flowable<List<Movie>> getMovies();
    void insertAll(List<Movie> movies);
}
