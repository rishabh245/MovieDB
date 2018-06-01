package com.example.farmguide.moviedb.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.farmguide.moviedb.R;
import com.example.farmguide.moviedb.data.api.MoviesResponse;
import com.example.farmguide.moviedb.data.db.Movie;
import com.example.farmguide.moviedb.ui.review.ReviewActivityModule;
import com.example.farmguide.moviedb.ui.review.ReviewsActivity;
import com.example.farmguide.moviedb.usecases.GetMoviesUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MovieClickListener {

    @Inject  MainViewModelFactory mainViewModelFactory;
    @Inject  LinearLayoutManager linearLayoutManager;
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    @Inject  MoviesAdapter moviesAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
        recyclerView = findViewById(R.id.movies_recyler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(moviesAdapter);

        mainViewModel = ViewModelProviders.of(this ,
                mainViewModelFactory).get(MainViewModel.class);
        mainViewModel.getMoviesObservable().observe(this, new android.arch.lifecycle.Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if(movies!=null){
                    moviesAdapter.setMoviesList(movies);
                }
            }
        });

        mainViewModel.loadMovies();

    }

    @Override
    public void onMoviesClicked(Movie movie) {
         Intent intent = new Intent(this , ReviewsActivity.class);
         intent.putExtra("MOVIEID" , movie.getId());
         startActivity(intent);
    }
}
