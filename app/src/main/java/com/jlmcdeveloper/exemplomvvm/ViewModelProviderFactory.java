package com.jlmcdeveloper.exemplomvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jlmcdeveloper.exemplomvvm.data.LoginDataSource;
import com.jlmcdeveloper.exemplomvvm.data.LoginRepository;
import com.jlmcdeveloper.exemplomvvm.ui.login.LoginViewModel;
import com.jlmcdeveloper.exemplomvvm.ui.main.MainViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    @Inject
    public ViewModelProviderFactory(){

    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        }
        else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel();
        }
        else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
