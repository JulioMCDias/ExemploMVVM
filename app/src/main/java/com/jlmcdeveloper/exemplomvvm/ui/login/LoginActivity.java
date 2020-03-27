package com.jlmcdeveloper.exemplomvvm.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.jlmcdeveloper.exemplomvvm.BR;
import com.jlmcdeveloper.exemplomvvm.R;
import com.jlmcdeveloper.exemplomvvm.ViewModelProviderFactory;
import com.jlmcdeveloper.exemplomvvm.databinding.ActivityLoginBinding;
import com.jlmcdeveloper.exemplomvvm.ui.base.BaseActivity;
import com.jlmcdeveloper.exemplomvvm.ui.main.MainActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {
    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding activityLoginBinding;

    //----------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = getViewDataBinding();
        loginViewModel.setNavigator(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        loginViewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
        return loginViewModel;
    }



    //------------
    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleError(int info) {
        Toast.makeText(this, getText(info), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return activityLoginBinding.name.getEditableText().toString();
    }

    @Override
    public String getPassword() {
        return activityLoginBinding.password.getEditableText().toString();
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
