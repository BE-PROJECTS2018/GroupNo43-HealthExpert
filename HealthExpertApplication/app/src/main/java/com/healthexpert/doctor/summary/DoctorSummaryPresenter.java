package com.healthexpert.doctor.summary;

import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorSummaryResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 1/29/2017.
 */

public class DoctorSummaryPresenter implements DoctorSummaryContract.DoctorPresenter {

    DoctorRestService doctorRestService;
    DoctorSummaryContract.DoctorView doctorView;

    public DoctorSummaryPresenter(DoctorRestService doctorRestService,DoctorSummaryContract.DoctorView doctorView) {
        this.doctorRestService = doctorRestService;
        this.doctorView = doctorView;
    }


    @Override
    public void fetchSummary(MyRequest summaryRequest) {
        doctorRestService.fetchSummary(summaryRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DoctorSummaryResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (doctorView != null)
                            doctorView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(DoctorSummaryResponse doctorSummaryResponse) {
                        if (doctorView != null)
                            doctorView.onSummary(doctorSummaryResponse);
                    }
                });
    }
}
