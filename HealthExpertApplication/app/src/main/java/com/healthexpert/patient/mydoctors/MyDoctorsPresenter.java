package com.healthexpert.patient.mydoctors;

import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.MyRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.doctor.mypatients.MyPatientsContract;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 1/29/2017.
 */

public class MyDoctorsPresenter implements MyDoctorsContract.MyDoctorsPresenter{

    PatientRestService patientRestService;
    MyDoctorsContract.MyDoctorsView myDoctorsView;

    public MyDoctorsPresenter(PatientRestService patientRestService, MyDoctorsContract.MyDoctorsView myDoctorsView) {
        this.patientRestService = patientRestService;
        this.myDoctorsView = myDoctorsView;
    }


    @Override
    public void getDoctors(MyRequest myRequest) {
        patientRestService.getBookmarkDoctors(myRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DoctorResponseWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (myDoctorsView != null)
                            myDoctorsView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(DoctorResponseWrapper doctorResponseWrapper) {
                        if (myDoctorsView != null)
                            myDoctorsView.onDoctorsData(doctorResponseWrapper);
                    }
                });        
    }
}
