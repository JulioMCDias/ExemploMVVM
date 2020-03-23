package com.jlmcdeveloper.exemplomvvm.ui.base;



import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<N> extends ViewModel {

    private WeakReference<N> navigator;
    private CompositeDisposable compositeDisposable;



    public BaseViewModel() {
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }


    public N getNavigator() {
        return navigator.get();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<>(navigator);
    }
}
