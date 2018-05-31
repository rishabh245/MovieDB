package com.example.farmguide.moviedb.data.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;


public class Migration_1_2 extends Migration {


    public Migration_1_2() {
        super(1, 2);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        database.beginTransaction();
        try{
            database.execSQL("ALTER TABLE `movies` "
                    + " RENAME TO `movies1`");

            database.execSQL("CREATE TABLE `movies` (`id` INTEGER NOT NULL, " +
                    " `title` TEXT , `releaseDate` TEXT , PRIMARY KEY(`id`))");

            database.execSQL("INSERT INTO movies (id, title, releaseDate)\n" +
                    "  SELECT id, title, releaseData\n" +
                    "  FROM movies1;");

            database.execSQL("DROP TABLE movies1");
            database.setTransactionSuccessful();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            database.endTransaction();
        }
    }
}
