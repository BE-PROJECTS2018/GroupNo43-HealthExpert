package com.healthexpert.auth.patient;

import android.util.Log;

import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.requests.PatientRegisterRequest;
import com.healthexpert.data.remote.models.requests.PatientRequest;
import com.healthexpert.data.remote.models.requests.PatientRequestNoIcon;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 2/9/2018.
 */

public class PatientRegisterPresenter implements PatientRegisterContracts.PatientRegisterPresenter {
    PatientRestService patientRestService;
    PatientRegisterContracts.PatientRegisterView patientRegisterView;

    public PatientRegisterPresenter(PatientRestService patientRestService, PatientRegisterContracts.PatientRegisterView patientRegisterView) {
        this.patientRestService = patientRestService;
        this.patientRegisterView = patientRegisterView;
    }

    @Override
    public void registerPatient(PatientRegisterRequest patientRegisterRequest) {
        patientRestService.doPatientRegister(patientRegisterRequest).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("PatientRegPresenter", "Success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (patientRegisterView != null)
                            patientRegisterView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (patientRegisterView != null)
                            patientRegisterView.onRegisterPatient(userRegisterResponse);
                    }
                });
    }

    @Override
    public void addPatientNoIcon(PatientRequestNoIcon patientRequestNoIcon) {
        patientRestService.addPatientNoIcon(patientRequestNoIcon)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d("PatientRegisterPresenter", "Success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (patientRegisterView != null)
                            patientRegisterView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (patientRegisterView != null)
                            patientRegisterView.onRegisterPatient(userRegisterResponse);
                    }
                });
    }
}
