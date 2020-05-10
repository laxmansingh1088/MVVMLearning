package com.example.mvvmlearning.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {
    private int initialValue = 0;

    public MainActivityViewModelFactory(int initialValue) {
        this.initialValue = initialValue;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(initialValue);
    }
}
