package com.vibhor.moviereviewer.viewmodel;

import android.arch.lifecycle.*;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private MoviesViewModel viewModel;

    public ViewModelFactory(MoviesViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
