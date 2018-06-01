package com.example.farmguide.moviedb.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ReviewDao {

   @Query("SELECT * FROM reviews where movieId = :movieId")
     Flowable<List<Review>> getReviews(int movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insertAll(List<Review> reviews);
}
