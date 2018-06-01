package com.example.farmguide.moviedb.usecases;

import android.util.Log;

import com.example.farmguide.moviedb.data.api.ApiHelper;
import com.example.farmguide.moviedb.data.api.MoviesResponse;
import com.example.farmguide.moviedb.data.api.Result;
import com.example.farmguide.moviedb.data.db.AppDatabase;
import com.example.farmguide.moviedb.data.db.Movie;
import com.example.farmguide.moviedb.repo.MoviesRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class GetMoviesUseCase {


    private CompositeDisposable disposable  = new CompositeDisposable();

    private ApiHelper apiHelper;
    private MoviesRepository moviesRepository;

    @Inject
    public GetMoviesUseCase(ApiHelper apiHelper, MoviesRepository moviesRepository){
        this.apiHelper = apiHelper;
        this.moviesRepository = moviesRepository;
    }

    public Flowable<List<Movie>> getMovies(){
        fetchFromNetwork();
        return moviesRepository.getMovies().subscribeOn(Schedulers.io());
    }

    private void fetchFromNetwork() {
        disposable.add(apiHelper.getMovies().subscribeOn(Schedulers.io())
                .map(new Function<MoviesResponse, List<Movie>>() {
                    @Override
                    public List<Movie> apply(MoviesResponse moviesResponse) throws Exception {
                        return MovieMapper.mapResponsetoMovies(moviesResponse);
                    }
                }).subscribe(new Consumer<List<Movie>>() {
            @Override
            public void accept(List<Movie> movies) throws Exception {
                moviesRepository.insertAll(movies);
            }
        }));
    }

    static class MovieMapper{

        public static List<Movie> mapResponsetoMovies(MoviesResponse moviesResponse){
            List<Result> results = moviesResponse.getResults();
            List<Movie> movies = new ArrayList<>();
            for(Result result : results){
                Movie movie = new Movie(result.id,result.title , result.releaseDate);
                movies.add(movie);
            }
            return movies;
        }
    }
}
