package com.vibhor.moviereviewer.viewmodel;

import android.app.*;
import android.arch.lifecycle.*;
import android.support.annotation.*;
import android.widget.*;

import com.vibhor.moviereviewer.R;
import com.vibhor.moviereviewer.model.*;
import com.vibhor.moviereviewer.network.*;
import com.vibhor.moviereviewer.shared.*;

import java.util.*;

import retrofit2.*;

public class MoviesViewModel extends AndroidViewModel {
    private static final int MOVIES_IN_PAGE_COUNT = 20;

    private MutableLiveData<List<Movie>> movies;
    private MutableLiveData<Boolean> hasNextPageData;

    private MoviesApi moviesApi;

    private int currentMoviesPage = 0;
    private boolean isRequestingNextPage = false;

    public MoviesViewModel(Application application) {
        super(application);

        this.movies = new MutableLiveData<>();
        this.hasNextPageData = new MutableLiveData<>();
        this.hasNextPageData.setValue(true);

        this.moviesApi = ((MoviesReviewApp)application).getMoviesApi();
    }

    public void loadMovies() {
        this.currentMoviesPage = 0;
        this.isRequestingNextPage = false;

        this.moviesApi.getMovies(0).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call,
                                   @NonNull Response<ApiResponse> response) {

                processResponse(response, 0);
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                showError(R.string.error_failed_to_execute_request);
            }
        });
    }

    public void requestNextPage() {
        if (!hasNextPage() || isRequestingNextPage) {
            return;
        }

        this.isRequestingNextPage = true;
        final int nextPage = currentMoviesPage + 1;

        this.moviesApi.getMovies(nextPage * MOVIES_IN_PAGE_COUNT).enqueue(
                new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call,
                                   @NonNull Response<ApiResponse> response) {
                MoviesViewModel.this.isRequestingNextPage = false;
                processResponse(response, nextPage);
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call,
                                  @NonNull Throwable t) {
                MoviesViewModel.this.isRequestingNextPage = false;

                showError(R.string.error_failed_to_execute_request);
            }
        });
    }

    private void processResponse(Response<ApiResponse> response, int nextPage) {
        if (response.isSuccessful()) {
            ApiResponse apiResponse = response.body();
            if (apiResponse != null && apiResponse.getResults() != null) {
                List<Movie> movies = apiResponse.getResults();
                List<Movie> existing = MoviesViewModel.this.movies.getValue();

                if (existing != null) {
                    movies.addAll(0, existing);
                }
                MoviesViewModel.this.movies.setValue(movies);
                MoviesViewModel.this.currentMoviesPage = nextPage;
                checkIfHasNextPage(apiResponse);
            } else {
                showError(R.string.error_server_error);
            }
        } else {
            showError(R.string.error_server_error);
        }
    }

    private void checkIfHasNextPage(ApiResponse response) {
        hasNextPageData.setValue(response.hasMoreMovies());
    }

    private boolean hasNextPage() {
        return hasNextPageData.getValue() != null &&
                hasNextPageData.getValue();
    }

    private void showError(int messageRes) {
        Toast.makeText(getApplication(), messageRes, Toast.LENGTH_SHORT).show();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public MutableLiveData<Boolean> hasNextPageData() {
        return hasNextPageData;
    }
}
