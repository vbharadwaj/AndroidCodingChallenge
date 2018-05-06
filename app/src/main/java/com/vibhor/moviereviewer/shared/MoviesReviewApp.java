package com.vibhor.moviereviewer.shared;

import android.app.*;

import com.vibhor.moviereviewer.network.*;

import okhttp3.*;
import retrofit2.*;
import retrofit2.converter.gson.*;

public class MoviesReviewApp extends Application {

    private MoviesApi moviesApi;

    @Override
    public void onCreate() {
        super.onCreate();

        initMoviesApi();
    }

    private void initMoviesApi() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new MoviesApiRequestInterceptor(Constants.API_KEY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.moviesApi = retrofit.create(MoviesApi.class);
    }

    public MoviesApi getMoviesApi() {
        return moviesApi;
    }
}
