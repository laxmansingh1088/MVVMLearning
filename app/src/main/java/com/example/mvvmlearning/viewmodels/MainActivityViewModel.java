package com.example.mvvmlearning.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private int count = 0;
    private MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>();
    private int initialValue = 0;

    public MainActivityViewModel(int initialValue) {
        this.initialValue = initialValue;
    }

    public LiveData<Integer> getInitialCount() {
        integerMutableLiveData.setValue(count);
        return integerMutableLiveData;
    }

    public void getCount() {
        count += 1;
        integerMutableLiveData.setValue(count + initialValue);
    }
}
