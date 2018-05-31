package com.example.farmguide.moviedb.data.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

@Database(entities = {Movie.class} , version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}

