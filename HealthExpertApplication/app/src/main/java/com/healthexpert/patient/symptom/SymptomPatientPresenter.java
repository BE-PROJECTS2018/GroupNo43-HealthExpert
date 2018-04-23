package com.healthexpert.patient.symptom;

import android.util.Log;

import com.healthexpert.data.remote.api.UserRestService;
import com.healthexpert.data.remote.models.response.SymptomResponseWrapper;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 2/9/2018.
 */

public class SymptomPatientPresenter implements SymptomPatientContracts.SymptomPatientPresenter {
    UserRestService userRestService;
    SymptomPatientContracts.SymptomPatientView symptomPatientView;

    public SymptomPatientPresenter(UserRestService userRestService, SymptomPatientContracts.SymptomPatientView symptomPatientView) {
        this.userRestService = userRestService;
        this.symptomPatientView = symptomPatientView;
    }

    @Override
    public void symptomPatient() {
        userRestService.symptomPatient()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SymptomResponseWrapper>() {
                    @Override
                    public void onCompleted() {
                        Log.d("SymptomPatientPresenter", "Success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (symptomPatientView != null)
                            symptomPatientView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(SymptomResponseWrapper symptomResponseWrapper) {
                        if (symptomPatientView != null)
                            symptomPatientView.onSymptomPatient(symptomResponseWrapper);
                    }
                });
    }
    @Override
    public void symptomPatientAll() {
        userRestService.symptomPatient()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SymptomResponseWrapper>() {
                    @Override
                    public void onCompleted() {
                        Log.d("SymptomPatientPresenter", "Success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (symptomPatientView != null)
                            symptomPatientView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(SymptomResponseWrapper symptomResponseWrapper) {
                        if (symptomPatientView != null)
                            symptomPatientView.getSymptoms(symptomResponseWrapper);
                    }
                });
    }

}
