package com.healthexpert.auth;

import android.util.Log;


import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.FirebaseRequest;
import com.healthexpert.data.remote.models.requests.UserLoginRequest;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;
import com.healthexpert.data.remote.models.response.UserResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shivani on 1/13/2017.
 */

public class LoginPresenter implements LoginContract.LoginPresenter {

    LoginContract.LoginView view;
    UserRestService userRestService;

    public LoginPresenter(UserRestService userRestService, LoginContract.LoginView view) {
        this.view = view;
        this.userRestService = userRestService;
    }


    @Override
    public void login(UserLoginRequest userLoginRequest) {
        userRestService
                .doLogin(userLoginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("UserMessageComplete", "Complete");

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null)
                            view.onNetworkException(e);

                    }

                    @Override
                    public void onNext(UserResponse userResponse) {
                        if (view != null)
                            view.onLogin(userResponse);

                    }
                });
    }

    @Override
    public void Fuid(FirebaseRequest firebaseRequest) {
        userRestService
                .doFuid(firebaseRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("UserMessageComplete", "Complete");

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null)
                            view.onNetworkException(e);

                    }

                    @Override
                    public void onNext(UserRegisterResponse userResponse) {
                        if (view != null)
                            view.onFuid(userResponse);

                    }
                });
    }


}
