package com.jlmcdeveloper.exemplomvvm.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.lifecycle.ViewModelProvider;

import com.jlmcdeveloper.exemplomvvm.BR;
import com.jlmcdeveloper.exemplomvvm.R;
import com.jlmcdeveloper.exemplomvvm.ViewModelProviderFactory;
import com.jlmcdeveloper.exemplomvvm.databinding.ActivityMainBinding;
import com.jlmcdeveloper.exemplomvvm.ui.base.BaseActivity;
import com.jlmcdeveloper.exemplomvvm.ui.note.NoteActivity;
import com.jlmcdeveloper.exemplomvvm.utils.Constants;

import javax.inject.Inject;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements MainNavigator , NoteAdapter.Listener {

    @Inject ViewModelProviderFactory factory;
    @Inject NoteAdapter adapter;
    private MainViewModel mainViewModel;
    private ActivityMainBinding activityMainBinding;

    //--------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel.setNavigator(this);
        adapter.setListener(this);
        activityMainBinding = getViewDataBinding();
        activityMainBinding.recycleView.setAdapter(adapter);

        mainViewModel.getNotesLiveData()
                .observe(this, notes -> adapter.updateItems(notes));
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
    public MainViewModel getViewModel(){
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        return mainViewModel;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.fetchNotes();
    }

    @Override
    public void onClickNote(Long id) {
        openNote(id);
    }

    // -------------
    @Override
    public void openNote(Long id) {
        startActivity(new Intent(this, NoteActivity.class).putExtra(Constants.KEY_INTENT_NOTE, id));
    }

    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleError(int info) {
        Toast.makeText(this, getText(info), Toast.LENGTH_SHORT).show();
    }

}
