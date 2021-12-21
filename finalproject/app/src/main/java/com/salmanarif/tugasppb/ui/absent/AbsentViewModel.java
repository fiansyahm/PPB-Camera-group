package com.salmanarif.tugasppb.ui.absent;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AbsentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AbsentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}