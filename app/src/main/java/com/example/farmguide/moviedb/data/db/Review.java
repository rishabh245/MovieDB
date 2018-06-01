package com.example.farmguide.moviedb.data.db;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "reviews" , foreignKeys = @ForeignKey(entity = Movie.class,
        parentColumns = "id",
        childColumns = "movieId",
        onDelete = CASCADE))
public class Review {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "reviewId")
    private String reviewId;

    @ColumnInfo(name = "movieId")
    private int movieId;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "content")
    private String content;

    public Review(String reviewId, int movieId, String author, String content) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.author = author;
        this.content = content;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
