package com.vibhor.moviereviewer.network;

import android.support.annotation.*;
import java.io.*;
import okhttp3.*;

public class MoviesApiRequestInterceptor implements Interceptor {

    private String apiKey;

    public MoviesApiRequestInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl newUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api-key", this.apiKey)
                .build();

        Request.Builder requestBuilder = originalRequest.newBuilder()
                .url(newUrl);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
