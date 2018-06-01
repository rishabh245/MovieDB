package com.example.farmguide.moviedb.repo;

import com.example.farmguide.moviedb.data.db.Movie;
import com.example.farmguide.moviedb.data.db.MovieDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;


public class MoviesRepositoryImpl implements MoviesRepository {

    private MovieDao movieDao;


    public MoviesRepositoryImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Flowable<List<Movie>> getMovies() {
        return movieDao.getMovies();
    }

    @Override
    public void insertAll(List<Movie> movies) {
        movieDao.insertAll(movies);
    }
}
