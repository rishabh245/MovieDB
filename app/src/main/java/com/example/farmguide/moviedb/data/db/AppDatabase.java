package com.example.farmguide.moviedb.data.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;


@Database(entities = {Movie.class,Review.class} , version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract ReviewDao reviewDao();

    public static AppDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "database-name")
                .addMigrations(getMigration()).build();
    }

    private static Migration[] getMigration(){
        Migration[] migrations = {new Migration_1_2()};
        return  migrations;
    }

}



