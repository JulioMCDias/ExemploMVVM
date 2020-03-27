package com.jlmcdeveloper.exemplomvvm.ui.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.jlmcdeveloper.exemplomvvm.BR;
import com.jlmcdeveloper.exemplomvvm.R;
import com.jlmcdeveloper.exemplomvvm.ViewModelProviderFactory;
import com.jlmcdeveloper.exemplomvvm.databinding.ActivityNoteBinding;
import com.jlmcdeveloper.exemplomvvm.ui.base.BaseActivity;
import com.jlmcdeveloper.exemplomvvm.utils.Constants;

import java.util.Objects;

import javax.inject.Inject;

public class NoteActivity extends BaseActivity<ActivityNoteBinding, NoteViewModel> implements NoteNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private NoteViewModel noteViewModel;
    private ActivityNoteBinding activityNoteBinding;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNoteBinding = getViewDataBinding();

        toolbar = activityNoteBinding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        noteViewModel.setNavigator(this);
        noteViewModel.setIdNote(getIntent().getLongExtra(Constants.KEY_INTENT_NOTE,-1));
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_note;
    }

    @Override
    public NoteViewModel getViewModel() {
        noteViewModel = new ViewModelProvider(this, factory).get(NoteViewModel.class);
        return noteViewModel;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_save:
                noteViewModel.save();
                break;
            case R.id.menu_delete:
                noteViewModel.delete();
                break;
            case android.R.id.home:
                closeActivity();
                break;
        }

        return true;
    }

    //----
    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleError(int info) {
        Toast.makeText(this, getText(info), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public String getNoteTitle() {
        return activityNoteBinding.title.getEditableText().toString();
    }

    @Override
    public String getNoteDescription() {
        return activityNoteBinding.description.getEditableText().toString();
    }
}
