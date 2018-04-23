package com.healthexpert.doctor.patients;

import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.models.requests.DoctorRequest;
import com.healthexpert.data.remote.models.requests.PredictionRequest;
import com.healthexpert.data.remote.models.response.PatientWrapper;
import com.healthexpert.data.remote.models.response.PredictionResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 1/29/2017.
 */

public class PatientPresenter implements PatientContract.PatientPresenter {

    DoctorRestService doctorRestService;
    PatientContract.PatientView patientView;

    public PatientPresenter(DoctorRestService doctorRestService, PatientContract.PatientView patientView) {
        this.doctorRestService = doctorRestService;
        this.patientView = patientView;
    }

    @Override
    public void fetchPatientData(DoctorRequest doctorRequest) {
        doctorRestService.getPatientData(doctorRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PatientWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (patientView != null)
                            patientView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(PatientWrapper patientWrapper) {
                        if (patientView != null)
                            patientView.onPatientData(patientWrapper);
                    }
                });
    }

    @Override
    public void predictDisease(PredictionRequest predictionRequest) {
        doctorRestService.predictDisease(predictionRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PredictionResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (patientView != null)
                            patientView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(PredictionResponse predictionResponse) {
                        if (patientView != null)
                            patientView.onPredict(predictionResponse);
                    }
                });
    }
}
