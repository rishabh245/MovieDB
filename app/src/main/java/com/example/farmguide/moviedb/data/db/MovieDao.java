package com.example.farmguide.moviedb.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    Flowable<List<Movie>> getMovies();

    @Insert
    void insert(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Movie> movies);
}
