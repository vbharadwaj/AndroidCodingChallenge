package com.vibhor.moviereviewer.network;

import com.vibhor.moviereviewer.model.*;

import retrofit2.*;
import retrofit2.http.*;

public interface MoviesApi {

    @GET("picks.json?order=by-opening-date")
    Call<ApiResponse> getMovies(@Query("offset") int offset);
}
