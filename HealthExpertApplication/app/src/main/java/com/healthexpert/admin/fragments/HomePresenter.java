package com.healthexpert.admin.fragments;

import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.response.DoctorWrapper;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shivani on 1/29/2017.
 */

public class HomePresenter implements HomeContract.HomePresenter {

    UserRestService userRestService;
    HomeContract.HomeView homeView;

    public HomePresenter(UserRestService userRestService, HomeContract.HomeView homeView) {
        this.userRestService = userRestService;
        this.homeView = homeView;
    }

    @Override
    public void fetchHomeData() {
        userRestService.getHomeData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DoctorWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (homeView != null)
                            homeView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(DoctorWrapper doctorWrapper) {
                        if (homeView != null)
                            homeView.onHomeData(doctorWrapper);
                    }
                });
    }
}
