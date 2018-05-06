package com.vibhor.moviereviewer.activity;

import android.arch.lifecycle.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import com.vibhor.moviereviewer.R;
import com.vibhor.moviereviewer.adapter.*;
import com.vibhor.moviereviewer.viewmodel.*;

import java.util.*;

import butterknife.*;

public class MoviesReviewActivity extends LifecycleActivity {

    @BindView(R.id.movies_recycler_view)
    RecyclerView moviesRecyclerView;
    @BindView(R.id.movies_no_results_text_view)
    TextView noResultsTextView;

    private MoviesViewModel viewModel;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupViewModel();

        setContentView(R.layout.activity_movies_review);
        ButterKnife.bind(this);
        initMoviesRecyclerView();
        viewModel.loadMovies();
    }

    private void initMoviesRecyclerView() {

        this.moviesAdapter = new MoviesAdapter(new ArrayList<>());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        this.moviesRecyclerView.setLayoutManager(layoutManager);
        this.moviesRecyclerView.setAdapter(this.moviesAdapter);
        this.moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (layoutManager.findLastVisibleItemPosition() >= layoutManager.getItemCount() - 1) {
                    MoviesReviewActivity.this.viewModel.requestNextPage();
                }
            }
        });
    }

    private void setupViewModel() {
        ViewModelFactory factory = new ViewModelFactory(new MoviesViewModel(MoviesReviewActivity.this.getApplication()));
        this.viewModel = ViewModelProviders.of(this, factory)
                .get(MoviesViewModel.class);
        this.viewModel.getMovies().observe(this, movies -> {
            if (movies == null || movies.isEmpty()) {
                noResultsTextView.setVisibility(View.VISIBLE);
                moviesRecyclerView.setVisibility(View.GONE);
            } else {
                noResultsTextView.setVisibility(View.GONE);
                moviesRecyclerView.setVisibility(View.VISIBLE);
            }

            moviesAdapter.setData(movies);
            moviesAdapter.notifyDataSetChanged();
        });
        this.viewModel.hasNextPageData().observe(this, aBoolean -> {
            if (aBoolean != null) {
                this.moviesAdapter.isFooterVisible(aBoolean);
            }
        });
    }
}
