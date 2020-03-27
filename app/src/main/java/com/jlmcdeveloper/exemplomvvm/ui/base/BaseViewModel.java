package com.jlmcdeveloper.exemplomvvm.ui.base;



import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.jlmcdeveloper.exemplomvvm.data.DataManager;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<N> extends ViewModel {

    private WeakReference<N> navigator;
    private CompositeDisposable compositeDisposable;
    private DataManager dataManager;
    private final ObservableBoolean mIsLoading = new ObservableBoolean();


    public BaseViewModel(DataManager dataManager) {
        this.dataManager =dataManager;
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public N getNavigator() {
        return navigator.get();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<>(navigator);
    }
}
