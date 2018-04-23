package com.healthexpert.doctor.mypatients;

import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.doctor.doctors.DoctorContract;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 1/29/2017.
 */

public class MyPatientsPresenter implements MyPatientsContract.MyPatientsPresenter {

    DoctorRestService doctorRestService;
    MyPatientsContract.MyPatientsView myPatientsView;

    public MyPatientsPresenter(DoctorRestService doctorRestService, MyPatientsContract.MyPatientsView myPatientsView) {
        this.doctorRestService = doctorRestService;
        this.myPatientsView = myPatientsView;
    }

    @Override
    public void getPatients(MyRequest myRequest) {
        doctorRestService.getPatients(myRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PatientWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (myPatientsView != null)
                            myPatientsView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(PatientWrapper patientWrapper) {
                        if (myPatientsView != null)
                            myPatientsView.onPatientsData(patientWrapper);
                    }
                });
    }
}
