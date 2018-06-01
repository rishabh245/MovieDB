package com.example.farmguide.moviedb.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.farmguide.moviedb.R;
import com.example.farmguide.moviedb.data.db.Movie;
import com.example.farmguide.moviedb.ui.review.ReviewsActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

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

    }

    @Override
    public void onMoviesClicked(Movie movie) {
        ReviewsActivity.start(this, movie.getId());
    }
}
