package com.paymentwall.sample.activity.tabs;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

public class ControllerFactory implements ViewModelProvider.Factory {

    private final Application application;

    public ControllerFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StoreViewModel.class)) {
            Log.d("DATPH", "create StoreViewModel");
            return (T) new StoreViewModel(application);
        }
        throw new IllegalArgumentException("Unknow ViewModel Class: " + modelClass.getName());
    }
}
