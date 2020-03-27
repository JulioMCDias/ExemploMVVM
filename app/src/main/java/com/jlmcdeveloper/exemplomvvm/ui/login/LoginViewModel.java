package com.jlmcdeveloper.exemplomvvm.ui.login;

import android.text.TextUtils;
import android.util.Log;

import com.jlmcdeveloper.exemplomvvm.R;
import com.jlmcdeveloper.exemplomvvm.data.DataManager;
import com.jlmcdeveloper.exemplomvvm.data.model.LoggedInUser;
import com.jlmcdeveloper.exemplomvvm.data.model.LoginUser;
import com.jlmcdeveloper.exemplomvvm.data.model.api.LoginResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.db.User;
import com.jlmcdeveloper.exemplomvvm.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager) {
        super(dataManager);
    }



    private void setUser(String name, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .setLoginRemote(new LoginUser(name, password))
                .map(Response::body)
                .doOnSuccess(login ->{
                    getDataManager().setToken(login.getAccessToken());
                    getDataManager().setLoggedInUser(login.getUser());
                })
                .map(LoginResponse::getUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((user, throwable) -> {
                    if(user != null && user.getUserId() != -1){
                        setIsLoading(false);
                        createUserLocal(name, password);
                        getNavigator().openMainActivity();
                    } else
                        setUserLocal(name, password);
                }));

    }

    // buscar usuario em database local
    private void setUserLocal(String name, String password){
        getCompositeDisposable().add(getDataManager()
                .setLoginLocal(new User(name, password))
                .doOnSuccess(user -> getDataManager().setLoggedInUser(
                        new LoggedInUser(user.getUserId(),user.getName(), user.getEmail())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user-> {
                    if(user != null && user.getUserId() != -1){
                        createUser(name, password);     // cria usuario remoto
                        getNavigator().openMainActivity();
                    } else
                        getNavigator().handleError(R.string.error_login_network);
                    setIsLoading(false);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                    setIsLoading(false);
                }));

    }


    private void createUser(String name, String password) {
        setIsLoading(true);
        createUserLocal(name, password);        // cria usuario local

        getCompositeDisposable().add(getDataManager()
                .createLoginRemote(new LoginUser(name, password))
                .map(Response::body)
                .map(LoginResponse::getUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user->{
                    if(user == null || user.getUserId() != -1)
                        getNavigator().handleError(R.string.error_new_user);
                    setIsLoading(false);
                },throwable -> {
                    getNavigator().handleError(R.string.error_in_network);
                    setIsLoading(false);
                }));
    }




    private void createUserLocal(String name, String password){
        getCompositeDisposable().add(getDataManager()
                .createLoginLocal(new User(null, name, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete(throwable -> {
                    getNavigator().handleError(throwable);
                    return true;
                }).subscribe());
    }



    //--------- View -----------
    public void viewLogin(){
        String name = getNavigator().getName();
        String password = getNavigator().getPassword();
        if(isNameAndPasswordValid(name, password))
            setUser(name, password);
        else
            getNavigator().handleError( R.string.invalid_email_password);
    }




    public void viewCreateUser(){
        String name = getNavigator().getName();
        String password = getNavigator().getPassword();
        if(isNameAndPasswordValid(name, password))
            createUser(name, password);
        else
            getNavigator().handleError(R.string.invalid_email_password);
    }
    //---

    private boolean isNameAndPasswordValid(String name, String password) {
        // validate name and password
        return (! TextUtils.isEmpty(password)) && (! TextUtils.isEmpty(name));
    }
}
