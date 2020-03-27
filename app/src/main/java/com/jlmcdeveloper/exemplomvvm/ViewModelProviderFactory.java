package com.jlmcdeveloper.exemplomvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jlmcdeveloper.exemplomvvm.data.DataManager;
import com.jlmcdeveloper.exemplomvvm.ui.login.LoginViewModel;
import com.jlmcdeveloper.exemplomvvm.ui.main.MainViewModel;
import com.jlmcdeveloper.exemplomvvm.ui.note.NoteViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(dataManager);
        }
        else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(dataManager);
        }
        else if (modelClass.isAssignableFrom(NoteViewModel.class)) {
            return (T) new NoteViewModel(dataManager);
        }
        else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
