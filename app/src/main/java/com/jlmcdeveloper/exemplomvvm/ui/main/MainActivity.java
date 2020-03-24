package com.jlmcdeveloper.exemplomvvm.ui.main;

import android.content.Intent;
import android.os.Bundle;


import androidx.lifecycle.ViewModelProvider;

import com.jlmcdeveloper.exemplomvvm.BR;
import com.jlmcdeveloper.exemplomvvm.R;
import com.jlmcdeveloper.exemplomvvm.ViewModelProviderFactory;
import com.jlmcdeveloper.exemplomvvm.databinding.ActivityMainBinding;
import com.jlmcdeveloper.exemplomvvm.ui.base.BaseActivity;
import com.jlmcdeveloper.exemplomvvm.ui.note.NoteActivity;

import javax.inject.Inject;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator{
    @Inject
    ViewModelProviderFactory factory;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel.setNavigator(this);
    }


    @Override
    public void openNote() {
        startActivity(new Intent(this, NoteActivity.class));
        finish();
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        return mainViewModel;
    }
}
