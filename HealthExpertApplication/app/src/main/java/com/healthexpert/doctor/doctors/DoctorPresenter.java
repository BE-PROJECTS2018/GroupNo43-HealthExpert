package com.healthexpert.doctor.doctors;

import com.healthexpert.data.remote.api.DoctorRestService;
import com.healthexpert.data.remote.api.PatientRestService;
import com.healthexpert.data.remote.models.requests.PrescriptionRequest;
import com.healthexpert.data.remote.models.response.DoctorResponseWrapper;
import com.healthexpert.data.remote.models.response.Speciality;
import com.healthexpert.data.remote.models.response.SpecialityWrapper;
import com.healthexpert.data.remote.models.response.UserRegisterResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shivani on 1/29/2017.
 */

public class DoctorPresenter implements DoctorContract.DoctorPresenter {

    DoctorRestService doctorRestService;
    DoctorContract.DoctorView doctorView;

    public DoctorPresenter(DoctorRestService doctorRestService, DoctorContract.DoctorView doctorView) {
        this.doctorRestService = doctorRestService;
        this.doctorView = doctorView;
    }

    @Override
    public void getDoctors() {
        doctorRestService.getDoctors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DoctorResponseWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (doctorView != null)
                            doctorView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(DoctorResponseWrapper doctorWrapper) {
                        if (doctorView != null)
                            doctorView.onDoctorData(doctorWrapper);
                    }
                });
    }

    @Override
    public void prescription(PrescriptionRequest prescriptionRequest) {
        doctorRestService.prescription(prescriptionRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserRegisterResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (doctorView != null)
                            doctorView.onNetworkException(e);
                    }

                    @Override
                    public void onNext(UserRegisterResponse userRegisterResponse) {
                        if (doctorView != null)
                            doctorView.onPrescription(userRegisterResponse);
                    }
                });
    }
}
