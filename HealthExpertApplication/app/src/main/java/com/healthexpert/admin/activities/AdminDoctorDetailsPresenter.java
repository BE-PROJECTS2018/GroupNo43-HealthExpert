package com.healthexpert.admin.activities;

import com.healthexpert.admin.fragments.HomeContract;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.AdminDoctorDetails;
import com.healthexpert.data.remote.models.response.DoctorWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shivani on 1/29/2017.
 */

public class AdminDoctorDetailsPresenter implements AdminDoctorDetailsContract.AdminDoctorDetailsPresenter {

    UserRestService userRestService;
    AdminDoctorDetailsContract.AdminDoctorDetailsView homeView;

    public AdminDoctorDetailsPresenter(UserRestService userRestService, AdminDoctorDetailsContract.AdminDoctorDetailsView adminDoctorDetailsView) {
        this.userRestService = userRestService;
        this.homeView = adminDoctorDetailsView;
    }


    @Override
    public void statusDoctor(AdminDoctorDetails adminDoctorDetails) {
        userRestService.statusDoctor(adminDoctorDetails)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (homeView != null)
                            homeView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (homeView != null)
                            homeView.onData(userRegisterResponse);
                    }
                });
    }


}
