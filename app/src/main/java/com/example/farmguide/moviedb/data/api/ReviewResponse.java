package com.example.farmguide.moviedb.data.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("results")
    @Expose
    private List<ReviewApi> reviews ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ReviewApi> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewApi> reviews) {
        this.reviews = reviews;
    }
}
