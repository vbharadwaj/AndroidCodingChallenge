package com.vibhor.moviereviewer.model;

import com.google.gson.annotations.*;

import java.util.*;

public class ApiResponse {

    private String status;
    private String copyright;

    @SerializedName("has_more")
    private boolean hasMore;

    @SerializedName("num_results")
    private int numResults;

    @SerializedName("results")
    private List<Movie> results;

    public boolean hasMoreMovies() {
        return hasMore;
    }

    public List<Movie> getResults() {
        return results;
    }
}
